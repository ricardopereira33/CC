import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;

public class RPThread extends Thread {
	private DatagramSocket socket;
	//private Tabela tab;


	public RPThread (){
		//this.tab = tab;
	}

	public void run(){
		byte[] buffer = new byte[512];	
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
	
		try{
			this.socket = new DatagramSocket(5555);
			
			System.out.println("Server stared");

			while(true){
				//receber pacotes
				socket.receive(packet);
				String info = new String(packet.getData(),0,packet.getLength()) + 
								         ", from address: " + packet.getAddress() + 
								         ", port: " + packet.getPort();

				System.out.println(info);

				DatagramPacket out = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
				socket.send(out);
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}	

}