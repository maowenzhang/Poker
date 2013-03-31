package ai;

import game.Deck;
import game.Hand;

public abstract class DealerAI {

	protected int numOfCardsChanged;
	protected Hand hand;
	protected Deck deck;

	//public abstract void disposeUnwantedCards();

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
