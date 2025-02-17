package imageViewer.start;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ImageFrame frame = new ImageFrame();
        if(args.length >= 1){
            frame.getPane().setImage(args[0]);
        }
    }
}
