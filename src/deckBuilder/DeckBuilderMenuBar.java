package deckBuilder;

import javax.swing.JMenuBar;
import cardPlaces.Deck;
import games.*;

@SuppressWarnings("serial")
public class DeckBuilderMenuBar extends JMenuBar{
	/**
	 * The DeckBuilderMenuBar is used to contain
	 * all the menu options associated with the
	 * deck builder.
	 */
	private AbstractGame selectedGame; // the game the user is building a deck for
	private Deck selectedDeck; // the current deck that is being built 
	//TODO: make some way to load previous decks
	private AddCardMenu addCard;
	
	public DeckBuilderMenuBar(Deck d){
		selectedGame = new TestGame(); // TODO: change this
		selectedDeck = d;
		addCard = new AddCardMenu(selectedGame, selectedDeck);
		add(addCard);
	}
}
