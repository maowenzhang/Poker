package gui;


import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import pokerLauncher.GameController.IsPlayerTurn;

public class GUIController implements Observer{

	private PlayerHandPanel playerHandPanel;
	private DealerHandPanel dealerHandPanel;
	private ControlPanel controlPanel;

	private int playerCardsForExchange = 0;
	private final int RAISE_HEIGHT = 80;
	
	private boolean isPlayerTurn;

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
			playerHandPanel.setLabelBorders();
		}
	}

	/**
	 * sets all cards as card back for given hand
	 */
	public void setCardDisplay(int[] cardsToDisplay) {
		if (getIsPlayerTurn()){	
			int i = 1;
			for (int element : cardsToDisplay){
				playerHandPanel.setCardDisplay("res/graphics/classic-cards/" + element + ".png",i);
				i++;
			}
			playerHandPanel.setLabelBorders();
		} 
		else {
			int i = 1;
			for (int element : cardsToDisplay){
				dealerHandPanel.setCardDisplay("res/graphics/classic-cards/" + element + ".png",i);
				i++;
			}
			dealerHandPanel.setDealerCardsToOriginalPosition();
		}
	}
	
	/**
	 * determines if a card display shows a card raised for exchange
	 * @param a card display
	 * @return true if card display shows a raised card raised for exchange
	 * 
	 * ARE WE STILL USING THIS?
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

		return playerHandPanel.getCardRaisedStatus(cardDisplay);
	}
	
	public void displayResults(String[] results){
		controlPanel.displayResults(results);
	}
	


	/**
	 * asks player hand panel to show the backs of its cards 
	 */
	public void setPlayerCardsToBack() {
		playerHandPanel.setPlayerCardsToBack();
	}
	
	
	
		//	try {
		//			Thread.sleep(2000);
		//		} catch(InterruptedException e) {
		//		} 

	
	public void setDealerSwapNum (int dealerSwapNum){

	Random generator1 = new Random();

	int cardToSwap1 = 0;
	int cardToSwap2 = 0;
	int cardToSwap3 = 0;
	int tempCardValue = 0;
	
	if (dealerSwapNum != 0) {
		cardToSwap1 = generator1.nextInt(5) + 1;

		moveDealerCard(cardToSwap1);

		if (dealerSwapNum >= 2) {
			do {
				tempCardValue = generator1.nextInt(4) + 1;
			} while (tempCardValue == cardToSwap1);
			cardToSwap2 = tempCardValue;

			moveDealerCard(cardToSwap2);
		}

		if (dealerSwapNum >= 3) {
			do {
				tempCardValue = generator1.nextInt(4) + 1;
			} while (tempCardValue == cardToSwap1 || tempCardValue == cardToSwap2 );
			cardToSwap3 = tempCardValue;
			moveDealerCard(cardToSwap3);
			
			}
		}
	}
	
	public void moveDealerCard(int cardToSwap) {
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
		if(arg instanceof IsPlayerTurn){
			setIsPlayerTurn(((IsPlayerTurn) arg).getIsPlayerTurn());
		}
	}

	public void setDealerCardSwapMessage(int dealerSwapNum) {
		controlPanel.dealerCardSwapMessage(dealerSwapNum); 
	}

	public void refreshGame() {
		playerHandPanel.refresh();
		dealerHandPanel.resetDealerHandDisplay();
		controlPanel.refresh();
	}

	public void setIsPlayerTurn(boolean isPlayerTurn) {
		this.isPlayerTurn = isPlayerTurn;
	}

	public boolean getIsPlayerTurn() {
		return isPlayerTurn;
	}


}
