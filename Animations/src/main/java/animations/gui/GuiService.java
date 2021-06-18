package animations.gui;

/**
 *
 * @author Matt
 */
public class GuiService {
    public Window createWindow(){
        AnimationTimer timer = new AnimationTimer();
        Canvas canvas = new Canvas(timer);
        timer.addTimerListener(canvas::update);
        Content c = new Content(canvas, timer);
        return new Window(c);
    }
}
