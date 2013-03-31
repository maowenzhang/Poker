package ai;

import game.Evaluator;

import java.util.logging.Logger;

public class DealerAISmart extends DealerAI{

	private int dealerAggression; //value under which smart dealer will swap cards. setting high makes dealer very conservative, setting low makes dealer very aggreesive (likely to change)

	private static Logger log = Logger.getLogger("NewLogger");

	public void evaluate(){

		hand.evaluate();
		hand.getHandScore();

		numOfCardsChanged = 0;
		int dealerAggression = 7;

		int[][] cardMatcher = new int[5][2];
		cardMatcher = hand.getCardMatcher();

		//.................................................................................................................
		for (int loopCounter = 0; loopCounter<=4; loopCounter++) {
			log.info(cardMatcher[loopCounter][0] + "   " + cardMatcher[loopCounter][1]);
		}

		switch (hand.getHandType()) {
		case Evaluator.HIGH_CARD:
			disposeUnwantedCards(dealerAggression, cardMatcher);
			break;
		case Evaluator.ONEPAIR:
			disposeUnwantedCards(dealerAggression, cardMatcher);
			break;
		case Evaluator.TWOPAIR:
			disposeUnwantedCards(dealerAggression, cardMatcher);
			break;
		case Evaluator.THREEOFAKIND: //change cards outside the threesome if under specified value
			disposeUnwantedCards(dealerAggression, cardMatcher);
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
		//GuiToGameLink.printHand("dealer");
	}

	private void disposeUnwantedCards(int dealerAggression, int[][] cardMatcher) {
		for (int cardCounter = 0; cardCounter < cardMatcher.length; cardCounter++) {
			if (cardMatcher[cardCounter][1] == 1 && cardMatcher[cardCounter][0] <= dealerAggression) {
				hand.set(cardCounter, deck.dealCard());
				setNumOfCardsChanged(getNumOfCardsChanged()+1);
			}
		}
	}
	

	//		public void setControl(CardController cardController) {
	//			this.cardController = cardController;
	//		}




}



