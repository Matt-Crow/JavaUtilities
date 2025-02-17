package toDoList.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Matt Crow
 */
public class Panel extends JPanel {
    
    private final TaskTable taskList;
    
    public Panel(TaskCreator taskCreator, TaskTableModel model){
        super();
        setLayout(new BorderLayout());
        
        add(new JLabel("Tasks"), BorderLayout.PAGE_START);
        
        taskList = new TaskTable(model);
        taskCreator.addTaskCreatedListener((task)->{
            taskList.addTask(task);
            repaint();
        });
        
        JScrollPane scroll = new JScrollPane(taskList);
        taskList.setFillsViewportHeight(true);
        
        add(scroll, BorderLayout.CENTER);
        
        add(taskCreator, BorderLayout.PAGE_END);
    }
}
