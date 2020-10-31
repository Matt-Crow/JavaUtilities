package imageViewer.start;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
/**
 *
 * @author Matt
 */
public class ImagePane extends JPanel {
    private BufferedImage img;
    public ImagePane(){
        super();
        setLayout(new GridLayout(1, 1));
    }
    
    public final void setImage(String path){
        try {
            img = ImageIO.read(new FileInputStream(path));
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
        if(img != null){
            g.drawImage(img, 0, 0, this);
        }
    }
}
