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

import image.ApplicationState;
import image.io.ImageLoader;

/**
 * Handles the drag-and-drop event
 */
public class DragAndDropHandler {
    private final ApplicationState applicationState;

    public DragAndDropHandler(ApplicationState applicationState) {
        this.applicationState = applicationState;
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
                        var image = new ImageLoader().loadImage(files.get(0).getAbsolutePath());
                        applicationState.setImage(image);
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
