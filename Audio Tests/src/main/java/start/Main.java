package start;

import gui.Frame;
import io.AudioInput;
import io.AudioOutput;
import java.net.SocketException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author Matt
 */
public class Main {
    public static void main(String[] args) throws SocketException, LineUnavailableException{
        new Frame();
        /*
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
        */
    }
}
