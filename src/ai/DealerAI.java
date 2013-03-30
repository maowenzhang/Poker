package ai;

import java.util.Random;
import java.util.logging.Logger;

import game.Hand;
import game.GameController;
import game.PlayingCard;

public class DealerAI {

	GameController gameController;

	public static final int STRAIGHT_FLUSH = 8;
	public static final int FOUROFAKIND = 7;
	public static final int FULLHOUSE = 6;
	public static final int FLUSH = 5;
	public static final int STRAIGHT = 4;
	public static final int THREEOFAKIND = 3;
	public static final int TWOPAIR = 2;
	public static final int ONEPAIR = 1;
	public static final int HIGH_CARD = 0;

	private int numOfCardsChanged;
	private String aiFeedback;

	private static Logger log = Logger.getLogger("NewLogger");

	public DealerAI(Hand hand) {

		gameController = new GameController();

		hand.evaluate();
		hand.getHandScore();

		//To be used for smarter AI!
		//int handType = hand.getHandType();

		boolean randomAI = true;

		setNumOfCardsChanged(0);

		if (randomAI) {
			int[] cardsInHand = {1,2,3,4,5};

			Random generator = new Random();
			int numToChange = generator.nextInt(2)+1;
			int loopCount = 1;
			int cardToChange = 0;

			if (numToChange>0) {
				do {
					do {
						cardToChange = generator.nextInt(cardsInHand.length-1);
					} while (cardsInHand[cardToChange] == 0);

					hand.set(cardsInHand[cardToChange], gameController.dealCard());
					setNumOfCardsChanged(getNumOfCardsChanged()+1);

					cardsInHand[cardToChange] = 0;
					loopCount++;
				} while (loopCount <= numToChange);
			}

			setAiFeedback("RANDOM dealer intelligence");

		} else {

			int[][] cardMatcher = new int[5][2];
			cardMatcher = hand.getCardMatcher();

			for (int loopCounter = 0; loopCounter<=4; loopCounter++) {
				log.info(cardMatcher[loopCounter][0] + "   " + cardMatcher[loopCounter][1]);
			}

			switch (hand.getHandType()) {
			case HIGH_CARD:
				break;
			case ONEPAIR:
				break;
			case TWOPAIR:
				break;
			case THREEOFAKIND:
				break;
			case STRAIGHT: //do nothing!
				break;
			case FLUSH: //do nothing!
				break;
			case FULLHOUSE: //do nothing!
				break;
			case FOUROFAKIND: //do nothing!
				break;
			case STRAIGHT_FLUSH: //do nothing!
				break;
			default:
				break;
			}

			setAiFeedback("SMART dealer intelligence");

		}


		//GuiToGameLink.printHand("dealer");
	}

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
