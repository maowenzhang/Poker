package pokerLauncher;

import game.GameController;
import java.util.logging.Logger;

public class Round {

	private int roundNumber;
	private int playerScore;
	private int dealerScore;
	public String[] results;
	
	private boolean gameOver;
	private boolean playerGo;
	
	private GameController gameController;
	
	private int dealerSwapNum;

	private Logger log;
	
	//chain of command interface with message sending and receiving would be really helpful! 

	/**
	 * constructs the first round with all scoring set to 0 and generates a game controller
	 */
	public Round(){
		
		roundNumber = 0;
		playerScore = 0;
		dealerScore = 0;
		
		gameOver = false;
		playerGo = true;
		
		gameController = new GameController();
		
		log = Logger.getLogger("NewLogger");

	}

	/**
	 * generates a new round or closes the game if the game is over
	 */
	public void newRound(){

		printHand();
		
		if (!gameOver){
		
			
			roundNumber++;
			
			gameController.refreshGame();
			
		}

		else{
			
			//sent message to GUI to say thanks for playing and give the score
			System.exit(0);
		}
	}

	/**
	 * asks the game controller to score each hand and compares the hands to determine the winner and winning hand
	 */
	
public void scoreRound() {
		
		gameController.evaluateHands();

		int playerHandScore = gameController.getHandScore("Player");
		int dealerHandScore = gameController.getHandScore("Dealer");
		results = new String[3];

		results[0] = "Player has: " + gameController.getHandDescription("player");
		results[1] = "Dealer has: " + gameController.getHandDescription("dealer");

		if (playerHandScore > dealerHandScore) {
			setPlayerScore(getPlayerScore() + 1);
			results[2] = "PLAYER";

		} else {
			setDealerScore(getDealerScore() + 1);
			results[2] = "DEALER";
		}
		
		for(String result:results){
			
			log.info(result);
			
		}

	}

/**
 * setter method - sets the player's score
 * @param the player's score
 */
	public void setPlayerScore(int newPlayerScore) {
		playerScore = newPlayerScore;
	}

	/**
	 * getter method - gets the player's score
	 * @return the player's score
	 */
	public int getPlayerScore() {
		return playerScore;
	}

	/**
	 * setter method - sets the dealer's score
	 * @param the player's score
	 */
	public void setDealerScore(int newDealerScore) {
		dealerScore = newDealerScore;
	}

	/**
	 * getter method - gets the player's score
	 * @return the player's score
	 */
	public int getDealerScore() {
		return dealerScore;
	}

	/**
	 * asks the game controller to find the unique value of a certain card
	 * @param a card within a hand (1 - 5)
	 * @return the unique value of a card
	 */
	public int getCardToDisplay(int cardNumber) {
		
		if (playerGo){
			return gameController.getCardToDisplay("player", cardNumber);
		}
		else{
			return gameController.getCardToDisplay("dealer", cardNumber);
		}
	}

	/**
	 * getter method - scores the round and updates the results
	 * @return the results
	 */
	public String[] getResults() {
		scoreRound();
		return results;
	}

	/**
	 * asks the game controller to get a new card in a certain position within the hand
	 * @param the card position within the hand
	 */
	public void getNewCard(int cardPosition) {
			
		if (playerGo) {	
			gameController.getNewCard("player",cardPosition);
		
		} 
			
		else {
			gameController.getNewCard("dealer",cardPosition);
		}
	}

	/**
	 * prints the player's and dealer's hands
	 */
	public void printHand() {
		
		//if(playerGo){
			log.info("Player hand.....\n" + gameController.getPlayerHand() + "\n....................");
		
					
		//}
		
		//else{
			log.info("Dealer hand.....\n" + gameController.getDealerHand() + "\n....................");
		//}
	}

	/**
	 * changes the whose turn it is - from dealer to player and visa-versa
	 */
	public void changePlayer(){
		
		playerGo = !playerGo;
	}

	/**
	 * asks the game controller to exchange cards for the dealer
	 * TOM ANNOTATE
	 */
	public void dealerExchange() {
		gameController.dealerExchange();
		setDealerSwapNum(gameController.getDealerSwapNum());
	}

	private void setDealerSwapNum(int dealerSwapNum) {
		this.dealerSwapNum = dealerSwapNum;
	}

	public int getDealerSwapNum() {
		return this.dealerSwapNum;
	}

	/*
	public int getCardToDisplay(int card) {
		
		if (playerGo) {
			return ((playerHand.get(card-1).getPlayingCardSuit()-1) * 13) + playerHand.get(card-1).getPlayingCardValue();
		
		} 
		else {
			return ((playerHand.get(card-1).getPlayingCardSuit()-1) * 13) + playerHand.get(card-1).getPlayingCardValue();
		}
		
	}

	public void getNewCard(int card) {
		
		if (playerGo) {	
			playerHand.set(card-1, deck.dealCard());
		} 
		
		else {
			dealerHand.set(card-1, deck.dealCard());
		}
	}

	public void printHand() {
		
		if(playerGo){
			log.info("Player hand.....\n" + playerHand.get(0).getPlayingCardFullName() + "\n" + playerHand.get(1).getPlayingCardFullName() + "\n" + playerHand.get(2).getPlayingCardFullName() + "\n" + playerHand.get(3).getPlayingCardFullName() + "\n" + playerHand.get(4).getPlayingCardFullName() + "\n....................");
		}
		
		else{
			log.info("Dealer hand.....\n" + dealerHand.get(0).getPlayingCardFullName() + "\n" + dealerHand.get(1).getPlayingCardFullName() + "\n" + dealerHand.get(2).getPlayingCardFullName() + "\n" + dealerHand.get(3).getPlayingCardFullName() + "\n" + dealerHand.get(4).getPlayingCardFullName() + "\n....................");
		}
	}

	*/
	

}

