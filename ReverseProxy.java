import java.lang.Thread;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;

public class ReverseProxy{

	public static void main(String args[]){
		
		try{
			DatagramSocket socket = new DatagramSocket();

			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf,buf.length);
			InetAddress hostAddress = InetAddress.getByName("localhost");

			while(true){
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				String outMessage = stdin.readLine();

				if(outMessage.equals("exit"))
					break;

				String outString = "Client say: " + outMessage;
				buf = outString.getBytes();

				DatagramPacket out = new DatagramPacket(buf,buf.length,hostAddress,5555);
				socket.send(out);

				socket.receive(dp);
				String rcvd = "rcvd from " + dp.getAddress() + ", " + dp.getPort() + ": "
        	  		+ new String(dp.getData(), 0, dp.getLength());
      			System.out.println(rcvd);
			}
			socket.close();
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}
}