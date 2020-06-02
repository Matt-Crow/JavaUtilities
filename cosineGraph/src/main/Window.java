package main;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public Window(){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
		setContentPane(new Draw());
	}
}
