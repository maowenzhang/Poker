package pokerfork;

import java.util.Collections;
//import pokerfork.EvaluatorTOM;

//import poker.EvaluatorTOM;

@SuppressWarnings("serial")
public class Hand extends CardStack{
	
	public static final int HANDSIZE = 5;
	private String handDescription;
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
	public int[][] getHandValue(){
		
		int[][] handValue = new int[HANDSIZE][2];
		
		for(int i = 0; i < HANDSIZE; i++){
			
				handValue[i][0]= get(i).getPlayingCardValue();
				handValue[i][1]= get(i).getPlayingCardSuit();
						
		}
		
		return handValue;
		
	}

	/*
	 * BECAUSE THIS HAS BEEN SUPERCEDED BY NEW JON VERSION
	 * 
	 * public int[] evaluate() {
		int[] evaluatedHand = EvaluatorTOM.getHandValue(this);
		setHandDescription(evaluatedHand);
		return evaluatedHand;
	}*/

	
	public int evaluate() {
		handEvaluator.evaluate(this);
		setHandDescription();
		return handEvaluator.getHandScore();
	}
	
	public void setHandDescription(){
		
		handDescription = handEvaluator.getHandName();
		
	}

	public String getHandDescription() {
		return handDescription;
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
	
}