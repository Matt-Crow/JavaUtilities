/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toDoList.gui;

import toDoList.data.Task;

/**
 *
 * @author Matt Crow
 */
public interface TaskCreatedListener {
    public void taskCreated(Task task);
}
