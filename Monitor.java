import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Monitor {

	static public void main (String[] args){
		int PORT = 5555;
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		DatagramSocket socket;
		try{

			socket = new DatagramSocket(PORT);

			System.out.println("Server stared");

			while(true){
				socket.receive(packet);
				String rcvd = new String(packet.getData(),0,packet.getLength()) + ", from address: " 
					+ packet.getAddress() + ", port: " + packet.getPort();
				System.out.println(rcvd);

				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				String outMessage = stdin.readLine();
				buf = ("Server say: " + outMessage).getBytes();
				DatagramPacket out = new DatagramPacket(buf,buf.length,packet.getAddress(),packet.getPort());
				socket.send(out);
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}
}