package poker;

import java.util.Random;

public class Deck extends CardStack{

	//public Deck(ArrayList<Integer> initial) {
	//	super(initial);
	//}
	
	public void generate() {
		super.generate();
		for (int loopCountCardValue = 1; loopCountCardValue <= 13; loopCountCardValue++) {
			for (int loopCountSuit = 1; loopCountSuit <= 4; loopCountSuit++) {
				PlayingCard newCard = new PlayingCard();
				newCard.setPlayingCard(loopCountCardValue, loopCountSuit);
				super.addCard(newCard);
			}
		}
	}
	
	public int getDeckSize() {
		return super.getCardStackSize();
	}
	
	public PlayingCard dealCard() {
		Random generator = new Random();
		int cardSelector = generator.nextInt(getDeckSize() - 1);
		PlayingCard cardDealt = super.getCard(cardSelector);
		super.deleteCard(cardSelector);
		return cardDealt;
	}
}
