package net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 * https://stackoverflow.com/questions/2983835/how-can-i-interrupt-a-serversocket-accept-method
 * @author Matt
 */
public class ChatServer {
    private final ServerSocket server;
    private final HashSet<InetAddress> validIpAddrs;
    private final HashMap<InetAddress, Connection> ipAddrToConn;
    private Thread connectionListenerThread;
    
    private static final int PORT = 4999;
    private static ChatServer instance;
    
    private ChatServer() throws IOException {
        if(instance != null){
            throw new ExceptionInInitializerError("ChatServer is a singleton: Use getInstance instead of invoking the constructor");
        }
        server = new ServerSocket(PORT);
        validIpAddrs = findValidIps();
        ipAddrToConn = new HashMap<>();
        startConnectionListenerThread();
    }
    
    public static void validateServer() throws IOException{
        if(instance == null){
            instance = new ChatServer();
        }
    }
    
    public static ChatServer getInstance() {
        if(instance == null){
            throw new NullPointerException("Make sure you call ChatServer.validateServer() before ChatServer.getInstance()!");
        }
        return instance;
    }
    
    private HashSet<InetAddress> findValidIps() throws SocketException{
        HashSet<InetAddress> ips = new HashSet<>();
        
        // get network interfaces
        Enumeration<NetworkInterface> eni = NetworkInterface.getNetworkInterfaces();
        NetworkInterface ni = null;
        Enumeration<InetAddress> addrs = null;
        InetAddress ia = null;
        
        while(eni.hasMoreElements()){
            ni = eni.nextElement();
            if(!ni.isLoopback() && ni.isUp()){
                //System.out.println(ni);
                addrs = ni.getInetAddresses();
                while(addrs.hasMoreElements()){
                    ia = addrs.nextElement();
                    //System.out.println(ia.getHostAddress());
                    ips.add(ia);
                }
            }
        }
        return ips;
    }
    
    private void startConnectionListenerThread(){
        if(connectionListenerThread == null){
            connectionListenerThread = new Thread(){
                @Override
                public void run(){
                    Socket remoteComputer = null;
                    while(true){
                        try {
                            remoteComputer = server.accept();
                            System.out.println("Accept " + remoteComputer.getInetAddress().getHostAddress());
                            connect(remoteComputer);
                        } catch (SocketException ex){
                            System.out.println("Server is closed. Breaking.");
                            break;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    connectionListenerThread = null;
                }
            };
            connectionListenerThread.start();
        }
    }
    
    public final String[] getIpAddrs(){
        return validIpAddrs.stream().map((InetAddress ina)->{
            return ina.getHostAddress();
        }).collect(Collectors.toList()).toArray(new String[]{});
    }
    
    public final void connect(String ipAddr) throws IOException{
        System.out.printf("Connecting to %s...\n", ipAddr);
        connect(new Socket(ipAddr, PORT));
    }
    
    public final void connect(Socket sock) throws IOException{
        if(ipAddrToConn.containsKey(sock.getInetAddress())){
            System.out.printf("Already connected to %s\n", sock.getInetAddress().getHostAddress());
        } else {
            // new connection
            ipAddrToConn.put(sock.getInetAddress(), new Connection(sock));
        }
    }
    
    public final void shutDown() throws IOException{
        ipAddrToConn.values().forEach((c)->c.close());
        // Javadoc: "Any thread currently blocked in ServerSocket.accept() will throw a SocketException."
        server.close();
    }
    
    public static void main(String[] args){
        try {
            ChatServer.validateServer();
        } catch (IOException ex) {
            System.err.println("Server won't start :(");
            ex.printStackTrace();
            System.exit(1);
        }
        
        ChatServer serv = ChatServer.getInstance();
        try {
            new Connection(new Socket("0.0.0.0", PORT));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        new Thread(){
            @Override
            public void run(){
                JOptionPane.showConfirmDialog(null, "Click OK to shut down server");
                try {
                    serv.shutDown();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }
}
