package image.gui;

import java.awt.event.MouseEvent;

/**
 * A user can have one MouseTool active at a time
 */
public interface MouseTool {

    public void handleMousePressed(MouseEvent e);

    public void handleMouseDragged(MouseEvent e);
}
