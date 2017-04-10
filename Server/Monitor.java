import java.io.*;
import java.net.*;

public class Monitor extends Thread{
	private DatagramSocket socket;
	private int port;
	private InetAddress addr;
	private ServerStatus ss;
	private MonitorThread mt;
	
	public Monitor(ServerStatus ss, int time){
		this.port = 5555;
		this.mt = new MonitorThread(ss,time);
	}

	public void run (){
		
		byte[] buffer = new byte[512];
		DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
		int i =0;
		try{
			addr = InetAddress.getByName("10.0.2.10");
			this.socket = new DatagramSocket(port);
			System.out.println("Monitor stared");
			mt.start();

			while(true){
				//receber pacoteMonitor
				this.socket.receive(packet);
				PacoteMonitor pm = new PacoteMonitor(packet.getData());

				pm.print();
				
				//enviar algo escrito do teclado
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				String msg = stdin.readLine();

				if(msg.equals("69")) ss.setDisp(false);

				//enviar pacoteMonitor
				PacoteMonitor pm2 = new PacoteMonitor(1,msg,packet.getAddress(),packet.getPort());
				pm2.setTempSaida(pm.getTempSaida());
				byte[] buf = pm2.converteByte();
				
				DatagramPacket out = new DatagramPacket(buf, buf.length, packet.getAddress(),packet.getPort());
				this.socket.send(out);
				//System.out.println(info);
			}
		}
		catch(Exception e){
			System.out.println("Erro");
		}
	}
}