/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toDoList.gui;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author Matt
 */
public class TaskCreator extends JComponent implements ActionListener {
    private final JButton createButton;
    
    public TaskCreator(){
        createButton = new JButton("Add task to list");
    }
}
