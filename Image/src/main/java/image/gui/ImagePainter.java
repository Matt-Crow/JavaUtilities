package image.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import image.ApplicationState;

/**
 * JPanel which paints an image
 */
public class ImagePainter extends JPanel {
    private final ApplicationState applicationState;
    private final Zoomer zoomer = new Zoomer();

    public ImagePainter(ApplicationState applicationState) {
        this.applicationState = applicationState;
        setBackground(Color.WHITE);
        addMouseMotionListener(new DrawTool(applicationState, zoomer, () -> repaint()));

        zoomer.handleEventsFor(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.scale(zoomer.getZoomFactor(), zoomer.getZoomFactor());
        g.drawImage(applicationState.getImage(), 0, 0, this);
    }
}
