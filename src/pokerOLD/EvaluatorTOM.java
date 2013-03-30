package pokerOLD;

import pokerfork.Hand;

public class EvaluatorTOM {

	public static final int STRAIGHT_FLUSH = 8;
	public static final int FOUROFAKIND = 7;
	public static final int FULLHOUSE = 6;
	public static final int FLUSH = 5;
	public static final int STRAIGHT = 4;
	public static final int THREEOFAKIND = 3;
	public static final int TWOPAIR = 2;
	public static final int ONEPAIR = 1;
	
	/*
	 * Determines the value of a given hand.
	 * 
	 * @param the hand being evaluated
	 * @return array consisting of: i) value of the hand, ii) the high card within the hand, iii) the high card 
	 * without the hand - where applicable
	 */

	public static int[] getHandValue(Hand hand){

		int handValue = 0;
		int highCardWithout = 0;

		hand.sort();

		int highCardWithin = hand.get(0).getPlayingCardValue();

		int card1value = hand.get(0).getPlayingCardValue();
		int card2value = hand.get(1).getPlayingCardValue();
		int card3value = hand.get(2).getPlayingCardValue();
		int card4value = hand.get(3).getPlayingCardValue();
		int card5value = hand.get(4).getPlayingCardValue();

		int card1suit = hand.get(0).getPlayingCardSuit();
		int card2suit = hand.get(1).getPlayingCardSuit();
		int card3suit = hand.get(2).getPlayingCardSuit();
		int card4suit = hand.get(3).getPlayingCardSuit();
		int card5suit = hand.get(4).getPlayingCardSuit();

		if(straightCalculator(hand)){

			if(card1value == 1 && card2value == 5){

				highCardWithin = card2value;

			}

			if(card1suit == card2suit &&
					card2suit == card3suit &&
					card3suit == card4suit &&
					card4suit == card5suit){

				handValue = STRAIGHT_FLUSH;
				highCardWithin = card1value;
				highCardWithout = card1suit;

			}

			else{

				handValue = STRAIGHT;
				highCardWithin = card1value;
				highCardWithout = card1suit;

			}

		}

		else if((card1value == card2value &&
				card2value == card3value &&
				card3value == card4value) ||
				(card2value == card3value &&
						card3value == card4value &&
						card4value == card5value)){

			handValue = FOUROFAKIND;
			highCardWithin = card3value;

			if(card1value != card2value){
				highCardWithout = card1value;
			}

			else{

				highCardWithout = card5value;
			}

		}

		if (handValue == 0 && card1suit == card2suit &&
				card2suit == card3suit &&
				card3suit == card4suit &&
				card4suit == card5suit) {
			handValue = FLUSH;
			highCardWithin = card1value;
		}

		if (handValue == 0) {
			//handValue = pairdAndTrioCheck(hand);
			if (card1value == card3value || card3value == card5value || card2value == card4value) {
				handValue = THREEOFAKIND;
				highCardWithin = card3value;
			}

			if (card1value == card3value) {
				if (card4value == card5value) {
					handValue = FULLHOUSE;
				}
			}

			if (card3value == card5value) {
				if (card1value == card2value) {
					handValue = FULLHOUSE;
				}
			}
		}

		if (handValue == 0) {
			if ((card1value == card2value && card3value == card4value) || (card1value == card2value && card4value == card5value) || (card2value == card3value && card4value == card5value)) {
				handValue = TWOPAIR;
				highCardWithin = card2value;
				highCardWithout = card4value;
			}
		}

		if (handValue == 0) {
			if (card1value == card2value) {
				handValue = ONEPAIR;
				highCardWithin = card1value;
				highCardWithout = card3value;	
			} else {
				if (card2value == card3value) {
					handValue = ONEPAIR;
					highCardWithin = card2value;
					highCardWithout = card1value;
				} else {
					if (card3value == card4value) {
						handValue = ONEPAIR;
						highCardWithin = card3value;
						highCardWithout = card2value;
					} else {
						if (card4value == card5value) {
							handValue = ONEPAIR;
							highCardWithin = card4value;
							highCardWithout = card3value;
						}
					}
				}
			}
		}
		

		if (handValue == 0) {
			highCardWithin = card1value;
			highCardWithout = card2value;
		}

		int returnValue[] = {handValue,highCardWithin,highCardWithout};

		return returnValue;
	}

	/* determines whether a hand is a straight - accounting for ace high or low
	 * @return boolean: true if hand is a straight
	 */

	private static boolean straightCalculator(Hand hand) {

		int card1value = hand.get(0).getPlayingCardValue();
		int card2value = hand.get(1).getPlayingCardValue();
		int card3value = hand.get(2).getPlayingCardValue();
		int card4value = hand.get(3).getPlayingCardValue();
		int card5value = hand.get(4).getPlayingCardValue();

		if((card1value == card2value + 1 && 
				card2value == card3value + 1 && 
				card3value == card4value + 1 && 
				card4value == card5value + 1) ||

				(card1value == 1 &&
						card2value == 5 &&
						card3value == 4 &&
						card4value == 3 &&
						card5value == 2) ||

						card1value == 1 &&
						card2value == 13 &&
						card3value == 12 &&
						card4value == 11 &&
						card5value == 10){

			return true;		

		}

		else return false;

	}
}




