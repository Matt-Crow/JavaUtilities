package cardPlaces;

import java.util.Iterator;
import java.util.Random;
import java.util.HashMap;

import cards.Card;
import utilities.Op;

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
	
	// use this() to invoke other constructors
	// TODO: finish 
	public Deck(String n){
		name = n;
	}
	
	public Deck(Deck d){
		name = d.getName();
		maxSize = d.maxSize;
		currentSize = d.currentSize;
		accessibleSize = d.accessibleSize;
		
		contents = new Card[maxSize];
		sortNumbers = new int[maxSize];
		
		for(int i = 0; i < d.currentSize; i++){
			contents[i] = new Card(d.contents[i]);
			sortNumbers[i] = d.sortNumbers[i];
		}
	}
	
	public Deck(int s){
		contents = new Card[s];
		sortNumbers = new int[s];
		currentSize = 0;
		accessibleSize = 0;
		maxSize = s;
		for(int i = 0; i < maxSize; i++){
			sortNumbers[i] = -1;
		}
	}
	
	public String getName(){
		return name;
	}
	
	public void setSize(int s){
		try{
			// first copy old data, if it exists
			Card[] oldContents = new Card[maxSize];
			int[] oldSortNumbers = new int[maxSize];
			int oldSize = maxSize;
			for(int i = 0; i < oldSize; i++){
				oldContents[i] = contents[i];
				oldSortNumbers[i] = sortNumbers[i];
			}
			maxSize = s;
			contents = new Card[s];
			sortNumbers = new int[s];
			currentSize = oldSize;
			
			for(int i = 0; i < oldSize; i++){
				contents[i] = oldContents[i];
				sortNumbers[i] = oldSortNumbers[i];
			}
		} catch (NullPointerException e){
			maxSize = s;
			contents = new Card[s];
			sortNumbers = new int[s];
			currentSize = 0;
			accessibleSize = 0;
		}
	}
	
	public void init(){
		for(int i = 0; i < currentSize; i++){
			sortNumbers[i] = 0;
		}
		accessibleSize = currentSize;
		shuffle();
	}
	
	//TODO: change to top of deck
	public void add(Card c){
		// adds a card to the bottom of the deck
		if(currentSize < maxSize){
			contents[currentSize] = new Card(c);
			sortNumbers[currentSize] = currentSize;
			currentSize++;
			accessibleSize++;
		}
	}
	public void add(Card c, int copies){
		for(int i = 0; i < copies; i++){
			add(c);
		}
	}
	
	public void remove(int remove){
		int index = getIndexOf(remove);
		if(sortNumbers[index] != -1){
			sortNumbers[index] = -1;
			accessibleSize--;
			for(int i = 0; i < currentSize; i++){
				if(sortNumbers[i] != -1){
					sortNumbers[i]--;
				}
			}
		}
	}
	public void remove(String n){
		boolean found = false;
		for(int i = 0; i < currentSize && !found; i++){
			if(contents[i].getName() == n && sortNumbers[i] != -1){
				found = true;
				remove(i);
			}
		}
	}
	public void remove(String n, int copies){
		for(int i = 0; i < copies; i++){
			remove(i);
		}
	}
	public void removeAll(String n){
		while(contains(n)){
			remove(n);
		}
	}
	
	public void discard(){
		remove(0);
	}
	public void discard(int topX){
		for(int i = 0; i < topX; i++){
			discard();
		}
	}
	
	public boolean contains(String n){
		boolean found = false;
		for(int i = 0; i < currentSize && !found; i++){
			if(contents[i].getName() == n && sortNumbers[i] != -1){
				found = true;
			}
		}
		return found;
	}
	
	public Card get(int index){
		Card ret = new Card("ERROR");
		boolean found = false;
		
		if(index < 0 || index >= accessibleSize){
			throw new IndexOutOfBoundsException();
		}
		
		for(int i = 0; i < currentSize && !found; i++){
			//Op.log("sortNumbers[" + i + "]: " + sortNumbers[i]);
			if(sortNumbers[i] == index){
				found = true;
				ret = contents[i];
			}
		}
		if(!found){
			Op.log("Not found index: " + index);
			throw new IndexOutOfBoundsException();
		}
		return ret;
	}
	
	public int getIndexOf(int index){
		int ret = -1;
		boolean found = false;
		for(int i = 0; i < currentSize && !found; i++){
			if(sortNumbers[i] == index){
				ret = i;
				found = true;
			}
		}
		if(ret == -1){
			Op.log("Not found index: " + index);
			throw new IndexOutOfBoundsException();
		}
		return ret;
	}
	
	public Deck topX(int x){
		Deck d = new Deck(x);
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
	
	public void displayData(){
		/*
		for(Card c : this){
			c.displayData();
		}*/
		HashMap<String, Integer> quantities = new HashMap<>();
		for(Card c : this){
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
