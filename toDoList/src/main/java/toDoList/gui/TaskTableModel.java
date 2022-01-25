package toDoList.gui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import toDoList.data.Task;

/**
 * https://stackoverflow.com/questions/13833688/adding-jbutton-to-jtable
 * @author Matt Crow
 */
public class TaskTableModel extends AbstractTableModel implements TableCellRenderer{
    private static final String[] COLUMNS = new String[]{
        "due date", "subject", "description", "delete?"
    };
    private static final Class<?>[] CLASSES = new Class<?>[]{
        Date.class, String.class, String.class, JButton.class
    };
    
    
    private final List<TaskRow> tasks;
    
    protected TaskTableModel(){
        super();
        tasks = new ArrayList<>();
    }
    
    public final void addTask(Task t){
        JButton deleteThis = new JButton("delete");
        TaskRow row = new TaskRow(t, deleteThis); 
        deleteThis.addActionListener((e)->{
            tasks.remove(row);
        });
        tasks.add(row);
    }
    
    @Override
    public String getColumnName(int column){
        return COLUMNS[column];
    }
    
    @Override
    public Class<?> getColumnClass(int column){
        return CLASSES[column];
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
                return tasks.get(rowIndex).getTask().getDueDate();
                // don't need break because of return
            case 1:
                return tasks.get(rowIndex).getTask().getSubject();
            case 2:
                return tasks.get(rowIndex).getTask().getDescription();
            case 3:
                return tasks.get(rowIndex).getDeleteButton();
            default:
                throw new IndexOutOfBoundsException(String.format("invalid column: %d", columnIndex));
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        /*
        this would need to check column if this class were added as a 
        TableCellRenderer in other columns
        */
        return tasks.get(row).getDeleteButton();
    }
    
    public final void click(int rowIdx, int colIdx){
        if(rowIdx >= tasks.size()){
            return;
        }
        
        if(colIdx == 3){ // don't like this
            tasks.get(rowIdx).getDeleteButton().doClick();
        }
    }

}
