package animations.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.Timer;

/**
 *
 * @author Matt
 */
public class AnimationTimer implements ActionListener {
    public static final int FRAMES_PER_SECOND = 20;
    
    private int frame;
    private final Consumer<Integer> runEachFrame;
    private final Timer timer;
    
    public AnimationTimer(Consumer<Integer> runEachFrame){
        this.runEachFrame = runEachFrame;
        frame = 0;
        timer = new Timer(msPerFrame(), this);
        timer.setRepeats(true);
    }
    
    private int msPerFrame(){
        return 1000 / FRAMES_PER_SECOND;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
    
    public final void reset(){
        frame = 0;
    }
    
    public final void start(){
        timer.start();
    }
    
    public final void pause(){
        timer.stop();
    }
    
    public final void update(){
        runEachFrame.accept(frame);
        ++frame;
    }
    
    public final int getFrame(){
        return frame;
    }
}
