package boardgameSimulator.deckBuilder;

import javax.swing.JMenu;
import boardgameSimulator.cards.Card;
import boardgameSimulator.cardPlaces.Deck;
import boardgameSimulator.games.AbstractGame;

public class AddCardMenu extends JMenu{
	public AddCardMenu(AbstractGame a, Deck d){
		super("add");
		for(Card c : a.getCards()){
			add(new AddCard(c, d));
		}
	}
}
