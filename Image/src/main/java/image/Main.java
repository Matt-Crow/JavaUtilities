package image;

import java.awt.image.BufferedImage;
import java.util.Optional;

import javax.swing.JFrame;

import image.gui.Whiteboard;
import imageViewer.start.ImageFrame;

public class Main {

    /**
     * Runs the Image application
     * @param args if specified, the first arg is used as a path to the image to view and edit.
     */
    public static void main(String[] args) {
        var filePath = (args.length == 0) 
            ? Optional.<String>empty() 
            : Optional.of(args[0]);
        new ImageFrame(filePath);

        // todo handle resizing, probably NOT on resizing the window, as that might mess with panzoom
        var buffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);

        var frame = new JFrame();
        frame.setTitle("Whiteboard");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Whiteboard(buffer));
        frame.setVisible(true);
    }    
}
