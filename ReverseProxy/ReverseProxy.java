import java.lang.Thread;
import java.util.Random;
import java.io.*;
import java.net.*;

public class ReverseProxy{
	ServerSocket server;
	Socket c = null;

	public static void main(String args[]){
		
		try{
			//iniciar thread de monitorizacao dos servidores
			Tabela tab = new Tabela();
			DatagramSocket ds = new DatagramSocket(5555);
			RPThreadWrite tw = new RPThreadWrite(tab,ds);
			RPThreadRead tr = new RPThreadRead(tab,ds);
			tw.start();
			tr.start();

			tw.join();
			tr.join();

			//ligar ligacoes TCP
			/*server = new ServerSocket(80);

			while((c = server.accpet())!=null){
				RPThread t = new RPThread(c);
				t.start();
			}
			server.close();*/
		}
		catch(Exception e){
			System.out.println("Erro");
		}	
	}
}