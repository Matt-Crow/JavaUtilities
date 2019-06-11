package fakenotepad;

import java.io.File;

/**
 *
 * @author Matt
 */
public class FakeNotepad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Frame f = new Frame();
        String filename;
        if(args.length >= 1){
            filename = args[0];
            ((Pane)f.getContentPane()).openFile(new File(filename));
        }
    }
}
