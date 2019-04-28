package io;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
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
}
