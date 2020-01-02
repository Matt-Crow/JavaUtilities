package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Matt Crow
 */
public class Backend {
    private final DatagramSocket socket;
    private InetAddress connectedTo;
    
    public Backend() throws SocketException{
        socket = new DatagramSocket(4000);
        connectedTo = null;
    }
    
    public void connect(String ip) throws UnknownHostException{
        connectedTo = InetAddress.getByName(ip);
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
