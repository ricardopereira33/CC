import java.io.*;
import java.net.*;

public class Server{

	public static void main (String args[]){
		try{
			Monitor m = new Monitor(Integer.parseInt(args[0]));
			m.start();

			m.join();
		}
		catch(Exception e){
			System.out.println("Erro");
		}	
	}
}