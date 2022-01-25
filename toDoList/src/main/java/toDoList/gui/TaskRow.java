package toDoList.gui;

import javax.swing.JButton;
import toDoList.data.Task;

/**
 *
 * @author Matt Crow
 */
class TaskRow {
    private final Task task;
    private final JButton deleteThisTask;
    
    TaskRow(Task task, JButton deleteThisTask){
        this.task = task;
        this.deleteThisTask = deleteThisTask;
    }
    
    public final Task getTask(){
        return task;
    }
    
    public final JButton getDeleteButton(){
        return deleteThisTask;
    }
}
