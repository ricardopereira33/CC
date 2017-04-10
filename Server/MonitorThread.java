import java.io.*;
import java.net.*;

public class MonitorThread extends Thread{
	private DatagramSocket socket;
	private int port;
	private InetAddress addr;
	private ServerStatus ss;
	private int time; //segundos

	public MonitorThread(ServerStatus ss, int time, DatagramSocket socket){
		this.port=5555;
		this.ss = ss;
		this.time = time;
		this.socket = socket;
	}

	public void run (){
		
		byte[] buffer = new byte[512];
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		int i =0;
		try{
			addr = InetAddress.getByName("10.0.2.10");

			while(true){
				if(ss.getDisp()){
					PacoteMonitor pm = new PacoteMonitor(-1,"available",packet.getAddress(),packet.getPort());
					byte[] buf = pm.converteByte();
				
					//endereço do servidor e enviar pacote
					DatagramPacket out = new DatagramPacket(buf, buf.length, addr, 5555);
					this.socket.send(out);
					Thread.sleep(this.time*1000);
				} 
				else {
					Thread.sleep(this.time*1000);
					System.out.println("Morri");
				}
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}
}