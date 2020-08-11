package fakenotepad;
import java.awt.Toolkit;
import javax.swing.JFrame;
/**
 *
 * @author Matt
 */
public class Frame extends JFrame{
    public Frame(){
        super();
        setTitle("Yup, definitely notepad");
        
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() 
            - Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration()).bottom;
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new Pane());
        setVisible(true);
    }
}
