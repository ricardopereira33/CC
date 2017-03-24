import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;

public class RPThread extends Thread {
	private DatagramSocket socket;
	private Tabela tab;


	public RPThread (Tabela tab){
		this.tab = tab;
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
				String info = new String(packet.getData(),0,packet.getLength());
				/* + 
								         ", from address: " + packet.getAddress() + 
								         ", port: " + packet.getPort();
				*/
				System.out.println(info);

				if(info.equals("1")) break;
				//registar servidor
				ServerInfo si = new ServerInfo(packet.getAddress(), packet.getPort(), 0, 0, 1);
				tab.setServerInfo(si);


				DatagramPacket out = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
				socket.send(out);
			}

			for(int i=0; i<tab.size(); i++){
				ServerInfo si = tab.getServerInfo(i);
				System.out.println("Endereço: " + si.getEndIp() +"\nPorta: " + si.getPort() +"\nRTT: "+ si.getRtt() + "\nTaxa: " + si.getTaxPacLost() +"\nNumCont: " + si.getNumConnect() );
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}	
}