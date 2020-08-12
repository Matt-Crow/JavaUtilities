package gui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import net.ChatServer;

/**
 *
 * @author Matt Crow
 */
public final class MainWindow extends JFrame{
    private static MainWindow instance;
    
    private MainWindow(){
        super();
        if(instance != null){
            throw new ExceptionInInitializerError("Cannot have multiple instances of MainWindow: It is a singleton.");
        }
        
        setTitle("Matt's Simple Chat App");
        setLayout(new GridLayout(1, 1));
        setContentPane(new MainPane());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setSize(
            (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 
            (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom
        );
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                try {
                    // move this to the connection classes,
                    // have them add themselves as window listeners
                    // this should break all active connections
                    ChatServer.getInstance().shutDown();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NullPointerException ex){
                    System.out.println("Server wasn't created via validateServer");
                }
            }
        });
        
        setVisible(true);
        revalidate();
        repaint();
    }
    
    public static final MainWindow getInstance(){
        if(instance == null){
            instance = new MainWindow();
        }
        return instance;
    }
}
