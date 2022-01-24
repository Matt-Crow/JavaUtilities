/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toDoList.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import toDoList.data.Task;


/**
 *
 * @author Matt Crow
 */
public class TaskCreator extends JComponent implements ActionListener {
    private final List<TaskCreatedListener> taskCreationListeners;
    
    private final JButton createButton;
    private final JLabel datePicker;
    private final JTextField subjectField;
    private final JTextField descriptionField;
    
    public TaskCreator(){
        super();
        setLayout(new FlowLayout());
        taskCreationListeners = new LinkedList<>();
        
        datePicker = new JLabel("TODO: date picker");
        add(datePicker);
        
        subjectField = new JTextField();
        subjectField.setToolTipText("enter subject");
        subjectField.setColumns(10);
        add(subjectField);
        
        descriptionField = new JTextField();
        descriptionField.setToolTipText("enter description");
        descriptionField.setColumns(30);
        add(descriptionField);
        
        createButton = new JButton("Add task to list");
        createButton.addActionListener(this);
        add(createButton);
    }
    
    public final void addTaskCreatedListener(TaskCreatedListener listener){
        taskCreationListeners.add(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Task newTask = new Task(subjectField.getText(), descriptionField.getText(), new Date());
        taskCreationListeners.forEach((listener)->listener.taskCreated(newTask));
    }
}
