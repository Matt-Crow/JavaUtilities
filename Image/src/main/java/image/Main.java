package image;

import javax.swing.JFrame;

import image.gui.DragAndDropHandler;
import image.gui.ImagePainter;
import image.io.ImageLoader;
import imageViewer.start.ToolBar;

public class Main {

    /**
     * Runs the Image application
     * @param args if specified, the first arg is used as a path to the image to view and edit.
     */
    public static void main(String[] args) {
        // TODO handle resizing, probably NOT on resizing the window, as that might mess with panzoom
        var applicationState = new ApplicationState();

        // load any image the user passed to the arguments
        if (args.length >= 1) {
            var image = new ImageLoader().loadImage(args[0]);
            applicationState.setImage(image);
        }

        var frame = new JFrame();
        frame.setTitle("Whiteboard");
        frame.setJMenuBar(new ToolBar(applicationState));
        frame.setContentPane(new ImagePainter(applicationState));

        new DragAndDropHandler(applicationState)
            .handleDragAndDropFor(frame);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.revalidate();
        frame.repaint();
    }
}
