package cards;

import utilities.Op;

public class Card {
	private String name;
	private String text;
	private PlayState state;
	
	// constructors
	public Card(String n, String t){
		name = n;
		text = t;
		state = PlayState.IN_DECK;
	}
	public Card(String n){
		name = n;
		text = "";
		state = PlayState.IN_DECK;
	}
	public Card(Card c){
		name = c.getName();
		text = c.getText();
		state = PlayState.IN_DECK;
	}
	
	// getters
	public String getName(){
		return name;
	}
	public String getText(){
		return text;
	}
	// this will be overridden for stuff like LOTRCard
	public String getDesc(){
		return text;
	}
	// TODO: use this in drawing and discarding
	public void setState(PlayState s){
		state = s;
	}
	public PlayState getState(){
		return state;
	}
	
	public void init(){
		state = PlayState.IN_DECK;
	}
	
	// debug
	public void displayData(){
		Op.log("<Card>");
		Op.log(name);
		Op.log(text);
		String msg = "";
		
		switch(state){
		case IN_PLAY:
			msg = "in play";
			break;
		case IN_DISCARD:
			msg = "discarded";
			break;
		case IN_HAND:
			msg = "in hand";
			break;
		case IN_DECK:
			msg = "in deck";
			break;
		}
		Op.log(msg);
		Op.log("");
	}
}




