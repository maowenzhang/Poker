package pokerLauncher;

import java.util.logging.Logger;
import pokerfork.Deck;
import pokerfork.Evaluator;
import pokerfork.Hand;

public class GuiToGameLink {
	static Deck deck = new Deck();
	static Hand playerHand = new Hand(deck);
	static Hand dealerHand = new Hand(deck);

	static int roundNumber = 0;
	static int playerScore = 0;
	private static int dealerScore = 0;
	static boolean gameOver = false;


	private static Logger log = Logger.getLogger("NewLogger");

	public static void resestRound() {
		deck = new Deck();
		playerHand = new Hand(deck);
		dealerHand = new Hand(deck);
	}
	
	public static void initialise() {
		printHand("player");
		printHand("dealer");
	}

	public static int getCardToDisplay(String handToDisplay, int card) {
		int valueToReturn = 0;
		int tempSuit = 0;
		int tempValue = 0;
		if (handToDisplay.equalsIgnoreCase("player")) {
			//valueToReturn = ((playerHand.get(card-1).getPlayingCardSuit()-1) * 13) + playerHand.get(card-1).getPlayingCardValue();
			return ((getPlayerHand().get(card-1).getPlayingCardSuit()-1) * 13) + getPlayerHand().get(card-1).getPlayingCardValue();
		} else {
			tempSuit = ((dealerHand.get(card-1).getPlayingCardSuit()-1) * 13);
			tempValue = dealerHand.get(card-1).getPlayingCardValue();
			valueToReturn = tempSuit + tempValue;
			return valueToReturn;
			//return ((dealerHand.get(card-1).getPlayingCardSuit()-1) * 13) + dealerHand.get(card-1).getPlayingCardValue();
		}
	}

	public static void getNewCard(String handToDisplay, int card) {
		if (handToDisplay.equalsIgnoreCase("player")) {
			getPlayerHand().set(card-1, deck.dealCard());
		} else {
			dealerHand.set(card-1, deck.dealCard());
		}
	}

	public static void printHand(String handToDisplay) {
		if (handToDisplay.equalsIgnoreCase("player")) {
			log.info(handToDisplay + " hand.....\n" + getPlayerHand().get(0).getPlayingCardFullName() + "\n" + getPlayerHand().get(1).getPlayingCardFullName() + "\n" + getPlayerHand().get(2).getPlayingCardFullName() + "\n" + getPlayerHand().get(3).getPlayingCardFullName() + "\n" + getPlayerHand().get(4).getPlayingCardFullName() + "\n....................");
		} else {
			log.info(handToDisplay + " hand.....\n" + dealerHand.get(0).getPlayingCardFullName() + "\n" + dealerHand.get(1).getPlayingCardFullName() + "\n" + dealerHand.get(2).getPlayingCardFullName() + "\n" + dealerHand.get(3).getPlayingCardFullName() + "\n" + dealerHand.get(4).getPlayingCardFullName() + "\n....................");
		}
	}

	public static Hand getPlayerHand() {
		return playerHand;
	}

	public static Hand getDealerHand() {
		return dealerHand;
	}

	public static String[] evaluateHands() {
		int[] playerHandScore = Evaluator.getHandValue(GuiToGameLink.getPlayerHand());
		int[] dealerHandScore = Evaluator.getHandValue(GuiToGameLink.getDealerHand());
		String[] results = new String[3];

		results[0] = "Player has: ";
		//to be finished...
		switch (playerHandScore[0]) {
		case 0:
			results[0] += "High Card";
			break;
		case 1:
			results[0] += "One Pair (" + playerHandScore[1] + ")";
			break;
		case 2:
			results[0] += "Two Pair (" + playerHandScore[1] + ")";
			break;
		case 4:
			results[1] += "Straight (" + playerHandScore[1] + " high)";
			break;
		case 5:
			results[1] += "Flush";
			break;
		case 6:
			results[1] += "Full House (three " + playerHandScore[1] + "'s)";
			break;
		case 7:
			results[1] += "Four of a kind (" + playerHandScore[1] + ")";
			break;
		case 8:
			results[1] += "Straight Flush (" + playerHandScore[1] + " high)";
			break;
		default:
			results[1] = "ERROR";
			break;
		}

		results[1] = "Dealer has: ";
		//to be finished...
		switch (dealerHandScore[0]) {
		case 0:
			results[1] += "High Card";
			break;
		case 1:
			results[1] += "One Pair (" + dealerHandScore[1] + ")";
			break;
		case 2:
			results[1] += "Two Pair (" + dealerHandScore[1] + ")";
			break;
		case 3:
			results[1] += "Three of a kind (" + dealerHandScore[1] + ")";
			break;
		case 4:
			results[1] += "Straight (" + dealerHandScore[1] + " high)";
			break;
		case 5:
			results[1] += "Flush";
			break;
		case 6:
			results[1] += "Full House (three " + dealerHandScore[1] + "'s)";
			break;
		case 7:
			results[1] += "Four of a kind (" + dealerHandScore[1] + ")";
			break;
		case 8:
			results[1] += "Straight Flush (" + dealerHandScore[1] + " high)";
			break;
		default:
			results[1] = "ERROR";
			break;
		}


		//need to make sure there is no way for scores to draw!
		if (playerHandScore[0] < dealerHandScore[0]) {
			results[2] = "DEALER";
		} else {
			if (dealerHandScore[0] < playerHandScore[0]) {
				results[2] = "PLAYER";
			} else {
				if (playerHandScore[1] < dealerHandScore[1]) {
					if (playerHandScore[1] == 1) {
						results[2] = "PLAYER";
					} else {
						results[2] = "DEALER";
					}
				} else {
					if (dealerHandScore[1] < playerHandScore[1]) {
						if (dealerHandScore[1] == 1) {
							results[2] = "DEALER";
						} else {
							results[2] = "PLAYER";
						}
					} else {
						if (playerHandScore[2] < dealerHandScore[2]) {
							if (playerHandScore[2] == 1) {
								results[2] = "PLAYER";
							} else {
								results[2] = "DEALER WINS!";
							}
						} else {
							if (dealerHandScore[2] < playerHandScore[2]) {
								if (dealerHandScore[2] == 1) {
									results[2] = "DEALER";
								} else {
									results[2] = "PLAYER";
								}
							} else {
								// Need to pass through suit value, so that this cannot happen!
								results[2] = "DRAW";
							}
						}
					}
				}
			}
		}

		if (results[2].equals("PLAYER")) {
			setPlayerScore(getPlayerScore() + 1);
		} else {
			if (results[2].equals("DEALER")) {
				setDealerScore(getDealerScore() + 1);
			}
		}
		
		return results;
	}

	public static void setPlayerScore(int newPlayerScore) {
		playerScore = newPlayerScore;
	}

	public static int getPlayerScore() {
		return playerScore;
	}

	public static void setDealerScore(int newDealerScore) {
		dealerScore = newDealerScore;
	}

	public static int getDealerScore() {
		return dealerScore;
	}

}
