package gui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.management.timer.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pokerLauncher.GUIController;
import pokerLauncher.GuiToGameLink;

public class PlayerHandPanel extends JPanel implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RAISE_HEIGHT = 80;
	private int numberOfCardsSelected = 0;
	private boolean playerHasExchangedCards = false;
	GUIController guiControl;

	JButton btnDeal = new JButton("Deal");
	JButton btnCardExchange = new JButton("Exchange Cards");
	JButton btnShowDealerHand = new JButton("Show Dealer Hand");

	static JLabel cardDisplay1;
	static JLabel cardDisplay2;
	static JLabel cardDisplay3;
	static JLabel cardDisplay4;
	static JLabel cardDisplay5;

	//private GUIController guiControl;

	/*	BufferedImage playingCard1;
	BufferedImage playingCard2;
	BufferedImage playingCard3;
	BufferedImage playingCard4;
	BufferedImage playingCard5;*/

	//private final int PANEL_WIDTH = 250;
	//private final int PANEL_HEIGHT = 300;
	
	public PlayerHandPanel() {

		FlowLayout playingCardLayout = new FlowLayout(FlowLayout.CENTER,20,0);
		setLayout(playingCardLayout);

		//BoxLayout playingCardLayout = new BoxLayout(FlowLayout.CENTER,20,0);
		//setLayout(playingCardLayout);

		setPlayerCardsToBack();

		cardDisplay1.addMouseListener(this);
		cardDisplay2.addMouseListener(this);
		cardDisplay3.addMouseListener(this);
		cardDisplay4.addMouseListener(this);
		cardDisplay5.addMouseListener(this);
		
		add(cardDisplay1);
		add(cardDisplay2);
		add(cardDisplay3);
		add(cardDisplay4);
		add(cardDisplay5);

		//JLabel spacer = new JLabel(new ImageIcon("res/graphics/classic-cards/b2fv.png"));
		//spacer.setSize(500, 50);
		//spacer.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));
		//spacer.setVisible(false);
		//add(spacer);

		btnDeal.addActionListener(this);
		//btnDeal.setBorder(BorderFactory.createEmptyBorder(100, 50, 50, 50));
		//add(btnDeal);

		btnCardExchange.addActionListener(this);
		//btnCardExchange.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		btnCardExchange.setEnabled(false);
		//add(btnCardExchange);

		btnShowDealerHand.addActionListener(this);
		btnShowDealerHand.setEnabled(false);
		//add(btnShowDealerHand);

		/*
		try {
			playingCard1 = ImageIO.read(new File("res/graphics/classic-cards/1.png"));
			playingCard2 = ImageIO.read(new File("res/graphics/classic-cards/2.png"));
			playingCard3 = ImageIO.read(new File("res/graphics/classic-cards/3.png"));
			playingCard4 = ImageIO.read(new File("res/graphics/classic-cards/4.png"));
			playingCard5 = ImageIO.read(new File("res/graphics/classic-cards/5.png"));
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		 */

	}
	
	public void repaintPlayerHand() {
		this.repaint();
	}

	public static void setPlayerCardsToBack() {
		ImageIcon testCard = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		
		cardDisplay1 = new JLabel(testCard);
		cardDisplay1.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay2 = new JLabel(testCard);
		cardDisplay2.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay3 = new JLabel(testCard);
		cardDisplay3.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay4 = new JLabel(testCard);
		cardDisplay4.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay5 = new JLabel(testCard);
		cardDisplay5.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
	}

	//	public void exchangeCard(JLabel clickedCard) {
	//		clickedCard.setIcon(new ImageIcon("res/graphics/classic-cards/" + randomCard() + ".png"));
	//	}
	//	
	//	public int randomCard() {
	//		Random generator = new Random();
	//		int cardSelector = generator.nextInt(51) + 1;
	//		return cardSelector;
	//	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*		g.drawImage(playingCard1, 0, 0, null);
		g.drawImage(playingCard2, 100, 0, null);
		g.drawImage(playingCard3, 200, 0, null);
		g.drawImage(playingCard4, 300, 0, null);
		g.drawImage(playingCard5, 400, 0, null);*/
	}




	public void setControl(GUIController guiControl) {
		this.guiControl = guiControl;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent event) {
		//System.out.println(event.getSource());
		Boolean actionCarriedOut = false;

		JLabel clickedCard = (JLabel)event.getSource();
		///setCardDisplay(clickedCard);
		if (!getCardName(clickedCard).contains("b")) {
			if (!actionCarriedOut && numberOfCardsSelected <=2 && clickedCard.getBorder().getBorderInsets(clickedCard).top == RAISE_HEIGHT) {
				clickedCard.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				numberOfCardsSelected += 1;
				actionCarriedOut = true;
			}

			if (!actionCarriedOut && clickedCard.getBorder().getBorderInsets(clickedCard).top == 0) {
				clickedCard.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
				numberOfCardsSelected -= 1;
				actionCarriedOut = true;
			}

			guiControl.setPlayerCardsforExchange(numberOfCardsSelected);
			
//			if (numberOfCardsSelected>0) {
//				btnCardExchange.setEnabled(true);
//			} else {
//				btnCardExchange.setEnabled(false);
//			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

/*		if (actionEvent.getActionCommand().equals("Deal")) {
			btnDeal.setEnabled(false);
			//btnCardExchange.setEnabled(true);

			setCardDisplay(cardDisplay1, "player", 1);
			setCardDisplay(cardDisplay2, "player", 2);
			setCardDisplay(cardDisplay3, "player", 3);
			setCardDisplay(cardDisplay4, "player", 4);
			setCardDisplay(cardDisplay5, "player", 5);

			btnShowDealerHand.setEnabled(true);
		}*/

		if (actionEvent.getActionCommand().equals("Exchange Cards")) {
			btnCardExchange.setEnabled(false);
			btnDeal.setEnabled(true);

			if (getCardRaisedStatus(cardDisplay1)) {
				GuiToGameLink.getNewCard("player", 1);
				setCardDisplay(cardDisplay1, "player", 1);
			}

			if (getCardRaisedStatus(cardDisplay2)) {
				GuiToGameLink.getNewCard("player", 2);
				setCardDisplay(cardDisplay2, "player", 2);
			}

			if (getCardRaisedStatus(cardDisplay3)) {
				GuiToGameLink.getNewCard("player", 3);
				setCardDisplay(cardDisplay3, "player", 3);
			}

			if (getCardRaisedStatus(cardDisplay4)) {
				GuiToGameLink.getNewCard("player", 4);
				setCardDisplay(cardDisplay4, "player", 4);
			}

			if (getCardRaisedStatus(cardDisplay5)) {
				GuiToGameLink.getNewCard("player", 5);
				setCardDisplay(cardDisplay5, "player", 5);
			}

			numberOfCardsSelected = 0;
			playerHasExchangedCards = true;

			GuiToGameLink.printHand("player");

			//Timer timer = new Timer();
			/*		    try {
		    	for (int loopCount = 1; loopCount<RAISE_HEIGHT; loopCount++) {
					//timer.
					setCardHeight(cardDisplay1,loopCount);
		    	}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

		if (actionEvent.getActionCommand().equals("Show Dealer Hand")) {
			if (!playerHasExchangedCards) {
				int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you don't want to exchange any cards?",
						"Just checking...",
						JOptionPane.YES_NO_OPTION);
			}
			
			//guiControl.showDealerHand();
		}
	}

	public void setCardDisplay(JLabel cardDisplay, String hand, int cardNumber) {
		//exchangeCard(cardDisplay);
		cardDisplay.setIcon(new ImageIcon("res/graphics/classic-cards/" + GuiToGameLink.getCardToDisplay(hand, cardNumber) + ".png"));
		cardDisplay.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
	}

	public String getCardName(JLabel cardDisplay) {
		return cardDisplay.getIcon().toString().substring(cardDisplay.getIcon().toString().lastIndexOf("/")+1,29);
		//return "" + cardDisplay.getIcon().toString().lastIndexOf("/");
	}

	public int getCardHeight(JLabel cardDisplay) {
		return cardDisplay.getBorder().getBorderInsets(cardDisplay).top;
	}

	public void setCardHeight(JLabel cardDisplay, int newHeight) {
		cardDisplay.getBorder().getBorderInsets(cardDisplay).top = newHeight;
	}

	public boolean getCardRaisedStatus(JLabel cardDisplay) {
		if (cardDisplay.getBorder().getBorderInsets(cardDisplay).top == RAISE_HEIGHT) {
			return false;
		} else {
			return true;
		}
	}
}
