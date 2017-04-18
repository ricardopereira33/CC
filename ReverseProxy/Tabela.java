import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Tabela{
	private Map<InetAddress,ServerInfo> servers;

	public Tabela(){
		this.servers = new HashMap<>();
	}

	public synchronized boolean contains(InetAddress ip){
		return servers.containsKey(ip);
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

	public synchronized ArrayList<ServerInfo> getList(){
		ArrayList<ServerInfo> lista = new ArrayList<>();

		for(Map.Entry<InetAddress, ServerInfo> entry : servers.entrySet()){
			lista.add(entry.getValue());
		}

		return lista;
	}
}
