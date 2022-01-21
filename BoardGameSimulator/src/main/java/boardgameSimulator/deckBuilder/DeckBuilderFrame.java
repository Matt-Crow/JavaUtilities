package boardgameSimulator.deckBuilder;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class DeckBuilderFrame extends JFrame {

    public DeckBuilderFrame() {
        super();
        setTitle("Deckbuilder");
        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()
                - Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration()).bottom);
        setContentPane(new DeckBuilderPanel());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new DeckBuilderFrame();
    }
}
