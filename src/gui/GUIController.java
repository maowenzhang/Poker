package gui;


import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import pokerLauncher.Hub;

public class GUIController implements Observer{

	private PlayerHandPanel playerHandPanel;
	private DealerHandPanel dealerHandPanel;
	private ControlPanel controlPanel;

	private int playerCardsForExchange = 0;
	private final int RAISE_HEIGHT = 80;

	private Hub hub;



	/**
	 * asks dealer hand panel to display the dealer's hand
	 */
	public void showDealerHand() {
		//presumably nulls ensure that it won't crash if objects don't exist. do we need?
		if (playerHandPanel != null && dealerHandPanel != null) {
			dealerHandPanel.showDealerHand();
		}
	}

	/**
	 * setter method - enables GUI controller to see player hand panel
	 * @param the player hand panel reference
	 */
	public void setPlayerHandPanel(PlayerHandPanel playerHandPanel) {
		this.playerHandPanel = playerHandPanel;
	}

	/**
	 * setter method - enables GUI controller to see dealer hand panel
	 * @param the dealer hand panel reference
	 */
	public void setDealerHandPanel(DealerHandPanel dealerHandPanel) {
		this.dealerHandPanel = dealerHandPanel;
	}

	/**
	 * setter method - enables GUI controller to see control panel
	 * @param the control panel reference
	 */
	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	/**
	 * setter method - asks control panel to disable exchange button if no cards are selected to exchange
	 * @param the number of cards selected to exchange
	 */
	public void setPlayerCardsforExchange(int number) {
		playerCardsForExchange = number;

		if (playerCardsForExchange>0) {
			controlPanel.exchangeBtnEnable();
		} else {
			controlPanel.exchangeBtnDisable();
		}
	}

	//private void testing() {
	//	controlPanel.exchangeBtnEnable();
	//}

	//public int getPlayerCardsforExchange() {
	//	return playerCardsForExchange;
	//}

	/**
	 * asks the player hand panel to set its display
	 */
	public void setPlayerCardDisplay() {

		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(1) + ".png",1);
		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(2) + ".png",2);
		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(3) + ".png",3);
		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(4) + ".png",4);
		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(5) + ".png",5);

		playerHandPanel.setLabelBorders();

	}

	/**
	 * asks the dealer hand panel to set its display
	 */
	public void setDealerCardDisplay() {

		hub.changePlayer();
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(1) + ".png",1);
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(2) + ".png",2);
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(3) + ".png",3);
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(4) + ".png",4);
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + hub.getCardToDisplay(5) + ".png",5);

		// should this be used like for player? 
		//playerHandPanel.setLabelBorders();
	}

	/**
	 * determines if a card display shows a card raised for exchange
	 * @param a card display
	 * @return true if card display shows a raised card raised for exchange
	 * 
	 * I THINK WE'RE NOT USING THIS ANYMORE
	 */
	public boolean getCardRaisedStatus(JLabel cardDisplay) {
		if (cardDisplay.getBorder().getBorderInsets(cardDisplay).top == RAISE_HEIGHT) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * asks the player hand panel to determine if a card has been raised for exchange
	 * @param the card display to check (1 - 5)
	 * @return true if the card has been raised for exchange
	 */
	public boolean getCardRaisedStatus(int cardDisplay) {

		boolean raisedStatus = false;
		/*
		switch (cardDisplay){
			case 1: raisedStatus = playerHandPanel.getCardRaisedStatus(cardDisplay);
			case 2: raisedStatus = playerHandPanel.getCardRaisedStatus(cardDisplay);
			case 3: raisedStatus = playerHandPanel.getCardRaisedStatus(cardDisplay);
			case 4: raisedStatus = playerHandPanel.getCardRaisedStatus(cardDisplay);
			case 5: raisedStatus = playerHandPanel.getCardRaisedStatus(cardDisplay);
		}
		 */

		raisedStatus = playerHandPanel.getCardRaisedStatus(cardDisplay);

		return raisedStatus;

	}

	/**
	 * asks player hand panel to show the backs of its cards 
	 */
	public void setPlayerCardsToBack() {
		playerHandPanel.setPlayerCardsToBack();
	}

	public String[] getScore(){
		return hub.getResults();
	}

	public int getPlayerScore() {
		return hub.getPlayerScore();
	}

	public int getDealerScore() {
		return hub.getDealerScore();
	}

	public void getNewCard(int cardPosition) {
		hub.getNewCard(cardPosition);	
	}

	public void printHand() {
		hub.printHand();
	}

	public void dealerExchange() {
		hub.dealerExchange();
		controlPanel.dealerCardSwapMessage(hub.getDealerSwapNum());

		//based on number of cards to change in dealer hand, build array of random number card backs to move
		int cardToSwap1 = 0;
		int cardToSwap2 = 0;
		int cardToSwap3 = 0;
		int tempCardValue = 0;
		//int counter = 0;
		//int loopCount = 0;

		Random generator1 = new Random();

		if (hub.getDealerSwapNum() != 0) {
			cardToSwap1 = generator1.nextInt(4) + 1;

			moveDealerCard(cardToSwap1);

			if (hub.getDealerSwapNum() >= 2) {
				do {
					tempCardValue = generator1.nextInt(4) + 1;
				} while (tempCardValue == cardToSwap1);
				cardToSwap2 = tempCardValue;

				moveDealerCard(cardToSwap2);
			}

			if (hub.getDealerSwapNum() >= 3) {
				do {
					tempCardValue = generator1.nextInt(4) + 1;
				} while (tempCardValue == cardToSwap1 || tempCardValue == cardToSwap2 );
				cardToSwap3 = tempCardValue;
				moveDealerCard(cardToSwap3);
			}
		}


		//		try {
		//			Thread.sleep(2000);
		//		} catch(InterruptedException e) {
		//		} 

		//dealerHandPanel.testCardDisplay1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//dealerHandPanel.testCardDisplay2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//dealerHandPanel.testCardDisplay3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//dealerHandPanel.testCardDisplay4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//dealerHandPanel.testCardDisplay5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


		//		while (loopCount <= hub.getDealerSwapNum() && loopCount!=0) {
		//
		//			do {
		//				Random generator1 = new Random();
		//				int cardToSwap = generator1.nextInt(4);
		//				cardsToSwap[loopCount] = cardToSwap;
		//			} while (1!=1);
		//
		//			loopCount++;
		//		}

		//dealerHandPanel.setCardDisplay
	}

	private void moveDealerCard(int cardToSwap) {
		switch (cardToSwap) {
		case 1:
			dealerHandPanel.CardDisplay1.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
			break;
		case 2:
			dealerHandPanel.CardDisplay2.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
			break;
		case 3:
			dealerHandPanel.CardDisplay3.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
			break;
		case 4:
			dealerHandPanel.CardDisplay4.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
			break;
		case 5:
			dealerHandPanel.CardDisplay5.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
			break;
		}

		dealerHandPanel.repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("OBSERVED by " + o.countObservers() + o.hasChanged());

	}

	public void setControl(Hub hub) {
		this.hub = hub;
	}

}
