package io;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Matt Crow
 */
public class AudioInput {
    private final TargetDataLine input;
    private final AudioFormat format;
    
    public AudioInput(AudioFormat af) throws LineUnavailableException{
        format = af;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        input = (TargetDataLine)AudioSystem.getLine(info);
    }
    
    public int getBufferSize(){
        return input.getBufferSize();
    }
    
    public void start() throws LineUnavailableException{
        input.open(format);
        input.start();
    }
    
    public void close(){
        input.close();
    }
    
    /**
     * Reads data from this audio input,
     * and fills the buffer.
     * 
     * @param buff the byte array to fill with audio input
     * @return the number of bytes read.
     */
    public int read(byte[] buff){
        int bytesRead = input.read(buff, 0, buff.length);
        return bytesRead;
    }
}
