package com.mattcrow.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class HighestPriorityFirst extends AbstractSchedulingAlgorithm {

    public HighestPriorityFirst() {
        super("HPF");
    }

    @Override
    public void run(List<Process> processes){
        List<Process> copy = new ArrayList<>(processes);
        // sort in ascending order (priority 0 > priority 9999)
        copy.sort((p1, p2)->{
            // returning negative means p1 < p2
            return p1.getPriority() - p2.getPriority();
        });
        super.run(copy);
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
