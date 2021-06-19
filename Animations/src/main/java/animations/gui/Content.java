package animations.gui;

import animations.animation.AbstractAnimation;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Matt
 */
public class Content extends JPanel {
    private final Canvas animationCanvas;
    private final JPanel tools;
    private final AnimationTimer timer;
    
    public Content(Canvas animationCanvas, AnimationTimer timer){
        super();
        setLayout(new BorderLayout());
        this.animationCanvas = animationCanvas;
        add(animationCanvas, BorderLayout.CENTER);
        
        this.tools = new JPanel();
        tools.setLayout(new FlowLayout());
        add(tools, BorderLayout.PAGE_END);
        
        this.timer = timer;
    }
    
    public final void setAnimation(AbstractAnimation newAnimation){
        animationCanvas.setAnimation(newAnimation);
        timer.reset();
        timer.start();
    }
    
    public final void addTool(JComponent newTool){
        this.tools.add(newTool);
    }    
}
