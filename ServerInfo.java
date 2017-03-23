import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerInfo{
	private String endIP;
	private float rtt;
	private float taxPacLost;
	private int numConnect;

	public ServerInfo(String endIP, float rtt, float taxPacLost, int numConnect){
	   this.endIP = endIP;
       this.rtt = rtt;
       this.taxPacLost = taxPacLost;
       this.numConnect = numConnect;
	}

    public String getEndIp(){
        return endIP;
    }

    public void setEndIp(String endIP){
        this.endIP = endIP;
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
}