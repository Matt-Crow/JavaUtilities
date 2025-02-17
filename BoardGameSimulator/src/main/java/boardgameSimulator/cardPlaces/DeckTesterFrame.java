package boardgameSimulator.cardPlaces;

import javax.swing.JFrame;

public class DeckTesterFrame extends JFrame{
	public DeckTesterFrame(){
		super();
		setTitle("Color Tester");
		setContentPane(new DeckTesterPanel());
		setVisible(true);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
