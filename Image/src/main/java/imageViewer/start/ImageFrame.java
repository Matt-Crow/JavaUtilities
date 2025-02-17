package imageViewer.start;

import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import image.ApplicationState;
import image.gui.DragAndDropHandler;
import image.io.ImageLoader;

/**
 *
 * @author Matt
 */
public class ImageFrame extends JFrame {
    private final ImagePane pane;
    private final ApplicationState applicationState;

    public ImageFrame(ApplicationState applicationState, Optional<String> filePath) {
        super();
        this.applicationState = applicationState;

        setTitle("Drag and drop images in here to view them");

        pane = new ImagePane(applicationState);
        JScrollPane scroll = new JScrollPane(pane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        ToolBar tb = new ToolBar();
        tb.addFileSelectionListener((f) -> setImage(f.getAbsolutePath()));
        setJMenuBar(tb);

        new DragAndDropHandler(applicationState)
            .handleDragAndDropFor(this);

        setContentPane(scroll);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        revalidate();

        filePath.ifPresent(path -> setImage(path));

        repaint();
    }

    private void setImage(String path) {
        applicationState.setImage(new ImageLoader().loadImage(path));
    }
}
