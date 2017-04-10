import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.HashMap;

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
		boolean exist = false;
		try{
			this.socket = new DatagramSocket(5555);
			
			System.out.println("Server stared");

			while(true){
				//receber package
				this.socket.receive(packet);
				PacoteMonitor pm = new PacoteMonitor(packet.getData());
				
				if(!tab.contains(packet.getAddress())){
					//registar servidor
					ServerInfo si = new ServerInfo(packet.getAddress(), packet.getPort(), 0, 0, 1,true);
					tab.setServerInfo(si);
				}
				else if(pm.getNumPacote()!=-1){
						//actualizar info do respectivo servidor
						updateServerInfo(pm,packet.getAddress(),packet.getPort());
						exist = true;
				}

				if(pm.getTipo().equals("available")) 
					System.out.println(pm.getEndIP()+" - available");
				else if(pm.getTipo().equals("1")) break;
				else System.out.println(pm.getTipo());

				//enviar pacoteMonitor
				PacoteMonitor pm2;
				if(exist){
					pm2 = new PacoteMonitor(pm.getNumPacote()+1,"cenas",packet.getAddress(),packet.getPort());
				}
				else {
					pm2 = new PacoteMonitor(1,"cenas",packet.getAddress(),packet.getPort());
				}	
				byte[] buf = pm2.converteByte();
				DatagramPacket out = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());
				socket.send(out);
			}
			//print das infos de cada servidor
			print();

		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}	
}
