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

		gameController = new GameController();

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
			
			guiController.printHand();
			
			btnDeal.setEnabled(false);
			//btnCardExchange.setEnabled(true);
			//System.out.println("test: " + this.p);

			btnDeal.setEnabled(false);
			//btnCardExchange.setEnabled(true);

			gameController.setDealHands();
			//guiController.setPlayerCardDisplay();

			btnShowDealerHand.setEnabled(true);
		}


		if (actionEvent.getActionCommand().equals("Exchange Cards")) {
			exchangePlayerCards();
			

			//-TOM NEW--------------------------------------------------------------------------------------------------------------------------------

			JOptionPane.showMessageDialog(this,"Dealer will now exchange cards...",
					"Dealer",
					JOptionPane.OK_OPTION);

			guiController.dealerExchange();

			//---------------------------------------------------------------------------------------------------------------------------------

		}

		if (actionEvent.getActionCommand().equals("Show Dealer Hand")) {
			if (!playerHasExchangedCards) {
				int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you don't want to exchange any cards?",
						"Just checking...",
						JOptionPane.YES_NO_OPTION);

				//no = 1
				if (confirm == 0) {
					showDealerHand();
				}
			} else {
				showDealerHand();
			}
		}

		if (actionEvent.getActionCommand().equals("Score Hands")) {
			String[] results = guiController.getScore();

			JOptionPane.showMessageDialog(this, results[0] + "\n" + results[1] + "\n\n" + results[2] + " has won the round!",
					"Results",
					JOptionPane.OK_OPTION);

			if (JOptionPane.showConfirmDialog(this, "Player score is: " + guiController.getPlayerScore() + "\nDealer score is: " + guiController.getDealerScore() + "\n\nDo you want to play another round?\nWell, do ya, punk?",
					"Another Hub?",
					JOptionPane.YES_NO_OPTION) == 0) {
				// RESET GAME
				//int temp = 3;
				//temp=54;
				guiController.setPlayerCardsToBack();
				//PlayerHandPanel.
				this.repaint();
				
				gameController.setScoreHands(true);
			} else {
				
				//tell round to exit instead
				System.exit(0);
			}

		}
	}
	
	/**
	 * Asks GUI controller to make dealer's hand visible
	 */
	private void showDealerHand() {
		btnCardExchange.setEnabled(false);
		btnShowDealerHand.setEnabled(false);
		guiController.showDealerHand();
		btnScoreHands.setEnabled(true);
	}
	
	/**
	 * Asks GUI controller which cards player has selected for exchange, then asks GUI to replace these with new cards.
	 * Once this is done, asks GUI to update the card display
	 */
	private void exchangePlayerCards() {
		btnCardExchange.setEnabled(false);
		//btnDeal.setEnabled(true);

		if (guiController.getCardRaisedStatus(1)) {
			guiController.getNewCard(1);
		}

		if (guiController.getCardRaisedStatus(2)) {
			guiController.getNewCard(2);
		}

		if (guiController.getCardRaisedStatus(3)) {
			guiController.getNewCard(3);
		}

		if (guiController.getCardRaisedStatus(4)) {
			guiController.getNewCard(4);
		}

		if (guiController.getCardRaisedStatus(5)) {
			guiController.getNewCard(5);
		}
		
		guiController.setPlayerCardDisplay();
		guiController.setPlayerCardsforExchange(0);
		playerHasExchangedCards = true;

		guiController.printHand();
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
}
