package toDoList.gui;

import javax.swing.JTable;
import toDoList.data.Task;

/**
 *
 * @author Matt Crow
 */
public class TaskTable extends JTable{
    private final TaskTableModel model;
    
    public TaskTable(TaskTableModel model){
        super(model);
        this.model = model;
        //setAutoCreateRowSorter(true);
        addMouseListener(new TaskTableClickHandler(this));
        
        // don't like this
        getColumn("delete?").setCellRenderer(model);
    }
    
    public final void addTask(Task t){
        model.addTask(t);
    }
    
    public final void click(int row, int col){
        model.click(row, col);
    }
}
