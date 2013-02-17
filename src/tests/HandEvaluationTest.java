package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import poker.Hand;

public class HandEvaluationTest {

	ArrayList<Integer> emptyTestHand = new ArrayList<Integer>();
	Hand testHand = new Hand(emptyTestHand);

	private static Logger log = Logger.getLogger("NewLogger");
	
	@BeforeClass
	public static void setUp() {
	}
	
	@Test
	public void straightFlushCheck() {
		String handContents = "Test hand: ";
		//for (int loopCount = 8; loopCount <= 12; loopCount++) {
		//	testHand.takeNewCard(loopCount);
		//	handContents = handContents + loopCount + ".";
		//}

		testHand.takeNewCard(1);
		testHand.takeNewCard(2);
		testHand.takeNewCard(3);
		testHand.takeNewCard(4);
		testHand.takeNewCard(5);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard());
		assertEquals("Straight Flush not correctly detected", 8, testHand.evaluateHandType());
	}
	
	@Test
	public void fourOfAKindCheck() {
		String handContents = "Test hand: ";

		testHand.takeNewCard(9);
		testHand.takeNewCard(22);
		testHand.takeNewCard(31);
		testHand.takeNewCard(35);
		testHand.takeNewCard(48);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard());
		assertEquals("FOAK not correctly detected", 7, testHand.evaluateHandType());
	}
	
	@Test
	public void fullHouseCheck() {
		String handContents = "Test hand: ";

		testHand.takeNewCard(2);
		testHand.takeNewCard(19);
		testHand.takeNewCard(28);
		testHand.takeNewCard(45);
		testHand.takeNewCard(32);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard());
		assertEquals("Full House not correctly detected", 6, testHand.evaluateHandType());
	}
	
	@Test
	public void flushCheck() {
		String handContents = "Test hand: ";

		testHand.takeNewCard(28);
		testHand.takeNewCard(31);
		testHand.takeNewCard(32);
		testHand.takeNewCard(36);
		testHand.takeNewCard(39);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard());
		assertEquals("Flush not correctly detected", 5, testHand.evaluateHandType());
	}
	
	@Test
	public void straightCheck() {
		String handContents = "Test hand: ";

		testHand.takeNewCard(4);
		testHand.takeNewCard(18);
		testHand.takeNewCard(19);
		testHand.takeNewCard(33);
		testHand.takeNewCard(34);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard());
		assertEquals("Straight not correctly detected", 4, testHand.evaluateHandType());
	}
	
	@Test
	public void threeOfAKindCheck() {
		String handContents = "Test hand: ";

		testHand.takeNewCard(2);
		testHand.takeNewCard(33);
		testHand.takeNewCard(46);
		testHand.takeNewCard(7);
		testHand.takeNewCard(24);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard());
		assertEquals("3 OAK not correctly detected", 3, testHand.evaluateHandType());
	}
	
	@Test
	public void twoPairCheck() {
		String handContents = "Test hand: ";

		testHand.takeNewCard(25);
		testHand.takeNewCard(3);
		testHand.takeNewCard(35);
		testHand.takeNewCard(9);
		testHand.takeNewCard(16);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard());
		assertEquals("2 Pair not correctly detected", 2, testHand.evaluateHandType());
	}
	
	@Test
	public void onePairCheck() {
		String handContents = "Test hand: ";

		testHand.takeNewCard(25);
		testHand.takeNewCard(3);
		testHand.takeNewCard(35);
		testHand.takeNewCard(9);
		testHand.takeNewCard(52);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard());
		assertEquals("1 Pair not correctly detected", 1, testHand.evaluateHandType());
	}
	
	@Test
	public void highCardCheck() {
		String handContents = "Test hand: ";

		testHand.takeNewCard(1);
		testHand.takeNewCard(15);
		testHand.takeNewCard(29);
		testHand.takeNewCard(43);
		testHand.takeNewCard(6);
		
		log.info(handContents + "Hand value is: " + testHand.evaluateHandType() + ". Highest card is: " + testHand.getHighestUsedCard());
		assertEquals("High not correctly detected", 0, testHand.evaluateHandType());
	}
}
