package toDoList.gui;

import javax.swing.JTable;
import toDoList.data.Task;

/**
 *
 * @author Matt Crow
 */
public class TaskTable extends JTable {
    private final TaskTableModel model;
    
    public TaskTable(TaskTableModel model){
        super(model);
        this.model = model;
        setAutoCreateRowSorter(true);
    }
    
    public final void addTask(Task t){
        model.addTask(t);
    }
}
