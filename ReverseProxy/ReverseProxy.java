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
			//iniciar threads de monitorizacao dos servidores
			Tabela tab = new Tabela();
			DatagramSocket ds = new DatagramSocket(5555);
			RPThreadWrite tw = new RPThreadWrite(tab,ds);
			RPThreadRead tr = new RPThreadRead(tab,ds);
			tw.start();
			tr.start();

			//ligacoes TCP
			server = new ServerSocket(80);

			while((c = server.accept())!=null){
				System.out.println("ola");
				RPThreadClient t = new RPThreadClient(tab,c);
				t.start();
			}

			tw.join();
			tr.join();
			server.close();
		}
		catch(Exception e){
			System.out.println("Erro");
		}	
	}
}