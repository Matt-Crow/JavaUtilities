package main;
import cardPlaces.Deck;
import cards.Card;

public class Main {
	public static void main(String[] args) {
		Deck d = new Deck("Test deck");
		d.setSize(6);
		d.add(new Card("Test 1"), 3);
		d.add(new Card("test 2"), 2);
		d.add(new Card("test 3"));
		d.remove("Test 1", 2);
		d.displayData();
		//new ColorTesterFrame();
	}
}
