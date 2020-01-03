package gui;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

/**
 *
 * @author Matt Crow
 */
public class Frame extends JFrame{
    public Frame() throws LineUnavailableException{
        setSize(500, 300);
        Pane p = new Pane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(p);
        setVisible(true);
        addWindowListener(p);
        requestFocus();
    }
}
