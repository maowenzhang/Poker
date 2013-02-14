package poker;

import java.util.ArrayList;
import java.util.Random;

public class Deck extends CardStack{

	public Deck(ArrayList<Integer> initial) {
		super(initial);
	}
	
	public void generate() {
		cardStack.clear();
		for (int loopCount = 0; loopCount < 52; loopCount++) {
			cardStack.add(loopCount+1);
		}
	}
	
	public int dealCard() {
		Random generator = new Random();
		int cardSelector = generator.nextInt(cardStack.size());
		int cardDealt = cardStack.get(cardSelector)+1;
		cardStack.remove(cardSelector);
		return cardDealt;
	}
}
