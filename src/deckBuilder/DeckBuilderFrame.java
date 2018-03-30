package deckBuilder;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DeckBuilderFrame extends JFrame{
	public DeckBuilderFrame(){
		super();
		setTitle("Deckbuilder");
		setContentPane(new DeckBuilderPanel());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
