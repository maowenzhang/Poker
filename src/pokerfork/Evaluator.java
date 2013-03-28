package pokerfork;

public class Evaluator{
	
	public static final int STRAIGHT_FLUSH = 8;
	public static final int FOUROFAKIND = 7;
	public static final int FULLHOUSE = 6;
	public static final int FLUSH = 5;
	public static final int STRAIGHT = 4;
	public static final int THREEOFAKIND = 3;
	public static final int TWOPAIR = 2;
	public static final int ONEPAIR = 1;
	
	private  PlayingCard card1;
	private  PlayingCard card2;
	private  PlayingCard card3;
	private  PlayingCard card4;
	private  PlayingCard card5;
	
	private  int card1value;
	private  int card2value;
	private  int card3value;
	private  int card4value;
	private  int card5value;
	
	private  int card1suit;
	private  int card2suit;
	private  int card3suit;
	private  int card4suit;
	private  int card5suit;
	
	private  int handValue = 0;
	//private static PlayingCard highCard;
	private  PlayingCard highCard = new PlayingCard();
	
	private  int highCardWithin = 0;
	private  int highCardWithin2 = 0;
	private  int highCardWithout = 0;
	private  int highCardWithout2 = 0;
	private  int highCardWithout3 = 0;
	private  int highCardWithout4 = 0;
	private  int highCardSuit = 0;

	
	private int handScore = 0;
	private String handName = "";

	
	/*
	 * Arranges the hand in order from highest to lowest and sets records each card's suit and value
	 * 
	 */
	
	public  void setHandValue(){


		if(straightCalculator()){
			
			handValue = STRAIGHT;

			if(card1value == 1 && card2value == 5){

				highCard.setPlayingCard(card2value, card2suit);
			}

			if(flushCalculator()){
				
				handValue = STRAIGHT_FLUSH;		
						
			}

		}

		else if(fourCalculator()){

			handValue = FOUROFAKIND;

			if(card1value != card2value){
				highCardWithout = card1value;
			}

			else{

				highCardWithout = card5value;

			}

		}

		else if(fullHouseCaclulator()){
			
			handValue = FULLHOUSE;
			
			if(card2value != card3value){
				
				highCard.setPlayingCard(card3value, card3suit);
				
			}
		}
		
		else if(threeCalculator()){
			
			handValue = THREEOFAKIND;
			
			if(card1value == card3value){
				
				highCardWithout = card4value;
				
			}
			
			else if(card2value == card4value){

				highCard.setPlayingCard(card2value, card2suit);
				highCardWithout = card1value;
				
			}
			
			else if(card3value == card5value){
				
				highCard.setPlayingCard(card3value, card3suit);
				highCardWithout = card1value;
				
			}
		
		}
			
		else if(twoTwoCalculator()){
			
			handValue = TWOPAIR;
			
			if(card1value == card2value){
				
				highCard.setPlayingCard(card1value, card1suit);
				
				if(card3value!=card4value){
				
					highCardWithout = card3value;	
					highCardWithin2 = card4value;
					
				}
				
				else{
					
					highCardWithout = card5value;
					highCardWithin2 = card3value;
					
				}
				
			}
			
			else{
				
				highCard.setPlayingCard(card2value, card2suit);
				highCardWithin2 = card4value;
				highCardWithout = card1value;
				
			}
			
		}
		
		else if(twoCalculator()){
			
			handValue = ONEPAIR;
			
			if (card1value == card2value){
				
				highCardWithout = card3value;
				highCardWithout2  = card4value;
				highCardWithout3 = card5value;
				
			}

			else if(card2value == card3value){

				highCard.setPlayingCard(card2value, card2suit);
				highCardWithout = card1value;
				highCardWithout2  = card4value;
				highCardWithout3 = card5value;
				
			}

			else if (card3value == card4value){

				highCard.setPlayingCard(card3value, card3suit);
				highCardWithout = card1value;
				highCardWithout2  = card2value;
				highCardWithout3 = card5value;
				
			}
			
			else if (card4value == card5value){

				highCard.setPlayingCard(card4value, card4suit);
				highCardWithout = card1value;
				highCardWithout2  = card2value;
				highCardWithout3 = card3value;
				
			}
			
		}
		
		//This is just a high card - hand value score defaults to 0
			
		else{
				
				highCardWithout = card2value;
				highCardWithout2 = card3value;
				highCardWithout3 = card4value;
				highCardWithout4 = card5value;
				
			}
			
		}
	
		private  boolean twoCalculator() {
		
			return (card1value == card2value) ||
				   (card2value == card3value) ||
				   (card3value == card4value) ||
				   (card4value == card5value);

	}

	private  boolean twoTwoCalculator() {

		return (card1value == card2value &&
				card3value == card4value) ||
					
			(card1value == card2value &&
					card4value == card5value) ||
					
				   (card2value == card3value &&
				    card4value ==card5value);
	}

	private  boolean threeCalculator() {
		
		return (card1value == card3value) ||
					
			   (card2value == card4value) ||
					
			   (card3value == card5value);
			
	}

	private  boolean fullHouseCaclulator() {
			
		return (card1value == card3value &&
				card4value == card5value) ||
					
				(card1value == card2value &&
				 card3value == card5value);
	}

	private  boolean fourCalculator() {
		
		return (card1value == card4value) ||
				
			   (card2value == card5value);
	}

	private  boolean flushCalculator() {
		
		return (card1suit == card2suit &&
				card2suit == card3suit &&
				card3suit == card4suit &&
				card4suit == card5suit);
		
	}
	
	/* determines whether a hand is a straight - accounting for ace high or low
	 * @return boolean: true if hand is a straight
	 */

	private  boolean straightCalculator() {

		return 
			//normal straight
			(card1value == card2value + 1 && 
			card2value == card3value + 1 && 
			card3value == card4value + 1 && 
			card4value == card5value + 1) ||
			
			//straight ascending from ace
			(card1value == 1 &&
			card2value == 5 &&
			card3value == 4 &&
			card4value == 3 &&
			card5value == 2) ||
			
			//straight ascending into ace
			card1value == 1 &&
			card2value == 13 &&
			card3value == 12 &&
			card4value == 11 &&
			card5value == 10;
	}
	
	public void evaluate(Hand hand) {
		

		hand.sort();
		
		card1 = hand.get(0);
		card2 = hand.get(1);
		card3 = hand.get(2);
		card4 = hand.get(3);
		card5 = hand.get(4);
		
		card1value = card1.getPlayingCardValue();
		card2value = card2.getPlayingCardValue();
		card3value = card3.getPlayingCardValue();
		card4value = card4.getPlayingCardValue();
		card5value = card5.getPlayingCardValue();
		
		card1suit = card1.getPlayingCardSuit();
		card2suit = card2.getPlayingCardSuit();
		card3suit = card3.getPlayingCardSuit();
		card4suit = card4.getPlayingCardSuit();
		card5suit = card5.getPlayingCardSuit();


		highCard.setPlayingCard(card1value, card1suit);
		highCardWithin = highCard.getPlayingCardValue();
		
		setHandValue();
		
		
		
		/* treat aces as high */
		
		if(highCardWithin == 1){highCardWithin = 14;}
		if(highCardWithin2 == 1){highCardWithin2 = 14;}
		if(highCardWithout == 1){highCardWithout = 14;}
		if(highCardWithout2 == 1){highCardWithout2 = 14;}
		if(highCardWithout3 == 1){highCardWithout3 = 14;}
		if(highCardWithout4 == 1){highCardWithout4 = 14;}
		
		
		// NEED TO REORGANISE AND REMOVE THIS TEST CODE BLOCK
		int tempCardValue;
		if (highCard.getPlayingCardValue() == 1) {
			tempCardValue = 14;
		} else {
			tempCardValue = highCard.getPlayingCardValue();
		}
		
		setHandScore(handValue + 1139 + tempCardValue + 563 + highCardWithin2 + 275 + highCardWithout + 131 + highCardWithout2 + 59 + highCardWithout3 + 23 + highCardWithout4 + 5 + highCard.getPlayingCardSuit());
		setHandName();
		
	}

	

	public String getHandName() {
		return this.handName;
	}
	
	private void setHandName() {
		String handName = "";
		
		switch (handValue) {
		case 0:
			handName = "High Card (" + highCard.getPlayingCardFullName() + ")";
			break;
		case 1:
			handName = "One Pair (" + highCard.getPlayingCardValueName() + ")";
			break;
		case 2:
			handName = "Two Pair (" + highCard.getPlayingCardValueName() + ")";
			break;
		case 4:
			handName = "Straight (" + highCard.getPlayingCardValueName() + " high)";
			break;
		case 5:
			handName = "Flush (" + highCard.getPlayingCardSuitName() + ")";
			break;
		case 6:
			//SORT OUT HIGH CARD WITHIN 2 TO INSERT PAIR VALUE NAME
			handName = "Full House (" + highCard.getPlayingCardValueName() + ")";
			break;
		case 7:
			handName = "Four of a kind (" + highCard.getPlayingCardValueName() + ")";
			break;
		case 8:
			handName = "Straight Flush (" + highCard.getPlayingCardFullName() + " high)";
			break;
		default:
			handName = "ERROR";
			break;
		}
		
		this.handName = handName;
	}

	public int getHandScore() {
		return handScore;
	}

	private void setHandScore(int handScore) {
		this.handScore = handScore;
	}

	
}
