import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PacoteMonitor implements Serializable{
	private int numPacote;
	private long tempSaida;
	private String tipo;
	private InetAddress endIP;
    private int porta;

	public PacoteMonitor(int numPacote, String tipo, InetAddress endIP, int porta){
		this.numPacote = numPacote;
		this.tempSaida = System.currentTimeMillis();
		this.tipo = tipo;
		this.endIP = endIP;
        this.porta = porta;
	}

    public PacoteMonitor(byte[] data){
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInput in = null;
        Object o = null;
        try {
            in = new ObjectInputStream(bis);
            o = in.readObject(); 

            PacoteMonitor pm = (PacoteMonitor) o;

            this.numPacote = pm.getNumPacote();
            this.tempSaida = pm.getTempSaida();
            this.tipo = pm.getTipo();
            this.endIP = pm.getEndIP();
            this.porta = pm.getPorta();
        }
        catch(Exception ex){
            System.out.println("BytesErro");
        }
    }

    public byte[] converteByte(){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] data = null;
        try {
            out = new ObjectOutputStream(bos);   
            out.writeObject(this);
            out.flush();
            data = bos.toByteArray();
        } 
        catch (Exception ex) {
            System.out.println("converteByteErro");
        }
        return data;
    } 

    public void print(){
        System.out.println("numPac : "+this.numPacote+"\n");
        System.out.println("tempSaida : "+this.tempSaida+"\n");
        System.out.println("tipo : "+this.tipo+"\n");
        System.out.println("endIP : "+this.endIP+"\n");
        System.out.println("porta : "+this.porta+"\n");
    }

	public int getNumPacote(){
		return numPacote;
	}

    public void setNumPacote(int numPacote){
        this.numPacote = numPacote;
    }

    public long getTempSaida() {
        return tempSaida;
    }

    public void setTempSaida(long tempSaida) {
        this.tempSaida = tempSaida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public InetAddress getEndIP() {
        return endIP;
    }

    public void setEndIP(InetAddress endIP) {
        this.endIP = endIP;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}