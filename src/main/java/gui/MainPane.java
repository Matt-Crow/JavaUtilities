package gui;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author Matt
 */
public class MainPane extends JPanel{
    public MainPane(){
        super();
        setBackground(Color.BLACK);
        setLayout(new GridLayout(1, 1));
        setVisible(true);
    }
}
