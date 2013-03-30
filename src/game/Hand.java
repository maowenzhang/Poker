package game;

import java.util.Collections;

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
	Evaluator handEvaluator = new Evaluator();
	
	
	/* constructor requires a deck to deal cards to the hand */
	public Hand (Deck deck){
		
		for (int i = 0; i < HANDSIZE; i++){
			
			add(deck.dealCard());
			
		}
		
	}
	
	/* Sorts the hand by card values - ace is high*/
	public void sort(){
	
		Collections.sort(this);
		Collections.reverse(this);
	}

	/* 
	 * Getter method for purpose of evaluating contents of hand
	 * @return two dimensional array consisting of i)card values, ii) suits
	 */

////MAKE THIS OVERRIDE A REQUEST FOR -1 (SO THAT WE CAN CALL CARD'S 1-5, NOT 0-4)
	public PlayingCard get(int cardPosition) {
		return super.get(cardPosition);
	}
	
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

	
	public void evaluate() {
		handEvaluator.evaluate(this);
		setHandDescription();
		setHandScore();
		setHandTypeWeighting();
		setCardMatcher();
	}

	private void setCardMatcher() {
		this.cardMatcher = new int[5][2];
		this.cardMatcher = handEvaluator.getCardMatcher();
	}
	
	public int[][] getCardMatcher() {
		return this.cardMatcher;
	}

	private void setHandScore() {
		this.handScore = handEvaluator.getHandScore();
	}
	
	public int getHandScore() {
		return this.handScore;
	}

	public void setHandDescription(){
		this.handDescription = handEvaluator.getHandName();
	}

	public String getHandDescription() {
		return this.handDescription;
	}
	
	public void setHandType(){
		this.handType = handEvaluator.getHandType();
	}

	public int getHandType() {
		return this.handType;
	}
	
	/////possibly delete - may not be bneeded for AI
	public void setHandTypeWeighting() {
		this.handTypeWeighting = handEvaluator.getHandTypeWeighting();
	}

	/////possibly delete - may not be bneeded for AI
	public int getHandTypeWeighting() {
		return this.handTypeWeighting;
	}

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

	public PlayingCard getCard(int cardPosition) {
		return this.get(cardPosition);
	}

	public int getCardToDisplay(int cardNumber) {
		int cardValue = ((getCard(cardNumber-1).getPlayingCardSuit()-1)*13) + getCard(cardNumber-1).getPlayingCardValue();
		return cardValue;
	}
	
}