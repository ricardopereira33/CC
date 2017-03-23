import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class pacoteMonitor{
	private int numPacote;
	private float tempSaida;
	private String tipo;
	private String endIP;

	public pacoteMonitor(int numPacote, float tempSaida, String tipo, String endIP){
		this.numPacote = numPacote;
		this.tempSaida = tempSaida;
		this.tipo = tipo;
		this.endIP = endIP;
	}

    public pacoteMonitor(byte[] data){
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.BIG_ENDIAN); // or LITTLE_ENDIAN

        this.numPacote = bb.getInt();
        this.tempSaida = bb.getFloat();
        this.tipo = bb.getString();
        this.endIP = bb.getString();
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

    public String getEndIP() {
        return endIP;
    }

    public void setEndIP(String endIP) {
        this.endIP = endIP;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}