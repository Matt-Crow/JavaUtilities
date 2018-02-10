package cardPlaces;

import java.util.ArrayList;

import cards.Card;

public class Deck extends AbstractOutOfPlayPlace{
	private String name;
	private DiscardPile discardPile; 
	
	public Deck(String s){
		super();
		name = s;
	}
	public Deck(String s, ArrayList<Card> c){
		super(c);
		name = s;
	}
	public Deck(Deck d){
		super(d);
		name = d.getName();
	}
	
	public String getName(){
		return name;
	}
	public DiscardPile getDiscardPile(){
		return discardPile;
	}
	
	public void init(){
		discardPile = new DiscardPile(this);
		shuffle();
	}
	public void discard(int topX){
		for(int i = 0; i < topX; i++){
			discardPile.addCard(getCardAt(getSize() - 1));
			removeCard(getSize() - 1);
		}
	}
	public void discard(){
		discard(1);
	}
}
