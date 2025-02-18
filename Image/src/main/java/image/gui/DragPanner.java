package image.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;

public class DragPanner {

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

    public void handleEventsFor(JComponent component) {

        // record where the users starts holding down...
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                prevX = e.getX();
                prevY = e.getY();
                // don't need to repaint here
            }
        });

        // ... then pan when they move the mouse
        component.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - prevX;
                int dy = e.getY() - prevY;
                panX += dx;
                panY += dy;
                prevX = e.getX();
                prevY = e.getY();

                // repaint show it shows with panning applied
                component.repaint();
            }
        });
    }
}
