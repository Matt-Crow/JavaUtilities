package cardPlaces;

import java.util.ArrayList;
import cards.Card;

public abstract class AbstractInPlayPlace extends AbstractCardPlace{
	public AbstractInPlayPlace(){
		super();
	}
	public AbstractInPlayPlace(ArrayList<Card> c){
		super(c);
	}
}
