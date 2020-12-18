package imageViewer.start;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.function.BiConsumer;

/**
 *
 * @author Matt
 */
class DragPanner extends MouseAdapter {
    private final LinkedList<BiConsumer<Integer, Integer>> deltaListeners;
    private int prevX;
    private int prevY;
    DragPanner(){
        super();
        prevX = -1;
        prevY = -1;
        deltaListeners = new LinkedList<>();
    }
    
    public void addDeltaListener(BiConsumer<Integer, Integer> listener){
        deltaListeners.add(listener);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        prevX = e.getX();
        prevY = e.getY();
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        int dx = e.getX() - prevX;
        int dy = e.getY() - prevY;
        prevX = e.getX();
        prevY = e.getY();
        deltaListeners.forEach((listener)->listener.accept(dx, dy));
    }
}
