package image.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import image.ApplicationState;

/**
 * JPanel which paints an image
 */
public class ImagePainter extends JPanel {
    private final ApplicationState applicationState;
    
    public ImagePainter(ApplicationState applicationState) {
        this.applicationState = applicationState;
        setBackground(Color.WHITE);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                applicationState.getMouseTool().handleMousePressed(e);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                applicationState.getMouseTool().handleMouseDragged(e);
            }
        });
        applicationState.getZoomTool().handleEventsFor(this);
        
        // TODO switch between panning and drawing modes for moving the mouse
        // applicationState.setMouseTool(new DrawTool(applicationState, zoomer, panner));

        applicationState.addImageChangeListener(this::handleImageChanged);
    }

    private void handleImageChanged(BufferedImage image) {
        setSize(image.getWidth(), image.getHeight());
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var panner = applicationState.getPannerTool();
        g.translate(panner.getPanX(), panner.getPanY());

        var zoomer = applicationState.getZoomTool();
        Graphics2D g2d = (Graphics2D)g;
        g2d.scale(zoomer.getZoomFactor(), zoomer.getZoomFactor());
        g.drawImage(applicationState.getImage(), 0, 0, this);
    }
}
