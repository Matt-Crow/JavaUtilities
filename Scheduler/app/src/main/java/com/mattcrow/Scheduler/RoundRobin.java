package com.mattcrow.Scheduler;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class RoundRobin extends AbstractSchedulingAlgorithm {
    private final int timeSlice;
    private final int overhead;
    
    public RoundRobin(int timeSlice, int overhead) {
        super(String.format("RR with q=%d, o=%d", timeSlice, overhead));
        this.timeSlice = timeSlice;
        this.overhead = overhead;
    }

    @Override
    protected RunStatistics doRun(List<Process> processes) {
        RunStatistics all = new RunStatistics();
        int timeElapsed = 0;
        boolean moreToProcess = true;
        RoundRobinStatistics rrs;
        
        HashMap<Process, RoundRobinStatistics> stats = new HashMap<>();
        for(Process p : processes){
            stats.put(p, new RoundRobinStatistics(p));
        }
        
        int t;
        boolean firstTimeAround = true;
        
        while(moreToProcess){
            moreToProcess = false;
            for(Process p : processes){
                rrs = stats.get(p);
                if(!rrs.isDone()){
                    if(firstTimeAround){
                        firstTimeAround = false;
                    } else {
                        rrs.payOverhead(overhead); // need to load process
                        timeElapsed += overhead;
                    }
                    t = rrs.run(timeSlice);
                    timeElapsed += t;
                    if(rrs.isDone()){
                        rrs.setTurnAroundTime(timeElapsed);
                        all.addProcessStatistics(rrs, rrs.getTotalTimeUsed());
                    } else {
                        //rrs.payOverhead(overhead); // need to save process
                        //timeElapsed += overhead;
                        moreToProcess = true;
                    }
                }
            }
            firstTimeAround = false;
        }
        
        return all;
    }
}
