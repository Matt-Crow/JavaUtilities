package imageViewer.start;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 *
 * @author Matt
 */
class Zoomer implements MouseWheelListener {
    public static final double MIN_ZOOM = 0.1;
    public static final double MAX_ZOOM = 10.0;
    
    /**
     * The factor by which mouse wheel rotations are multiplied.
     * Higher values mean zooming occurs faster.
     */
    public static final double ZOOM_PER_TICK = 0.05;
    
    private final LinkedList<Consumer<Double>> scrollListeners;
    
    Zoomer(){
        scrollListeners = new LinkedList<>();
    }
    
    public void addScrollListener(Consumer<Double> listener){
        scrollListeners.add(listener);
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double dTheta = -e.getPreciseWheelRotation() * ZOOM_PER_TICK;
        scrollListeners.forEach((listener)->listener.accept(dTheta));
    }
}
