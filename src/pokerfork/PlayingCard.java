package pokerfork;

public class PlayingCard implements Comparable<PlayingCard> {
	private int cardValue;
	private int suit;
	//private int rankingInHand;
	//private String fullName;

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

	public void setPlayingCard (int cardValue, int suit) {
		this.cardValue = cardValue;
		this.suit = suit;
		//this.rankingInHand = 0;
	}

	public int getPlayingCardValue() {
		return this.cardValue;
	}

	public int getPlayingCardSuit() {
		return this.suit;
	}

	public String getPlayingCardFullName() {
		boolean plural = false;
		return getPlayingCardValueName(plural) + " of " + getPlayingCardSuitName();
	}

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