import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;

public class ReverseProxy{
	ServerSocket server;
	Socket c = null;

	public static void main(String args[]){
		ServerSocket server;
		Socket c = null;		
		
		try{
			Tabela tab = new Tabela();
			DatagramSocket ds = new DatagramSocket(5555);

			//iniciar threads de monitorizacao dos servidores
			RPThreadWrite tw = new RPThreadWrite(tab,ds);
			RPThreadRead tr = new RPThreadRead(tab,ds);
			RPCheckAvailable tca = new RPCheckAvailable(tab);
			tw.start();
			tr.start();
			tca.start();

			//ligacoes TCP
			InetAddress addr = InetAddress.getByName("10.0.2.10");
			server = new ServerSocket(80,30,addr);

			while((c = server.accept()) != null){
				RPThreadClient t = new RPThreadClient(tab,c);
				t.start();
			}

			tw.join();
			tr.join();
			tca.join();
			server.close();
		}
		catch(Exception e){
			System.out.println("Erro");
		}	
	}
}
