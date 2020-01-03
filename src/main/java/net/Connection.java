package net;

import io.AudioInput;
import io.AudioOutput;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Matt Crow
 */
public class Connection {
    private DatagramSocket socket;
    private final InetAddress connectedTo;
    private final AudioInput in;
    private final AudioOutput out;
    private volatile boolean isOpen;
    
    public Connection(AudioInput input, AudioOutput output, InetAddress client) throws SocketException{
        socket = null;
        connectedTo = client;
        in = input;
        out = output;
        isOpen = false;
    }
    
    public void open() throws SocketException{
        socket = new DatagramSocket(4000);
        isOpen = true;
        /*
        read input from the microphone,
        send it through the socket
        */
        Thread inputThread = new Thread(){
            @Override
            public void run(){
                byte[] buff = new byte[in.getBufferSize()];
                int bytesRead;
                while(isOpen){
                    bytesRead = in.read(buff);
                    try {
                        send(buff, bytesRead);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        inputThread.start();
        
        /*
        read output from the socket,
        send it to the speakers
        */
        Thread outputThread = new Thread(){
            @Override
            public void run(){
                int size = in.getBufferSize();
                byte[] buff = new byte[size];
                int bytesRead;
                while(isOpen){
                    try {
                        //wait for the socket to receive,
                        //copy into buffer
                        bytesRead = receive(buff, in.getBufferSize());
                        out.write(buff, bytesRead);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        outputThread.start();
    }
    
    public void close(){
        isOpen = false;
        if(socket != null){
            socket.close();
            socket = null;
        }
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
