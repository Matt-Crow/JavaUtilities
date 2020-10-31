package imageViewer.start;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Matt
 */
public class ImageFrame extends JFrame {
    private final ImagePane pane;
    public ImageFrame(){
        super();
        pane = new ImagePane();
        JScrollPane scroll = new JScrollPane(pane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setContentPane(scroll);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        revalidate();
        repaint();
    }
    
    public final ImagePane getPane(){
        return pane;
    }
}
