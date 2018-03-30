package deckBuilder;

import cardPlaces.Deck;
import cards.Card;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

@SuppressWarnings("serial")
public class DeckBuilderPanel extends JPanel{
	private Deck selectedDeck; //make some way to load previous decks
	private JMenuBar menu;
	
	public DeckBuilderPanel(){
		selectedDeck = new Deck("New Deck", 50);
		menu = new JMenuBar();
		JMenu add = new JMenu("add card");
		menu.add(add);
		add(menu);
		for(int i = 0; i < 9; i++){
			Card c = new Card("Card #" + i, "I am card #" + i);
			CardMenuItem cm = new CardMenuItem(c, selectedDeck);
			add.add(cm);
		}
	}
}