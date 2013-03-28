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

import poker.EvaluatorTOM;
import pokerLauncher.GUIController;
import pokerLauncher.GuiToGameLink;
import pokerLauncher.Poker;

public class ControlPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton btnDeal = new JButton("Deal");
	JButton btnCardExchange = new JButton("Exchange Cards");
	JButton btnShowDealerHand = new JButton("Show Dealer Hand");
	JButton btnScoreHands = new JButton("Score Hands");
	private boolean playerHasExchangedCards = false;

	private GUIController guiControl;

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
		this.guiControl = guiControl;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		//JButton clickedButton = (JButton)actionEvent.getSource();

		if (actionEvent.getActionCommand().equals("Deal")) {
			btnDeal.setEnabled(false);
			btnCardExchange.setEnabled(true);
			//System.out.println("test: " + this.p);

			btnDeal.setEnabled(false);
			//btnCardExchange.setEnabled(true);

			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay1, "player", 1);
			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay2, "player", 2);
			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay3, "player", 3);
			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay4, "player", 4);
			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay5, "player", 5);

			btnShowDealerHand.setEnabled(true);
		}


		if (actionEvent.getActionCommand().equals("Exchange Cards")) {
			exchangePlayerCards();
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
			String[] results = GuiToGameLink.evaluateHands();

			JOptionPane.showMessageDialog(this, results[0] + "\n" + results[1] + "\n\n" + results[2] + " has won the round!",
					"Results",
					JOptionPane.OK_OPTION);

			if (JOptionPane.showConfirmDialog(this, "Player score is: " + GuiToGameLink.getPlayerScore() + "\nDealer score is: " + GuiToGameLink.getDealerScore() + "\n\nDo you want to play another round?\nWell, do ya, punk?",
					"Another Round?",
					JOptionPane.YES_NO_OPTION) == 0) {
				// RESET GAME
				//int temp = 3;
				//temp=54;
				guiControl.setPlayerCardsToBack();
				//PlayerHandPanel.
				this.repaint();
			} else {
				System.exit(0);
			}

		}
	}

	public static void setPlayerCardsToBack() {
		// TODO Auto-generated method stub
		
	}

	private void showDealerHand() {
		btnCardExchange.setEnabled(false);
		btnShowDealerHand.setEnabled(false);
		guiControl.showDealerHand();
		btnScoreHands.setEnabled(true);
	}

	private void exchangePlayerCards() {
		btnCardExchange.setEnabled(false);
		//btnDeal.setEnabled(true);

		if (guiControl.getCardRaisedStatus(PlayerHandPanel.cardDisplay1)) {
			GuiToGameLink.getNewCard("player", 1);
			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay1, "player", 1);
		}

		if (guiControl.getCardRaisedStatus(PlayerHandPanel.cardDisplay2)) {
			GuiToGameLink.getNewCard("player", 2);
			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay2, "player", 2);
		}

		if (guiControl.getCardRaisedStatus(PlayerHandPanel.cardDisplay3)) {
			GuiToGameLink.getNewCard("player", 3);
			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay3, "player", 3);
		}

		if (guiControl.getCardRaisedStatus(PlayerHandPanel.cardDisplay4)) {
			GuiToGameLink.getNewCard("player", 4);
			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay4, "player", 4);
		}

		if (guiControl.getCardRaisedStatus(PlayerHandPanel.cardDisplay5)) {
			GuiToGameLink.getNewCard("player", 5);
			guiControl.setCardDisplay(PlayerHandPanel.cardDisplay5, "player", 5);
		}

		guiControl.setPlayerCardsforExchange(0);
		playerHasExchangedCards = true;

		GuiToGameLink.printHand("player");
	}

	public void exchangeBtnEnable() {
		btnCardExchange.setEnabled(true);
	}

	public void exchangeBtnDisable() {
		btnCardExchange.setEnabled(false);
	}
}
