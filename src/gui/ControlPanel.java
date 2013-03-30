package gui;


import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pokerLauncher.Poker;
import pokerLauncher.Round;

public class ControlPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton btnDeal = new JButton("Deal");
	JButton btnCardExchange = new JButton("Exchange Cards");
	JButton btnShowDealerHand = new JButton("Show Dealer Hand");
	JButton btnScoreHands = new JButton("Score Hands");
	private boolean playerHasExchangedCards = false;

	private GUIController guiController;

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

	public void setControl(GUIController guiControl) {
		this.guiController = guiControl;
	}

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

			guiController.setPlayerCardDisplay();

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
					"Another Round?",
					JOptionPane.YES_NO_OPTION) == 0) {
				// RESET GAME
				//int temp = 3;
				//temp=54;
				guiController.setPlayerCardsToBack();
				//PlayerHandPanel.
				this.repaint();
			} else {
				
				//tell round to exit instead
				System.exit(0);
			}

		}
	}

	private void showDealerHand() {
		btnCardExchange.setEnabled(false);
		btnShowDealerHand.setEnabled(false);
		guiController.showDealerHand();
		btnScoreHands.setEnabled(true);
	}

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

	public void exchangeBtnEnable() {
		btnCardExchange.setEnabled(true);
	}

	public void exchangeBtnDisable() {
		btnCardExchange.setEnabled(false);
	}

	public void dealerCardSwapMessage(int dealerSwapNum) {
		JOptionPane.showMessageDialog(this, "Dealer will swap " + dealerSwapNum + " cards.",
				"Dealer",
				JOptionPane.OK_OPTION);
	}
}
