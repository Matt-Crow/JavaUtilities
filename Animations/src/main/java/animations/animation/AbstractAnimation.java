package animations.animation;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Matt Crow
 */
public abstract class AbstractAnimation {
    /**
     * This method is invoked every frame by an AnimationTimer, which renders
     * this Animation on a given Graphics context.
     * 
     * @param component
     * @param g
     * @param t seconds elapsed since the start of the animation. 
     * Ranges from [0, infinity)
     */
    public abstract void paint(JComponent component, Graphics g, double t);
}
