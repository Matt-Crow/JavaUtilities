package com.mattcrow.Scheduler;

/**
 *
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class WaitingProcessStats extends ProcessStats {
    private int waitTime;
    
    public WaitingProcessStats(Process proc, int waitTime){
        super(proc);
        this.waitTime = waitTime;
    }
    
    public void waitedFor(int time){
        waitTime += time;
    }
    
    public int getWaitTime(){
        return waitTime;
    }
    
    @Override
    public String toString(){
        return String.format("%s, waited %d", super.toString(), waitTime);
    }
}
