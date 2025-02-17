package mattcrow.whiteboard;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class SaveAction {
    private final JComponent saveMe;

    public SaveAction(JComponent saveMe) {
        this.saveMe = saveMe;
    }

    public void saveTo(File file) {
        var bi = new BufferedImage(saveMe.getWidth(), saveMe.getHeight(), BufferedImage.TYPE_INT_ARGB);
        try {
            var g = bi.createGraphics();
            saveMe.paint(g);
            var written = ImageIO.write(bi, "png", file);
            if (!written) {
                System.err.println("Failed to write file for some reason");
            }
            g.dispose();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
