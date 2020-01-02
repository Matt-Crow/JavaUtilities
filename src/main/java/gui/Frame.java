package gui;

import javax.swing.JFrame;

/**
 *
 * @author Matt Crow
 */
public class Frame extends JFrame{
    public Frame(){
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new Pane());
        setVisible(true);
        requestFocus();
    }
}
