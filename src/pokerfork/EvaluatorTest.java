package pokerfork;

import static org.junit.Assert.*;

import org.junit.Test;

public class EvaluatorTest {
	
	Deck testDeck = new Deck();
	Hand testHand = new Hand(testDeck);
	Evaluator testEvaluator = new Evaluator();

	/*tests whether flush calculator works - accounting for both high and low aces */
	@Test
	public void straightTest() {
		
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
		
		PlayingCard two = testDeck.PlayingCardFactory();
		two.setPlayingCard(2, 2);
		
		PlayingCard three = testDeck.PlayingCardFactory();
		three.setPlayingCard(3, 3);
		
		PlayingCard four = testDeck.PlayingCardFactory();
		four.setPlayingCard(4, 4);
		
		PlayingCard five = testDeck.PlayingCardFactory();
		five.setPlayingCard(5, 1);
		
		testHand.clear();
			
		testHand.add(0,ace);
		testHand.add(1,king);
		testHand.add(2,queen);
		testHand.add(3,jack);
		testHand.add(4,ten);
		
		int handValue = Evaluator.getHandValue(testHand)[0];
		
		assertEquals(Evaluator.STRAIGHT_FLUSH,handValue);
		
		testHand.clear();
		
		testHand.add(0,ace);
		testHand.add(1,five);
		testHand.add(2,four);
		testHand.add(3,three);
		testHand.add(4,two);
		
		handValue = Evaluator.getHandValue(testHand)[0];
		
		assertEquals(Evaluator.STRAIGHT,handValue);
	}
	
	
	@Test
	public void straightFlushTest() {
		
	}

}
