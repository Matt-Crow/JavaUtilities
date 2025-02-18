package image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import image.gui.DragPanner;
import image.gui.MouseTool;

/**
 * The current state of the application
 */
public class ApplicationState {

    private BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    private MouseTool mouseTool = new DragPanner();
    private List<Consumer<BufferedImage>> imageChangeListeners = new ArrayList<>();

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        imageChangeListeners.forEach(x -> x.accept(image));
    }

    public void addImageChangeListener(Consumer<BufferedImage> imageChangeListener) {
        imageChangeListeners.add(imageChangeListener);
    }

    public MouseTool getMouseTool() {
        return mouseTool;
    }

    public void setMouseTool(MouseTool mouseTool) {
        this.mouseTool = mouseTool;
    }
}
