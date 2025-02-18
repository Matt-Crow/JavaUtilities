package imageViewer.start;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import image.ApplicationState;
import image.gui.DragPanner;
import image.gui.Zoomer;
/**
 *
 * @author Matt
 */
public class ImagePane extends JPanel {
    private BufferedImage image;
    private final Zoomer zoom = new Zoomer();
    private final DragPanner panner = new DragPanner();
    
    public ImagePane(ApplicationState applicationState){
        super();
        image = applicationState.getImage();
        
        panner.handleEventsFor(this);
        zoom.handleEventsFor(this);

        applicationState.addImageChangeListener(this::handleImageChanged);
    }
    
    private void handleImageChanged(BufferedImage image) {
        this.image = image;
        setSize(image.getWidth(), image.getHeight());
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.translate(panner.getPanX(), panner.getPanY());
        Graphics2D g2d = (Graphics2D)g;
        g2d.scale(zoom.getZoomFactor(), zoom.getZoomFactor());
        g.drawImage(image, 0, 0, this);
    }
}
