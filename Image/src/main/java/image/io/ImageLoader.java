package image.io;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

    /**
     * Attempts to load the given image, or throws an exception if it fails
     * @param path
     * @return
     */
    public BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new FileInputStream(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
