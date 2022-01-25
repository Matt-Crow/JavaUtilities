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
    private static final String[] COLUMNS = new String[]{
        "due date", "subject", "description", "delete?"
    };
    
    
    private final List<Task> tasks;
    
    protected TaskTableModel(){
        super();
        tasks = new ArrayList<>();
    }
    
    public final void addTask(Task t){
        tasks.add(t);
        System.out.println();
    }
    
    @Override
    public String getColumnName(int column){
        return COLUMNS[column];
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
        return COLUMNS.length;
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
            case 3:
                return new Boolean(false);
            default:
                throw new IndexOutOfBoundsException(String.format("invalid column: %d", columnIndex));
        }
    }

}
