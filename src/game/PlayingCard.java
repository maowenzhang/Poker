package game;

/**
 * the playing card class is the currency of the game and can be generated and passed around between different card stack classes. 
 * Playing cards have suits and values which are comparable. The playing card class can relay its state to the hand class that
 * contains it.
 * @author Tom & Jonathan
 *
 */
public class PlayingCard implements Comparable<PlayingCard> {
	private int cardValue;
	private int suit;
	//private int rankingInHand;
	//private String fullName;

	/**
	 * Compare method to allow the hand to sort. Aces are treated as high cards
	 */
	public int compareTo(PlayingCard card){

		/* ace treated as high card */
		if(this.cardValue == 1 && card.cardValue == 1){
			
			if(this.suit > card.suit){
				
				return 1;
				
			}
			
			else return -1;
			
		}

		if(this.cardValue == 1){
			return 1;
		}

		if(card.cardValue ==1){
			return -1;
		}

		if (this.cardValue > card.cardValue){
			return 1;
		}

		if (this.cardValue < card.cardValue){
			return -1;
		}

		else if (this.suit > card.suit){
			
			return 1;
		}
			
		else return -1;

	}
	
	/** 
	 * setter method - sets the suit and value of a playing card
	 * @param the card value
	 * @param the suit value
	 */
	public void setPlayingCard (int cardValue, int suit) {
		this.cardValue = cardValue;
		this.suit = suit;
		//this.rankingInHand = 0;
	}
	
	/**
	 * getter method - gets the value of a card
	 * @return the value of a card
	 */
	public int getPlayingCardValue() {
		return this.cardValue;
	}

	/**
	 * getter method - gets the suit of a card
	 * @return the suit of a card
	 */
	public int getPlayingCardSuit() {
		return this.suit;
	}
	
	/**
	 *  getter method - gets the full name of a playing card (e.g. Ace of Spades)
	 * @return the full name of a playing card
	 */
	public String getPlayingCardFullName() {
		boolean plural = false;
		return getPlayingCardValueName(plural) + " of " + getPlayingCardSuitName();
	}

	/**
	 * getter method - gets the value name of a playing card (e.g. Ace)
	 * @param whether the card is plural or not
	 * @return the value name of a playing card
	 */
	public String getPlayingCardValueName(boolean plural) {
		switch (this.cardValue) {
		case 1:
			if (plural) {return "Aces";} else {return "Ace";}
		case 2:
			if (plural) {return "Twos";} else {return "Two";}
		case 3:
			if (plural) {return "Threes";} else {return "Three";}
		case 4:
			if (plural) {return "Fours";} else {return "Four";}
		case 5:
			if (plural) {return "Fives";} else {return "Five";}
		case 6:
			if (plural) {return "Sixes";} else {return "Six";}
		case 7:
			if (plural) {return "Sevens";} else {return "Seven";}
		case 8:
			if (plural) {return "Eights";} else {return "Eight";}
		case 9:
			if (plural) {return "Nines";} else {return "Nine";}
		case 10:
			if (plural) {return "Tens";} else {return "Ten";}
		case 11:
			if (plural) {return "Jacks";} else {return "Jack";}
		case 12:
			if (plural) {return "Queens";} else {return "Queen";}
		case 13:
			if (plural) {return "Kings";} else {return "King";}
		default:
			return "ERROR: invalid card value";
		}
	}

	/**
	 * getter method - gets the name of the playing card suit
	 * @return the name of the playing card suit
	 */
	public String getPlayingCardSuitName() {
		switch (this.suit) {
		case 1:
			return "Clubs";
		case 2:
			return "Diamonds";
		case 3:
			return "Hearts";
		case 4:
			return "Spades";
		default:
			return "ERROR: invalid suit value";
		}
	}
}