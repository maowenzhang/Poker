package game;

import ai.DealerAI;

//QUESTION - SHOULDNT GameController have visibility of Evaluate to decouple it from Hand?

/**
 * the game controller class receives requests from the round class and in turn passes these on to the rest of the game engine
 * before sending them back. It queries the round class to find out whether to perform requests for player or dealer
 * @author Tom & Jonathan
 *
 */
public class GameController {
	
	Deck deck;
	Hand playerHand;
	Hand dealerHand;
	//---------------------------------------------------------------------------------------------------------------------------------
	DealerAI dealerAI;
	
	private int dealerSwapNum;
	//---------------------------------------------------------------------------------------------------------------------------------

	/**
	 * constructor generates the deck and player's and dealer's hands; using the deck to deal cards to both hands
	 */
	public GameController(){
		refreshGame();
	}

	/**
	 * generates a new deck and player's and dealer's hands; using the deck to deal cards to both hands
	 */
	public void refreshGame(){
		//can we do this in a better way - e.g. clear the hand/deck and refill them. factory singleton deck which clears and refills
		deck = new Deck();
		playerHand = new Hand(deck);
		dealerHand = new Hand(deck);
		
	}
	
	/**
	 * asks both player's and dealer's hands to evaluate
	 */
	public void evaluateHands() {
		playerHand.evaluate();
		dealerHand.evaluate();
	}
	
	/**
	 * asks round which hand to look at, before asking this hand to return its score
	 * @return the value of the hand's score
	 */
	public int getHandScore(String hand) {
		if(hand.equalsIgnoreCase("player")){
			return playerHand.getHandScore();
		} else {
			return dealerHand.getHandScore();
		}
	}
	
	/**
	 * asks round which hand to look at, before asking this hand to return a description of itself
	 * @return a description of the hand
	 */
	public String getHandDescription(String hand){
		
		String handDescription = "";
		
		if(hand.equals("player")){
			
			handDescription = playerHand.getHandDescription();
			
		}
		
		else if (hand.equals("dealer")){
			
			handDescription = dealerHand.getHandDescription();
			
		}
		
		return handDescription;
		
	}
	
	/**
	 * asks round which hand to look at, before asking this hand for the unique value (a number between 1 and 52) of a certain 
	 * card
	 * @param which card to look at within the hand (1-5)
	 * @return the unique value of a card within the hand
	 */
	//could this be an array of all the cards?
	public int getCardToDisplay(String hand, int cardNumber) {
		
		int cardToDisplay = 0;
		
		if(hand.equals("player")){
			
			cardToDisplay = playerHand.getCardToDisplay(cardNumber);
			
		}
		
		else if (hand.equals("dealer")){
			
			cardToDisplay = dealerHand.getCardToDisplay(cardNumber);
			
		}
		
		return cardToDisplay;
		
	}
	
	/**
	 * asks deck to deal a card
	 * @return a playing card
	 */
	public PlayingCard dealCard() {
		return deck.dealCard();
	}

	/**
	 * asks round which hand to look at, before asking this hand to replace a certain card with a new card from the deck
	 * @param which card to look at within the hand (1-5)
	 */
	public void getNewCard(String player, int cardPosition) {
		if(player.equals("player")){
			playerHand.set(cardPosition-1, dealCard());
		}
		else if(player.equals("dealer")){
			dealerHand.set(cardPosition-1, dealCard());
		}
		
	}

	//SHOULDNT WE DO THESE TWO METHODS IN ONE IN THE SAME STYLE AS ABOVE (REQUESTING INFO FROM ROUND)?

	/**
	 * asks the player hand to return the full names of each card within it
	 * @return the full names of each of the player's cards
	 */
	public String getPlayerHand() {
		return playerHand.get(0).getPlayingCardFullName() + "\n" + playerHand.get(1).getPlayingCardFullName() + "\n" + playerHand.get(2).getPlayingCardFullName() + "\n" + playerHand.get(3).getPlayingCardFullName() + "\n" + playerHand.get(4).getPlayingCardFullName();
	}
	
	/**
	 * asks the dealer hand to return the full names of each card within it
	 * @return the full names of each of the dealer's card
	 */
	public String getDealerHand(){
		return dealerHand.get(0).getPlayingCardFullName() + "\n" + dealerHand.get(1).getPlayingCardFullName() + "\n" + dealerHand.get(2).getPlayingCardFullName() + "\n" + dealerHand.get(3).getPlayingCardFullName() + "\n" + dealerHand.get(4).getPlayingCardFullName();
	}
	
	//---tom new------------------------------------------------------------------------------------------------------------------------------
	public void dealerExchange() {
		DealerAI dealerAI = new DealerAI(dealerHand);
		setDealerSwapNum(dealerAI.getNumOfCardsChanged());
	}
	
	private void setDealerSwapNum(int dealerSwapNum) {
		this.dealerSwapNum = dealerSwapNum;
	}
	
	public int getDealerSwapNum() {
		return this.dealerSwapNum;
	}

	//---------------------------------------------------------------------------------------------------------------------------------


}

