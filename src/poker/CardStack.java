package poker;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
	private ArrayList<PlayingCard> cardStack = new ArrayList<PlayingCard>();
	
	//ArrayList<Integer> initial
	//public CardStack() {	
	//	cardStack = initial;
	//}

	public void generate() {
		cardStack.clear();
	}
	
	public void addCard(PlayingCard newCard) {
		cardStack.add(newCard);
	}
	
	public PlayingCard getCard(int getCardInPos) {
		return cardStack.get(getCardInPos);
	}
	
	public void deleteCard(int cardToRemove) {
		cardStack.remove(cardToRemove);
	}
	
	public int getCardStackSize() {
		return cardStack.size();
	}

	//supposed to be a bubble sort... possibly not quite right maths
	public void sortCards() {
		
		int loopCount1 = cardStack.size();
		do {
			int loopCount2 = 0;
			for (int loopCount3 = 1; loopCount3 < loopCount1; loopCount3++) {
				if ((cardStack.get(loopCount3 - 1).getPlayingCardValue() * cardStack.get(loopCount3 - 1).getPlayingCardSuit()) > (cardStack.get(loopCount3).getPlayingCardValue() * cardStack.get(loopCount3).getPlayingCardSuit())) {
					//PlayingCard tempCard = cardStack.get(loopCount).getPlayingCard();
					Collections.swap(cardStack, loopCount3 - 1, loopCount3);
					loopCount2 = loopCount3;
				}
			}
			loopCount1 = loopCount2;
		} while (loopCount1 > 0);
		
		//Collections.sort(cardStack);
		//for (int outerLoopCount = cardStack.size()-1; outerLoopCount == 1; outerLoopCount--) {
		//	for (int innerLoopCount = cardStack.size()-outerLoopCount; innerLoopCount <= cardStack.size()-1; innerLoopCount++) {
		//		if ((cardStack.get(innerLoopCount).getPlayingCardValue() * cardStack.get(innerLoopCount).getPlayingCardSuit()) < (cardStack.get(innerLoopCount-1).getPlayingCardValue() * cardStack.get(innerLoopCount-1).getPlayingCardSuit())) {
		//			//PlayingCard tempCard = cardStack.get(loopCount).getPlayingCard();
		//			Collections.swap(cardStack, innerLoopCount, innerLoopCount - 1);
		//		}
		//	}
		//}
	}
}
