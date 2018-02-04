package cards;

import utilities.Op;

public class Card {
	private String name;
	private String text;
	
	// constructors
	public Card(String n, String t){
		name = n;
		text = t;
	}
	public Card(String n){
		name = n;
		text = "";
	}
	public Card(Card c){
		name = c.getName();
		text = c.getText();
	}
	
	// getters
	public String getName(){
		return name;
	}
	public String getText(){
		return text;
	}
	
	// debug
	public void displayData(){
		Op.log("<Card>");
		Op.log(name);
		Op.log(text);
		Op.log("");
	}
}
