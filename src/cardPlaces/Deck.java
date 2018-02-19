package cardPlaces;

import java.util.ArrayList;

import cards.Card;

public class Deck extends AbstractOutOfPlayPlace{
	private String name;
	
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
	public void init(){
		shuffle();
	}
	public void discard(int topX){
		for(int i = 0; i < topX; i++){
			removeCard(getSize() - 1);
		}
	}
	public void discard(){
		discard(1);
	}
}
