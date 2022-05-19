package com.mattcrow.Scheduler;

import java.util.List;

/**
 *
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public abstract class AbstractSchedulingAlgorithm {
    private final String abrev;
    
    public AbstractSchedulingAlgorithm(String abrev){
        this.abrev = abrev;
    }
    
    public void run(List<Process> processes){
        System.out.printf("Process list in %s order:%n", abrev);
        processes.forEach((p)->System.out.println(p.asTuple()));
        System.out.println("End of list.");
        System.out.println();
        
        RunStatistics stats = doRun(processes);
        
        System.out.println(stats);
        
        System.out.printf("End of %s.%n", abrev);
    }
    
    /**
     * don't call this directly
     * 
     * @param processes
     * @return 
     */
    protected abstract RunStatistics doRun(List<Process> processes);
}
