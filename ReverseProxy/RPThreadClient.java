import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.HashMap;

public class RPThreadClient extends Thread {
	private Socket socketClient;
	private Socket socketServer;
	private Tabela tab;


	public RPThreadClient (Tabela tab, Socket socket){
		this.socketClient = socket;
		this.socketServer = null;
		this.tab = tab;
	}

	public void run(){
		//escolher servidor
		ServerInfo si = tab.chooseServer(); 
		si.incrNumConnect();

		try{
			System.out.println("RPThreadClient stared");

			//Input e output do socket para um cliente
			InputStream isClient = this.socketClient.getInputStream();
			OutputStream osClient = this.socketClient.getOutputStream();
			
			this.socketServer = new Socket(si.getEndIp(),80);

			if(this.socketServer!=null){
				InputStream isServer = this.socketServer.getInputStream();
				OutputStream osServer = this.socketServer.getOutputStream();
				byte[] data;
				data = null;
				data = new byte[512];
				while( (isClient.read(data)) != -1){
					
					//receber info do cliente e enviar para o servidor
					//isClient.read(data);
					osServer.write(data);

					data = null;
					data = new byte[512];

					//receber info do servidor e enviar para o cliente
					while( (isServer.read(data)) != -1){
						osClient.write(data);
					}

					data = null;
					data = new byte[512];

					//Flush dos outputs 
					osServer.flush();
					osClient.flush();
				}
				isServer.close();
				osServer.close();
			}
			else {
				String msg = new String("All servers busy!");
				byte[] info = msg.getBytes();
				osClient.write(info);
			}

			isClient.close();
			osClient.close();
			this.socketServer.close();
			this.socketClient.close();
			System.out.println("RPThreadClient finish");
			si.decrNumConnect();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("RPThreadClient finish");
			si.decrNumConnect();
		}
	}	
}
