import java.io.*;
import java.net.*;

public class Server{

	public static void main (String args[]){
		try{
			ServerStatus server = new ServerStatus();
			Socket c;
			//iniciar o monitor do Servidor
			Monitor m = new Monitor(server,2);
			m.start();

			//Faz seu trabalho
			//ServerSocket server = new ServerSocket(80);
			//aceitar pedidos TCP
			
			/*
			while((c = server.accpet())!=null){
				RPThreadClient t = new RPThreadClient(c);
				t.start();
			}*/

			m.join();
			//server.close();
		}
		catch(Exception e){
			System.out.println("Erro");
		}	
	}
}