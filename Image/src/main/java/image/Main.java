package image;

import java.util.Optional;

import imageViewer.start.ImageFrame;

public class Main {

    /**
     * Runs the Image application
     * @param args if specified, the first arg is used as a path to the image to view and edit.
     */
    public static void main(String[] args) {
        var filePath = (args.length == 0) 
            ? Optional.<String>empty() 
            : Optional.of(args[0]);
        new ImageFrame(filePath);
    }    
}
