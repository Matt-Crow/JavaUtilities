package deckBuilder;

import cardPlaces.Deck;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import games.TestGame;

@SuppressWarnings("serial")
public class DeckBuilderPanel extends JPanel{
	private Deck selectedDeck; //make some way to load previous decks
	private JMenuBar menu;
	
	public DeckBuilderPanel(){
		selectedDeck = new Deck("New Deck", 50);
		menu = new JMenuBar();
		add(menu);
		menu.add(new CardMenu(new TestGame(), selectedDeck));
	}
}