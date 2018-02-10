package cardPlaces;

import cards.Card;
import utilities.Op;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class AbstractCardPlace {
	private ArrayList<Card> cards;
	
	public AbstractCardPlace(){
		cards = new ArrayList<>();
	}
	public AbstractCardPlace(ArrayList<Card> initCards){
		this();
		for(Card c : initCards){
			addCard(c);
		}
	}
	public AbstractCardPlace(AbstractCardPlace a){
		this(a.copy());
	}
	
	public ArrayList<Card> copy(){
		ArrayList<Card> ret = new ArrayList<>();
		
		for(Card c : cards){
			ret.add(new Card(c));
		}
		
		return ret;
	}
	
	//todo: add checking
	public Card getCardAt(int i){
		return cards.get(i);
	}
	public int getSize(){
		return cards.size();
	}
	
	public void addCard(Card c){
		cards.add(new Card(c));
	}
	public void addCard(Card c, int numCopies){
		for(int i = 0; i < numCopies; i++){
			addCard(c);
		}
	}
	public void removeCard(int i){
		cards.remove(i);
	}
	public void removeCard(String n){
		boolean found = false;
		for(int i = 0; i < cards.size() && !found; i++){
			if(cards.get(i).getName() == n){
				found = true;
				cards.remove(i);
			}
		}
	}
	public void removeCard(String n, int copies){
		for(int i = 0; i < copies; i++){
			removeCard(n);
		}
	}
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
	public void displayData(){
		HashMap<String, Integer> quantities = new HashMap<>();
		for(Card c : cards){
			if(quantities.containsKey(c.getName())){
				quantities.put(c.getName(), quantities.get(c.getName()) + 1);
			} else {
				quantities.put(c.getName(), 1);
			}
		}
		
		for(String key : quantities.keySet()){
			Op.log(key + ": x" + quantities.get(key));
		}
	}
}
