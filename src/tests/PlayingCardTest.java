package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import game.PlayingCard;

public class PlayingCardTest {

	/**
	 *  testing whether the compareTo method works correctly
	 */  
	@Test
	public void compareToTest() {
		
		PlayingCard testCard1 = new PlayingCard();
		PlayingCard testCard2 = new PlayingCard();
		PlayingCard testCard3 = new PlayingCard();
		PlayingCard testCard4 = new PlayingCard();
		
		testCard1.setPlayingCard(5, 1);
		testCard2.setPlayingCard(5, 2);
		testCard3.setPlayingCard(6, 3);
		testCard4.setPlayingCard(1, 4);
		
		int expectedResult1 = -1;
		int expectedResult2 = -1;
		int expectedResult3 = 1;
		int expectedResult4 = -1;
		
		int actualResult1 = testCard1.compareTo(testCard3);
		int actualResult2 = testCard1.compareTo(testCard2);
		int actualResult3 = testCard3.compareTo(testCard1);
		int actualResult4 = testCard1.compareTo(testCard4);

		assertEquals(expectedResult1,actualResult1);
		assertEquals(expectedResult2,actualResult2);
		assertEquals(expectedResult3,actualResult3);
		assertEquals(expectedResult4,actualResult4);
	}

}
