package game;

import java.util.Random;

/**
 * the deck class deals playing cards to the hand class. It ensures integrity such that there are only ever 52 unique cards
 * to play with. It inherits from the card stack class.
 * @author Tom & Jonathan
 *
 */
@SuppressWarnings("serial")
public class Deck extends CardStack{
	
	/**
	 *  constructor method builds a deck filled with 52 playing cards 
	 */
	public Deck() {
		clear();
		for (int loopCountCardValue = 1; loopCountCardValue <= 13; loopCountCardValue++) {
			for (int loopCountSuit = 1; loopCountSuit <= 4; loopCountSuit++) {
				PlayingCard newCard = PlayingCardFactory();
				newCard.setPlayingCard(loopCountCardValue, loopCountSuit);
				add(newCard);
			}
		}
	}
	
	/**
	 *  method to randomly deal a card from the deck, and in doing so remote it from the deck.
	 * @return a PlayingCard
	 */
	public PlayingCard dealCard() {
		Random generator = new Random();
		int cardSelector = generator.nextInt(size());
		PlayingCard cardDealt = get(cardSelector);
		remove(cardSelector);
		return cardDealt;
	}

}
