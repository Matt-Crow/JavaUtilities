package toDoList.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * https://stackoverflow.com/questions/13833688/adding-jbutton-to-jtable
 * @author Matt Crow
 */
public class TaskTableClickHandler extends MouseAdapter {
    private final TaskTable table;
    
    public TaskTableClickHandler(TaskTable table){
        this.table = table;
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        int colIdx = table.getColumnModel().getColumnIndexAtX(e.getX());
        int rowIdx = e.getY() / table.getRowHeight();
        table.click(rowIdx, colIdx);
        table.repaint();
    }
}
