package pokerfork;

public class PlayingCard {
	private int cardValue;
	private int suit;
	private String fullName;

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
		switch (this.cardValue) {
		case 1:
			this.fullName = "Ace of ";
			break;
		case 2:
			this.fullName = "Two of ";
			break;
		case 3:
			this.fullName = "Three of ";
			break;
		case 4:
			this.fullName = "Four of ";
			break;
		case 5:
			this.fullName = "Five of ";
			break;
		case 6:
			this.fullName = "Six of ";
			break;
		case 7:
			this.fullName = "Seven of ";
			break;
		case 8:
			this.fullName = "Eight of ";
			break;
		case 9:
			this.fullName = "Nine of ";
			break;
		case 10:
			this.fullName = "Ten of ";
			break;
		case 11:
			this.fullName = "Jack of ";
			break;
		case 12:
			this.fullName = "Queen of ";
			break;
		case 13:
			this.fullName = "King of ";
			break;
		default:
			this.fullName = "ERROR: invalid card value: " + this.cardValue + ".  ";
			break;
		}

		switch (this.suit) {
		case 1:
			this.fullName = this.fullName + "Spades";
			break;
		case 2:
			this.fullName = this.fullName + "Hearts";
			break;
		case 3:
			this.fullName = this.fullName + "Clubs";
			break;
		case 4:
			this.fullName = this.fullName + "Diamonds";
			break;
		default:
			this.fullName = this.fullName + "ERROR: invalid suit value: " + this.suit;
			break;
		}

		return fullName;
	}
}