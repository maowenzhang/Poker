package tests;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;
import game.Deck;
import game.PlayingCard;

public class DeckTest {

	Deck testDeck1 = new Deck();
	Deck testDeck2 = new Deck();
	
	int correctDeckSize = 52;
	
	
	/**
	 *  test to determine whether deck has been constructed with 52 objects inside it 
	 */
	@Test
	public void generateTest() {
		
		int deckSize = testDeck1.size();
		
		assertEquals(correctDeckSize,deckSize);
	}

	/** 
	 * test to determine whether deck contains only exactly one copy of a randomly chosen card 
	 */
	@Test
	public void dealTest(){
		
		Random random = new Random();
		PlayingCard randomCard = testDeck2.PlayingCardFactory();
		
		randomCard.setPlayingCard(random.nextInt(13),random.nextInt(4));
		
		int numberOfMatches = 0;
		int correctNumberOfMatches = 1;
		
		for(int i = 1; i<=correctDeckSize; i++){
			
			PlayingCard deckCard =  testDeck2.dealCard();
			
			if(randomCard.getPlayingCardFullName().equals(deckCard.getPlayingCardFullName())){
				
				numberOfMatches++;
				
			}
		}
		
		assertEquals(correctNumberOfMatches,numberOfMatches);
	}
	
}
