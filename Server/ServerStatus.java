import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerStatus{
	private boolean disponivel;

	public ServerStatus(){
		this.disponivel = true;
	}

	public synchronized boolean getDisp(){
		return this.disponivel;
	}

	public synchronized void setDisp(boolean d){
		this.disponivel = d;
	}
}