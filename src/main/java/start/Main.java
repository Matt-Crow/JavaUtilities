package start;

import gui.MainWindow;
import java.io.IOException;
import net.ChatServer;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ChatServer.validateServer();
            System.out.println("Started Chat Server");
            MainWindow.getInstance();
        } catch (IOException ex) {
            System.err.println("Server failed to start");
            ex.printStackTrace();
        }
        
    }
}
