package gui;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import pokerLauncher.Round;

public class GUIController {
	
	private PlayerHandPanel playerHandPanel;
	private DealerHandPanel dealerHandPanel;
	private ControlPanel controlPanel;

	private int playerCardsForExchange = 0;
	private final int RAISE_HEIGHT = 80;
	
	private Round round;
	
	public void startRound(){
		round = new Round();	
	}

	public void showDealerHand() {
		//presumably nulls ensure that it won't crash if objects don't exist. do we need?
		if (playerHandPanel != null && dealerHandPanel != null) {
			dealerHandPanel.showDealerHand();
		}
	}

	public void setPlayerHandPanel(PlayerHandPanel playerHandPanel) {
		this.playerHandPanel = playerHandPanel;
	}

	public void setDealerHandPanel(DealerHandPanel dealerHandPanel) {
		this.dealerHandPanel = dealerHandPanel;
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

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

	public void setPlayerCardDisplay() {
		
		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(1) + ".png",1);
		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(2) + ".png",2);
		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(3) + ".png",3);
		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(4) + ".png",4);
		playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(5) + ".png",5);
		
		playerHandPanel.setLabelBorders();
		
	}
	
	public void setDealerCardDisplay() {
		
		round.changePlayer();
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(1) + ".png",1);
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(2) + ".png",2);
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(3) + ".png",3);
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(4) + ".png",4);
		dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + round.getCardToDisplay(5) + ".png",5);
		
		// should this be used like for player? 
		//playerHandPanel.setLabelBorders();
	}

	public boolean getCardRaisedStatus(JLabel cardDisplay) {
		if (cardDisplay.getBorder().getBorderInsets(cardDisplay).top == RAISE_HEIGHT) {
			return false;
		} else {
			return true;
		}
	}
	
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

	public void setPlayerCardsToBack() {
		playerHandPanel.setPlayerCardsToBack();
	}
	
	public String[] getScore(){
		return round.getResults();
	}

	public int getPlayerScore() {
		return round.getPlayerScore();
	}

	public int getDealerScore() {
		return round.getDealerScore();
	}

	public void getNewCard(int cardPosition) {
		round.getNewCard(cardPosition);	
	}

	public void printHand() {
		round.printHand();
	}

	public void dealerExchange() {
		round.dealerExchange();
	}
}
