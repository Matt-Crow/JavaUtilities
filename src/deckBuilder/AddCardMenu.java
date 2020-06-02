package deckBuilder;

import javax.swing.JMenu;
import cards.Card;
import cardPlaces.Deck;
import games.AbstractGame;

@SuppressWarnings("serial")
public class AddCardMenu extends JMenu{
	public AddCardMenu(AbstractGame a, Deck d){
		super("add");
		for(Card c : a.getCards()){
			add(new AddCard(c, d));
		}
	}
}
