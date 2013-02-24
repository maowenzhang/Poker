package pokerfork;

import java.util.ArrayList;

import pokerfork.PlayingCard;

@SuppressWarnings("serial")
public abstract class CardStack extends ArrayList<PlayingCard>{
	
	public PlayingCard PlayingCardFactory(){
		
		return new PlayingCard();
		
	}

}
