package pokerLauncher;

import java.util.Observable;

public class GameController extends Observable {
	
	private int playerScore = 0;
	private int dealerScore = 0;
	
	private int roundNumber = 0;
	
	boolean isPlayerTurn = true;
	
	boolean dealHands = false;
	boolean exchangeCards = false;
	boolean showDealerHand = false;
	boolean scoreHands = false;



	public void setDealHands(){
		
		dealHands = true;
		
	}
	
	public void setexchangeCards(){
		
		exchangeCards = true;
		
	}
	
	public void setShowDealerHand(){
		
		showDealerHand = true;
		
	}
	
	public void setScoreHands(boolean newValue){
		
		scoreHands = newValue;
		setChanged();
		notifyObservers(newValue);
		
	}

	
	
	
	
	
	
	
	
	
	/*
	 * public void setPlayerScore(int playerScore) {
	
		this.playerScore = this.playerScore + playerScore;
		notifyObservers(this.playerScore);	
	}


	public int getPlayerScore() {
		return playerScore;
	}
	
	 */
}
