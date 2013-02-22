package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import poker.Hand;
import poker.PlayingCard;

public class HandEvaluationTest {

	//ArrayList<Integer> emptyTestHand = new ArrayList<Integer>();

	Hand testHand = new Hand();
	private static Logger log = Logger.getLogger("NewLogger");
	
	//@BeforeClass
	//public static void setUp() {
	//}
	
	@Test
	public void straightFlushCheck() {
		String handContents = "Test hand: ";
		//for (int loopCount = 8; loopCount <= 12; loopCount++) {
		//	testHand.takeNewCard(loopCount);
		//	handContents = handContents + loopCount + ".";
		//}

		PlayingCard test1 = new PlayingCard();
		test1.setPlayingCard(1, 1);
		PlayingCard test2 = new PlayingCard();
		test2.setPlayingCard(2, 1);
		PlayingCard test3 = new PlayingCard();
		test3.setPlayingCard(3, 1);
		PlayingCard test4 = new PlayingCard();
		test4.setPlayingCard(4, 1);
		PlayingCard test5 = new PlayingCard();
		test5.setPlayingCard(5, 1);
		
		testHand.takeNewCard(test1);
		testHand.takeNewCard(test2);
		testHand.takeNewCard(test3);
		testHand.takeNewCard(test4);
		testHand.takeNewCard(test5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard().getPlayingCardFullName());
		assertEquals("Straight Flush not correctly detected", 8, testHand.evaluateHandType());
	}
	
	@Test
	public void fourOfAKindCheck() {
		String handContents = "Test hand: ";

		PlayingCard test1 = new PlayingCard();
		test1.setPlayingCard(9, 1);
		PlayingCard test2 = new PlayingCard();
		test2.setPlayingCard(9, 2);
		PlayingCard test3 = new PlayingCard();
		test3.setPlayingCard(5, 3);
		PlayingCard test4 = new PlayingCard();
		test4.setPlayingCard(9, 3);
		PlayingCard test5 = new PlayingCard();
		test5.setPlayingCard(9, 4);
		
		testHand.takeNewCard(test1);
		testHand.takeNewCard(test2);
		testHand.takeNewCard(test3);
		testHand.takeNewCard(test4);
		testHand.takeNewCard(test5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard().getPlayingCardFullName());
		assertEquals("FOAK not correctly detected", 7, testHand.evaluateHandType());
	}
	
	@Test
	public void fullHouseCheck() {
		String handContents = "Test hand: ";

		PlayingCard test1 = new PlayingCard();
		test1.setPlayingCard(2, 1);
		PlayingCard test2 = new PlayingCard();
		test2.setPlayingCard(6, 2);
		PlayingCard test3 = new PlayingCard();
		test3.setPlayingCard(2, 3);
		PlayingCard test4 = new PlayingCard();
		test4.setPlayingCard(6, 4);
		PlayingCard test5 = new PlayingCard();
		test5.setPlayingCard(6, 3);
		
		testHand.takeNewCard(test1);
		testHand.takeNewCard(test2);
		testHand.takeNewCard(test3);
		testHand.takeNewCard(test4);
		testHand.takeNewCard(test5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard().getPlayingCardFullName());
		assertEquals("Full House not correctly detected", 6, testHand.evaluateHandType());
	}
	
	@Test
	public void flushCheck() {
		String handContents = "Test hand: ";

		PlayingCard test1 = new PlayingCard();
		test1.setPlayingCard(2, 3);
		PlayingCard test2 = new PlayingCard();
		test2.setPlayingCard(5, 3);
		PlayingCard test3 = new PlayingCard();
		test3.setPlayingCard(6, 3);
		PlayingCard test4 = new PlayingCard();
		test4.setPlayingCard(10, 3);
		PlayingCard test5 = new PlayingCard();
		test5.setPlayingCard(13, 3);
		
		testHand.takeNewCard(test1);
		testHand.takeNewCard(test2);
		testHand.takeNewCard(test3);
		testHand.takeNewCard(test4);
		testHand.takeNewCard(test5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard().getPlayingCardFullName());
		assertEquals("Flush not correctly detected", 5, testHand.evaluateHandType());
	}
	
	@Test
	public void straightCheck() {
		String handContents = "Test hand: ";

		PlayingCard test1 = new PlayingCard();
		test1.setPlayingCard(6, 2);
		PlayingCard test2 = new PlayingCard();
		test2.setPlayingCard(7, 2);
		PlayingCard test3 = new PlayingCard();
		test3.setPlayingCard(8, 2);
		PlayingCard test4 = new PlayingCard();
		test4.setPlayingCard(9, 4);
		PlayingCard test5 = new PlayingCard();
		test5.setPlayingCard(10, 3);
		
		testHand.takeNewCard(test1);
		testHand.takeNewCard(test2);
		testHand.takeNewCard(test3);
		testHand.takeNewCard(test4);
		testHand.takeNewCard(test5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard().getPlayingCardFullName());
		assertEquals("Straight not correctly detected", 4, testHand.evaluateHandType());
	}
	
	@Test
	public void threeOfAKindCheck() {
		String handContents = "Test hand: ";

		PlayingCard test1 = new PlayingCard();
		test1.setPlayingCard(2, 1);
		PlayingCard test2 = new PlayingCard();
		test2.setPlayingCard(7, 3);
		PlayingCard test3 = new PlayingCard();
		test3.setPlayingCard(7, 4);
		PlayingCard test4 = new PlayingCard();
		test4.setPlayingCard(7, 1);
		PlayingCard test5 = new PlayingCard();
		test5.setPlayingCard(11, 2);
		
		testHand.takeNewCard(test1);
		testHand.takeNewCard(test2);
		testHand.takeNewCard(test3);
		testHand.takeNewCard(test4);
		testHand.takeNewCard(test5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard().getPlayingCardFullName());
		assertEquals("3 OAK not correctly detected", 3, testHand.evaluateHandType());
	}
	
	@Test
	public void twoPairCheck() {
		String handContents = "Test hand: ";

		PlayingCard test1 = new PlayingCard();
		test1.setPlayingCard(12, 2);
		PlayingCard test2 = new PlayingCard();
		test2.setPlayingCard(3, 1);
		PlayingCard test3 = new PlayingCard();
		test3.setPlayingCard(9, 3);
		PlayingCard test4 = new PlayingCard();
		test4.setPlayingCard(9, 1);
		PlayingCard test5 = new PlayingCard();
		test5.setPlayingCard(3, 2);
		
		testHand.takeNewCard(test1);
		testHand.takeNewCard(test2);
		testHand.takeNewCard(test3);
		testHand.takeNewCard(test4);
		testHand.takeNewCard(test5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard().getPlayingCardFullName());
		assertEquals("2 Pair not correctly detected", 2, testHand.evaluateHandType());
	}
	
	@Test
	public void onePairCheck() {
		String handContents = "Test hand: ";

		PlayingCard test1 = new PlayingCard();
		test1.setPlayingCard(12, 2);
		PlayingCard test2 = new PlayingCard();
		test2.setPlayingCard(3, 1);
		PlayingCard test3 = new PlayingCard();
		test3.setPlayingCard(9, 3);
		PlayingCard test4 = new PlayingCard();
		test4.setPlayingCard(9, 4);
		PlayingCard test5 = new PlayingCard();
		test5.setPlayingCard(13, 4);
		
		testHand.takeNewCard(test1);
		testHand.takeNewCard(test2);
		testHand.takeNewCard(test3);
		testHand.takeNewCard(test4);
		testHand.takeNewCard(test5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard().getPlayingCardFullName());
		assertEquals("1 Pair not correctly detected", 1, testHand.evaluateHandType());
	}
	
	@Test
	public void highCardCheck() {
		String handContents = "Test hand: ";

		PlayingCard test1 = new PlayingCard();
		test1.setPlayingCard(2, 1);
		PlayingCard test2 = new PlayingCard();
		test2.setPlayingCard(3, 2);
		PlayingCard test3 = new PlayingCard();
		test3.setPlayingCard(4, 3);
		PlayingCard test4 = new PlayingCard();
		test4.setPlayingCard(5, 4);
		PlayingCard test5 = new PlayingCard();
		test5.setPlayingCard(7, 1);
		
		testHand.takeNewCard(test1);
		testHand.takeNewCard(test2);
		testHand.takeNewCard(test3);
		testHand.takeNewCard(test4);
		testHand.takeNewCard(test5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard().getPlayingCardFullName());
		assertEquals("High not correctly detected", 0, testHand.evaluateHandType());
	}
}
