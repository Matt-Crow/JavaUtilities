package main;
import cards.Card;
import utilities.DeckStructure;
import utilities.Op;
//import graphics.ColorTesterFrame;

public class Main {
	public static void main(String[] args) {
		//Deck d = new Deck("Test deck");
		DeckStructure d = new DeckStructure(52);
		for(int i = 0; i < 52; i++){
			d.add(new Card("test"));
		}
		d.shuffle();
		d.displayData();
		for(Card c : d){
			Op.log("Name: " + c.getName());
		}
		//new ColorTesterFrame();
	}
}
