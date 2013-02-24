package pokerfork;

import static org.junit.Assert.*;

import org.junit.Test;

public class HandTest {
	
	Deck deck = new Deck();
	Hand testHand = new Hand(deck);

	@Test
	public void getHandValueTest() {
		
		int [] expectedCardValues = new int[Hand.HANDSIZE];
		int [] expectedSuitValues = new int[Hand.HANDSIZE];
		
		int [] actualCardValues = new int[Hand.HANDSIZE];
		int [] actualSuitValues = new int[Hand.HANDSIZE];
		
		/*
		 * for each card in the hand marks down that card's value and suit before
		 * determines each card's value and suit using getHandValue method and 
		 * testing to see if they match up to the declared values
		 */
		
		for (int i = 0; i < Hand.HANDSIZE; i++){
			
			expectedCardValues[i] = testHand.get(i).getPlayingCardValue();
			expectedSuitValues[i] = testHand.get(i).getPlayingCardSuit();
			
			actualCardValues[i] = testHand.getHandValue()[i][0];	
			actualSuitValues[i] = testHand.getHandValue()[i][1];
			
			assertEquals(expectedCardValues[i],actualCardValues[i]);
			assertEquals(expectedSuitValues[i],actualSuitValues[i]);
			
		}
		
	}

}
