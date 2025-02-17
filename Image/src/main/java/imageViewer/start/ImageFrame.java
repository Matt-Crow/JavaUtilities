package imageViewer.start;

import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import image.gui.DragAndDropHandler;

/**
 *
 * @author Matt
 */
public class ImageFrame extends JFrame {
    private final ImagePane pane;

    public ImageFrame(Optional<String> filePath) {
        super();
        setTitle("Drag and drop images in here to view them");

        pane = new ImagePane();
        JScrollPane scroll = new JScrollPane(pane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        ToolBar tb = new ToolBar();
        tb.addFileSelectionListener((f) -> pane.setImage(f.getAbsolutePath()));
        setJMenuBar(tb);

        new DragAndDropHandler(pane)
            .handleDragAndDropFor(this);

        setContentPane(scroll);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        revalidate();

        filePath.ifPresent(path -> pane.setImage(path));

        repaint();
    }
}
