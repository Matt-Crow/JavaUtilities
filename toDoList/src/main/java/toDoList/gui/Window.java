package toDoList.gui;

import javax.swing.JFrame;

/**
 *
 * @author Matt Crow
 */
public class Window extends JFrame {
    public Window(Panel content){
        super();
        setTitle("To Do List");
        setContentPane(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        
        setVisible(true);
        revalidate();
        repaint();
    }
}
