package poker;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Poker {

	private static ArrayList<Integer> emptyDeck = new ArrayList<Integer>();
	private static Deck deck = new Deck(emptyDeck);
	private static ArrayList<Integer> emptyPlayerHand = new ArrayList<Integer>();
	private static Hand playerHand = new Hand(emptyPlayerHand);
	private static ArrayList<Integer> emptyDealerHand = new ArrayList<Integer>();
	private static Hand dealerHand = new Hand(emptyDealerHand);

	static int roundNumber = 0;
	static int playerScore = 0;
	static int dealerScore = 0;
	private static boolean gameOver;

	private static Logger log = Logger.getLogger("NewLogger");
	private static Scanner scanner = new Scanner(System.in);

	public static void main (String [] args){
		//USE LINE BELOW TO TURN LOGGING TO CONSOLE OUTPUT OFF!
		//log.setLevel(Level.OFF);
		playRounds();
		displayScores();
	}

	private static void playRounds() {
		while (!gameOver) {
			roundNumber++;
			playSingleRound();
			log.info("Do you want to play another round?");

			if (scanner.next().equals("no")) {
				gameOver=true;
			}
		}
	}

	public static void playSingleRound(){
		deal();
		cardExchange();
		scoreKeeper(evaluate());
	}

	public static void cardExchange() {
		playerCardExchange();
		dealerCardExchange();
	}
	
	public static void playerCardExchange() {
		//TO WRITE - GIVE PLAYER CHANCE TO EXCHANGE UP TO 3 CARDS. - sets "numToSwap" - 1 used as test. Need to pass in index of card to be discarded - 3 used as test.
		int numToSwap = 1;
		int cardToDiscard = 3;
		discardCards(playerHand, numToSwap, cardToDiscard);
	}

	private static void discardCards(Hand hand, int numToSwap, int cardToDiscard) {
		for (int loopCount = 1; loopCount <= numToSwap; loopCount++) {
			hand.discardCard(cardToDiscard);
			hand.takeNewCard(deck.dealCard());
		}
	}
	
	public static void dealerCardExchange() {
		int bestHand;
		
		bestHand = dealerHand.evaluateHandType();
		
		//some sort of logic/statistical engine based on current hand and highest card - or find existing library to use!
		
		
		//TO WRITE - GIVE DEALER CHANCE TO EXCHANGE UP TO 3 CARDS. - sets "numToSwap" - 1 used as test. Need to pass in index of card to be discarded - 3 used as test.
		int numToSwap = 1;
		int cardToDiscard = 3;
		discardCards(dealerHand, numToSwap, cardToDiscard);
	}
	

	private static void scoreKeeper(String winner) {
		if (winner.equals("Dealer")) {
			dealerScore += 1;
		} else {
			playerScore += 1;
		}
	}

	private static String evaluate() {
		// DELETE THIS LINE FOR PROPER RUNNING!!!
		//playerHand.highestUsedCard = 2;

		String winner = compareHands(playerHand.evaluateHandType(), playerHand.getHighestUsedCard(), dealerHand.evaluateHandType(), dealerHand.getHighestUsedCard());

		outputHand("PLAYER final", playerHand, playerHand.evaluateHandType());
		outputHand("DEALER final", dealerHand, dealerHand.evaluateHandType());
			// DELETE THESE FOR PROPER RUNNING!!!
			//for (int loopCount = 0; loopCount < playerHand.cardStack.size(); loopCount++) {
			//	log.info(String.valueOf("SORTED player card: " + (loopCount + 1) + " is: " + playerHand.cardStack.get(loopCount)));
			//}

		return winner;
	}

	private static String compareHands(int playerHandType, int playerHighestUsedCard, int dealerHandType, int dealerHighestUsedCard) {
		String winner = "";
		if (playerHandType > dealerHandType) {
			winner = "Player";
		} else {
			if (dealerHandType > playerHandType) {
				winner = "Dealer";
			} else {
				if (playerHighestUsedCard > dealerHighestUsedCard) {
					winner = "Player";
				}
			}
		}
		return winner;
	}

	private static void deal() {
		
		//log.info("Deck size: " + String.valueOf(deck.cardStack.size()));
		//log.info("Player Hand size: " + String.valueOf(playerHand.cardStack.size()));
		//log.info("Dealer Hand size: " + String.valueOf(dealerHand.cardStack.size()));

		deck.generate();
		playerHand.generate();
		dealerHand.generate();

		//log.info("Deck size: " + String.valueOf(deck.cardStack.size()));
		//log.info("Player Hand size: " + String.valueOf(playerHand.cardStack.size()));

		DealPlayerHand();

		//log.info("Deck size: " + String.valueOf(deck.cardStack.size()));
		//log.info("Player Hand size: " + String.valueOf(playerHand.cardStack.size()));

		//outputHand("PLAYER initial", playerHand);

		//log.info(String.valueOf("Deck size: " + deck.cardStack.size()));
		//log.info(String.valueOf("Dealer Hand size: " + playerHand.cardStack.size()));

		DealDealerHand();

		//log.info("Deck size: " + String.valueOf(deck.cardStack.size()));
		//log.info("Dealer Hand size: " + String.valueOf(dealerHand.cardStack.size()));

		//outputHand("DEALER initial", dealerHand);
	}

	private static void outputHand(String handName, Hand hand, int handScore) {
		String handContents = handName + " hand: ";
		for (int loopCount = 0; loopCount < hand.cardStack.size(); loopCount++) {
			handContents = handContents + hand.cardStack.get(loopCount) + ".";
		}
		handContents = handContents + " Hand score: " + handScore;
		log.info(handContents);
	}

	private static void DealDealerHand() {
		for (int loopCount = 1; loopCount <= 5; loopCount++) {
			dealerHand.takeNewCard(deck.dealCard());
		}
	}

	private static void DealPlayerHand() {
		for (int loopCount = 1; loopCount <= 5; loopCount++) {
			playerHand.takeNewCard(deck.dealCard());
		}
	}

	private static void displayScores() {
		log.info("After " + String.valueOf(roundNumber) + " rounds:");
		log.info("Dealer score is: " + String.valueOf(dealerScore));
		log.info("Player score is: " + String.valueOf(playerScore));

		if (playerScore > dealerScore) {
			log.info("Winner is Player!");
		} else {
			if (dealerScore > playerScore) {
				log.info("Winner is Dealer!");
			} else {
				log.info("Game is a draw!");
			}
		}
	}
}
