package toDoList.gui;

import javax.swing.JFrame;

/**
 *
 * @author Matt Crow
 */
public class Window extends JFrame {
    private final Panel content;
    
    public Window(Panel content){
        super();
        this.content = content;
        
        setTitle("To Do List");
        setContentPane(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        
        setVisible(true);
        revalidate();
        repaint();
    }
}
