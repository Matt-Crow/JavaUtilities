/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toDoList.data;

import java.util.Date;

/**
 * @author Matt
 */
public class Task {
    
    private final String subject;
    private final String description;
    private final Date dueDate;

    public Task(String subject, String description, Date dueDate){
        this.subject = subject;
        this.description = description;
        this.dueDate = dueDate;
    }

    public final String getSubject(){
        return subject;
    }

    public final String getDescription(){
        return description;
    }

    public final Date getDueDate(){
        return dueDate;
    }
}
