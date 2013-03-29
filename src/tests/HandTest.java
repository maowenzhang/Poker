package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import pokerfork.Deck;
import pokerfork.Hand;

public class HandTest {
	
	Deck deck = new Deck();
	Hand testHand1 = new Hand(deck);
	Hand testHand2 = new Hand(deck);
	
	@Test
	public void sortTest(){
		
		testHand1.sort();
		
		assertTrue(testHand1.get(0).getPlayingCardValue() >= testHand1.get(1).getPlayingCardValue() || testHand1.get(0).getPlayingCardValue() == 1);
		assertTrue(testHand1.get(1).getPlayingCardValue() >= testHand1.get(2).getPlayingCardValue() || testHand1.get(1).getPlayingCardValue() == 1);
		assertTrue(testHand1.get(2).getPlayingCardValue() >= testHand1.get(3).getPlayingCardValue() || testHand1.get(2).getPlayingCardValue() == 1);
		assertTrue(testHand1.get(3).getPlayingCardValue() >= testHand1.get(4).getPlayingCardValue() || testHand1.get(3).getPlayingCardValue() == 1);

	}
	
/*	@Test
	public void getHandValueTest() {
		
		int [] expectedCardValues = new int[Hand.HANDSIZE];
		int [] expectedSuitValues = new int[Hand.HANDSIZE];
		
		int [] actualCardValues = new int[Hand.HANDSIZE];
		int [] actualSuitValues = new int[Hand.HANDSIZE];
		
		
		 * for each card in the hand marks down that card's value and suit before
		 * determines each card's value and suit using getHandValue method and 
		 * testing to see if they match up to the declared values
		 
		
		for (int i = 0; i < Hand.HANDSIZE; i++){
			
			expectedCardValues[i] = testHand2.get(i).getPlayingCardValue();
			expectedSuitValues[i] = testHand2.get(i).getPlayingCardSuit();
			
			actualCardValues[i] = testHand2.getHandValue()[i][0];	
			actualSuitValues[i] = testHand2.getHandValue()[i][1];
			
			assertEquals(expectedCardValues[i],actualCardValues[i]);
			assertEquals(expectedSuitValues[i],actualSuitValues[i]);
			
		}
		
	}*/

}
