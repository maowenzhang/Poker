package pokerfork;

public class PlayingCard implements Comparable<PlayingCard> {
	private int cardValue;
	private int suit;
	private String fullName;

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
	}

	public int getPlayingCardValue() {
		return this.cardValue;
	}

	public int getPlayingCardSuit() {
		return this.suit;
	}

	public String getPlayingCardFullName() {
		return getPlayingCardValueName() + " of " + getPlayingCardSuitName();
	}

	public String getPlayingCardValueName() {
		switch (this.cardValue) {
		case 1:
			return "Ace";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		case 10:
			return "Ten";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
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