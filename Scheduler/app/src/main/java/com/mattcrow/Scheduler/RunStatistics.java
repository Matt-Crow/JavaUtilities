package com.mattcrow.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class RunStatistics {
    private final List<ProcessStats> proc;
    private int totalTime;
    
    public RunStatistics(){
        proc = new ArrayList<>();
        totalTime = 0;
    }
    
    public void addProcessStatistics(ProcessStats stats, int time){
        proc.add(stats);
        totalTime += time;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int totalWait = 0;
        int totalTurnAround = 0;
        boolean trackWait = false;
        sb.append("* * * STATS * * *\n");
        for(ProcessStats p : proc){
            sb.append(String.format("* %s%n", p.toString()));
            totalTurnAround += p.getTurnAroundTime();
            if(p instanceof WaitingProcessStats){
                totalWait += ((WaitingProcessStats)p).getWaitTime();
                trackWait = true;
            }
        }
        if(trackWait){
            sb.append(String.format("* average wait: %f%n", ((double)totalWait) / proc.size()));
        }
        sb.append(String.format("* average turn around: %f%n", ((double)totalTurnAround) / proc.size()));
        sb.append(String.format("* throughput: %f%n", ((double)proc.size()) / totalTime));
        return sb.toString();
    }
}
