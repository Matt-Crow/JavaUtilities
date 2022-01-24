package toDoList.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Matt Crow
 */
public class Panel extends JPanel {
    
    private final JTable taskList;
    private final TaskCreator taskCreator;
    
    public Panel(TaskCreator taskCreator){
        super();
        setLayout(new BorderLayout());
        
        add(new JLabel("Tasks"), BorderLayout.PAGE_START);
        
        taskList = new JTable(new Object[][]{}, new Object[]{
            "subject", "description", "due date"
        });
        taskCreator.addTaskCreatedListener((task)->{
            // how to add row to JTabel?
        });
        
        add(taskList, BorderLayout.CENTER);
        
        add(taskCreator, BorderLayout.PAGE_END);
        
        this.taskCreator = taskCreator;
    }
}
