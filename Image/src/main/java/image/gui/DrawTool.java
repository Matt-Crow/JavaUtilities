package image.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import image.ApplicationState;

/**
 * Allows the user to draw on a BufferedImage
 */
public class DrawTool implements MouseTool {
    private final int MARKER_SIZE = 10;
    private final ApplicationState applicationState;

    public DrawTool(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
    
    }

    @Override
    public void handleMouseDragged(MouseEvent e) {
        var panner = applicationState.getPannerTool();
        var zoomer = applicationState.getZoomTool();
        var x = (int)((e.getX() - panner.getPanX()) / zoomer.getZoomFactor());
        var y = (int)((e.getY() - panner.getPanY()) / zoomer.getZoomFactor());

        for (var dx = 0; dx < MARKER_SIZE; dx++) {
            for (var dy = 0; dy < MARKER_SIZE; dy++) {
                // TODO allow choosing color
                applicationState.getImage().setRGB(x + dx, y + dy, Color.BLACK.getRGB()); 
            }
        }
        e.getComponent().repaint();
    }
}
