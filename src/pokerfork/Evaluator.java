package pokerfork;

public class Evaluator{

	public static final int STRAIGHT_FLUSH = 8;
	public static final int FOUROFAKIND = 7;
	public static final int FULLHOUSE = 6;
	public static final int FLUSH = 5;
	public static final int STRAIGHT = 4;
	public static final int THREEOFAKIND = 3;
	public static final int TWOPAIR = 2;
	public static final int ONEPAIR = 1;
	public static final int HIGH_CARD = 0;

	private  PlayingCard card1;
	private  PlayingCard card2;
	private  PlayingCard card3;
	private  PlayingCard card4;
	private  PlayingCard card5;

	private  int card1value;
	private  int card2value;
	private  int card3value;
	private  int card4value;
	private  int card5value;

	private  int card1suit;
	private  int card2suit;
	private  int card3suit;
	private  int card4suit;
	private  int card5suit;

	private  int handValue = 0;


	//private static PlayingCard scoringCard1;
	private  PlayingCard scoringCard1 = new PlayingCard();
	//private  int scoringCard1 = 0;
	private  PlayingCard scoringCard2 = new PlayingCard();
	//private  int scoringCard2 = 0;
	private  PlayingCard scoringCard3 = new PlayingCard();
	//private  int highCardWithout = 0;
	//private  int highCardWithout2 = 0;
	//private  int highCardWithout3 = 0;
	//private  int highCardWithout4 = 0;
	//private  int highCardSuit = 0;
	private PlayingCard scoringCard4 = new PlayingCard();
	private PlayingCard scoringCard5 = new PlayingCard();


	private int handScore = 0;
	private String handName = "";


	/*
	 * Arranges the hand in order from highest to lowest and sets records each card's suit and value
	 * 
	 */

	public  void setHandValue(){

		if(straightCalculator()){

			handValue = STRAIGHT;

			if(flushCalculator()){
				handValue = STRAIGHT_FLUSH;	
			}

			// Straight 5432A - so 5 (2nd card in sort) is higher than ace - only incidence where A is not high
			if(card1value == 1 && card2value == 5){
				scoringCard1.setPlayingCard(card2value, card2suit);
			}

		} else {

			if(fourCalculator()) {

				handValue = FOUROFAKIND;

				// if kicker is higher value than the other 4, then - 2nd card is part of 4, so may be used as high card
				if(card1value != card2value) {
					scoringCard1.setPlayingCard(card2value, card2suit);
				}

			} else {

				if(fullHouseCalculator()){

					handValue = FULLHOUSE;

					// if 2nd and 3rd cards in sort are not same face value, then 3ofKind must be lower value than Pair, so need to reset high card values
					if(card2value != card3value){
						scoringCard1.setPlayingCard(card3value, card3suit);
						scoringCard2.setPlayingCard(card1value, card1suit);
					} else {
						scoringCard2.setPlayingCard(card4value, card4suit);
					}

				} else {

					if(flushCalculator()){

						handValue = FLUSH;

						scoringCard2.setPlayingCard(card2value, card2suit);
						scoringCard3.setPlayingCard(card3value, card3suit);
						scoringCard4.setPlayingCard(card4value, card4suit);
						scoringCard5.setPlayingCard(card5value, card5suit);

					} else {

						if(threeCalculator()){

							handValue = THREEOFAKIND;

							if(card2value == card4value){

								scoringCard1.setPlayingCard(card2value, card2suit);

							} else {

								if(card3value == card5value){

									scoringCard1.setPlayingCard(card3value, card3suit);

								}
							}

						} else {

							if(twoTwoCalculator()) {

								handValue = TWOPAIR;

								// ERROR?!?!?!? ... now cured???.......................................................................................

								if(card1value == card2value) {

									if(card3value == card4value) {

										scoringCard2.setPlayingCard(card3value, card3suit);
										scoringCard3.setPlayingCard(card5value, card5suit);

									} else {

										scoringCard2.setPlayingCard(card4value, card4suit);
										scoringCard3.setPlayingCard(card3value, card3suit);

									}

								} else {

									scoringCard1.setPlayingCard(card2value, card2suit);
									scoringCard2.setPlayingCard(card4value, card4suit);
									scoringCard3.setPlayingCard(card1value, card1suit);

								}

							} else {

								if(twoCalculator()){

									handValue = ONEPAIR;

									if (card1value == card2value) {

										scoringCard2.setPlayingCard(card3value, card3suit);
										scoringCard3.setPlayingCard(card4value, card4suit);
										scoringCard4.setPlayingCard(card5value, card5suit);

									} else {

										if(card2value == card3value){

											scoringCard1.setPlayingCard(card2value, card2suit);
											scoringCard2.setPlayingCard(card1value, card1suit);
											scoringCard3.setPlayingCard(card4value, card4suit);
											scoringCard4.setPlayingCard(card5value, card5suit);

										} else {

											if (card3value == card4value){

												scoringCard1.setPlayingCard(card3value, card3suit);
												scoringCard2.setPlayingCard(card1value, card1suit);
												scoringCard3.setPlayingCard(card2value, card2suit);
												scoringCard4.setPlayingCard(card5value, card5suit);

											} else {

												scoringCard1.setPlayingCard(card4value, card4suit);
												scoringCard2.setPlayingCard(card1value, card1suit);
												scoringCard3.setPlayingCard(card2value, card2suit);
												scoringCard4.setPlayingCard(card3value, card3suit);

											} 
										} 
									} 

								} else {

									handValue = HIGH_CARD;

									scoringCard1.setPlayingCard(card1value, card1suit);
									scoringCard2.setPlayingCard(card2value, card2suit);
									scoringCard3.setPlayingCard(card3value, card3suit);
									scoringCard4.setPlayingCard(card4value, card4suit);
									scoringCard5.setPlayingCard(card5value, card5suit);

								}
							}
						}
					}
				}
			}
		}
	}

	private  boolean twoCalculator() {

		return (card1value == card2value) ||
		(card2value == card3value) ||
		(card3value == card4value) ||
		(card4value == card5value);

	}

	private  boolean twoTwoCalculator() {

		return (card1value == card2value && card3value == card4value) ||
		(card1value == card2value && card4value == card5value) ||
		(card2value == card3value && card4value == card5value);
	}

	private  boolean threeCalculator() {

		return (card1value == card3value) ||
		(card2value == card4value) ||
		(card3value == card5value);

	}

	private  boolean fullHouseCalculator() {

		return (card1value == card3value && card4value == card5value) ||
		(card1value == card2value && card3value == card5value);
	}

	private  boolean fourCalculator() {

		return (card1value == card4value) ||
		(card2value == card5value);

	}

	private  boolean flushCalculator() {

		return (card1suit == card2suit &&
				card2suit == card3suit &&
				card3suit == card4suit &&
				card4suit == card5suit);

	}

	/* determines whether a hand is a straight - accounting for ace high or low
	 * @return boolean: true if hand is a straight
	 */

	private boolean straightCalculator() {

		return 
		//normal straight
		(card1value == card2value + 1 && 
				card2value == card3value + 1 && 
				card3value == card4value + 1 && 
				card4value == card5value + 1) ||

				//straight 5432A
				(card1value == 1 &&
						card2value == 5 &&
						card3value == 4 &&
						card4value == 3 &&
						card5value == 2) ||

						//straight AKQJ10
						(card1value == 1 &&
								card2value == 13 &&
								card3value == 12 &&
								card4value == 11 &&
								card5value == 10);

	}


	//.................................................................................................................................................................

	public void evaluate(Hand hand) {


		hand.sort();

		card1 = hand.getCard(0);
		card2 = hand.getCard(1);
		card3 = hand.getCard(2);
		card4 = hand.getCard(3);
		card5 = hand.getCard(4);

		card1value = card1.getPlayingCardValue();
		card2value = card2.getPlayingCardValue();
		card3value = card3.getPlayingCardValue();
		card4value = card4.getPlayingCardValue();
		card5value = card5.getPlayingCardValue();

		card1suit = card1.getPlayingCardSuit();
		card2suit = card2.getPlayingCardSuit();
		card3suit = card3.getPlayingCardSuit();
		card4suit = card4.getPlayingCardSuit();
		card5suit = card5.getPlayingCardSuit();

		// Assume highest sorted card is most valuable for the moment
		scoringCard1.setPlayingCard(card1value, card1suit);
		// set other cards to 0 value
		scoringCard2.setPlayingCard(0, 0);
		scoringCard3.setPlayingCard(0, 0);
		scoringCard4.setPlayingCard(0, 0);
		scoringCard5.setPlayingCard(0, 0);

		setHandValue();

		/* treat aces as high */

		if(scoringCard1.getPlayingCardValue() == 1) {
			scoringCard1.setPlayingCard(14, scoringCard1.getPlayingCardSuit());
		}
		if(scoringCard2.getPlayingCardValue() == 1) {
			scoringCard2.setPlayingCard(14, scoringCard2.getPlayingCardSuit());
		}
		if(scoringCard3.getPlayingCardValue() == 1) {
			scoringCard2.setPlayingCard(14, scoringCard3.getPlayingCardSuit());
		}
		if(scoringCard4.getPlayingCardValue() == 1) {
			scoringCard2.setPlayingCard(14, scoringCard4.getPlayingCardSuit());
		}
		if(scoringCard5.getPlayingCardValue() == 1) {
			scoringCard2.setPlayingCard(14, scoringCard5.getPlayingCardSuit());
		}

		//if(highCardWithout == 1){highCardWithout = 14;}
		//if(highCardWithout2 == 1){highCardWithout2 = 14;}
		//if(highCardWithout3 == 1){highCardWithout3 = 14;}
		//if(highCardWithout4 == 1){highCardWithout4 = 14;}


		//		// NEED TO REORGANISE AND REMOVE THIS TEST CODE BLOCK
		//		int tempCardValue;
		//		if (scoringCard1.getPlayingCardValue() == 1) {
		//			tempCardValue = 14;
		//		} else {
		//			tempCardValue = scoringCard1.getPlayingCardValue();
		//		}

		//setHandScore(handValue + 1139 + scoringCard1.getPlayingCardValue() + 563 + scoringCard2.getPlayingCardValue() + 275 + highCardWithout + 131 + highCardWithout2 + 59 + highCardWithout3 + 23 + highCardWithout4 + 5 + scoringCard1.getPlayingCardSuit());
		//setHandScore(handValue + scoringCard1.getPlayingCardValue() + 563 + scoringCard2.getPlayingCardValue() + 275 + scoringCard3.getPlayingCardValue() + 131 + scoringCard4.getPlayingCardValue() + 59 + scoringCard5.getPlayingCardValue() + 5 + scoringCard1.getPlayingCardSuit());

		setHandScore(
				(handValue * 2151296)
				+ (scoringCard1.getPlayingCardValue() * 153664)
				+ (scoringCard2.getPlayingCardValue() * 10976)
				+ (scoringCard3.getPlayingCardValue() * 784)
				+ (scoringCard4.getPlayingCardValue() * 56)
				+ (scoringCard5.getPlayingCardValue() * 4)
				+ scoringCard1.getPlayingCardSuit()
		);
		

		if(scoringCard1.getPlayingCardValue() == 14) {
			scoringCard1.setPlayingCard(1, scoringCard1.getPlayingCardSuit());
		}
		if(scoringCard2.getPlayingCardValue() == 14) {
			scoringCard2.setPlayingCard(1, scoringCard2.getPlayingCardSuit());
		}
		if(scoringCard3.getPlayingCardValue() == 14) {
			scoringCard2.setPlayingCard(1, scoringCard3.getPlayingCardSuit());
		}
		if(scoringCard4.getPlayingCardValue() == 14) {
			scoringCard2.setPlayingCard(1, scoringCard4.getPlayingCardSuit());
		}
		if(scoringCard5.getPlayingCardValue() == 14) {
			scoringCard2.setPlayingCard(1, scoringCard5.getPlayingCardSuit());
		}
		
		setHandName();

	}

	public String getHandName() {
		return this.handName;
	}

	private void setHandName() {
		String handName = "";

		switch (handValue) {
		case HIGH_CARD:
			handName = "High Card (" + scoringCard1.getPlayingCardFullName() + ")";
			break;
		case ONEPAIR:
			handName = "One Pair (" + scoringCard1.getPlayingCardValueNamePlural() + ")";
			break;
		case TWOPAIR:
			handName = "Two Pair (" + scoringCard1.getPlayingCardValueNamePlural() + " and " + scoringCard2.getPlayingCardValueNamePlural() + ")";
			break;
		case THREEOFAKIND:
			handName = "Three of a kind (" + scoringCard1.getPlayingCardValueNamePlural() + ")";
			break;
		case STRAIGHT:
			handName = "Straight (" + scoringCard1.getPlayingCardValueName() + " high)";
			break;
		case FLUSH:
			handName = "Flush (" + scoringCard1.getPlayingCardSuitName() + ")";
			break;
		case FULLHOUSE:
			handName = "Full House (" + scoringCard1.getPlayingCardValueNamePlural() + " full of " + scoringCard2.getPlayingCardValueNamePlural() +  ")";
			break;
		case FOUROFAKIND:
			handName = "Four of a kind (" + scoringCard1.getPlayingCardValueNamePlural() + ")";
			break;
		case STRAIGHT_FLUSH:
			handName = "Straight Flush (" + scoringCard1.getPlayingCardFullName() + " high)";
			break;
		default:
			handName = "ERROR";
			break;
		}

		this.handName = handName;
	}

	public int getHandScore() {
		return handScore;
	}

	private void setHandScore(int handScore) {
		this.handScore = handScore;
	}


}
