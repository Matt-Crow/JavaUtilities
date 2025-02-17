package imageViewer.start;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import image.ApplicationState;
/**
 *
 * @author Matt
 */
public class ImagePane extends JPanel {
    private BufferedImage image;
    private int panX;
    private int panY;
    private double zoomFactor;
    
    public ImagePane(ApplicationState applicationState){
        super();
        image = applicationState.getImage();
        panX = 0;
        panY = 0;
        zoomFactor = 1.0;
        DragPanner panner = new DragPanner();
        panner.addDeltaListener((dx, dy)->{
            panX += dx;
            panY += dy;
            repaint();
        });
        
        // need to add panner as both mouse listener and motion listener
        addMouseListener(panner);
        addMouseMotionListener(panner);
        
        Zoomer zoom = new Zoomer();
        zoom.addScrollListener((dTheta)->{
            zoomFactor += dTheta;
            repaint();
        });
        addMouseWheelListener(zoom);

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
        Graphics2D g2d = (Graphics2D)g;
        g.translate(panX, panY);
        g2d.scale(zoomFactor, zoomFactor);
        g.drawImage(image, 0, 0, this);
    }
}
