package matrix;

import javax.swing.JFrame;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pane p = new Pane();
        if(args.length == 1){
            p.trySetImg(args[0]);
        } else {
            System.err.println("Need to pass path to image file as first and only argument!");
        }
        JFrame frame = new JFrame();
        frame.setContentPane(p);
        frame.setTitle("Matrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }
}
