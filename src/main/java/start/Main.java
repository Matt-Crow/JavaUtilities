package start;

import io.AudioInput;
import io.AudioOutput;
import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Matt
 */
public class Main {
    public static void main(String[] args){
        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
        
        try {
            AudioInput microphone = new AudioInput(format);
            AudioOutput speakers = new AudioOutput(format);
            
            microphone.start();
            speakers.start();
            
            int i = 0;
            int numBytesRead = 0;
            byte[] buff = new byte[microphone.getBufferSize()];
            while(i < 99999){
                numBytesRead = microphone.read(buff);
                speakers.write(buff, numBytesRead);
                i++;
            }
            microphone.close();
            speakers.close();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
        
    }
}
