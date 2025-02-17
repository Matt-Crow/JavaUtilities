package image;

import javax.swing.JFrame;

import image.gui.DragAndDropHandler;
import image.gui.Whiteboard;
import image.io.ImageLoader;
import imageViewer.start.ImagePane;
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

        var imageFrame = new JFrame();
        imageFrame.setTitle("Image Frame");
        imageFrame.setContentPane(new ImagePane(applicationState));
        init(imageFrame, applicationState);


        var whiteboardFrame = new JFrame();
        whiteboardFrame.setTitle("Whiteboard");
        whiteboardFrame.setContentPane(new Whiteboard(applicationState));
        init(whiteboardFrame, applicationState);
    }
    
    private static void init(JFrame frame, ApplicationState applicationState) {
        frame.setJMenuBar(new ToolBar(applicationState));

        new DragAndDropHandler(applicationState)
            .handleDragAndDropFor(frame);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.revalidate();
        frame.repaint();
    }
}
