package toDoList.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import toDoList.data.Task;

/**
 *
 * @author Matt Crow
 */
public class TaskTableModel extends AbstractTableModel {
    private final List<Task> tasks;
    
    protected TaskTableModel(){
        super();
        tasks = new ArrayList<>();
    }
    
    public final void addTask(Task t){
        tasks.add(t);
    }
    
    @Override
    public String getColumnName(int column){
        String[] names = new String[]{
            "due date", "subject", "description"
        };
        return names[column];
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return tasks.get(rowIndex).getDueDate();
                // don't need break because of return
            case 1:
                return tasks.get(rowIndex).getSubject();
            case 2:
                return tasks.get(rowIndex).getDescription();
            default:
                throw new IndexOutOfBoundsException(String.format("invalid column: %d", columnIndex));
        }
    }

}
