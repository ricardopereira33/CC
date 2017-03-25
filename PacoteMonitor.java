import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PacoteMonitor implements Serializable{
	private int numPacote;
	private float tempSaida;
	private String tipo;
	private InetAddress endIP;
    private int porta;

	public PacoteMonitor(int numPacote, float tempSaida, String tipo, InetAddress endIP, int porta){
		this.numPacote = numPacote;
		this.tempSaida = tempSaida;
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

	public int getNumPacote(){
		return numPacote;
	}

    public void setNumPacote(int numPacote){
        this.numPacote = numPacote;
    }

    public float getTempSaida() {
        return tempSaida;
    }

    public void setTempSaida(float tempSaida) {
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