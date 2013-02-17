package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Hand extends CardStack{
	private int highestUsedCard;
	private boolean aceHigh = false;

	public Hand(ArrayList<Integer> initial) {
		super(initial);
	}

	public void takeNewCard(int newCard) {
		cardStack.add(newCard);
	}

	public void discardCard(int cardToDiscard) {
		cardStack.remove(cardToDiscard);
	}

	public int evaluateHandType() {
		Collections.sort(cardStack);
		setHighestUsedCard(cardStack.get(0));
		int handScore = 0;

		if (straightAndFlushCalculator("StraightFlush")) {
			handScore += 1;
		}

		if ((handScore != 0) || pairsTriosAndQuads("FourOfAKind")) {
			handScore += 1;
		}

		if ((handScore != 0) || pairsTriosAndQuads("FullHouse")) {
			handScore += 1;
		}

		if ((handScore != 0) || straightAndFlushCalculator("Flush")) {
			handScore += 1;
		}

		if ((handScore != 0) || straightAndFlushCalculator("Straight")) {
			handScore += 1;
		}

		if ((handScore != 0) || pairsTriosAndQuads("ThreeOfAKind")) {
			handScore += 1;
		}

		if ((handScore != 0) || pairsTriosAndQuads("TwoPair")) {
			handScore += 1;
		}

		if ((handScore != 0) || pairsTriosAndQuads("OnePair")) {
			handScore += 1;
		}

		return handScore;
	}

	private boolean pairsTriosAndQuads(String handType) {
		boolean fourOfAKind = false;
		boolean fullHouse = false;
		boolean threeOfAKind = false;
		boolean twoPair = false;
		boolean onePair = false;
		boolean firstPair = true;
		int trioValue = 0;
		int firstPairValue = 0;
		int secondPairValue = 0;
		ArrayList<Integer> handWithoutSuits = new ArrayList<Integer>();

		for (int loopCount = 0; loopCount <= cardStack.size()-1; loopCount++) {
			handWithoutSuits.add(cardStack.get(loopCount) % 13);
		}
		
		Collections.sort(handWithoutSuits);

		for (int loopCount = 0; loopCount <= handWithoutSuits.size()-1; loopCount++) {
			if (loopCount != 0) {
				if (handWithoutSuits.get(loopCount) == handWithoutSuits.get(loopCount-1)) {
					if (firstPair) {
						onePair = true;
						firstPair = false;
						firstPairValue = handWithoutSuits.get(loopCount);
						setHighestUsedCard(findHighestUsedCard(handWithoutSuits.get(loopCount)));
					} else {
						if (handWithoutSuits.get(loopCount-1) != firstPairValue) {
							twoPair = true;
							secondPairValue = handWithoutSuits.get(loopCount);
							setHighestUsedCard(findHighestUsedCard(handWithoutSuits.get(loopCount)));
						}
					}
				}
				
				if ((loopCount >= 2) && (handWithoutSuits.get(loopCount) == handWithoutSuits.get(loopCount-1)) && (handWithoutSuits.get(loopCount-1) == handWithoutSuits.get(loopCount-2))) {
					threeOfAKind = true;
					trioValue = handWithoutSuits.get(loopCount);
					setHighestUsedCard(findHighestUsedCard(handWithoutSuits.get(loopCount)));
				}
				
				if ((loopCount >= 3) && (handWithoutSuits.get(loopCount) == handWithoutSuits.get(loopCount-1)) && (handWithoutSuits.get(loopCount-1) == handWithoutSuits.get(loopCount-2)) && (handWithoutSuits.get(loopCount-2) == handWithoutSuits.get(loopCount-3))) {
					if (handType.equals("FourOfAKind")) {
						fourOfAKind = true;
						setHighestUsedCard(findHighestUsedCard(handWithoutSuits.get(loopCount)));
						return fourOfAKind;
					}
				}
			}
		}
		
		if (onePair && threeOfAKind && (firstPairValue > 0) && (secondPairValue > 0) && (trioValue > 0) && ((firstPairValue != trioValue) || (secondPairValue != trioValue))) {
			if (handType.equals("FullHouse")) {
				fullHouse = true;
				return fullHouse;
			}
		}
		
		if (threeOfAKind) {
			if (handType.equals("ThreeOfAKind")) {
				return threeOfAKind;
			}
		} else {
			if (twoPair && handType.equals("TwoPair")) {
				return twoPair;
			} else {
				if (onePair && handType.equals("OnePair")) {
					return onePair;
				}				
			}
		}
		
		return false;
	}

	private int findHighestUsedCard(Integer cardTypeToFind) {
		int highestValueCardOfTypeToFind = 0;
		
		if (cardTypeToFind == 0) {
			for (int counter = 0; counter <= 4; counter++) {
				if (cardStack.get(counter) % 13 == cardTypeToFind) {
					highestValueCardOfTypeToFind = cardStack.get(counter);
					return highestValueCardOfTypeToFind;
				}
			}
		} else {
			for (int counter = 4; counter >= 0; counter--) {
				if (cardStack.get(counter) % 13 == cardTypeToFind) {
					highestValueCardOfTypeToFind = cardStack.get(counter);
				}
			}
		}
		
		return highestValueCardOfTypeToFind;
	}

	private boolean straightAndFlushCalculator(String handType) {
		boolean straightFlush = false;
		boolean flush = false;
		boolean flushPossible = true;
		boolean straight = false;
		boolean acePresent = false;
		int cardDiffMem = 0;
		int suit = 0;

		for (int loopCount = 1; loopCount <= cardStack.size(); loopCount++) {
			//checks if ace is present
			if (cardStack.get(loopCount-1) % 13 == 1) {
				acePresent = true;
			}

			// on first iteration, sets current suit to compare for flush
			if (loopCount == 1) {
				suit = suitCheck(cardStack.get(loopCount-1));
			} else {
				// cardDiffMem tracks total of all "gaps" between cards - if only one at a time, then straight is possible
				cardDiffMem = cardDiffMem + ((cardStack.get(loopCount-1) % 13) - (cardStack.get(loopCount-2) % 13));
				if (suit != suitCheck(cardStack.get(loopCount-1))) {
					straightFlush = false;
					flushPossible = false;
				} else {
					if ((loopCount == 5) && (flushPossible == true)) {
						flush = true;
					}
				}
			}
		}
		
		
		
		// as cards in a row and there is a flush, then it must be straight flush
		if ((cardDiffMem == 4) && (flush == true)) {
			straightFlush = true;
		} else {
			if (cardDiffMem == 4) {
				straight = true;
			}
		}
		
		if (handType.equals("StraightFlush")) {
			return straightFlush;
		} else {
			if (handType.equals("Flush")) {
				return flush;
			} else {
				if (handType.equals("Straight")) {
					return straight;
				}
			}
		}
		
		return false;
	}

	private int suitCheck(int cardID) {
		int suit = 0;
		if (cardID % 13 != 0) {
			suit = cardID / 13;
		} else {
			suit = (cardID / 13) - 1;
		}
		return suit;
	}

	public void setHighestUsedCard(int highestUsedCard) {
		this.highestUsedCard = highestUsedCard;
	}

	public int getHighestUsedCard() {
		return highestUsedCard;
	}

	private void setAceHigh(boolean aceHigh) {
		this.aceHigh = aceHigh;
	}

	private boolean getAceHigh() {
		return aceHigh;
	}
}
