package com.mattcrow.Scheduler;

import java.util.List;

/**
 *
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class FirstComeFirstServed extends AbstractSchedulingAlgorithm {

    public FirstComeFirstServed() {
        super("FCFS");
    }

    @Override
    protected RunStatistics doRun(List<Process> processes) {
        RunStatistics all = new RunStatistics();
        int timeElapsed = 0;
        WaitingProcessStats s;
        for(Process p : processes){
            s = new WaitingProcessStats(p, timeElapsed);
            s.setTurnAroundTime(timeElapsed + p.getTime());
            all.addProcessStatistics(
                    s, 
                    p.getTime()
            );
            timeElapsed += p.getTime();
        }
        
        return all;
    }
}
