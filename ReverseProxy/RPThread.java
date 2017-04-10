package ReverseProxy;

import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.HashMap;

import Package.PacoteMonitor;

public class RPThread extends Thread {
	private DatagramSocket socket;
	private Tabela tab;


	public RPThread (Tabela tab){
		this.tab = tab;
	}

	private void updateServerInfo(PacoteMonitor pm, InetAddress ip, int port){
		ServerInfo si = this.tab.getServerInfo(ip);
		float alfa = 0.125f;
		long time = System.currentTimeMillis();

		float newRtt = (float) ((1 - alfa)*si.getRtt() + alfa*( time - pm.getTempSaida()));

		si.setRtt(newRtt);
	}

	private void print(){
		Map<InetAddress,ServerInfo> lista = tab.lista();
			for(Map.Entry<InetAddress,ServerInfo> entry: lista.entrySet()){
				
				ServerInfo si = entry.getValue();

				if(si==null) System.out.println("Cenas");
	
				System.out.println("Endereço: " + si.getEndIp() +
						 "\nPorta: " + si.getPort() +
						 "\nRTT: "+ si.getRtt() + 
					         "\nTaxa: " + si.getTaxPacLost() +
						 "\nNumCont: " + si.getNumConnect() );
			}
	}

	public void run(){
		byte[] buffer = new byte[512];	
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		int i=0;
		try{
			this.socket = new DatagramSocket(5555);
			
			System.out.println("Server stared");

			while(true){
				//receber pacotes
				socket.receive(packet);
				String info = new String(packet.getData(),0,packet.getLength());
				System.out.println(info);

				//sair do ciclo
				if(info.equals("1")) break;
				
				//registar servidor
				ServerInfo si = new ServerInfo(packet.getAddress(), packet.getPort(), 0, 0, 1);
				tab.setServerInfo(si);

				//enviar pacoteMonitor
				PacoteMonitor pm = new PacoteMonitor(1,"cenas",packet.getAddress(),packet.getPort());
				byte[] buf = pm.converteByte();

				DatagramPacket out = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());
				socket.send(out);

				//receber pacoteMonitor
				this.socket.receive(packet);
				
				PacoteMonitor pm2 = new PacoteMonitor(packet.getData());

				//actualizar info do respectivo servidor
				updateServerInfo(pm2,packet.getAddress(),packet.getPort());
			}

			//print das infos de cada servidor
			print();

		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}	
}
