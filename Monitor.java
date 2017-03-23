import java.io.*;
import java.net.*;

public class Monitor extends Thread{
	private DatagramSocket socket;
	private int port;
	
	public Monitor(int port){
		this.port=port;
	}

	public void run (){
		
		byte[] buffer = new byte[512];
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);

		try{
			this.socket = new DatagramSocket(port);
			System.out.println("Monitor stared");

			while(true){
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				String msg = stdin.readLine();
				buffer = msg.getBytes();
				
				//endereço do servidor
				InetAddress addr = InetAddress.getByName("127.0.0.1");
				DatagramPacket out = new DatagramPacket(buffer, buffer.length, addr, 5555);
				this.socket.send(out);

				//receber pacote
				this.socket.receive(packet);
				String info = new String(packet.getData(),0,packet.getLength()) + 
										", from address: " + packet.getAddress() + 
										", port: " + packet.getPort();
				
				System.out.println(info);
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}
}