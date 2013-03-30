package game;

import java.util.ArrayList;

/**
 * the card class inherits functionality from arraylist and adds a factory method to generate playing cards. It is an abstract
 * class which both deck and hand inherit from.
 * @author Tom & Jonathan
 *
 */
@SuppressWarnings("serial")
public abstract class CardStack extends ArrayList<PlayingCard>{
	
	/** 
	 * factory method to return playing card
	 * @return Playing Card
	 */
	public PlayingCard PlayingCardFactory(){
		
		return new PlayingCard();
		
	}
}
