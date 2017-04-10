import java.io.*;
import java.net.*;
import Package.PacoteMonitor;

public class Monitor extends Thread{
	private DatagramSocket socket;
	private int port;
	private InetAddress addr;
	
	public Monitor(){
		this.port=5555;
	}

	public void run (){
		
		byte[] buffer = new byte[512];
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		int i =0;
		try{
			addr = InetAddress.getByName("10.0.2.10");
			this.socket = new DatagramSocket(port);
			System.out.println("Monitor stared");

			while(true){
				//enviar algo escrito do teclado
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				String msg = stdin.readLine();
				buffer = msg.getBytes();
				
				//endereço do servidor e enviar pacote
				DatagramPacket out = new DatagramPacket(buffer, buffer.length, addr, 5555);
				this.socket.send(out);

				//receber pacoteMonitor
				this.socket.receive(packet);
				PacoteMonitor pm = new PacoteMonitor(packet.getData());

				pm.print();

				//enviar pacoteMonitor
				PacoteMonitor pm2 = new PacoteMonitor(1,"cenas",packet.getAddress(),packet.getPort());
				pm2.setTempSaida(pm.getTempSaida());
				byte[] buf = pm2.converteByte();
				
				out = new DatagramPacket(buf, buf.length, packet.getAddress(),packet.getPort());
				this.socket.send(out);
				//System.out.println(info);
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}
}