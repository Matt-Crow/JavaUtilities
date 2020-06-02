package deckBuilder;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import cardPlaces.Deck;

@SuppressWarnings("serial")
public class DeckContentViewer extends JScrollPane{
	private JTextArea text;
	private Deck d;
	public DeckContentViewer(Deck deck){
		super();
		d = deck;
		text = new JTextArea(d.getContentsString());
		setViewportView(text);
		text.setEditable(false);
	}
	public void setDeck(Deck newDeck){
		d = newDeck;
	}
	public void update(){
		text.setText(d.getContentsString());
	}
}
