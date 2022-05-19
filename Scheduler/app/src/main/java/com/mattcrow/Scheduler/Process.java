package com.mattcrow.Scheduler;

/**
 *
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class Process {
    private final int id;
    private final int time;
    private final int priority;
    
    public Process(int id, int time, int priority){
        this.id = id;
        this.time = time;
        this.priority = priority;
    }
    
    public int getId(){
        return id;
    }
    
    public int getTime(){
        return time;
    }
    
    public int getPriority(){
        return priority;
    }
    
    public String asTuple(){
        return String.format("%2d %2d %2d", id, time, priority);
    }
    
    @Override
    public String toString(){
        return asTuple();
    }
}
