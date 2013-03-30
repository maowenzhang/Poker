package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import pokerfork.Deck;
import pokerfork.Evaluator;
import pokerfork.Hand;
import pokerfork.PlayingCard;

public class EvaluatorTest {
	
	Deck testDeck = new Deck();
	Hand testHand1 = new Hand(testDeck);
	Hand testHand2 = new Hand(testDeck);
	Evaluator testEvaluator = new Evaluator();

	@Test
	public void royalFlushVsAceHighTest() {
	
		PlayingCard ace = testDeck.PlayingCardFactory();
		ace.setPlayingCard(1, 1);
		
		PlayingCard king = testDeck.PlayingCardFactory();
		king.setPlayingCard(13, 1);
		
		PlayingCard queen = testDeck.PlayingCardFactory();
		queen.setPlayingCard(12, 1);
		
		PlayingCard jack = testDeck.PlayingCardFactory();
		jack.setPlayingCard(11, 1);

		PlayingCard ten = testDeck.PlayingCardFactory();
		ten.setPlayingCard(10, 1);
		
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
		

		testHand1.clear();
		testHand1.add(ace);
		testHand1.add(king);
		testHand1.add(queen);
		testHand1.add(jack);
		testHand1.add(ten);
		testHand1.evaluate();
		int testHand1Value = testHand1.getHandScore();
		
		testHand2.clear();
		testHand2.add(ace);
		testHand2.add(king);
		testHand2.add(queen);
		testHand2.add(jack);
		testHand2.add(nine);
		testHand2.evaluate();
		int testHand2Value = testHand2.getHandScore();
		
		assertTrue("FAIL MESSAGE: " + testHand1Value + " NOT larger than " + testHand2Value + ". Difference is " + (testHand1Value - testHand2Value),testHand1Value > testHand2Value);
	}
	
	@Test
	public void royalFlushTest() {
	
		PlayingCard ace = testDeck.PlayingCardFactory();
		ace.setPlayingCard(1, 1);
		
		PlayingCard king = testDeck.PlayingCardFactory();
		king.setPlayingCard(13, 1);
		
		PlayingCard queen = testDeck.PlayingCardFactory();
		queen.setPlayingCard(12, 1);
		
		PlayingCard jack = testDeck.PlayingCardFactory();
		jack.setPlayingCard(11, 1);

		PlayingCard ten = testDeck.PlayingCardFactory();
		ten.setPlayingCard(10, 1);
		

		testHand1.clear();
		testHand1.add(ace);
		testHand1.add(king);
		testHand1.add(queen);
		testHand1.add(jack);
		testHand1.add(ten);
		testHand1.evaluate();
		
		String testHand1Desc = testHand1.getHandDescription();
		
		assertEquals("Royal Flush (Ace of Clubs high)", testHand1Desc);
	}
	
	@Test
	public void fourOfAKindTest() {
	
		PlayingCard seven1 = testDeck.PlayingCardFactory();
		seven1.setPlayingCard(7, 2);
		
		PlayingCard seven2 = testDeck.PlayingCardFactory();
		seven2.setPlayingCard(7, 4);
		
		PlayingCard nine = testDeck.PlayingCardFactory();
		nine.setPlayingCard(9, 2);
		
		PlayingCard seven3 = testDeck.PlayingCardFactory();
		seven3.setPlayingCard(7, 3);

		PlayingCard seven4 = testDeck.PlayingCardFactory();
		seven4.setPlayingCard(7, 1);
		

		testHand1.clear();
		testHand1.add(seven1);
		testHand1.add(seven2);
		testHand1.add(nine);
		testHand1.add(seven3);
		testHand1.add(seven4);
		testHand1.evaluate();
		
		String testHand1Desc = testHand1.getHandDescription();
		
		assertEquals("Four of a kind (Sevens)", testHand1Desc);
	}

}
