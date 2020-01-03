package net;

import io.AudioInput;
import io.AudioOutput;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matt Crow
 */
public class Backend {
    private final DatagramSocket socket;
    private InetAddress connectedTo;
    private AudioInput in;
    private AudioOutput out;
    
    public Backend() throws SocketException{
        socket = new DatagramSocket(4000);
        connectedTo = null;
    }
    
    public void connect(String ip) throws UnknownHostException{
        connectedTo = InetAddress.getByName(ip);
    }
    
    public void setInput(AudioInput input){
        in = input;
        Thread inputThread = new Thread(){
            @Override
            public void run(){
                byte[] buff = new byte[in.getBufferSize()];
                int bytesRead;
                while(true){
                    bytesRead = in.read(buff);
                    if(connectedTo != null){
                        try {
                            send(buff, bytesRead);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        };
        inputThread.start();
    }
    
    public void send(byte[] buff, int len) throws IOException{
        DatagramPacket packet = new DatagramPacket(buff, len, connectedTo, 4000);
        socket.send(packet);
    }
    
    /**
     * waits for the socket to receive a datagram,
     * then copies the contents of that datagram into buff
     * 
     * @param buff
     * @param len the maximum number of bytes to copy
     * @return the actual number of bytes copied
     * @throws IOException 
     */
    public int receive(byte[] buff, int len) throws IOException{
        DatagramPacket buffer = new DatagramPacket(new byte[len], len);
        socket.receive(buffer);
        System.arraycopy(buffer.getData(), 0, buff, 0, buffer.getLength());
        return buffer.getLength();
    }
}
