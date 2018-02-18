package cardPlaces;

import cards.Card;
import utilities.Op;
import utilities.DeckStructure;

import java.util.ArrayList;
import java.util.HashMap;

// what sort of data structure should I use?
public abstract class AbstractCardPlace {
	private DeckStructure cards;
	
	public AbstractCardPlace(){
		
	}
	public void setSize(int s){
		cards = new DeckStructure(s);
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
	
	// setters and getters
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
	
	// add cards
	public void addCard(Card c){
		cards.add(new Card(c));
	}
	public void addCard(Card c, int numCopies){
		for(int i = 0; i < numCopies; i++){
			addCard(c);
		}
	}
	
	// remove cards
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
	
	// searching
	public boolean checkIfContains(String name){
		return checkIfContains(name, cards.size());
	}
	public boolean checkIfContains(String name, int topX){
		boolean found = false;
		for(int i = 0; i < topX && !found; i++){
			if(cards.get(i).getName() == name){
				found = true;
			}
		}
		return found;
	}
	
	// getting cards
	public ArrayList<Card> getTopX(int x){
		ArrayList<Card> topX = new ArrayList<>();
		for(int i = 0; i < x && i < cards.size(); i++){
			topX.add(cards.get(i));
		}
		return topX;
	}
	

	public void shuffle(){
		cards.shuffle();
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
