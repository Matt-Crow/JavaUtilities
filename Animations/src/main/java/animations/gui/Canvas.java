package animations.gui;

import animations.animation.AbstractAnimation;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The canvas where Animations are rendered
 * @author Matt Crow
 */
public class Canvas extends JPanel {
    private AbstractAnimation currentAnimation;
    private final AnimationTimer timer;
    
    public Canvas(AnimationTimer timer){
        super();
        this.timer = timer;
        this.setBackground(Color.BLACK);
    }
    
    public final void setAnimation(AbstractAnimation newAnimation){
        this.currentAnimation = newAnimation;
    }
    
    protected final void update(int frame){
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(currentAnimation != null){
            currentAnimation.paint(this, g, timer.getFrame());
        }
    }
}
