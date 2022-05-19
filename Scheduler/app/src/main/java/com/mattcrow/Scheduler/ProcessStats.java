package com.mattcrow.Scheduler;

/**
 * Records the statistics of an individual process
 * 
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class ProcessStats {
    private final Process p;
    private int turnAroundTime;
    
    public ProcessStats(Process p, int turnAroundTime){
        this.p = p;
        this.turnAroundTime = turnAroundTime;
    }
    
    public ProcessStats(Process p){
        this(p, 0);
    }
    
    
    protected Process getProcess(){
        return p;
    }
    
    public ProcessStats setTurnAroundTime(int turnAroundTime){
        this.turnAroundTime = turnAroundTime;
        return this;
    }
    
    public int getTurnAroundTime(){
        return turnAroundTime;
    }
    
    @Override
    public String toString(){
        return String.format("%s: done in %d", p.toString(), turnAroundTime);
    }
}
