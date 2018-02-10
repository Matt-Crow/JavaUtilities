package main;
import cardPlaces.Deck;
import cards.Card;
import utilities.Op;

public class Main {
	public static void main(String[] args) {
		Deck d = new Deck("Test deck");
		d.addCard(new Card("Test1"), 3);
		d.addCard(new Card("Test2"));
		d.addCard(new Card("Test3"));
		d.addCard(new Card("Test3"), 1);
		d.displayData();
		d.init();
		for(int i = 0; i < 3; i++){
			Op.log("Deck:");
			d.discard();
			d.displayData();
			Op.log("Disc:");
			d.getDiscardPile().displayData();
		}
	}
}
