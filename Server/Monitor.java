import java.io.*;
import java.net.*;

public class Monitor extends Thread{
	private DatagramSocket socket;
	private int port;
	private InetAddress addr;
	private String address;
	private ServerStatus ss;
	private int time;
	private MonitorThread mt;
	
	public Monitor(ServerStatus ss, int time,String address){
		this.port = 5555;
		this.ss = ss;
		this.time = time;
		this.address = address;
	}

	public void run (){
		
		byte[] buffer = new byte[512];
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		int i =0;
		try{
			addr = InetAddress.getByName(this.address);
			this.socket = new DatagramSocket(port);
			System.out.println("Monitor stared");
		
			this.mt = new MonitorThread(ss,time,socket,this.address);
			
			mt.start();

			while(true){
				//receber pacoteMonitor
				this.socket.receive(packet);
				PacoteMonitor pm = new PacoteMonitor(packet.getData());

				//pm.print();
				
				String msg = "Probing";

				//enviar pacoteMonitor
				PacoteMonitor pm2 = new PacoteMonitor(pm.getNumPacote(),msg,packet.getAddress(),packet.getPort());
				pm2.setTempSaida(pm.getTempSaida());
				byte[] buf = pm2.converteByte();
				
				DatagramPacket out = new DatagramPacket(buf, buf.length, packet.getAddress(),packet.getPort());
				this.socket.send(out);
				//System.out.println(info);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
