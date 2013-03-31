package gui;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pokerLauncher.GameController;

/**
 * the control panel class is where the human player can interact with the GUI 
 * @author Tom & Jonathan
 *
 */
public class ControlPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton btnDeal = new JButton("Deal");
	JButton btnCardExchange = new JButton("Exchange Cards");
	JButton btnShowDealerHand = new JButton("Show Dealer Hand");
	JButton btnScoreHands = new JButton("Score Hands");
	private boolean playerHasExchangedCards = false;

	private GUIController guiController;
	private GameController gameController;

	/**
	 * constructor adds buttons and actionlistener to the panel 
	 */
	public ControlPanel() {
		FlowLayout controlPanelLayout = new FlowLayout(FlowLayout.CENTER,20,0);
		setLayout(controlPanelLayout);

		btnDeal.addActionListener(this);
		add(btnDeal);

		btnCardExchange.addActionListener(this);
		btnCardExchange.setEnabled(false);
		add(btnCardExchange);

		btnShowDealerHand.addActionListener(this);
		btnShowDealerHand.setEnabled(false);
		add(btnShowDealerHand);

		btnScoreHands.addActionListener(this);
		btnScoreHands.setEnabled(false);
		add(btnScoreHands);
		
	}
	
	public void refresh(){
		playerHasExchangedCards = false;
		
		btnDeal.setEnabled(true);
		btnCardExchange.setEnabled(false);
		btnShowDealerHand.setEnabled(false);
		btnScoreHands.setEnabled(false);
	}

	/**
	 * setter method - enables panel to see the GUI controller
	 * @param the guiControl reference
	 */
	public void setControl(GUIController guiControl) {
		this.guiController = guiControl;
	}
	
	/**
	 * Logic determines which action to take place and passes orders to GUI controller
	 * Deal - sends message to GUI controller to displayer player's hand
	 * Exchange cards - sends message to GUI controller to exchange player cards and then dealer's cards
	 * Score hands - sends request to GUI controller to find out score and displays score
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		//JButton clickedButton = (JButton)actionEvent.getSource();

		if (actionEvent.getActionCommand().equals("Deal")) {
			//guiController.printHand();

			btnDeal.setEnabled(false);
			gameController.displayHand();
			btnShowDealerHand.setEnabled(true);
		}


		if (actionEvent.getActionCommand().equals("Exchange Cards")) {
			
			gameController.exchangeCards(exchangePlayerCards());
			gameController.changeTurn();
			
			//-TOM NEW--------------------------------------------------------------------------------------------------------------------------------

			JOptionPane.showMessageDialog(this,"Dealer will now exchange cards...",
					"Dealer",
					JOptionPane.OK_OPTION);
			gameController.exchangeCards();


			//---------------------------------------------------------------------------------------------------------------------------------

		}
		//CHANGE OF TURN IS ASYNCHRONOUS (DEPENDING ON WHAT BUTTONS GET PRESSED
		if (actionEvent.getActionCommand().equals("Show Dealer Hand")) {
			if (!playerHasExchangedCards) {
				int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you don't want to exchange any cards?",
						"Just checking...",
						JOptionPane.YES_NO_OPTION);
				//no = 1
				if (confirm == 0) {
					gameController.changeTurn();
					gameController.exchangeCards();
					btnShowDealerHand.setEnabled(false);
					btnScoreHands.setEnabled(true);
				}
			} else {
				gameController.exchangeCards();
				showDealerHand();
			}
		}

		if (actionEvent.getActionCommand().equals("Score Hands")) {
			
			gameController.scoreHands();
			gameController.changeTurn();
		}
	}
	
	/**
	 * Asks GUI controller to make dealer's hand visible
	 */
	private void showDealerHand() {
		btnCardExchange.setEnabled(false);
		btnShowDealerHand.setEnabled(false);
		btnScoreHands.setEnabled(true);
		
		gameController.showDealerHand();
	}
	
	/**
	 * Asks GUI controller which cards player has selected for exchange, then asks GUI to replace these with new cards.
	 * Once this is done, asks GUI to update the card display
	 */
	private boolean[] exchangePlayerCards() {

		btnCardExchange.setEnabled(false);
		
		boolean[] cardsToExchange = new boolean[5];

		if (guiController.getCardRaisedStatus(1)) {
			cardsToExchange[0] = true;
		}

		if (guiController.getCardRaisedStatus(2)) {
			cardsToExchange[1] = true;
		}

		if (guiController.getCardRaisedStatus(3)) {
			cardsToExchange[2] = true;
		}

		if (guiController.getCardRaisedStatus(4)) {
			cardsToExchange[3] = true;
		}

		if (guiController.getCardRaisedStatus(5)) {
			cardsToExchange[4] = true;
		}
		
		//guiController.setPlayerCardDisplay();
		guiController.setPlayerCardsforExchange(0);
		playerHasExchangedCards = true;
		
		return cardsToExchange;
		//guiController.printHand();
	}

	/**
	 * enables exchange button
	 */
	public void exchangeBtnEnable() {
		btnCardExchange.setEnabled(true);
	}

	/**
	 * disables exchange button
	 */
	public void exchangeBtnDisable() {
		btnCardExchange.setEnabled(false);
	}

	public void dealerCardSwapMessage(int dealerSwapNum) {
		JOptionPane.showMessageDialog(this, "Dealer will swap " + dealerSwapNum + " cards.",
				"Dealer",
				JOptionPane.OK_OPTION);
	}

	public void setControl(GameController gameController) {
		this.gameController = gameController;
	}

	public void displayResults(String[] results) {

		JOptionPane.showMessageDialog(this, results[0] + "\n" + results[1] + "\n\n" + results[2] + " has won the round!",
				"Results",
				JOptionPane.OK_OPTION);

		if (JOptionPane.showConfirmDialog(this, "Player score is: " + gameController.getPlayerPoints() + "\nDealer score is: " + gameController.getDealerPoints() + "\nDo you want to play another round?\nWell, do ya, punk?",
				"Another Round?",
				JOptionPane.YES_NO_OPTION) == 0) {
			// RESET GAME
			//int temp = 3;
			//temp=54;
			guiController.setPlayerCardsToBack();
			//PlayerHandPanel.
			this.repaint();
			gameController.newRound();
			}
		else {
			JOptionPane.showMessageDialog(this,"Sayonara, sweetheart");
			gameController.endGame();
		}
		
	}
}
