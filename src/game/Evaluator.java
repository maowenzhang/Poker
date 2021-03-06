package game;

import java.util.logging.Logger;

/**
 * the evaluator class contains all the logic needed to evaluate a hand and determine the scoring
 * @author Tom & Jonathan
 *
 */
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

	private PlayingCard card1;
	private PlayingCard card2;
	private PlayingCard card3;
	private PlayingCard card4;
	private PlayingCard card5;

	private int card1value;
	private int card2value;
	private int card3value;
	private int card4value;
	private int card5value;

	private int card1suit;
	private int card2suit;
	private int card3suit;
	private int card4suit;
	private int card5suit;

	private int handType;
	private PlayingCard scoringCard1;
	private PlayingCard scoringCard2;
	private PlayingCard scoringCard3;
	private PlayingCard scoringCard4;
	private PlayingCard scoringCard5;

	/**
	 * These weightings designed to ensure that the hand score ultimately returns a unique
	 * integer, which will correctly place the hand in the order of precedence.
	 * Each element of the score is multiplied by the maximum value of the immediately lower
	 * stage of the calculation after its own weighting. Accordingly, these factors are calculated backwards.
	 * The last possible element is the suit value of the highest scoring card - being a suit, this is one of 4 values.
	 * The penultimate element is the card face value of the 5th scoring card (range 2-14)
	 *  - this is multiplied by the maximum value of the previous component (4), meaning that the maximum value is 4*14 (56).
	 * The next element of the calculation is the face value of the 4th scoring card (range 2-14). This is multiplied by the
	 * maximum value of the previous component (56), meaning that the maximum value is 56*14 (784).
	 * This pattern is extended to all weightings. Possible value ranges displayed below for reference.
	 */
	private static final int HandTypeWeighting = 2151296; //range of values 0-8
	private static final int ScoringCard1Weighting = 153664; //range of values 2-14
	private static final int ScoringCard2Weighting = 10976; //range of values 2-14
	private static final int ScoringCard3Weighting = 784; //range of values 2-14
	private static final int ScoringCard4Weighting = 56; //range of values 2-14
	private static final int ScoringCard5Weighting = 4; //range of values 2-14

	private int handScore;
	private String handName;

	private int[][] cardMatcher = {{0,0},{0,0},{0,0},{0,0},{0,0}};

	/*
	 * Arranges the hand in order from highest to lowest and sets records each card's suit and value
	 * 
	 */
	/**
	 * With the hand now already set in order from highest to lowest (ace high), determines the hand value and what the scoring  
	 * cards are. The system works by principle of elimination going from the best hand to the worst - once the hand value is 
	 * found, the logic exits. Thus there is no confusion as to whether hand is a full house or a three of a kind or a pair etc.
	 */

	public  void setHandValue(){

		if(straightCalculator()){

			setHandType(STRAIGHT);

			if(flushCalculator()){
				setHandType(STRAIGHT_FLUSH);	
			}

			// Straight 5432A - so 5 (2nd card in sort) is higher than ace - only incidence where A is not high
			if(card1value == 1 && card2value == 5){
				scoringCard1.setPlayingCard(card2value, card2suit);
			}

		} else {

			if(fourCalculator()) {

				setHandType(FOUROFAKIND);

				// if kicker is higher value than the other 4, then - 2nd card is part of 4, so may be used as high card
				if(card1value != card2value) {
					scoringCard1.setPlayingCard(card2value, card2suit);
				}

			} else {

				if(fullHouseCalculator()){

					setHandType(FULLHOUSE);

					// if 2nd and 3rd cards in sort are not same face value, then 3ofKind must be lower value than Pair, so need to reset high card values
					if(card2value != card3value){
						scoringCard1.setPlayingCard(card3value, card3suit);
						scoringCard2.setPlayingCard(card1value, card1suit);
					} else {
						scoringCard2.setPlayingCard(card4value, card4suit);
					}

				} else {

					if(flushCalculator()){

						setHandType(FLUSH);

						scoringCard2.setPlayingCard(card2value, card2suit);
						scoringCard3.setPlayingCard(card3value, card3suit);
						scoringCard4.setPlayingCard(card4value, card4suit);
						scoringCard5.setPlayingCard(card5value, card5suit);

					} else {

						if(threeCalculator()){

							setHandType(THREEOFAKIND);
							
							if(card2value == card4value){

								scoringCard1.setPlayingCard(card2value, card2suit);

								//flags cards which aren't in the threesome for possible deletion
								setCardMatcher(1, card1value, 1);
								//setCardMatcher(2, card2value, 0);
								//setCardMatcher(3, card3value, 0);
								//setCardMatcher(4, card4value, 0);
								setCardMatcher(5, card5value, 1);

							} else {

								if(card3value == card5value){

									scoringCard1.setPlayingCard(card3value, card3suit);

									//flags cards which aren't in the threesome for possible deletion
									setCardMatcher(1, card1value, 1);
									setCardMatcher(2, card2value, 1);
									//setCardMatcher(3, card3value, 0);
									//setCardMatcher(4, card4value, 0);
									//setCardMatcher(5, card5value, 0);
								} else {

									//flags cards which aren't in the threesome for possible deletion
									//setCardMatcher(1, card1value, 0);
									//setCardMatcher(2, card2value, 0);
									//setCardMatcher(3, card3value, 0);
									setCardMatcher(4, card4value, 1);
									setCardMatcher(5, card5value, 1);
								}
							}

						} else {

							if(twoTwoCalculator()) {

								setHandType(TWOPAIR);

								// ERROR?!?!?!? ... now cured???.......................................................................................

								if(card1value == card2value) {

									if(card3value == card4value) {

										scoringCard2.setPlayingCard(card3value, card3suit);
										scoringCard3.setPlayingCard(card5value, card5suit);

										//flags card which isn't in the two pairs for possible deletion
										setCardMatcher(5, card5value, 1);

									} else {

										scoringCard2.setPlayingCard(card4value, card4suit);
										scoringCard3.setPlayingCard(card3value, card3suit);

										//flags card which isn't in the two pairs for possible deletion
										setCardMatcher(3, card3value, 1);
									}

								} else {

									scoringCard1.setPlayingCard(card2value, card2suit);
									scoringCard2.setPlayingCard(card4value, card4suit);
									scoringCard3.setPlayingCard(card1value, card1suit);

									//flags card which isn't in the two pairs for possible deletion
									setCardMatcher(1, card1value, 1);
								}

							} else {

								if(twoCalculator()){

									setHandType(ONEPAIR);

									if (card1value == card2value) {

										scoringCard2.setPlayingCard(card3value, card3suit);
										scoringCard3.setPlayingCard(card4value, card4suit);
										scoringCard4.setPlayingCard(card5value, card5suit);

										//flags cards which aren't in the pair for possible deletion
										setCardMatcher(3, card3value, 1);
										setCardMatcher(4, card4value, 1);
										setCardMatcher(5, card5value, 1);
									} else {

										if(card2value == card3value){

											scoringCard1.setPlayingCard(card2value, card2suit);
											scoringCard2.setPlayingCard(card1value, card1suit);
											scoringCard3.setPlayingCard(card4value, card4suit);
											scoringCard4.setPlayingCard(card5value, card5suit);

											//flags cards which aren't in the pair for possible deletion
											setCardMatcher(1, card1value, 1);
											setCardMatcher(4, card4value, 1);
											setCardMatcher(5, card5value, 1);

										} else {

											if (card3value == card4value){

												scoringCard1.setPlayingCard(card3value, card3suit);
												scoringCard2.setPlayingCard(card1value, card1suit);
												scoringCard3.setPlayingCard(card2value, card2suit);
												scoringCard4.setPlayingCard(card5value, card5suit);

												//flags cards which aren't in the pair for possible deletion
												setCardMatcher(1, card1value, 1);
												setCardMatcher(2, card2value, 1);
												setCardMatcher(5, card5value, 1);
											} else {

												scoringCard1.setPlayingCard(card4value, card4suit);
												scoringCard2.setPlayingCard(card1value, card1suit);
												scoringCard3.setPlayingCard(card2value, card2suit);
												scoringCard4.setPlayingCard(card3value, card3suit);

												//flags cards which aren't in the pair for possible deletion
												setCardMatcher(1, card1value, 1);
												setCardMatcher(2, card2value, 1);
												setCardMatcher(3, card3value, 1);
											} 
										} 
									} 

								} else {

									setHandType(HIGH_CARD);

									scoringCard1.setPlayingCard(card1value, card1suit);
									scoringCard2.setPlayingCard(card2value, card2suit);
									scoringCard3.setPlayingCard(card3value, card3suit);
									scoringCard4.setPlayingCard(card4value, card4suit);
									scoringCard5.setPlayingCard(card5value, card5suit);

									//flags lowest 3 cards for possible deletion
									setCardMatcher(3, card3value, 1);
									setCardMatcher(4, card4value, 1);
									setCardMatcher(5, card5value, 1);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Determines if hand is a pair
	 * @return true if hand is a pair
	 */
	private  boolean twoCalculator() {

		return (card1value == card2value) ||
		(card2value == card3value) ||
		(card3value == card4value) ||
		(card4value == card5value);

	}

	/**
	 * Determines if hand is two pair
	 * @return true if hand is two pair
	 */
	private  boolean twoTwoCalculator() {

		return (card1value == card2value && card3value == card4value) ||
		(card1value == card2value && card4value == card5value) ||
		(card2value == card3value && card4value == card5value);
	}

	/**
	 * Determines if hand is a three of a kind
	 * @return true if hand is a three of a kind
	 */
	private  boolean threeCalculator() {

		return (card1value == card3value) ||
		(card2value == card4value) ||
		(card3value == card5value);

	}

	/**
	 * Determines if hand is a full house
	 * @return true if hand is a full house
	 */
	private  boolean fullHouseCalculator() {

		return (card1value == card3value && card4value == card5value) ||
		(card1value == card2value && card3value == card5value);
	}

	/**
	 * Determines if hand is a four of a kind
	 * @return true if hand is a four of a kind
	 */
	private  boolean fourCalculator() {

		return (card1value == card4value) ||
		(card2value == card5value);

	}

	/**
	 * Determines if hand is a flush
	 * @return true if hand is a flush
	 */
	private  boolean flushCalculator() {

		return (card1suit == card2suit &&
				card2suit == card3suit &&
				card3suit == card4suit &&
				card4suit == card5suit);

	}

	/* determines whether a hand is a straight - accounting for ace high or low
	 * @return boolean: true if hand is a straight
	 */

	/** determines whether a hand is a straight - accounting for ace high or low
	 * @return true if hand is a straight
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

	/**
	 * Sorts the hand from highest to lowest... TOM ANNOTATE
	 * @param player or dealer's hand to be evaluated
	 */
	public void evaluate(Hand hand) {


		hand.sort();

		Logger log = Logger.getLogger("NewLogger");
		log.info("HAND: \n" + hand.getCard(0).getPlayingCardFullName() + "\n" + hand.getCard(1).getPlayingCardFullName() + "\n" + hand.getCard(2).getPlayingCardFullName() + "\n" + hand.getCard(3).getPlayingCardFullName() + "\n" + hand.getCard(4).getPlayingCardFullName());
		
		
		card1 = hand.get(0);
		card2 = hand.get(1);
		card3 = hand.get(2);
		card4 = hand.get(3);
		card5 = hand.get(4);

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


		scoringCard1 = new PlayingCard();
		scoringCard2 = new PlayingCard();
		scoringCard3 = new PlayingCard();
		scoringCard4 = new PlayingCard();
		scoringCard5 = new PlayingCard();


		// Assume highest sorted card is most valuable for the moment
		scoringCard1.setPlayingCard(card1value, card1suit);
		// set other cards to 0 value
		scoringCard2.setPlayingCard(0, 0);
		scoringCard3.setPlayingCard(0, 0);
		scoringCard4.setPlayingCard(0, 0);
		scoringCard5.setPlayingCard(0, 0);

		setHandValue();

		setHandName();

		/* treat aces as high */
		swapCardValues(1, 14);

		setHandScore(
				(getHandType() * getHandTypeWeighting())
				+ (scoringCard1.getPlayingCardValue() * ScoringCard1Weighting)
				+ (scoringCard2.getPlayingCardValue() * ScoringCard2Weighting)
				+ (scoringCard3.getPlayingCardValue() * ScoringCard3Weighting)
				+ (scoringCard4.getPlayingCardValue() * ScoringCard4Weighting)
				+ (scoringCard5.getPlayingCardValue() * ScoringCard5Weighting)
				+ scoringCard1.getPlayingCardSuit()
		);
		
		
		//for testing!
		//setCardMatcher();
	}

	/**
	 * TOM ANNOTATE
	 * @return
	 */
	public int[][] getCardMatcher() {
		return cardMatcher;
	}

	/**
	 * TOM ANNOTATE
	 * @param cardNumber
	 * @param forDisposal
	 */
	private void setCardMatcher(int cardPos, int cardFaceValue, int forPossibleDisposal) {
		//5*2 array, capturing the cards in order, and flagging with 0 for 'keep' and 1 for 'delete'
		cardMatcher[cardPos-1][0] = cardFaceValue;
		cardMatcher[cardPos-1][1] = forPossibleDisposal;
	}

	/**
	 * TOM ANNOTATE	
	 */
	//FOR TESTING
	private void setCardMatcher() {
		//TEST
		for (int loopCounter = 0; loopCounter <= 4; loopCounter++) {
			cardMatcher[loopCounter][0] = loopCounter;
			if (loopCounter%2 == 0) {
				cardMatcher[loopCounter][1] = 0;
			} else {
				cardMatcher[loopCounter][1] = 1;
			}
		}
	}
	
	/**
	 * TOM ANNOTATE
	 * @param swapThis
	 * @param forThat
	 */
	private void swapCardValues(int swapThis, int forThat) {
		if(scoringCard1.getPlayingCardValue() == swapThis) {
			scoringCard1.setPlayingCard(forThat, scoringCard1.getPlayingCardSuit());
		}
		if(scoringCard2.getPlayingCardValue() == swapThis) {
			scoringCard2.setPlayingCard(forThat, scoringCard2.getPlayingCardSuit());
		}
		if(scoringCard3.getPlayingCardValue() == swapThis) {
			scoringCard2.setPlayingCard(forThat, scoringCard3.getPlayingCardSuit());
		}
		if(scoringCard4.getPlayingCardValue() == swapThis) {
			scoringCard2.setPlayingCard(forThat, scoringCard4.getPlayingCardSuit());
		}
		if(scoringCard5.getPlayingCardValue() == swapThis) {
			scoringCard2.setPlayingCard(forThat, scoringCard5.getPlayingCardSuit());
		}
	}

	/**
	 * getter method - returns the name of the hand (e.g. pair, three of a kind etc.)
	 * @return the name of the hand
	 */
	public String getHandName() {
		return this.handName;
	}

	/**
	 * setter method - sets the name of the hand
	 */
	private void setHandName() {
		String handName = "";
		boolean plural = true;

		switch (getHandType()) {
		case HIGH_CARD:
			handName = "High Card (" + scoringCard1.getPlayingCardFullName() + ")";
			break;
		case ONEPAIR:
			handName = "One Pair (" + scoringCard1.getPlayingCardValueName(plural) + ")";
			break;
		case TWOPAIR:
			handName = "Two Pair (" + scoringCard1.getPlayingCardValueName(plural) + " and " + scoringCard2.getPlayingCardValueName(plural) + ")";
			break;
		case THREEOFAKIND:
			handName = "Three of a kind (" + scoringCard1.getPlayingCardValueName(!plural) + ")";
			break;
		case STRAIGHT:
			handName = "Straight (" + scoringCard1.getPlayingCardValueName(!plural) + " high)";
			break;
		case FLUSH:
			handName = "Flush (" + scoringCard1.getPlayingCardSuitName() + ")";
			break;
		case FULLHOUSE:
			handName = "Full House (" + scoringCard1.getPlayingCardValueName(plural) + " full of " + scoringCard2.getPlayingCardValueName(plural) +  ")";
			break;
		case FOUROFAKIND:
			handName = "Four of a kind (" + scoringCard1.getPlayingCardValueName(plural) + ")";
			break;
		case STRAIGHT_FLUSH:
			if (scoringCard1.getPlayingCardValue() == 1) {
				handName = "Royal Flush (" + scoringCard1.getPlayingCardFullName() + " high)";
			} else {
				handName = "Straight Flush (" + scoringCard1.getPlayingCardFullName() + " high)";
			}
			break;
		default:
			handName = "ERROR";
			break;
		}

		this.handName = handName;
	}

	/**
	 * getter method - returns the hand score
	 * @return the hand score 
	 */
	public int getHandScore() {
		return handScore;
	}

	/**
	 * setter method - sets the hand's score
	 * @param the hand's score to set
	 */
	private void setHandScore(int handScore) {
		this.handScore = handScore;
	}

	/** 
	 * TOM ANNOTATE
	 * @return
	 */
	public int getHandTypeWeighting() {
		return HandTypeWeighting;
	}

	/**
	 * ANNOTATE - BUT HAVENT WE ALREADY GOT A GETTER / SETTER THAT DOES THE SAME THING AS THIS??
	 * @param handType
	 */
	public void setHandType(int handType) {
		this.handType = handType;
	}

	/**
	 * ANNOTATE - BUT HAVENT WE ALREADY GOT A GETTER / SETTER THAT DOES THE SAME THING AS THIS??
	 * @return
	 */
	public int getHandType() {
		return handType;
	}


}
