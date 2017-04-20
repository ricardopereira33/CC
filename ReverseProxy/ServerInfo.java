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
    private int numPacote;
    private int fails;

	public ServerInfo(InetAddress endIP, int port, float rtt, float taxPacLost, int numConnect,boolean available, int numPacote){
	   this.endIP = endIP;
       this.port = port;
       this.rtt = rtt;
       this.taxPacLost = taxPacLost;
       this.numConnect = numConnect;
       this.available = available;
       this.numPacote = numPacote;
       this.fails = 0;
	}

    public synchronized void addFails(int dif){
        this.fails+=dif;
        this.taxPacLost = (this.fails/this.numPacote)*100;
    }

    public synchronized InetAddress getEndIp(){
        return endIP;
    }

    public synchronized void setEndIp(InetAddress endIP){
        this.endIP = endIP;
    }

    public synchronized int getPort(){
        return port;
    }

    public synchronized void setPort(int port){
        this.port = port;
    }

    public synchronized float getRtt(){
        return rtt;
    }

    public synchronized void setRtt(float rtt){
        this.rtt = rtt;
    }

    public synchronized float getTaxPacLost(){
        return taxPacLost;
    }

    public synchronized void setTaxPacLost(float taxPacLost){
        this.taxPacLost = taxPacLost;
    }

    public synchronized int getNumConnect(){
        return numConnect;
    }

    public synchronized void setNumConnect(int numConnect){
        this.numConnect = numConnect;
    }

    public synchronized boolean getAvailable(){
        return available;
    }

    public synchronized void setAvailable(boolean available){
        this.available = available;
    }

    public synchronized int getNumPacote(){
        return this.numPacote;  
    }

    public synchronized void setNumPacote(int numPacote){
        this.numPacote = numPacote;
    }
}