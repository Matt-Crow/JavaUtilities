package cards;

import java.util.ArrayList;
import utilities.Op;

public class Hand {
	private ArrayList<Card> hand;
	
	public Hand(){
		hand = new ArrayList<>();
	}
	
	public ArrayList<Card> getCards(){
		ArrayList<Card> ret = new ArrayList<>();
		for(Card c : hand){
			ret.add(new Card(c));
		}
		return ret;
	}
	
	public void addCard(Card c){
		hand.add(c);
	}
	public void removeCard(String n){
		boolean found = false;
		for(int i = 0; i < hand.size() && !found; i++){
			if(hand.get(i).getName() == n){
				found = true;
				hand.remove(i);
			}
		}
	}
	
	public void displayData(){
		Op.log("Cards in hand: ");
		for(Card c : hand){
			Op.log(c.getName());
		}
	}
}
