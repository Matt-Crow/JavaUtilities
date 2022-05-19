package com.mattcrow.Scheduler;

/**
 *
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class RoundRobinStatistics extends ProcessStats {
    private int timeSlicesUsed;
    private int timeUsed;
    private int overheadTime;
    
    public RoundRobinStatistics(Process p) {
        super(p);
        timeSlicesUsed = 0;
        timeUsed = 0;
        overheadTime = 0;
    }
    
    public void payOverhead(int overhead){
        overheadTime += overhead;
    }
    
    public int run(int time){
        ++timeSlicesUsed;
        int timeNeeded = getProcess().getTime() - timeUsed;
        if(timeNeeded >= time){
            timeUsed += time;
            return time; // used full slice
        } else {
            timeUsed += timeNeeded;
            return timeNeeded;
        }
    }
    
    public boolean isDone(){
        return timeUsed == getProcess().getTime();
    }
    
    public int getTotalTimeUsed(){
        return timeUsed + overheadTime;
    }
    
    @Override
    public String toString(){
        return String.format("%s, %d timeslices", super.toString(), timeSlicesUsed);
    }
}
