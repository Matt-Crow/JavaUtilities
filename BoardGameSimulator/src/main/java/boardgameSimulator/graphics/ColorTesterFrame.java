package boardgameSimulator.graphics;

import javax.swing.JFrame;

public class ColorTesterFrame extends JFrame{
	public ColorTesterFrame(){
		super();
		setTitle("Color Tester");
		setContentPane(new ColorTesterPanel());
		setVisible(true);
		setSize(256, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
