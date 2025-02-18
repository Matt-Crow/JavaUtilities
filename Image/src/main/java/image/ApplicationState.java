package image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import image.gui.DragPanner;
import image.gui.DrawTool;
import image.gui.MouseTool;
import image.gui.Zoomer;

/**
 * The current state of the application
 */
public class ApplicationState {

    // note: classes should reference this instead of creating their own so they can share panning info
    private final DragPanner pannerTool = new DragPanner();
    private final DrawTool drawTool;
    private final Zoomer zoomTool = new Zoomer();

    private BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    private MouseTool mouseTool = pannerTool;
    private List<Consumer<BufferedImage>> imageChangeListeners = new ArrayList<>();

    public ApplicationState() {
        drawTool = new DrawTool(this);
    }

    public DragPanner getPannerTool() {
        return pannerTool;
    }

    public DrawTool getDrawTool() {
        return drawTool;
    }

    public Zoomer getZoomTool() {
        return zoomTool;
    }

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
