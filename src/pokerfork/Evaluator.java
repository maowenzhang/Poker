package pokerfork;

import poker.PlayingCard;

public class Evaluator {
	
	private Hand hand1;
	private Hand hand2;
	
	private final int STRAIGHT_FLUSH = 8;
	private final int FOUROFAKIND = 7;
	private final int FULLHOUSE = 6;
	private final int FLUSH = 5;
	private final int STRAIGHT = 4;
	private final int THREEOFAKIND = 3;
	private final int TWOPAIR = 2;
	private final int ONEPAIR = 1;
	
	private void setHands(Hand first, Hand second){
		
		hand1 = first;
		hand2 = second;
	}
	
	/*
	 * Determines the value of a given hand.
	 * 
	 * @param the hand being evaluated
	 * @return array consisting of: i) value of the hand, ii) the high card within the hand, iii) the high card 
	 * without the hand - where applicable
	 */
	
	private int[] getHandValue(Hand hand){
		
		int handValue = 0;
		int highCardWithin = 0;
		int highCardWithout = 0;
		
		int returnValue[] = {handValue,highCardWithin,highCardWithout};
		
		/* Sorts the hand by card values*/
		for (int i = 0; i < Hand.HANDSIZE;i++){
			
			//hand.getHandValue()[i][0]
			
			
		}
		
		
		return returnValue;
	}
}

	
	
	
