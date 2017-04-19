import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class RPThreadWrite extends Thread {
	private DatagramSocket socket;
	private Tabela tab;


	public RPThreadWrite (Tabela tab, DatagramSocket socket){
		this.socket = socket;
		this.tab = tab;
	}

	public void run(){
		byte[] buffer = new byte[512];	
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		boolean exist = false;
		try{
			System.out.println("Server stared");

			while(true){
				//enviar pacoteMonitor
				PacoteMonitor pm;

				ArrayList<ServerInfo> lista = tab.getList();

				for(ServerInfo si : lista){

					pm = new PacoteMonitor(si.getNumPacote(),"cenas",si.getEndIp(),si.getPort());

					byte[] buf = pm.converteByte();
					DatagramPacket out = new DatagramPacket(buf, buf.length, si.getEndIp(),si.getPort());
					socket.send(out);

					Thread.sleep(3*1000);
				}
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}	
}
