package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Hand extends CardStack{
	int highestUsedCard;

	public Hand(ArrayList<Integer> initial) {
		super(initial);
	}
	
	public void takeNewCard(int newCard) {
		cardStack.add(newCard);
	}
	
	public void discardCard(int cardToDiscard) {
		cardStack.remove(cardToDiscard);
	}
	
	public int evaluateHandType() {
		Collections.sort(cardStack);
		int handScore = 1;
		highestUsedCard = 1;
		
		//big ass select statement naming and scoring the best hand and setting highest used card??
				
		return handScore;
	}

}
