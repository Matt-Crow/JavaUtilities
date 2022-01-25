package toDoList.data;

import java.util.Date;

/**
 * @author Matt Crow
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
