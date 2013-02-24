package pokerfork;

import java.util.Collections;

@SuppressWarnings("serial")
public class Hand extends CardStack{
	
	public static final int HANDSIZE = 5;
	
	
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
	
}