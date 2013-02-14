package poker;

import java.util.ArrayList;

public class Hand extends CardStack{

	public Hand(ArrayList<Integer> initial) {
		super(initial);
	}
	
	public void takeNewCard(int newCard) {
		cardStack.add(newCard);
	}

}
