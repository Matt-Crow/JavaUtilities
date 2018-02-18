package utilities;

import java.util.Iterator;
import utilities.Op;

import cards.Card;

public class DeckStructure implements Iterable<Card>{
	/*
	 * DeckStructure is a pair of arrays that are meant
	 * to provide less strain on the system than an
	 * ArrayList.
	 * 
	 * How it works:
	 * -Two arrays: contents and sortNumbers
	 * --contents are never removed
	 * --sortNumbers is the 'index' of the adjacent content, -1 means it is removed
	 * -Three sizes:
	 * --maxSize is the size of each array
	 * --currentSize is the number of elements in contents
	 * --accessibleSize is the number of defined, non-negative elements in sortNumbers
	 */
	
	private Card[] contents;
	private int[] sortNumbers;
	private int maxSize;
	private int currentSize;
	private int accessibleSize;
	
	public DeckStructure(int s){
		contents = new Card[s];
		sortNumbers = new int[s];
		currentSize = 0;
		accessibleSize = 0;
		maxSize = s;
	}
	public void add(Card c){
		// adds a card to the bottom of the deck
		if(currentSize < maxSize){
			contents[currentSize] = c;
			sortNumbers[currentSize] = currentSize;
			currentSize++;
			accessibleSize++;
		}
	}
	public void remove(int index){
		sortNumbers[getIndexOf(index)] = -1;
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
			throw new IndexOutOfBoundsException();
		}
		return ret;
	}
	
	public int getIndexOf(int index){
		int ret = -1;
		boolean found = false;
		for(int i = 0; i < accessibleSize && !found; i++){
			if(sortNumbers[i] == index){
				ret = i;
			}
		}
		if(ret == -1){
			throw new IndexOutOfBoundsException();
		}
		return ret;
	}
	
	public int size(){
		return accessibleSize;
	}
	
	public void shuffle(){
		//todo: random stuff
	}
	
	public void displayData(){
		for(int i = 0; i < maxSize; i++){
			Op.log(contents[i].getName() + ": at position " + sortNumbers[i]);
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
			return current < DeckStructure.this.accessibleSize;
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
