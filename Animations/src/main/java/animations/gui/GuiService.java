package animations.gui;

/**
 * Dependency Injector
 * 
 * @author Matt Crow
 */
public class GuiService {
    public Window createWindow(){
        AnimationTimer timer = new AnimationTimer();
        Canvas canvas = new Canvas(timer);
        Content c = new Content(canvas, timer);
        AnimationSpeedChooser speedChooser = new AnimationSpeedChooser();
        
        speedChooser.addSpeedChangedListener(timer::setSpeedMultiplier);
        timer.addTimerListener(canvas::update);
        c.addTool(speedChooser);
        
        return new Window(c);
    }
}
