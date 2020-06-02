package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import misc.Point;

/**
 * Generates .mwb files.
 * That stands for Matt's White Board
 * @author Matt
 */
public class MWBFile extends File{
    public MWBFile(String name){
        super(name);
    }
    
    public final LinkedList<Point> read(){
        LinkedList<Point> points = new LinkedList<>();
        try{
            BufferedReader b = new BufferedReader(new FileReader(this));
            Point p;
            String line;
            String[] split;
            while(b.ready()){
                line = b.readLine();
                split = line.split(",");
                try{
                    split[0] = split[0].replace('(', ' ').trim();
                    split[1] = split[1].replace(')', ' ').trim();
                    p = new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    points.add(p);
                } catch(Exception e){
                    System.out.println("Line fail: " + line);
                    e.printStackTrace();
                }
            }
            
        } catch (IOException e){
            e.printStackTrace();
        }
        return points;
    }
    
    public final void write(LinkedList<Point> points){
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(this));
            Iterator<Point> i = points.iterator();
            while(i.hasNext()){
                b.write(i.next().toString());
                b.newLine();
            }
            b.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //can't get regex to work
    public static void main(String[] args){
        String ip = " ";
        Scanner s = new Scanner(System.in);
        while(!ip.equals("q")){
            ip = s.nextLine().trim();
            System.out.println(Pattern.matches("m+", ip));
        }
    }
}
