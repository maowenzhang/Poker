package pokerLauncher;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.ControlPanel;
import gui.DealerHandPanel;
import gui.PlayerHandPanel;

public class GUIController {
	private static PlayerHandPanel playerHandPanel;
	private DealerHandPanel dealerHandPanel;
	private static ControlPanel controlPanel;

	private static int playerCardsForExchange = 0;
	private static final int RAISE_HEIGHT = 80;

	public void showDealerHand() {
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
		GUIController.controlPanel = controlPanel;
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

	public void setCardDisplay(JLabel cardDisplay, String hand, int cardNumber) {
		cardDisplay.setIcon(new ImageIcon("res/graphics/classic-cards/" + GuiToGameLink.getCardToDisplay(hand, cardNumber) + ".png"));
		cardDisplay.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
	}

	public boolean getCardRaisedStatus(JLabel cardDisplay) {
		if (cardDisplay.getBorder().getBorderInsets(cardDisplay).top == RAISE_HEIGHT) {
			return false;
		} else {
			return true;
		}
	}

	public static void setPlayerCardsToBack() {
		playerHandPanel.setPlayerCardsToBack();
	}
}
