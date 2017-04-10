import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerInfo implements Serializable{
	private InetAddress endIP;
    private int port;
	private float rtt;
	private float taxPacLost;
	private int numConnect;
    private boolean available;

	public ServerInfo(InetAddress endIP, int port, float rtt, float taxPacLost, int numConnect,boolean available){
	   this.endIP = endIP;
       this.port = port;
       this.rtt = rtt;
       this.taxPacLost = taxPacLost;
       this.numConnect = numConnect;
       this.available = available;
	}

    public InetAddress getEndIp(){
        return endIP;
    }

    public void setEndIp(InetAddress endIP){
        this.endIP = endIP;
    }

    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public float getRtt(){
        return rtt;
    }

    public void setRtt(float rtt){
        this.rtt = rtt;
    }

    public float getTaxPacLost(){
        return taxPacLost;
    }

    public void setTaxPacLost(float taxPacLost){
        this.taxPacLost = taxPacLost;
    }

    public int getNumConnect(){
        return numConnect;
    }

    public void setNumConnect(int numConnect){
        this.numConnect = numConnect;
    }

    public boolean getAvailable(){
        return available;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }
}