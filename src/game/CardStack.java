package game;

import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class CardStack extends ArrayList<PlayingCard>{
	
	public PlayingCard PlayingCardFactory(){
		
		return new PlayingCard();
		
	}
}
