package tests;

import org.junit.Test;
import game.Deck;
import game.Evaluator;
import game.Hand;
import game.PlayingCard;
import ai.DealerAI;

public class DealerAITest {

	Deck testDeck = new Deck();
	Hand testHand = new Hand(testDeck);
	DealerAI dealerAI;
	
	@Test
	/**
	 * tests to see if the sort method works correctly 
	 * TOM ANNOTATE
	 */
	public void sortTest(){

		PlayingCard nine = testDeck.PlayingCardFactory();
		nine.setPlayingCard(9, 1);
		
		PlayingCard two = testDeck.PlayingCardFactory();
		two.setPlayingCard(2, 2);
		
		PlayingCard three = testDeck.PlayingCardFactory();
		three.setPlayingCard(3, 3);
		
		PlayingCard four = testDeck.PlayingCardFactory();
		four.setPlayingCard(4, 4);
		
		PlayingCard five = testDeck.PlayingCardFactory();
		five.setPlayingCard(5, 1);
		

		testHand.clear();
		testHand.add(nine);
		testHand.add(two);
		testHand.add(three);
		testHand.add(four);
		testHand.add(five);


		//GuiToGameLink.printHand("testHand");
		
		DealerAI dealerAI = new DealerAI(testHand);
		
		//GuiToGameLink.printHand("testHand");
	}
}
