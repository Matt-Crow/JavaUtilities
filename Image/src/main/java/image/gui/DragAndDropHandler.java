package image.gui;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import imageViewer.start.ImagePane;

/**
 * Handles the drag-and-drop event
 */
public class DragAndDropHandler {
    private final ImagePane pane;

    public DragAndDropHandler(ImagePane pane) {
        this.pane = pane;
    }

    /**
     * Registers this to handle drag-and-drop onto the given component.
     * @param component the component this should handle drag-and-drop for.
     */
    public void handleDragAndDropFor(Component component) {
        var dropAdapter = new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                Transferable tf = dtde.getTransferable();
                try {
                    @SuppressWarnings("unchecked")
                    var files = (List<File>) tf.getTransferData(DataFlavor.javaFileListFlavor);

                    if (files.size() >= 1) {
                        pane.setImage(files.get(0).getAbsolutePath());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedFlavorException ex) {
                    ex.printStackTrace();
                }
            }
        };
        new DropTarget(component, dropAdapter);
    }
}
