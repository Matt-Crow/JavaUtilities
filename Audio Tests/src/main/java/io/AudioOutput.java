package io;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Matt Crow
 */
public class AudioOutput {
    private final AudioFormat format;
    private final SourceDataLine output;
    
    public AudioOutput(AudioFormat af) throws LineUnavailableException{
        format = af;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        output = (SourceDataLine)AudioSystem.getLine(info);
    }
    
    public void start() throws LineUnavailableException{
        output.open(format);
        output.start();
    }
    
    public void close(){
        output.drain();
        output.close();
    }
    
    public void write(byte[] buff, int bytesRead){
        output.write(buff, 0, bytesRead);
    }
}
