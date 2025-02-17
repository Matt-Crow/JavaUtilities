package image.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Allows the user to draw on a BufferedImage
 */
public class DrawTool extends MouseAdapter {
    private final int MARKER_SIZE = 10;
    private final BufferedImage buffer;
    private final Runnable afterMouseDragHandler;

    public DrawTool(BufferedImage buffer, Runnable afterMouseDragHandler) {
        this.buffer = buffer;
        this.afterMouseDragHandler = afterMouseDragHandler;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (var dx = 0; dx < MARKER_SIZE; dx++) {
            for (var dy = 0; dy < MARKER_SIZE; dy++) {
                // TODO allow choosing color
                buffer.setRGB(e.getX() + dx, e.getY() + dy, Color.BLACK.getRGB()); 
            }
        }
        afterMouseDragHandler.run();
    }
}
