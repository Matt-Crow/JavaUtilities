package animations.gui;

import animations.animation.AbstractAnimation;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Matt
 */
public class Content extends JPanel {
    private AbstractAnimation currentAnimation;
    private final AnimationTimer timer;
    
    public Content(){
        super();
        this.setBackground(Color.BLACK);
        this.timer = new AnimationTimer(this::update);
    }
    
    public final void setAnimation(AbstractAnimation newAnimation){
        this.currentAnimation = newAnimation;
        timer.reset();
        timer.start();
    }
    
    private void update(int frame){
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
