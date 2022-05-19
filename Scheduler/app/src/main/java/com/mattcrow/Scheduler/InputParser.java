package com.mattcrow.Scheduler;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;

/**
 * Reads input from the command line
 * 
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class InputParser {
    public static List<Process> readProcesses(){
        List<Process> processes = new LinkedList<>();
        
        printPrompt();
        processes.addAll(readAll());
        
        return processes;
    }
    
    private static void printPrompt(){
        String[] lines = {
            "Enter triples: process id, time in ms, and priority:",
            "For example:",
            "1 12 0",
            "3  9 1",
            "2 99 9",
            "process 1 needs 12 ms and has priority 0, very high,",
            "process 3 needs 9 ms and has priority 1.",
            "and so on ..."
        };
        for(String line : lines){
            System.out.println(line);
        }
    }
    
    public static List<Process> readAll(){
        List<Process> ret = new LinkedList<>();
        readAllLines().forEach((s)->{
            // digits, whitespace, digits, whitespace, digits
            Pattern p = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(\\d+)");
            Matcher m = p.matcher(s);
            m.matches(); // need to call this before anything else!
            
            Process proc = new Process(
                    Integer.parseInt(m.group(1)), // group(0) is s
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3))
            );
            ret.add(proc);
        });
        return ret;
    }
    
    public static List<String> readAllLines() {
        List<String> lines = new LinkedList<>();
        
        String curr = "...";
        
        /*
        For the life of me, I CANNOT get Scanner to work!
        */
        
        try (
                InputStream is = InputParser.class.getResourceAsStream("/input.txt");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader in = new BufferedReader(isr);
        ) {
            while(in.ready() && !"".equals(curr.trim())){
                curr = in.readLine();
                if(!curr.isBlank()){
                    lines.add(curr);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(InputParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lines;
    }
}
