package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import utilities.Op;

public class Deck {
	private String name;
	private ArrayList<Card> deck; // more efficient way?
	
	public Deck(String s){
		name = s;
		deck = new ArrayList<>();
	}
	public Deck(Deck d){
		name = d.getName();
		deck = d.copyDeck();
	}
	public Deck(String s, ArrayList<Card> d){
		name = s;
		deck = new ArrayList<Card>();
		for(Card c : d){
			deck.add(c);
		}
	}
	public void addCard(Card c){
		deck.add(new Card(c));
	}
	public void addCard(Card c, int num){
		for(int i = 0; i < num; i++){
			addCard(c);
		}
	}
	public void removeCard(String n){
		boolean found = false;
		for(int i = 0; i < deck.size() && !found; i++){
			if(deck.get(i).getName() == n){
				found = true;
				deck.remove(i);
			}
		}
	}
	public void removeCard(String n, int copies){
		for(int i = 0; i < copies; i++){
			removeCard(n);
		}
	}
	
	public String getName(){
		return name;
	}
	public ArrayList<Card> copyDeck(){
		// returns a copy of the deck
		ArrayList<Card> ret = new ArrayList<>();
		
		for(Card c : deck){
			ret.add(new Card(c));
		}
		
		return ret;
	}
	
	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	public void displayData(){
		HashMap<String, Integer> quantities = new HashMap<>();
		for(Card c : deck){
			if(quantities.containsKey(c.getName())){
				quantities.put(c.getName(), quantities.get(c.getName()) + 1);
			} else {
				quantities.put(c.getName(), 1);
			}
		}
		Op.log(name + " deck contents:");
		for(String key : quantities.keySet()){
			Op.log(key + ": x" + quantities.get(key));
		}
	}
}
