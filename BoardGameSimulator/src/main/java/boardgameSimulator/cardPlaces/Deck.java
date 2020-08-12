package boardgameSimulator.cardPlaces;

import java.lang.System;
import java.util.Iterator;
import java.util.Random;
import java.util.HashMap;

import boardgameSimulator.cards.Card;
import boardgameSimulator.cards.PlayState;
import boardgameSimulator.deckBuilder.DeckContentViewer;
import boardgameSimulator.utilities.Op;

public class Deck implements Iterable<Card>{
	/*
	 * DeckStructure is a pair of arrays that are meant
	 * to provide less strain on the system than an
	 * ArrayList.
	 * 
	 * How it works:
	 * -Two arrays: contents and sortNumbers
	 * --contents are never removed
	 * --sortNumbers is the 'index' of the adjacent content, -1 means it is removed
	 * -Four sizes:
	 * --maxSize is the size of each array
	 * --currentSize is the number of elements in contents
	 * --accessibleSize is the number of defined, non-negative elements in sortNumbers
	 */
	
	private String name;
	
	private int maxSize;
	private int currentSize;
	private int accessibleSize;
	
	private Card[] contents;
	private int[] sortNumbers;
	
	private DeckContentViewer contentViewer;
	
	public Deck(String n, int max, Card[] c, int[] sort){
		name = n;
		maxSize = max;
		currentSize = 0;
		accessibleSize = 0;
		contents = new Card[max];
		sortNumbers = new int[max];
		boolean error = false;
		
		for(int i = 0; i < max && !error; i++){
			try{
				contents[i] = new Card(c[i]);
				sortNumbers[i] = sort[i];
				currentSize++;
				if(sort[i] >= 0){
					accessibleSize++;
				}
			}catch(NullPointerException e){
				Op.log("Parameters 'c' or 'sort' did not contain enough elements while invoking Deck constructor");
				e.printStackTrace();
				error = true;
			}
		}
		fill();
	}
	public Deck(String n, int max){
		this(n, max, new Card[max], new int[max]);
	}
	public Deck(Deck d){
		this(d.getName(), d.maxSize, d.contents, d.sortNumbers);
	}
	
	public String getName(){
		return name;
	}
	
	public void fill(){
		// changes the index of undefined cards to -1
		for(int i = currentSize; i < maxSize; i++){
			sortNumbers[i] = -1;
		}
	}
	public void init(){
		for(int i = 0; i < currentSize; i++){
			contents[i].init();
			sortNumbers[i] = 0;
		}
		accessibleSize = currentSize;
		shuffle();
	}
	
	
	// deckBuilding functions
	public void add(Card c){
		if(currentSize < maxSize){
			contents[currentSize] = new Card(c);
			sortNumbers[currentSize] = currentSize;
			currentSize++;
			accessibleSize++;
		} else {
			Op.log("Deck " + name + " doesn't have enough room to add a copy of " + c.getName());
		}
	}
	public void add(Card c, int copies){
		for(int i = 0; i < copies; i++){
			add(c);
		}
	}
	
	public void remove(String n){
		if(contains(n)){
			int trueIndex = getIndexOf(n);
			Op.log("True index: " + trueIndex);
			int compareIndex = sortNumbers[trueIndex];
			Op.log("Compare index: " + compareIndex);
			currentSize--;
			accessibleSize--;
			
			for(int i = 0; i < currentSize; i++){
				if(i >= trueIndex){
					// overwrite the removed card, move others up
					contents[i] = contents[i + 1];
					sortNumbers[i] = sortNumbers[i + 1];
				}
				if(sortNumbers[i] > compareIndex){
					sortNumbers[i]--; // erase gaps
				}
			}
			// erase excess data
			contents[currentSize] = null;
			sortNumbers[currentSize] = -1;
			
			displayOrder();
		} else {
			Op.log("Deck " + name + " does not contain any copies of " + n);
		}
	}
	public void remove(String n, int copies){
		for(int i = 0; i < copies; i++){
			remove(n);
		}
	}
	public void removeAll(String n){
		while(contains(n)){
			remove(n);
		}
	}
	
	public boolean contains(String n){
		return getIndexOf(n) != -1;
	}
	
	// discarding functions
	public void discard(){
		get(0).setState(PlayState.IN_DISCARD);
		sortNumbers[getIndexOf(0)] = -1;
		accessibleSize--;
		for(int i = 0; i < currentSize; i++){
			if(sortNumbers[i] != -1){
				sortNumbers[i]--;
			}
		}
	}
	public void discardTopX(int topX){
		for(int i = 0; i < topX; i++){
			discard();
		}
	}
	
	
	
	public Card get(int index){
		Card ret = new Card("ERROR");
		boolean found = false;
		
		if(index < 0 || index >= accessibleSize){
			throw new IndexOutOfBoundsException();
		}
		
		for(int i = 0; i < currentSize && !found; i++){
			if(sortNumbers[i] == index){
				found = true;
				ret = contents[i];
			}
		}
		if(!found){
			Op.log("Not found index: " + index);
			Op.log("Existing indeces:");
			for(int i = 0; i < currentSize; i++){
				Op.log(sortNumbers[i]);
			}
			throw new IndexOutOfBoundsException();
		}
		return ret;
	}
	
	public int getIndexOf(String n){
		// returns the index of the first occurrence of card with name n in contents
		int ret = -1;
		boolean found = false;
		for(int i = 0; i < currentSize && !found; i++){
			if(contents[i].getName().equals(n)){
				ret = i;
				found = true;
			}
		}
		return ret;
	}
	public int getIndexOf(int fakeIndex){
		//translates a number in sort numbers to its index
		int ret = -1;
		boolean found = false;
		for(int i = 0; i < currentSize && !found; i++){
			if(sortNumbers[i] == fakeIndex){
				ret = i;
				found = true;
			}
		}
		return ret;
	}
	
	public Deck topX(int x){
		Deck d = new Deck("Top " + x + " cards of " + name, x);
		if(x > currentSize){
			d = this;
		} else {
			for(int i = 0; i < x; i++){
				d.contents[i] = new Card(get(i));
				d.sortNumbers[i] = i;
			}
		}
		return d;
	}
	
	public int size(){
		return accessibleSize;
	}
	
	public void shuffle(){
		Random rng = new Random();
		for(int i = 0; i < maxSize; i++){
			if(sortNumbers[i] != -1){
				sortNumbers[i] = rng.nextInt(maxSize) + 1;
			}
		}
		filterDown();
	}
	
	public void filterDown(){
		/* 
		 * makes it so the numbers 
		 * form a proper array
		 */
		
		Op.log("Initial data:");
		Op.log(sortNumbers);
		
		// keep going until you've reassigned all indices
		for(int nextNum = 0; nextNum < accessibleSize; nextNum++){
			//nextNum: the next index to assign to the smallest
			int smallestNum = Integer.MAX_VALUE; // the value of the smallest index
			int smallestIndex = -1; // the index of the smallest value
			
			// go through each element
			for(int i = 0; i < maxSize; i++){
				// make sure not to edit already reassigned or -1
				if(sortNumbers[i] > nextNum){
					if(sortNumbers[i] < smallestNum){
						smallestNum = sortNumbers[i];
						smallestIndex = i; // find the smallest
					} else {
						sortNumbers[i]++; // so that we can still sort with it
					}
				}
			}
			sortNumbers[smallestIndex] = nextNum;
			Op.log("Data after iteration:");
			Op.log(sortNumbers);
		}
	}
	
	public void displayOrder(){
		Op.log(name);
		int i = 0;
		for(Card c : this){
			Op.log(i + ": " + c.getName());
			i++;
		}
	}
	public void displayData(){
		Op.log(name);
		for(Card c : this){
			c.displayData();
		}
		
	}
	public String getContentsString(){
		String ret = "";
		HashMap<String, Integer> quantities = new HashMap<>();
		for(Card c : this){
			if(quantities.containsKey(c.getName())){
				quantities.put(c.getName(), quantities.get(c.getName()) + 1);
			} else {
				quantities.put(c.getName(), 1);
			}
		}
		for(String key : quantities.keySet()){
			ret += (key + ": x" + quantities.get(key) + System.lineSeparator());
		}
		return ret;
	}
	
	public void setContentViewer(DeckContentViewer d){
		contentViewer = d;
		contentViewer.setDeck(this);
	}
	public DeckContentViewer getContentView(){
		return contentViewer;
	}
	
	public Iterator<Card> iterator(){
		return new DeckIterator();
	}
	
	public class DeckIterator implements Iterator<Card>{
		private int current;
		
		public DeckIterator(){
			current = 0;
		}
		public boolean hasNext(){
			return current < Deck.this.accessibleSize;
		}
		public Card next(){
			Card ret;
			if(hasNext()){
				ret = get(current);
				current++;
			} else {
				throw new NullPointerException();
			}
			return ret;
		}
	}
}