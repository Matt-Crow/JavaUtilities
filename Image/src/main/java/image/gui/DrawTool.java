package image.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import image.ApplicationState;

/**
 * Allows the user to draw on a BufferedImage
 */
public class DrawTool extends MouseAdapter {
    private final int MARKER_SIZE = 10;
    private final ApplicationState applicationState;
    private final Zoomer zoomer;
    private final Runnable afterMouseDragHandler;

    public DrawTool(ApplicationState applicationState, Zoomer zoomer, Runnable afterMouseDragHandler) {
        this.applicationState = applicationState;
        this.zoomer = zoomer;
        this.afterMouseDragHandler = afterMouseDragHandler;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        var x = (int)(e.getX() / zoomer.getZoomFactor());
        var y = (int)(e.getY() / zoomer.getZoomFactor());

        for (var dx = 0; dx < MARKER_SIZE; dx++) {
            for (var dy = 0; dy < MARKER_SIZE; dy++) {
                // TODO allow choosing color
                applicationState.getImage().setRGB(x + dx, y + dy, Color.BLACK.getRGB()); 
            }
        }
        afterMouseDragHandler.run();
    }
}
