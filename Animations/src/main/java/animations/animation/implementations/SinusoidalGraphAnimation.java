package animations.animation.implementations;

import animations.animation.AbstractAnimation;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Matt
 */
public class SinusoidalGraphAnimation extends AbstractAnimation {
    private static final double PERIOD = 3.0;
    
    @Override
    public void paint(JComponent component, Graphics g, double t) {
        
        g.setColor(Color.black);
        g.fillRect(0, 0, component.getWidth(), component.getHeight());
        
        int circleCenter = component.getHeight() / 2;
        double r = component.getHeight() / 2;
        int tipX = (int) (circleCenter + r * Math.cos(theta(t)));
        int tipY = (int) (circleCenter - r * Math.sin(theta(t)));
        g.setColor(Color.yellow);
        g.drawOval(0, 0, (int) (2 * r), (int) (2 * r));
        g.drawLine(
            circleCenter, 
            circleCenter, 
            tipX,
            tipY
        );
        
        int w = (int) (component.getWidth() - tipX);
        int blockSize = 5;
        for(double offset = 0.0; offset < w; offset += 1.0){
            g.setColor(Color.red);
            g.fillRect(
                (int) (tipX + offset), 
                (int) (circleCenter - r * Math.sin(theta(t - offset / (w - 2 * circleCenter)))),
                blockSize, 
                blockSize
            );
            
            g.setColor(Color.blue);
            g.fillRect(
                (int) (tipX + offset), 
                (int) (circleCenter + r * Math.cos(theta(t - offset / (w - 2 * circleCenter)))),
                blockSize, 
                blockSize
            );
        }
    }
    
    private double theta(double t){
        return t * 2 * Math.PI / PERIOD;
    }
}
