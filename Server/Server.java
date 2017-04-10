import java.io.*;
import java.net.*;

public class Server{

	public static void main (String args[]){
		try{
			//iniciar o monitor do Servidor
			Monitor m = new Monitor();
			m.start();

			m.join();
		}
		catch(Exception e){
			System.out.println("Erro");
		}	
	}
}