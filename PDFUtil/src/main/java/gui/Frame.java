package gui;

import javax.swing.JFrame;

/**
 *
 * @author Matt Crow
 */
public class Frame extends JFrame {
    public Frame(){
        setTitle("Matt's PDF Utility (because he's too cheap to buy Acrobat pro)");
        setContentPane(new Page());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        revalidate();
        repaint();
    }
}
