import java.io.*;
import java.net.*;

public class Server{

	public static void main (String args[]){
		try{
			ServerStatus server = new ServerStatus();
			Socket c;
			//iniciar o monitor do Servidor
			Monitor m = new Monitor(server,5);
			m.start();

			m.join();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}	
	}
}