package poker;

import java.util.Scanner;
import java.util.logging.Logger;

public class Game {

	private static CardDeck deck;
	private static Hand playerHand;
	private static Hand dealerHand;
	
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
		
	}

	private static void DisplayScores() {
		// TODO Auto-generated method stub
		
	}
	
}
