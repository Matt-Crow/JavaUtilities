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
    public static final int FRAMES_PER_SECOND = 60;
    
    private double secondsElapsed;
    private double speedMultiplier;
    private final Timer timer;    
    private final LinkedList<Consumer<Double>> timerListeners;
    
    public AnimationTimer() {
        secondsElapsed = 0;
        speedMultiplier = 1.0;
        timer = new Timer(msPerFrame(), this);
        timer.setRepeats(true);
        timerListeners = new LinkedList<>();
    }
    
    public AnimationTimer(Consumer<Double> runEachFrame){
        this();
        addTimerListener(runEachFrame);
    }
    
    private int msPerFrame(){
        return 1000 / FRAMES_PER_SECOND;
    }
    
    public final void addTimerListener(Consumer<Double> listener){
        timerListeners.add(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
    
    public final void reset(){
        secondsElapsed = 0;
    }
    
    public final void start(){
        timer.start();
    }
    
    public final void pause(){
        timer.stop();
    }
    
    public final void setSpeedMultiplier(double rate){
        speedMultiplier = rate;
    }
    
    public final void update(){
        timerListeners.forEach((listener)->listener.accept(secondsElapsed));
        secondsElapsed += speedMultiplier / FRAMES_PER_SECOND;
    }
    
    public final double getSecondsElapsed(){
        return secondsElapsed;
    }
}
