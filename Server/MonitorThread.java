package Server;

import java.io.*;
import java.net.*;

public class MonitorThread extends Thread{
	private DatagramSocket socket;
	private int port;
	private InetAddress addr;
	
	public MonitorThread(){
		this.port=5555;
	}

	public void run (){
		
		byte[] buffer = new byte[512];
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		int i =0;
		try{
			while(1){
				if(disponivel==1) //send packet
				//else faz nada
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}
}