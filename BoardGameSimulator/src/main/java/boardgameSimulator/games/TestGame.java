package boardgameSimulator.games;

import boardgameSimulator.cards.Card;

// somehow make static?
public class TestGame extends AbstractGame{
	public TestGame(){
		super();
		for(int i = 1; i <= 10; i++){
			addCard(new Card("Test card #" + i, "This is the body text of card #" + i));
		}
	}
}
