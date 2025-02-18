package imageViewer.start;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import image.ApplicationState;
import image.gui.Zoomer;
/**
 *
 * @author Matt
 */
public class ImagePane extends JPanel {
    private BufferedImage image;
    private int panX;
    private int panY;
    private final Zoomer zoom = new Zoomer();
    
    public ImagePane(ApplicationState applicationState){
        super();
        image = applicationState.getImage();
        panX = 0;
        panY = 0;
        DragPanner panner = new DragPanner();
        panner.addDeltaListener((dx, dy)->{
            panX += dx;
            panY += dy;
            repaint();
        });
        
        // need to add panner as both mouse listener and motion listener
        addMouseListener(panner);
        addMouseMotionListener(panner);
        
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
        g.translate(panX, panY);
        Graphics2D g2d = (Graphics2D)g;
        g2d.scale(zoom.getZoomFactor(), zoom.getZoomFactor());
        g.drawImage(image, 0, 0, this);
    }
}
