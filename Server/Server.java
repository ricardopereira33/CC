import java.io.*;
import java.net.*;

public class Server{

	public static void main (String args[]){
		try{
			ServerStatus ss = new ServerStatus();
			//iniciar o monitor do Servidor
			Monitor m = new Monitor(ss,2);
			m.start();

			//Faz seu trabalho

			m.join();
		}
		catch(Exception e){
			System.out.println("Erro");
		}	
	}
}