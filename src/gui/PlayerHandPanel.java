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
import javax.swing.border.Border;

import pokerLauncher.Round;


public class PlayerHandPanel extends JPanel implements MouseListener, ActionListener {
	
	
	//create observer so that when player hand panel raises cards
	//the control panel knows to grey out the exchange button

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int RAISE_HEIGHT = 80;

	private JButton btnDeal;
	private JButton btnCardExchange;
	private JButton btnShowDealerHand;

	private JLabel cardDisplay1;
	private JLabel cardDisplay2;
	private JLabel cardDisplay3;
	private JLabel cardDisplay4;
	private JLabel cardDisplay5;
	
	private int numberOfCardsSelected = 0;
	private boolean playerHasExchangedCards = false;

	private GUIController guiController;
	
	public PlayerHandPanel() {
		
		btnDeal = new JButton("Deal");
		btnCardExchange = new JButton("Exchange Cards");
		btnShowDealerHand = new JButton("Show Dealer Hand");

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

	public void setPlayerCardsToBack() {
		ImageIcon testCard = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		
		cardDisplay1 = (new JLabel(testCard));
		cardDisplay1.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		
		cardDisplay2 = new JLabel(testCard);
		cardDisplay2.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay3 = new JLabel(testCard);
		cardDisplay3.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay4 = new JLabel(testCard);
		cardDisplay4.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay5 = new JLabel(testCard);
		cardDisplay5.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		
		repaint();
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

	public void mouseReleased(MouseEvent event) {
		//System.out.println(event.getSource());
		Boolean actionCarriedOut = false;

		JLabel clickedCard = (JLabel)event.getSource();
		///setCardDisplay(clickedCard);
		
		//if the image displayed is not the back of a card
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

			guiController.setPlayerCardsforExchange(numberOfCardsSelected);
			
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
				guiController.getNewCard(1);
			}

			if (getCardRaisedStatus(cardDisplay2)) {
				guiController.getNewCard(2);
			}
			
			if (getCardRaisedStatus(cardDisplay3)) {
				guiController.getNewCard(3);
			}
			
			if (getCardRaisedStatus(cardDisplay4)) {
				guiController.getNewCard(4);
			}
			
			if (getCardRaisedStatus(cardDisplay5)) {
				guiController.getNewCard(5);
			}

			guiController.setPlayerCardDisplay();
			
			numberOfCardsSelected = 0;
			playerHasExchangedCards = true;

			guiController.printHand();

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

	public Boolean getCardRaisedStatus(int cardDisplay) {
		
		switch (cardDisplay){
			case 1: return cardDisplay1.getBorder().getBorderInsets(cardDisplay1).top != RAISE_HEIGHT;
			case 2: return cardDisplay2.getBorder().getBorderInsets(cardDisplay2).top != RAISE_HEIGHT;
			case 3: return cardDisplay3.getBorder().getBorderInsets(cardDisplay3).top != RAISE_HEIGHT;
			case 4: return cardDisplay4.getBorder().getBorderInsets(cardDisplay4).top != RAISE_HEIGHT;
			case 5: return cardDisplay5.getBorder().getBorderInsets(cardDisplay5).top != RAISE_HEIGHT;
			default: return null;
			
		}
			
	}
	/* CHANGED
	public void setCardDisplay(JLabel cardDisplay, String hand, int cardNumber) {
		//exchangeCard(cardDisplay);
		cardDisplay.setIcon(new ImageIcon("res/graphics/classic-cards/" + round.getCardToDisplay(hand, cardNumber) + ".png"));
		cardDisplay.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
	}
	*/

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

	public void setControl(GUIController guiController) {
		this.guiController = guiController;		
	}

	public void setCardDisplay(String iconName, int cardDisplay) {
		
		switch (cardDisplay){
		case 1: cardDisplay1.setIcon(new ImageIcon(iconName));
		case 2: cardDisplay2.setIcon(new ImageIcon(iconName));
		case 3: cardDisplay3.setIcon(new ImageIcon(iconName));
		case 4: cardDisplay4.setIcon(new ImageIcon(iconName));
		case 5: cardDisplay5.setIcon(new ImageIcon(iconName));
		
		}
		
	}
	
	public void setLabelBorders() {
		cardDisplay1.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay2.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay3.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay4.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay5.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
