package ai;

import java.util.Random;
import java.util.logging.Logger;

import game.Deck;
import game.Hand;
import game.CardController;
import game.Evaluator;

/**
 * the dealer AI class evaluates its hand and decides which cards to exchange or not
 * @author Tom & Jonathan
 *
 */public class DealerAI {

	//CardController cardController;

	private int numOfCardsChanged;
	private String aiFeedback;
	private int dealerAggression; //value under which smart dealer will swap cards. setting high makes dealer very conservative, setting low makes dealer very aggreesive (likely to change)

	private static Logger log = Logger.getLogger("NewLogger");

	/**
	 * constructor evaluates the dealer's hand
	 * TOM ANNOTATE
	 * @param deck 
	 * @param the dealer's hand
	 */
	public DealerAI(Hand hand, Deck deck) {
		
		hand.evaluate();
		hand.getHandScore();

		boolean randomAI = false;
		int dealerAggression = 7;

		setNumOfCardsChanged(0);

		if (randomAI) {
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

			setAiFeedback("RANDOM dealer intelligence");

		} else {

			int[][] cardMatcher = new int[5][2];
			cardMatcher = hand.getCardMatcher();

			
			//.................................................................................................................
			for (int loopCounter = 0; loopCounter<=4; loopCounter++) {
				log.info(cardMatcher[loopCounter][0] + "   " + cardMatcher[loopCounter][1]);
			}

			switch (hand.getHandType()) {
			case Evaluator.HIGH_CARD:
				disposeUnwantedCards(hand, deck, dealerAggression, cardMatcher);
				break;
			case Evaluator.ONEPAIR:
				disposeUnwantedCards(hand, deck, dealerAggression, cardMatcher);
				break;
			case Evaluator.TWOPAIR:
				disposeUnwantedCards(hand, deck, dealerAggression, cardMatcher);
				break;
			case Evaluator.THREEOFAKIND: //change cards outside the threesome if under specified value
				disposeUnwantedCards(hand, deck, dealerAggression, cardMatcher);
				break;
			case Evaluator.STRAIGHT: //do nothing!
				break;
			case Evaluator.FLUSH: //do nothing!
				break;
			case Evaluator.FULLHOUSE: //do nothing!
				break;
			case Evaluator.FOUROFAKIND: //do nothing!
				break;
			case Evaluator.STRAIGHT_FLUSH: //do nothing!
				break;
			default:
				break;
			}

			setAiFeedback("SMART dealer intelligence");

		}


		//GuiToGameLink.printHand("dealer");
	}

	private void disposeUnwantedCards(Hand hand, Deck deck,
			int dealerAggression, int[][] cardMatcher) {
		for (int cardCounter = 0; cardCounter < cardMatcher.length; cardCounter++) {
			if (cardMatcher[cardCounter][1] == 1 && cardMatcher[cardCounter][0] <= dealerAggression) {
				hand.set(cardCounter, deck.dealCard());
				setNumOfCardsChanged(getNumOfCardsChanged()+1);
			}
		}
	}

//	public void setControl(CardController cardController) {
//		this.cardController = cardController;
//	}

	private void setNumOfCardsChanged(int numOfCardsChanged) {
		this.numOfCardsChanged = numOfCardsChanged;
	}

	public int getNumOfCardsChanged() {
		return numOfCardsChanged;
	}

	private void setAiFeedback(String aiFeedback) {
		this.aiFeedback = aiFeedback;
	}

	public String getAiFeedback() {
		return aiFeedback;
	}


}
