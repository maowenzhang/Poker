package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Hand extends CardStack{
	private PlayingCard highestUsedCard;
	private boolean aceHigh = false;

	//public Hand(ArrayList<Integer> initial) {
	//	super(initial);
	//}

	public void takeNewCard(PlayingCard newCard) {
		super.addCard(newCard);
	}

	public void discardCard(int cardToDiscard) {
		super.deleteCard(cardToDiscard);
	}

	public int evaluateHandType() {
		super.sortCards();
		setHighestUsedCard(super.getCard(0));
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

		for (int loopCount = 0; loopCount <= super.getCardStackSize()-1; loopCount++) {
			handWithoutSuits.add(super.getCard(loopCount).getPlayingCardValue());
		}

		Collections.sort(handWithoutSuits);

		for (int loopCount = 0; loopCount <= handWithoutSuits.size()-1; loopCount++) {
			if (loopCount != 0) {
				if (handWithoutSuits.get(loopCount) == handWithoutSuits.get(loopCount-1)) {
					if (firstPair) {
						onePair = true;
						firstPair = false;
						firstPairValue = handWithoutSuits.get(loopCount);
						setHighestUsedCard(findHighestUsedCard_PairTrioQuad(handWithoutSuits.get(loopCount)));
					} else {
						if (handWithoutSuits.get(loopCount-1) != firstPairValue) {
							twoPair = true;
							secondPairValue = handWithoutSuits.get(loopCount);
							setHighestUsedCard(findHighestUsedCard_PairTrioQuad(handWithoutSuits.get(loopCount)));
						}
					}
				}

				if ((loopCount >= 2) && (handWithoutSuits.get(loopCount) == handWithoutSuits.get(loopCount-1)) && (handWithoutSuits.get(loopCount-1) == handWithoutSuits.get(loopCount-2))) {
					threeOfAKind = true;
					trioValue = handWithoutSuits.get(loopCount);
					setHighestUsedCard(findHighestUsedCard_PairTrioQuad(handWithoutSuits.get(loopCount)));
				}

				if ((loopCount >= 3) && (handWithoutSuits.get(loopCount) == handWithoutSuits.get(loopCount-1)) && (handWithoutSuits.get(loopCount-1) == handWithoutSuits.get(loopCount-2)) && (handWithoutSuits.get(loopCount-2) == handWithoutSuits.get(loopCount-3))) {
					if (handType.equals("FourOfAKind")) {
						fourOfAKind = true;
						setHighestUsedCard(findHighestUsedCard_PairTrioQuad(handWithoutSuits.get(loopCount)));
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

	private PlayingCard findHighestUsedCard_PairTrioQuad(Integer cardTypeToFind) {
		PlayingCard highestValueCardOfTypeToFind = null;

		for (int counter = 0; counter <= super.getCardStackSize()-1; counter++) {
			if (super.getCard(counter).getPlayingCardValue() == cardTypeToFind) {
				highestValueCardOfTypeToFind = super.getCard(counter);
				return highestValueCardOfTypeToFind;
			}
		}

		//if (cardTypeToFind == 0) {
		//	for (int counter = 0; counter <= 4; counter++) {
		//		if (super.getCard(counter).getPlayingCardValue() == cardTypeToFind) {
		//			highestValueCardOfTypeToFind = super.getCard(counter);
		//			return highestValueCardOfTypeToFind;
		//		}
		//	}
		//} else {
		//	for (int counter = 4; counter >= 0; counter--) {
		//		if (super.getCard(counter).getPlayingCardValue() == cardTypeToFind) {
		//			highestValueCardOfTypeToFind = super.getCard(counter);
		//		}
		//	}
		//}

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

		for (int loopCount = 1; loopCount <= super.getCardStackSize(); loopCount++) {
			//checks if ace is present
			if (super.getCard(loopCount-1).getPlayingCardValue() == 1) {
				acePresent = true;
			}

			// on first iteration, sets current suit to compare for flush
			if (loopCount == 1) {
				suit = super.getCard(loopCount-1).getPlayingCardSuit();
			} else {
				// cardDiffMem tracks total of all "gaps" between cards - if only one at a time, then straight is possible
				cardDiffMem = cardDiffMem + ((super.getCard(loopCount-1).getPlayingCardValue()) - (super.getCard(loopCount-2).getPlayingCardValue()));
				if (suit != super.getCard(loopCount-1).getPlayingCardSuit()) {
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
			setWholeHandAsUsed();
			if ((super.getCard(0).getPlayingCardValue() == 1) && (super.getCard(1).getPlayingCardValue() == 2)) {
				setHighestUsedCard(super.getCard(0));
			} else {
				setHighestUsedCard(super.getCard(super.getCardStackSize()-1));
			}
			return straightFlush;
		} else {
			if (handType.equals("Flush")) {
				setWholeHandAsUsed();
				//setHighestUsedCard(findHighestUsedCard_StraightFlush());
				return flush;
			} else {
				if (handType.equals("Straight")) {
					setWholeHandAsUsed();
					//setHighestUsedCard(findHighestUsedCard_StraightFlush());
					return straight;
				}
			}
		}

		return false;
	}

	//private int suitCheck(int cardID) {
	//	int suit = 0;
	//	if (cardID % 13 != 0) {
	//		suit = cardID / 13;
	//	} else {
	//		suit = (cardID / 13) - 1;
	//	}
	//	return suit;
	//}

	private void setWholeHandAsUsed() {
		for (int loopCount = 0; loopCount < super.getCardStackSize(); loopCount++) {
			super.getCard(loopCount).setUsedInScoringHand(true);
		}
	}

	public void setHighestUsedCard(PlayingCard highestUsedCard) {
		this.highestUsedCard = highestUsedCard;
	}

	public PlayingCard getHighestUsedCard() {
		return highestUsedCard;
	}

	private void setAceHigh(boolean aceHigh) {
		this.aceHigh = aceHigh;
	}

	private boolean getAceHigh() {
		return aceHigh;
	}
}
