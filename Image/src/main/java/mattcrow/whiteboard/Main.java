package mattcrow.whiteboard;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setTitle("Whiteboard");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Whiteboard());
        frame.setVisible(true);
    }
}
