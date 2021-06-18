package animations.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.function.Consumer;
import javax.swing.Timer;

/**
 *
 * @author Matt
 */
public class AnimationTimer implements ActionListener {
    public static final int FRAMES_PER_SECOND = 20;
    
    private int frame;
    private int frameRate;
    private final Timer timer;    
    private final LinkedList<Consumer<Integer>> timerListeners;
    
    public AnimationTimer() {
        frame = 0;
        frameRate = 1;
        timer = new Timer(msPerFrame(), this);
        timer.setRepeats(true);
        timerListeners = new LinkedList<>();
    }
    
    public AnimationTimer(Consumer<Integer> runEachFrame){
        this();
        addTimerListener(runEachFrame);
    }
    
    private int msPerFrame(){
        return 1000 / FRAMES_PER_SECOND;
    }
    
    public final void addTimerListener(Consumer<Integer> listener){
        timerListeners.add(listener);
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
    
    public final void setFrameRate(int rate){
        frameRate = rate;
    }
    
    public final void update(){
        timerListeners.forEach((listener)->listener.accept(frame));
        frame += frameRate;
    }
    
    public final int getFrame(){
        return frame;
    }
}
