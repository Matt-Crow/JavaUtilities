package matrix;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Matt
 */
public class Pane extends JPanel {
    private BufferedImage img;
    private final Timer t;
    private final AffineTransform matrix;
    public Pane(){
        super();
        img = null;
        t = new Timer(500, (e)->updateMatrix());
        matrix = new AffineTransform();
    }
    
    public final void trySetImg(String path){
        try {
            img = ImageIO.read(new FileInputStream(new File(path)));
            t.start();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void updateMatrix(){
        matrix.shear(0.0001, 0.0001);
        matrix.translate(1, -1);
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(img != null){
            ((Graphics2D)g).setTransform(matrix);
            g.drawImage(img, 0, 0, null);
            matrix.shear(matrix.getShearX() + 0.01, matrix.getShearY() - 0.01);
        }
    }
}
