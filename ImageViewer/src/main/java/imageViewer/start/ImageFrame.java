package imageViewer.start;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Matt
 */
public class ImageFrame extends JFrame implements DropTargetListener {
    private final ImagePane pane;
    public ImageFrame(){
        super();
        setTitle("Drag and drop images in here to view them");
        
        pane = new ImagePane();
        JScrollPane scroll = new JScrollPane(pane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        ToolBar tb = new ToolBar();
        tb.addFileSelectionListener((f)->pane.setImage(f.getAbsolutePath()));
        setJMenuBar(tb);
        
        new DropTarget(this, this);
        
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

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {}

    @Override
    public void dragOver(DropTargetDragEvent dtde) {}

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {}

    @Override
    public void dragExit(DropTargetEvent dte) {}

    @Override
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable tf = dtde.getTransferable();
        for(DataFlavor flavor : tf.getTransferDataFlavors()){
            System.out.println(flavor.toString());
            if(flavor.isFlavorJavaFileListType()){
                try {
                    List<File> files = (List<File>) tf.getTransferData(flavor);
                    if(files.size() >= 1){
                        System.out.println(files.get(0));
                        pane.setImage(files.get(0).getAbsolutePath());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedFlavorException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
