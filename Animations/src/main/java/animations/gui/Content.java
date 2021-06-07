package animations.gui;

import animations.animation.AbstractAnimation;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Matt
 */
public class Content extends JPanel {
    private AbstractAnimation currentAnimation;
    
    public Content(){
        super();
        this.setBackground(Color.BLACK);
    }
}
