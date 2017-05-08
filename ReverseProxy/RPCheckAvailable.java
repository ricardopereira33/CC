import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.HashMap;

public class RPCheckAvailable extends Thread {
	private Tabela tab;


	public RPCheckAvailable (Tabela tab){
		this.tab = tab;
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
						 	"\nNumCont: " + si.getNumConnect() + 
						 	"\nAvailable: "+ si.getAvailable());
			}
	}

	public void run(){
		try{	
			System.out.println("RPCheckAvailable stared");
			while(true){
				long time = System.currentTimeMillis();
				Map<InetAddress,ServerInfo> lista = tab.lista();
				for(Map.Entry<InetAddress,ServerInfo> entry: lista.entrySet()){
					
					ServerInfo si = entry.getValue();
					boolean status = si.getAvailable();
					long timeServer = si.getLastCheck();

					if( status && ((time - timeServer)*1000)>=15 ){
						si.setAvailable(false);
					}
				}
				print();
				Thread.sleep(60*1000);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}	
}
