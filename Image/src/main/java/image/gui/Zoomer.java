package image.gui;

import java.awt.event.MouseWheelEvent;
import javax.swing.JComponent;

public class Zoomer {
    
    /**
     * The factor by which mouse wheel rotations are multiplied.
     * Higher values mean zooming occurs faster.
     */
    public static final double ZOOM_PER_TICK = 0.05;
    public static final double MIN_ZOOM = 0.1;
    public static final double MAX_ZOOM = 10.0;
    
    private double zoomFactor = 1.0;
    
    public double getZoomFactor() {
        return zoomFactor;
    }
    
    public void handleEventsFor(JComponent component) {
        component.addMouseWheelListener(e -> {
            mouseWheelMoved(e);
            component.repaint();
        });
    }
    
    private void mouseWheelMoved(MouseWheelEvent e) {
        zoomFactor -= e.getPreciseWheelRotation() * ZOOM_PER_TICK;
        if (zoomFactor < MIN_ZOOM) {
            zoomFactor = MIN_ZOOM;
        }
        if (zoomFactor > MAX_ZOOM) {
            zoomFactor = MAX_ZOOM;
        }
    }
}
