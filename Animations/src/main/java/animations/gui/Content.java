package animations.gui;

import animations.animation.AbstractAnimation;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Matt
 */
public class Content extends JPanel {
    private final Canvas animationCanvas;
    private final AnimationTimer timer;
    
    public Content(Canvas animationCanvas, AnimationTimer timer){
        super();
        setLayout(new BorderLayout());
        this.animationCanvas = animationCanvas;
        add(animationCanvas, BorderLayout.CENTER);
        this.timer = timer;
    }
    
    public final void setAnimation(AbstractAnimation newAnimation){
        animationCanvas.setAnimation(newAnimation);
        timer.reset();
        timer.start();
    }
    
    
    
    
}
