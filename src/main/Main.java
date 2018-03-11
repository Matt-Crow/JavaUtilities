package main;
import cards.Card;
import utilities.DeckStructure;
//import graphics.ColorTesterFrame;

public class Main {
	public static void main(String[] args) {
		//Deck d = new Deck("Test deck");
		DeckStructure d = new DeckStructure(52);
		for(int i = 0; i < 52; i++){
			d.add(new Card("test"));
		}
		for(int i = 0; i < 10; i++){
			d.shuffle();
		}
		//new ColorTesterFrame();
	}
}
