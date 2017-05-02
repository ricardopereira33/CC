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
    private int numPacoteCheck;

	public ServerInfo(InetAddress endIP, int port, float rtt, float taxPacLost, int numConnect,boolean available, int numPacote, int numPacoteCheck){
	   this.endIP = endIP;
       this.port = port;
       this.rtt = rtt;
       this.taxPacLost = taxPacLost;
       this.numConnect = numConnect;
       this.available = available;
       this.numPacote = numPacote;
       this.numPacoteCheck = numPacoteCheck;
       this.fails = 0;
	}

    public synchronized void addFails(int dif){
        this.fails+=dif;
       	System.out.println(this.fails+"-"+this.numPacote);
	this.taxPacLost =  ((float)this.fails/this.numPacote)*100;
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
        int aux = this.numPacote;
        this.numPacote++;
        return aux;  
    }

    public synchronized void setNumPacote(int numPacote){
        this.numPacote = numPacote;
    }

    public synchronized int getNumPacoteCheck(){
        return this.numPacoteCheck;  
    }

    public synchronized void setNumPacoteCheck(int numPacote){
        this.numPacoteCheck = numPacoteCheck;
    }
}
