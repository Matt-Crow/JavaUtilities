package main;
import cards.Card;
import cards.Deck;

public class Main {
	public static void main(String[] args) {
		Deck d = new Deck("Test deck");
		d.addCard(new Card("Test1"), 3);
		d.addCard(new Card("Test2"));
		d.addCard(new Card("Test3"));
		d.addCard(new Card("Test3"), 1);
		d.displayData();
	}
}
