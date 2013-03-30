package pokerLauncherOLD;

import java.util.Random;
import java.util.logging.Logger;

import pokerfork.Hand;

public class DealerAI {

	public static final int STRAIGHT_FLUSH = 8;
	public static final int FOUROFAKIND = 7;
	public static final int FULLHOUSE = 6;
	public static final int FLUSH = 5;
	public static final int STRAIGHT = 4;
	public static final int THREEOFAKIND = 3;
	public static final int TWOPAIR = 2;
	public static final int ONEPAIR = 1;
	public static final int HIGH_CARD = 0;


	private static Logger log = Logger.getLogger("NewLogger");
	
	public DealerAI(Hand hand) {
		hand.evaluate();
		hand.getHandScore();

		//To be used for smarter AI!
		//int handType = hand.getHandType();

		boolean randomAI = false;

		if (randomAI) {
			int[] cardsInHand = {1,2,3,4,5};

			int loopCount = 1;
			int cardToChange = 0;
			do {
				Random generator = new Random();
				do {
					cardToChange = generator.nextInt(cardsInHand.length);
				} while (cardsInHand[cardToChange] == 0);

				if (generator.nextBoolean()) {
					hand.set(cardToChange, GuiToGameLink.deck.dealCard());
				}

				cardsInHand[cardToChange] = 0;
				loopCount++;
			} while (loopCount <= 3);
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



		}


		GuiToGameLink.printHand("dealer");
	}

}
