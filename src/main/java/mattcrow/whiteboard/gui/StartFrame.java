package mattcrow.whiteboard.gui;

import javax.swing.JFrame;

/**
 *
 * @author Matt Crow
 */
public class StartFrame extends JFrame{
    public StartFrame(){
        super();
        setTitle("Whiteboard");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(new Whiteboard());
    }
    
    public static void main(String[] args){
        new StartFrame();
    }
}
