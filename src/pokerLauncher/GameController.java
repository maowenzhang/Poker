package pokerLauncher;

import java.util.Observable;

public class GameController extends Observable {
	
	private int playerScore = 0;
	private int dealerScore = 0;
	private int roundNumber = 0;
	
	IsPlayerTurn isPlayerTurn;
	/*
	boolean dealHands = false;
	boolean exchangeCards = false;
	boolean showDealerHand = false;
	boolean scoreHands = false;
	 */

	public GameController(){ 
	
	isPlayerTurn = new IsPlayerTurn();
	}
	
	public void displayHand(){
		
		DealHands dealHands = new DealHands();
		setChanged();
		notifyObservers(dealHands);
	}
	
	public void exchangeCards() {
		ExchangeCards exchangeCards = new ExchangeCards();
		setChanged();
		notifyObservers(exchangeCards);
	}
	
	public void exchangeCards(boolean[] cardsToExchange){
		ExchangeCards exchangeCards = new ExchangeCards(cardsToExchange);
		setChanged();
		notifyObservers(exchangeCards);
	}
	
	public void showDealerHand(){	
		
		ShowDealerHand showDealerHand = new ShowDealerHand();
		setChanged();
		notifyObservers(showDealerHand);	
	}
	
	public void changeTurn() {
		
		isPlayerTurn.changeTurn();
		setChanged();
		notifyObservers(isPlayerTurn);
	}

	public void scoreHands(){
		
		ScoreHands scoreHands = new ScoreHands();
		setChanged();
		notifyObservers(scoreHands);
		
	}

	public void addPointPlayer(boolean player) {
		if(player){
			playerScore++;
		}
		else{
			dealerScore++;
		}
	}

	public void endGame() {
		//display overall winner...
		System.exit(0);
	}

	public int getPlayerPoints() {
		return playerScore;
	}

	public int getDealerPoints() {
		return dealerScore;
	}

	public void newRound() {
		roundNumber++;
		Integer newRoundNumber = new Integer(roundNumber);
		setChanged();
		notifyObservers(newRoundNumber);
	}


	public class ExchangeCards {
		
		boolean[] cardsToExchange;
		
		public ExchangeCards(boolean[] cardsToExchange){
			this.cardsToExchange = cardsToExchange;
		}

		public ExchangeCards() {
			//pretty much unnecessary but stops an error if isPlayerTurn is wrongly set on CardController
			cardsToExchange = new boolean[1];
			cardsToExchange[0] = false;
		}
		
		public boolean[] getCardsToExchange(){
			return this.cardsToExchange;
		}
	}


	public class IsPlayerTurn {
		
		boolean isPlayerTurn;
		
		public IsPlayerTurn(){
			isPlayerTurn = true;
		}

		public void changeTurn() {
			isPlayerTurn = !isPlayerTurn;
		}
		
		public boolean getIsPlayerTurn(){
			return isPlayerTurn;
		}
	}

	
	public class ShowDealerHand{
		
	}
	
	
	public class ScoreHands {

	}


	public class DealHands {
		
	}

}
