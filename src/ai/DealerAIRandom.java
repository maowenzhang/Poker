package ai;

import java.util.Random;

/**
 * 
 * A Dealer "AI" which picks up to 3 cards entirely at random to exchange.
 * 
 * Not a worthy opponent.
 *
 */
public class DealerAIRandom extends DealerAI{

	@Override
	public void evaluate() {

		numOfCardsChanged = 0;
		int[] cardsInHand = {1,2,3,4,5};	

		Random generator = new Random();
		int numToChange = generator.nextInt(4);
		int loopCount = 1;
		int cardToChange = 0;

		if (numToChange>0) {
			do {
				do {
					cardToChange = generator.nextInt(hand.HANDSIZE);
				} while (cardsInHand[cardToChange] == 0);

				hand.set(cardsInHand[cardToChange]-1, deck.dealCard());
				setNumOfCardsChanged(getNumOfCardsChanged()+1);

				cardsInHand[cardToChange] = 0;
				loopCount++;
			} while (loopCount <= numToChange);
		}

	}

}

