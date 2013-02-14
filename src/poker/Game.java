package poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Game {

	private static ArrayList<Integer> emptyDeck = new ArrayList<Integer>();
	private static Deck deck = new Deck(emptyDeck);
	private static ArrayList<Integer> emptyPlayerHand = new ArrayList<Integer>();
	private static Hand playerHand = new Hand(emptyPlayerHand);
	private static ArrayList<Integer> emptyDealerHand = new ArrayList<Integer>();
	private static Hand dealerHand = new Hand(emptyDealerHand);
	
	private static int roundNumber = 0;
	private static boolean gameOver;
	
	private static Logger log = Logger.getLogger("NewLogger");
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main (String [] args){
		while (!gameOver){
			roundNumber++;
			Round();
			log.info("Do you want to play another round?");
			if (scanner.next().equals("no")) {gameOver=true;}
		}
		DisplayScores();
	}
	
	public static void Round(){
		log.info("Deck size: " + String.valueOf(deck.cardStack.size()));
		log.info("Player Hand size: " + String.valueOf(playerHand.cardStack.size()));
		log.info(String.valueOf("Dealer Hand size: " + playerHand.cardStack.size()));
		
		deck.generate();

		log.info("Deck size: " + String.valueOf(deck.cardStack.size()));
		log.info("Player Hand size: " + String.valueOf(playerHand.cardStack.size()));
		
		DealPlayerHand();

		log.info("Deck size: " + String.valueOf(deck.cardStack.size()));
		log.info("Player Hand size: " + String.valueOf(playerHand.cardStack.size()));
		
		for (int loopCount = 0; loopCount < playerHand.cardStack.size(); loopCount++) {
			log.info(String.valueOf("Player card: " + (loopCount + 1) + " is: " + playerHand.cardStack.get(loopCount)));
		}

		log.info(String.valueOf("Deck size: " + deck.cardStack.size()));
		log.info(String.valueOf("Dealer Hand size: " + playerHand.cardStack.size()));
		
		DealDealerHand();

		log.info("Deck size: " + String.valueOf(deck.cardStack.size()));
		log.info("Dealer Hand size: " + String.valueOf(dealerHand.cardStack.size()));

		for (int loopCount = 0; loopCount < dealerHand.cardStack.size(); loopCount++) {
			log.info(String.valueOf("Dealer card: " + (loopCount + 1) + " is: " + dealerHand.cardStack.get(loopCount)));
		}
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

	private static void DisplayScores() {
		// TODO Auto-generated method stub
		
	}
	
}
