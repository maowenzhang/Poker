package game;

import gui.GUIController;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import pokerLauncher.GameController;
import pokerLauncher.GameController.DealHands;
import pokerLauncher.GameController.ScoreHands;
import pokerLauncher.GameController.IsPlayerTurn;
import pokerLauncher.GameController.ShowDealerHand;
import pokerLauncher.GameController.ExchangeCards;
import ai.DealerAI;

//QUESTION - SHOULDNT CardController have visibility of Evaluate to decouple it from Hand?

/**
 * the game controller class receives requests from the round class and in turn passes these on to the rest of the game engine
 * before sending them back. It queries the round class to find out whether to perform requests for player or dealer
 * @author Tom & Jonathan
 *
 */
public class CardController implements Observer{

	Deck deck;
	Hand playerHand;
	Hand dealerHand;
	//---------------------------------------------------------------------------------------------------------------------------------
	DealerAI dealerAI;

	private int dealerSwapNum;
	private GUIController guiController;
	//---------------------------------------------------------------------------------------------------------------------------------

	private GameController gameController;
	private boolean isPlayerTurn;

	/**
	 * constructor generates the deck and player's and dealer's hands; using the deck to deal cards to both hands
	 * Sets local variable
	 */
	//	public CardController(){
	//		refreshGame();
	//	}

	/**
	 * generates a new deck and player's and dealer's hands; using the deck to deal cards to both hands
	 */
	public void refreshGame(){
		//can we do this in a better way - e.g. clear the hand/deck and refill them. factory singleton deck which clears and refills
		deck = new Deck();
		playerHand = new Hand(deck);
		dealerHand = new Hand(deck);

		Logger log = Logger.getLogger("NewLogger");
		log.info("PLAYER HAND: \n" + playerHand.getCard(0).getPlayingCardFullName() + "\n" + playerHand.getCard(1).getPlayingCardFullName() + "\n" + playerHand.getCard(2).getPlayingCardFullName() + "\n" + playerHand.getCard(3).getPlayingCardFullName() + "\n" + playerHand.getCard(4).getPlayingCardFullName());
		log.info("DEALER HAND: \n" + dealerHand.getCard(0).getPlayingCardFullName() + "\n" + dealerHand.getCard(1).getPlayingCardFullName() + "\n" + dealerHand.getCard(2).getPlayingCardFullName() + "\n" + dealerHand.getCard(3).getPlayingCardFullName() + "\n" + dealerHand.getCard(4).getPlayingCardFullName());
	}

	/**
	 * asks both player's and dealer's hands to evaluate
	 */
	public void evaluateHands() {
		playerHand.evaluate();
		dealerHand.evaluate();
	}


	@Override
	public void update(Observable obj, Object arg) {
		if(arg instanceof DealHands){
			getCardsToDisplay();
		}

		if(arg instanceof ExchangeCards){
			exchangeCards(((ExchangeCards) arg).getCardsToExchange());
		}

		if(arg instanceof IsPlayerTurn){
			this.isPlayerTurn = ((IsPlayerTurn) arg).getIsPlayerTurn();
		}

		if(arg instanceof ScoreHands){
			scoreRound();
		}

		if(arg instanceof Integer){
			//send message to control panel to say begin round [+ new round number]??
			refreshGame();
			guiController.refreshGame();
		}

		if(arg instanceof ShowDealerHand){
			guiController.setCardDisplay(dealerHand.getCardsToDisplay());
		}
	}


	public void scoreRound() {

		String[] results = new String[3];

		evaluateHands();

		int playerHandScore = playerHand.getHandScore();
		int dealerHandScore = dealerHand.getHandScore();

		results[0] = "Player has: " + playerHand.getHandDescription();
		results[1] = "Dealer has: " + dealerHand.getHandDescription();

		if (playerHandScore > dealerHandScore) {
			results[2] = "Player";
			gameController.addPointPlayer(true);
		} 
		else {
			gameController.addPointPlayer(false);
			results[2] = "Dealer";
		}
		guiController.displayResults(results);
	}

	private void exchangeCards(boolean[] cardsToExchange) {
		if(isPlayerTurn){
			int i = 0;
			for (boolean element : cardsToExchange){

				if(element){
					getNewCard(i);
				}
				i++;
			}
			guiController.setCardDisplay(playerHand.getCardsToDisplay());
		}
		else{
			DealerAI dealerAI = new DealerAI(dealerHand, deck);
			//this.setControl(dealerAI);
			//dealerAI.setControl(this);


			setDealerSwapNum(dealerAI.getNumOfCardsChanged());

			guiController.setDealerCardSwapMessage(getDealerSwapNum());
			guiController.setDealerSwapNum(getDealerSwapNum());
		}

	}

	//	private void setControl(DealerAI dealerAI) {
	//		this.dealerAI = dealerAI;
	//	}

	/**
	 * asks round which hand to look at, before asking this hand to replace a certain card with a new card from the deck
	 * @param which card to look at within the hand (1-5)
	 */
	public void getNewCard(int cardPosition) {
		if(isPlayerTurn){
			playerHand.set(cardPosition, dealCard());
		}
		else{
			dealerHand.set(cardPosition, dealCard());
		}

	}

	/**
	 * asks round which hand to look at, before asking this hand for the unique value (a number between 1 and 52) of a certain 
	 * card
	 * @param which card to look at within the hand (1-5)
	 * @return the unique value of a card within the hand
	 */


	//NEW
	//could this be an array of all the cards?
	public void getCardsToDisplay() {
		if(isPlayerTurn){	
			guiController.setCardDisplay(playerHand.getCardsToDisplay());
		}
		else{
			guiController.setCardDisplay(dealerHand.getCardsToDisplay());
		}
	}

	/**
	 * asks deck to deal a card
	 * @return a playing card
	 */
	public PlayingCard dealCard() {
		return deck.dealCard();
	}


	private void setDealerSwapNum(int dealerSwapNum) {
		this.dealerSwapNum = dealerSwapNum;
	}

	public int getDealerSwapNum() {
		return this.dealerSwapNum;
	}


	public void setControl(GUIController guiController) {
		this.guiController = guiController;

	}


	public void setControl(GameController gameController) {
		this.gameController = gameController;	
	}

	//---------------------------------------------------------------------------------------------------------------------------------
}

