package gui;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pokerLauncher.GuiToGameLink;

public class DealerHandPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RAISE_HEIGHT = 80;

	ImageIcon testCard = new ImageIcon("res/graphics/classic-cards/b2fv.png");
	JLabel testCardDisplay1;
	JLabel testCardDisplay2;
	JLabel testCardDisplay3;
	JLabel testCardDisplay4;
	JLabel testCardDisplay5;

	public DealerHandPanel() {

		FlowLayout playingCardLayout = new FlowLayout(FlowLayout.CENTER,20,0);
		setLayout(playingCardLayout);

		testCardDisplay1 = new JLabel(testCard);
		testCardDisplay1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		testCardDisplay2 = new JLabel(testCard);
		testCardDisplay2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		testCardDisplay3 = new JLabel(testCard);
		testCardDisplay3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		testCardDisplay4 = new JLabel(testCard);
		testCardDisplay4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		testCardDisplay5 = new JLabel(testCard);
		testCardDisplay5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		add(testCardDisplay1);
		add(testCardDisplay2);
		add(testCardDisplay3);
		add(testCardDisplay4);
		add(testCardDisplay5);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void showDealerHand() {
		testCardDisplay1.setIcon(new ImageIcon("res/graphics/classic-cards/" + GuiToGameLink.getCardToDisplay("dealer", 1) + ".png"));
		testCardDisplay2.setIcon(new ImageIcon("res/graphics/classic-cards/" + GuiToGameLink.getCardToDisplay("dealer", 2) + ".png"));
		testCardDisplay3.setIcon(new ImageIcon("res/graphics/classic-cards/" + GuiToGameLink.getCardToDisplay("dealer", 3) + ".png"));
		testCardDisplay4.setIcon(new ImageIcon("res/graphics/classic-cards/" + GuiToGameLink.getCardToDisplay("dealer", 4) + ".png"));
		testCardDisplay5.setIcon(new ImageIcon("res/graphics/classic-cards/" + GuiToGameLink.getCardToDisplay("dealer", 5) + ".png"));
	}
}
