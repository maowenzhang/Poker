package ai;

import game.Deck;
import game.Hand;

/**
 * 
 * Abstract class to define all the methods which a dealerAI will have to implement, and sets up required fields
 * Both "Smart" and "Random" AI's make use of these
 *
 */
public abstract class DealerAI {

	protected int numOfCardsChanged;
	protected Hand hand;
	protected Deck deck;

	public abstract void evaluate();

	public void setHand(Hand hand){
		this.hand = hand;
	}

	public void setDeck(Deck deck){
		this.deck = deck;
	}

	public void setNumOfCardsChanged(int numOfCardsChanged){
		this.numOfCardsChanged = numOfCardsChanged;
	}

	public int getNumOfCardsChanged(){
		return numOfCardsChanged;
	}

}
