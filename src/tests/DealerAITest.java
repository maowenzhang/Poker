package tests;

import static org.junit.Assert.assertEquals;
import ai.DealerAISmart;
import org.junit.Test;
import game.Deck;
import game.Hand;
import game.PlayingCard;

public class DealerAITest {

	Deck testDeck = new Deck();
	Hand testHand = new Hand(testDeck);
	DealerAISmart dealerAISmart;
	
	@Test
	/**
	 * test to make sure that Smart Dealer AI works correctly.
	 * DealerAI is given pair of 9's, a 3, and 8 and a 5
	 * - it should retain 9's and 8 (as this is over its "aggression" factor)
	 * and discard the 3 and 5. Accordingly it should discard 2 cards.
	 */
	public void smartDealerTest(){

		PlayingCard nine = testDeck.PlayingCardFactory();
		nine.setPlayingCard(9, 1);
		
		PlayingCard nine2 = testDeck.PlayingCardFactory();
		nine2.setPlayingCard(9, 2);
		
		PlayingCard three = testDeck.PlayingCardFactory();
		three.setPlayingCard(3, 3);
		
		PlayingCard four = testDeck.PlayingCardFactory();
		four.setPlayingCard(8, 4);
		
		PlayingCard five = testDeck.PlayingCardFactory();
		five.setPlayingCard(5, 1);
		

		testHand.clear();
		testHand.add(nine);
		testHand.add(nine2);
		testHand.add(three);
		testHand.add(four);
		testHand.add(five);

		DealerAISmart dealerAI = new DealerAISmart();

		dealerAI.setHand(testHand);
		dealerAI.setDeck(testDeck);
		dealerAI.evaluate();
		
		assertEquals(2 ,dealerAI.getNumOfCardsChanged());
	}
}
