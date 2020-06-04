package gui;

import java.awt.Toolkit;
import javax.swing.JFrame;

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
        //setContentPane();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //TODO: break connections before closing
        
        setSize(
            (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 
            (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom
        );
        setVisible(true);
    }
    
    public static final MainWindow getInstance(){
        if(instance == null){
            instance = new MainWindow();
        }
        return instance;
    }
}
