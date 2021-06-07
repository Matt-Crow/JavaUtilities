package animations.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Matt
 */
public class Window extends JFrame {
    private final Content content;
    
    public Window(Content content){
        super();
        this.content = content;
        setTitle("Java Animations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(content);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int bottomOfScreen = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
        setBounds(0, 0, screenSize.width, screenSize.height - bottomOfScreen);
        
        setVisible(true);
        revalidate();
        repaint();
    }
    
    public final Content getContent(){
        return content;
    }
}
