package net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Matt
 */
public class Connection {
    private final Socket clientSocket;
    private final BufferedReader fromClient;
    private final BufferedWriter toClient;
    
    public Connection(Socket s) throws IOException{
        System.out.printf("Connecting to %s...\n", s.getInetAddress().getHostAddress());
        clientSocket = s;
        toClient = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.printf("Done connection to %s\n", s.getInetAddress().getHostAddress());
    }
    
    public void close(){
        try {
            toClient.close();
        } catch (IOException ex) {
            System.err.println("couldn't close output");
            ex.printStackTrace();
        }
        try {
            fromClient.close();
        } catch (IOException ex) {
            System.err.println("couldn't close input");
            ex.printStackTrace();
        }
        try {
            clientSocket.close();
        } catch (IOException ex) {
            System.err.println("couldn't close socket");
            ex.printStackTrace();
        }
    }
    
    @Override
    public String toString(){
        return String.format("Connection to %s", clientSocket.getInetAddress().toString());
    }
}
