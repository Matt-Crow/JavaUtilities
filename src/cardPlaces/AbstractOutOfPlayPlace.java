package cardPlaces;

import java.util.ArrayList;
import cards.Card;

public abstract class AbstractOutOfPlayPlace extends AbstractCardPlace{
	public AbstractOutOfPlayPlace(){
		super();
	}
	public AbstractOutOfPlayPlace(ArrayList<Card> c){
		super(c);
	}
	public AbstractOutOfPlayPlace(AbstractCardPlace a){
		super(a);
	}
}
