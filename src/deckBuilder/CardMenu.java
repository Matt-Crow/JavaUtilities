package deckBuilder;

import javax.swing.JMenu;
import cards.Card;
import cardPlaces.Deck;
import games.AbstractGame;

@SuppressWarnings("serial")
public class CardMenu extends JMenu{
	
	public CardMenu(AbstractGame a, Deck d){
		super("card");
		for(Card c : a.getCards()){
			add(new CardMenuItem(c, d));
		}
	}
}
