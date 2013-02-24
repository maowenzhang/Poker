package pokerfork;

import java.util.Random;

import pokerfork.PlayingCard;

@SuppressWarnings("serial")
public class Deck extends CardStack{
	
	/* constructor method builds a deck filled with 52 playing cards */
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
	
	/* method to randomly deal a card from the deck, and in doing so removing it from the deck.
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
