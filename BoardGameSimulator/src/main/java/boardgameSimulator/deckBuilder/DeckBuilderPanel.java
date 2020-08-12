package boardgameSimulator.deckBuilder;

import boardgameSimulator.cardPlaces.Deck;

import javax.swing.JPanel;
import java.awt.GridLayout;


@SuppressWarnings("serial")
public class DeckBuilderPanel extends JPanel{
	/**
	 * The DeckBuilderPanel is used by the user 
	 * to create and customize decks, which they
	 * can then use in the games this program
	 * has.
	 */
	
	private DeckBuilderMenuBar menu; // the menu along the top of the screen
	private Deck selectedDeck;
	private DeckContentViewer deckContents;
	
	public DeckBuilderPanel(){
		super();
		setLayout(new GridLayout());
		selectedDeck = new Deck("New Deck", 50);
		deckContents = new DeckContentViewer(selectedDeck);
		selectedDeck.setContentViewer(deckContents);
		menu = new DeckBuilderMenuBar(selectedDeck);
		add(menu);
		add(deckContents);
	}
}