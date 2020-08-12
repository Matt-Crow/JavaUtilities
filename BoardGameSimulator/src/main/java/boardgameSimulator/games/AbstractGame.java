package boardgameSimulator.games;

import boardgameSimulator.cards.Card;
import java.util.ArrayList;

public abstract class AbstractGame {
	private ArrayList<Card> allCards;
	
	AbstractGame(){
		allCards = new ArrayList<>();
	}
	public void addCard(Card c){
		if(!contains(c.getName())){
			allCards.add(new Card(c));
		}
	}
	public boolean contains(String n){
		boolean found = false;
		for(int i = 0; i < allCards.size() && !found; i++){
			if(allCards.get(i).getName() == n){
				found = true;
			}
		}
		return found;
	}
	public Card[] getCards(){
		Card[] ret = new Card[allCards.size()];
		for(int i = 0; i < allCards.size(); i++){
			ret[i] = new Card(allCards.get(i));
		}
		return ret;
	}
}
