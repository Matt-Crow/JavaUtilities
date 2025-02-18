package image.gui;

import java.awt.event.MouseEvent;

public class DragPanner implements MouseTool {

    private int panX = 0;
    private int panY = 0;

    // tracks where the user started holding down their mouse
    private int prevX;
    private int prevY;

    public DragPanner() {
        super();
        prevX = -1;
        prevY = -1;
    }

    public int getPanX() {
        return panX;
    }

    public int getPanY() {
        return panY;
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
        prevX = e.getX();
        prevY = e.getY();
        // don't need to repaint here
    }

    @Override
    public void handleMouseDragged(MouseEvent e) {
        int dx = e.getX() - prevX;
        int dy = e.getY() - prevY;
        panX += dx;
        panY += dy;
        prevX = e.getX();
        prevY = e.getY();

        // repaint show it shows with panning applied
        e.getComponent().repaint();
    }
}
