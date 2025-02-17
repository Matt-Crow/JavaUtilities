package boardgameSimulator.cardPlaces;

import boardgameSimulator.cards.Card;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeckTesterPanel extends JPanel{
	private Deck d;
	public DeckTesterPanel(){
		d = new Deck("Test deck", 10);
		
		d.add(new Card("Keep me"), 5);
		d.add(new Card("DELETE ME"), 5);
		
		JButton j1 = new JButton("Remove top card");
		j1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				d.discardTopX(1);
				d.displayOrder();
			}
		});
		add(j1);
		
		JButton j2 = new JButton("Remove copy of DELETE ME");
		j2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				d.remove("DELETE ME");
				d.displayOrder();
			}
		});
		add(j2);
		
		JButton j3 = new JButton("Remove all copies of DELETE ME, then put them back in");
		j3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				d.removeAll("DELETE ME");
				d.displayOrder();
				d.add(new Card("DELETE ME"), 5);
			}
		});
		add(j3);
		
		JButton j4 = new JButton("Discard top 3");
		j4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				d.discardTopX(3);
				d.displayOrder();
			}
		});
		add(j4);
		
		JButton j5 = new JButton("Shuffle");
		j5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				d.shuffle();
				d.displayOrder();
			}
		});
		add(j5);
	}
}
