package main;
import cards.Card;
import utilities.Op;
import utilities.DeckStructure;
//import graphics.ColorTesterFrame;

public class Main {
	public static void main(String[] args) {
		//Deck d = new Deck("Test deck");
		DeckStructure d = new DeckStructure(10);
		d.add(new Card("Test1"), 3);
		d.add(new Card("Test2"));
		d.add(new Card("Test3"));
		d.add(new Card("Test3"), 1);
		d.displayData();
		d.shuffle();
		for(int i = 0; i < 3; i++){
			Op.log("Deck:");
			d.discard();
			d.displayData();
		}
		//new ColorTesterFrame();
	}
}
