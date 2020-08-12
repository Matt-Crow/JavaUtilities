package boardgameSimulator.deckBuilder;

import boardgameSimulator.cards.Card;
import boardgameSimulator.cardPlaces.Deck;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class AddCard extends JMenuItem{
	private Card registeredCard;
	private Deck registeredDeck;
	
	public AddCard(Card c, Deck d){
		super(c.getName());
		registeredCard = c;
		registeredDeck = d;
		
		addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				setToolTipText(registeredCard.getDesc());
			}
			public void mousePressed(MouseEvent e){
				registeredDeck.add(registeredCard);
				registeredDeck.getContentView().update();
			}
		});
	}
}
