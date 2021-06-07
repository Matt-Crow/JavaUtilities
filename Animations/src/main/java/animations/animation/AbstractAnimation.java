package animations.animation;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Matt
 */
public abstract class AbstractAnimation {
    public abstract void paint(JComponent component, Graphics g, int t);
}
