package animations.animation.implementations;

import animations.animation.AbstractAnimation;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Matt
 */
public class PolarGraphAnimation extends AbstractAnimation {
    private static final double PERIOD = 3.0;
    
    @Override
    public void paint(JComponent component, Graphics g, double t) {
        int size = (component.getWidth() + component.getHeight()) / 5;
        g.setColor(Color.PINK);
        double theta = theta(t);
        int petals = petalCount(t);
        double r;
        for(double i = 0; i < theta; i += 0.0001){
            r = size * Math.sin(petals * i);
            g.fillRect(
                (int) (component.getWidth() / 2 + r * Math.cos(i)), 
                (int) (component.getHeight() / 2 + r * Math.sin(i)), 
                1, 
                1
            );
        }
    }
    
    private double theta(double t){
        double theta = rawTheta(t);
        while(theta >= 2 * Math.PI){
            theta -= 2 * Math.PI;
        }
        return theta;
    }
    
    private double rawTheta(double t){
        return t * 2 * Math.PI / PERIOD;
    }
    
    private int petalCount(double t){
        return 2 + (int)Math.floor(t / PERIOD);
    }
}
