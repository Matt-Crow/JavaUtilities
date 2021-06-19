package animations.animation.implementations;

import animations.animation.AbstractAnimation;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author Matt
 */
public class VectorFieldAnimation extends AbstractAnimation {
    
    private final int VECTOR_SPACING = 10;
    private final int VECTOR_SIZE = 5;
    
    @Override
    public void paint(JComponent component, Graphics g, double t) {
        int w = component.getWidth();
        int h = component.getHeight();
        g.setColor(Color.black);
        g.fillRect(0, 0, w, h);
        
        int circleX = circleX(t, w);
        g.setColor(Color.white);
        //g.fillOval(circleX, h / 2, h / 5, h / 5);
        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.setColor(Color.MAGENTA);
        for(int i = 0; i < w; i += VECTOR_SPACING){
            for(int j = 0; j < h; j += VECTOR_SPACING){
                drawVector(g, w, h, i, j, t);
            }
        }
    }
    
    private int circleX(double t, int width){
        return ((int) (t * width / 3)) % width; // 3 seconds to cross screen
    }
    
    private void drawVector(Graphics g, int w, int h, int i, int j, double t) {
        int r = VECTOR_SIZE;
        
        double theta = Math.atan2(i - w / 2, j - h / 2) + t;
        g.drawLine(i, j, (int) (i + r * Math.cos(theta)), (int) (j - r * Math.sin(theta)));
    }
}
