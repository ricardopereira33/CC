import java.io.*;
import java.net.*;

public class Monitor extends Thread{
	private DatagramSocket socket;
	private int port;
	
	public Monitor(){
		this.port=5555;
	}

	public void run (){
		
		byte[] buffer = new byte[512];
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		int i =0;
		try{
			this.socket = new DatagramSocket(port);
			System.out.println("Monitor stared");

			while(true){
				if(i==0){
					BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
					String msg = stdin.readLine();
					buffer = msg.getBytes();
				
					//endereço do servidor
					InetAddress addr = InetAddress.getByName("10.0.2.10");
					DatagramPacket out = new DatagramPacket(buffer, buffer.length, addr, 5555);
					this.socket.send(out);
					i++;
				}
					//receber pacote
					this.socket.receive(packet);
					/*String info = new String(packet.getData(),0,packet.getLength()) + 
										", from address: " + packet.getAddress() + 
										", port: " + packet.getPort();*/

				PacoteMonitor pm = new PacoteMonitor(packet.getData());

				pm.print();

				PacoteMonitor pm2 = new PacoteMonitor(1,"cenas",packet.getAddress(),packet.getPort());
				pm2.setTempSaida(pm.getTempSaida());
				byte[] buf = pm2.converteByte();
				
				DatagramPacket out = new DatagramPacket(buf, buf.length, packet.getAddress(),packet.getPort());
				this.socket.send(out);
				//System.out.println(info);
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}
}