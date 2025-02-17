package imageViewer.start;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 *
 * @author Matt
 */
public class ImagePane extends JPanel {
    private BufferedImage img;
    private int panX;
    private int panY;
    private double zoomFactor;
    
    public ImagePane(){
        super();
        //setLayout(new GridLayout(1, 1));
        img = null;
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
    }
    
    public final void setImage(String path){
        try {
            img = ImageIO.read(new FileInputStream(path));
            this.setSize(img.getWidth(), img.getHeight());
            repaint();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if(img != null){
            g.translate(panX, panY);
            g2d.scale(zoomFactor, zoomFactor);
            g.drawImage(img, 0, 0, this);
        }
    }
}
