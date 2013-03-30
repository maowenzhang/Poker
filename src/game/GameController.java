package game;

import ai.DealerAI;

//QUESTION - SHOULDNT GameController have visibility of Evaluate to decouple it from Hand?

public class GameController {
	
	Deck deck;
	Hand playerHand;
	Hand dealerHand;
	//---------------------------------------------------------------------------------------------------------------------------------
	DealerAI dealerAI;
	//---------------------------------------------------------------------------------------------------------------------------------


	public GameController(){
		
		deck = new Deck();
		playerHand = new Hand(deck);
		dealerHand = new Hand(deck);
	
	}
	
	public void refreshGame(){
		
		//can we do this in a better way - e.g. clear the hand/deck and refill them. factory singleton deck which clears and refills
		deck = new Deck();
		playerHand = new Hand(deck);
		dealerHand = new Hand(deck);
		
	}
	
	public void evaluateHands() {
		playerHand.evaluate();
		dealerHand.evaluate();
	}
	
	public int getHandScore(String hand) {
		if(hand.equalsIgnoreCase("player")){
			return playerHand.getHandScore();
		} else {
			return dealerHand.getHandScore();
		}
	}
	
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
	
	public PlayingCard dealCard() {
		return deck.dealCard();
	}

	public void getNewCard(String player, int cardPosition) {
		if(player.equals("player")){
			playerHand.set(cardPosition-1, dealCard());
		}
		else if(player.equals("dealer")){
			dealerHand.set(cardPosition-1, dealCard());
		}
		
	}

	public String getPlayerHand() {
		return playerHand.get(0).getPlayingCardFullName() + "\n" + playerHand.get(1).getPlayingCardFullName() + "\n" + playerHand.get(2).getPlayingCardFullName() + "\n" + playerHand.get(3).getPlayingCardFullName() + "\n" + playerHand.get(4).getPlayingCardFullName();
	}
	
	public String getDealerHand(){
		return dealerHand.get(0).getPlayingCardFullName() + "\n" + dealerHand.get(1).getPlayingCardFullName() + "\n" + dealerHand.get(2).getPlayingCardFullName() + "\n" + dealerHand.get(3).getPlayingCardFullName() + "\n" + dealerHand.get(4).getPlayingCardFullName();
	}
	
	//---tom new------------------------------------------------------------------------------------------------------------------------------
	public void dealerExchange() {
		DealerAI dealerAI = new DealerAI(dealerHand);
	}
	//---------------------------------------------------------------------------------------------------------------------------------



}

