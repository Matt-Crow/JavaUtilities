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
    private final LinkedList<Consumer<Integer>> speedChangedListeners;
    private final JSlider hamburger;
    private final int MAX_SPEED = 100;
    
    public AnimationSpeedChooser(){
        super();
        
        setLayout(new BorderLayout());
        add(new JLabel("Animation Speed"), BorderLayout.PAGE_START);
        
        JPanel mid = new JPanel();
        add(mid, BorderLayout.CENTER);
        
        hamburger = new JSlider(JSlider.HORIZONTAL, 1, MAX_SPEED, 1);
        hamburger.addChangeListener(this);
        mid.add(hamburger);
        
        speedChangedListeners = new LinkedList<>();
    }
    
    protected final void addSpeedChangedListener(Consumer<Integer> listener){
        speedChangedListeners.add(listener);
    }
    
    public final void setSpeed(int speed){
        if(speed < 0 || MAX_SPEED < speed){
            throw new IllegalArgumentException(String.format("Speed cannot exceed %d, so setSpeed(%d) is not allowed", MAX_SPEED, speed));
        }
        speedChangedListeners.forEach((listener)->{
            listener.accept(speed);
        });
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(hamburger.equals(e.getSource())){
            setSpeed(hamburger.getValue());
        }
    }
}
