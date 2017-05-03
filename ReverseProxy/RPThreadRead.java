import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.HashMap;

public class RPThreadRead extends Thread {
	private DatagramSocket socket;
	private Tabela tab;


	public RPThreadRead (Tabela tab, DatagramSocket socket){
		this.socket = socket;
		this.tab = tab;
	}

	private void updateServerInfo(PacoteMonitor pm, InetAddress ip, int port){
		ServerInfo si = this.tab.getServerInfo(ip);
		float alfa = 0.125f;
		long time = System.currentTimeMillis();

		if(si.getNumPacoteCheck() == pm.getNumPacote())
			si.setNumPacoteCheck(si.getNumPacoteCheck()+1);
		else{ 
			si.addFails(pm.getNumPacote() - si.getNumPacoteCheck(),pm.getNumPacote()); 
		}
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
		int i = 0;
		byte[] buffer = new byte[512];	
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		try{
			
			System.out.println("RPThreadRead stared");
			while(true){
			//while(i<30){
				//receber package
				this.socket.receive(packet);
				PacoteMonitor pm = new PacoteMonitor(packet.getData());
				
				if(!tab.contains(packet.getAddress())){
					//registar servidor
					ServerInfo si = new ServerInfo(packet.getAddress(), packet.getPort(), 0, 0, 1,true,1,1);
					tab.setServerInfo(si);
				}
				else if(pm.getNumPacote()!=-1){
						//actualizar info do respectivo servidor
						updateServerInfo(pm,packet.getAddress(),packet.getPort());
				}
				i++;
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}	
}
