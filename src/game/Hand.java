package game;

import java.util.Collections;

/**
 * the hand class contains five playing card objects that are dealt to it by the deck class. The hand class can call the 
 * evaluator class and pass messages about its state to the game controller class. It inherits from the card stack class.
 * @author Tom & Jonathan
 *
 */
@SuppressWarnings("serial")
public class Hand extends CardStack{

	public static final int HANDSIZE = 5;
	private String handDescription;
	private int handScore;
	private int handType;
	int[][] cardMatcher;
	
	//possibly delete
	private int handTypeWeighting;
	//private Evaluator handEvaluator;
	Evaluator handEvaluator;
	
	
	/**
	 *  constructor requires a deck to deal cards to the hand. Constructor builds an evaluator to evaluate the hand
	 */
	public Hand (Deck deck){
		
		handEvaluator = new Evaluator();
		
		for (int i = 0; i < HANDSIZE; i++){
			
			add(deck.dealCard());
			
		}
		
	}
	
	/**
	 *  Sorts the hand by card values - ace is high
	 */
	public void sort(){
	
		Collections.sort(this);
		Collections.reverse(this);
	}

	/** returns the card situated at the requested index 
	 * @param the index of the card position
	 * @return a playing card
	 */
////MAKE THIS OVERRIDE A REQUEST FOR -1 (SO THAT WE CAN CALL CARD'S 1-5, NOT 0-4)
	public PlayingCard get(int cardPosition) {
		return super.get(cardPosition);
	}
	/** 
	 * TOM ANNOTATE
	 */
////MAKE THIS OVERRIDE A REQUEST FOR -1 (SO THAT WE CAN CALL CARD'S 1-5, NOT 0-4)
	public PlayingCard set(int position, PlayingCard newCard) {
		return super.set(position, newCard);
	}

	/*
	 * BECAUSE THIS HAS BEEN SUPERCEDED BY NEW JON VERSION
	 * 
	 * public int[] evaluate() {
		int[] evaluatedHand = EvaluatorTOM.getHandValue(this);
		setHandDescription(evaluatedHand);
		return evaluatedHand;
	}*/

	/**
	 * asks evaluator to evaluate hand and calls methods to .... TOM ANNOTATE
	 */
	public void evaluate() {
		handEvaluator.evaluate(this);
		setHandDescription();
		setHandScore();
		setHandTypeWeighting();
		setCardMatcher();
	}

	/**
	 * TOM ANNOTATE
	 */
	private void setCardMatcher() {
		this.cardMatcher = new int[5][2];
		this.cardMatcher = handEvaluator.getCardMatcher();
	}
	
	/**
	 * TOM ANNOTATE
	 */
	public int[][] getCardMatcher() {
		return this.cardMatcher;
	}

	/**
	 * setter method - asks the evaluator for the hand score and sets it within hand
	 */
	private void setHandScore() {
		this.handScore = handEvaluator.getHandScore();
	}
	
	/**
	 * getter method - returns the hand's score
	 * @return the hand's score 
	 */
	public int getHandScore() {
		return this.handScore;
	}

	/**
	 * setter method - asks the evaluator for the and sets it within hand
	 * WE ALREADY HAVE A METHOD CALLED SETHANDDESCRIPTION - CAN WE MERGE THIS WITH THE OTHER??
	 */
	public void setHandDescription(){
		this.handDescription = handEvaluator.getHandName();
	}

	/**
	 * getter method -returns the hand description
	 * @return the hand description
	 */
	public String getHandDescription() {
		return this.handDescription;
	}
	
	/**
	 * setter method - sets the hand type (e.g. flush, straight etc.)
	 */
	public void setHandType(){
		this.handType = handEvaluator.getHandType();
	}

	/**
	 * getter method - gets the hand type (e.g. flush, straight etc.)
	 * @return the hand type
	 */
	public int getHandType() {
		return this.handType;
	}
	
	/**
	 * TOM ANNOTATE
	 */
	/////possibly delete - may not be bneeded for AI
	public void setHandTypeWeighting() {
		this.handTypeWeighting = handEvaluator.getHandTypeWeighting();
	}

	/**
	 * TOM ANNOTATE
	 * @return
	 */
	/////possibly delete - may not be bneeded for AI
	public int getHandTypeWeighting() {
		return this.handTypeWeighting;
	}

	/**
	 * setter method - sets the hand description
	 * @param TOM ANNOTATE
	 */
	public void setHandDescription(int[] evaluatedHand) {
		switch (evaluatedHand[0]) {
		case 0:
			this.handDescription+= "High Card";
			break;
		case 1:
			this.handDescription = "One Pair (" + evaluatedHand[1] + ")";
			break;
		case 2:
			this.handDescription = "Two Pair (" + evaluatedHand[1] + ")";
			break;
		case 4:
			this.handDescription = "Straight (" + evaluatedHand[1] + " high)";
			break;
		case 5:
			this.handDescription = "Flush";
			break;
		case 6:
			this.handDescription = "Full House (three " + evaluatedHand[1] + "'s)";
			break;
		case 7:
			this.handDescription = "Four of a kind (" + evaluatedHand[1] + ")";
			break;
		case 8:
			this.handDescription = "Straight Flush (" + evaluatedHand[1] + " high)";
			break;
		default:
			this.handDescription = "ERROR";
			break;
		}
	}

	/**
	 * getter method - gets a card from a certain index position within the hand
	 * @param the index position within the hand
	 * @return a playing card
	 */
	public PlayingCard getCard(int cardPosition) {
		return this.get(cardPosition);
	}

	/**
	 * getter method - gets the unique value of the card at a certain index position within the hand
	 * @param the index position within the hand
	 * @return the unique value of the card
	 */
	public int getCardToDisplay(int cardNumber) {
		//This calculation creates a unique numeric value that can be used by the GUI to determine which card to display
		int cardValue = ((getCard(cardNumber-1).getPlayingCardSuit()-1)*13) + getCard(cardNumber-1).getPlayingCardValue();
		return cardValue;
	}
	
}