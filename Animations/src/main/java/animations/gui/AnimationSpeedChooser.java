package animations.gui;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.function.Consumer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Matt
 */
public class AnimationSpeedChooser extends JComponent implements ChangeListener {
    private final LinkedList<Consumer<Double>> speedChangedListeners;
    private final JSlider hamburger;
    private final int MIN_SPEED = 0;
    private final int MAX_SPEED = 10;
    private final double SLIDER_MULT = 0.25;
    /*
    The range of choices users have for speeds ranges from
    [ MIN_SPEED * SLIDER_MULT, MAX_SPEED * SLIDER_MULT ]
    */
    
    public AnimationSpeedChooser(){
        super();
        
        setLayout(new BorderLayout());
        add(new JLabel("Animation Speed"), BorderLayout.PAGE_START);
        
        JPanel mid = new JPanel();
        add(mid, BorderLayout.CENTER);
        
        hamburger = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, 1);
        hamburger.addChangeListener(this);
        mid.add(hamburger);
        
        JLabel minLabel = new JLabel(String.format("%.1fX", MIN_SPEED * SLIDER_MULT));
        add(minLabel, BorderLayout.LINE_START);
        
        JLabel maxLabel = new JLabel(String.format("%.1fX", MAX_SPEED * SLIDER_MULT));
        add(maxLabel, BorderLayout.LINE_END);
        
        speedChangedListeners = new LinkedList<>();
    }
    
    protected final void addSpeedChangedListener(Consumer<Double> listener){
        speedChangedListeners.add(listener);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if(hamburger.equals(e.getSource())){
            setSpeed(hamburger.getValue());
        }
    }
    
    public final void setSpeed(int speed){
        if(speed < 0 || MAX_SPEED < speed){
            throw new IllegalArgumentException(String.format("Speed cannot exceed %d, so setSpeed(%d) is not allowed", MAX_SPEED, speed));
        }
        speedChangedListeners.forEach((listener)->{
            listener.accept(speed * SLIDER_MULT);
        });
    }
}
