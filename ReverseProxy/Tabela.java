package ReverseProxy;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

public class Tabela{
	private Map<InetAddress,ServerInfo> servers;

	public Tabela(){
		this.servers = new HashMap<>();
	}

	public synchronized ServerInfo getServerInfo(InetAddress id){
		return servers.get(id);
	}
    
    public synchronized void setServerInfo(ServerInfo si){
    	this.servers.put(si.getEndIp(),si);
    }

    public int size(){
    	return servers.size();
    }
	

	public Map<InetAddress,ServerInfo> lista(){
		return servers;
	}
}
