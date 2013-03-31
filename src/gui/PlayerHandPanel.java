package gui;


import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PlayerHandPanel extends JPanel implements MouseListener, ActionListener {


	//create observer so that when player hand panel raises cards
	//the control panel knows to grey out the exchange button

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

	/**
	 * constructs the player hand panel with all its buttons and action listener and mouse listener
	 */
	public PlayerHandPanel() {

		btnDeal = new JButton("Deal");
		btnCardExchange = new JButton("Exchange Cards");
		btnShowDealerHand = new JButton("Show Dealer Hand");

		FlowLayout playingCardLayout = new FlowLayout(FlowLayout.CENTER,20,0);
		setLayout(playingCardLayout);

		cardDisplay1 = new JLabel();
		cardDisplay2 = new JLabel();
		cardDisplay3 = new JLabel();
		cardDisplay4 = new JLabel();
		cardDisplay5 = new JLabel();

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

	}

	/**
	 * setter method - sets all the card displays to the image of the back of a card
	 */
	public void setPlayerCardsToBack() {
		ImageIcon cardBack = new ImageIcon("res/graphics/classic-cards/b2fv.png");

		cardDisplay1.setIcon(cardBack);
		cardDisplay1.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay2.setIcon(cardBack);
		cardDisplay2.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay3.setIcon(cardBack);
		cardDisplay3.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay4.setIcon(cardBack);
		cardDisplay4.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		cardDisplay5.setIcon(cardBack);
		cardDisplay5.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		repaint();
	}

	public void refresh(){
		numberOfCardsSelected = 0;
		playerHasExchangedCards = false;
		setPlayerCardsToBack();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	/**
	 * Mouse release raises cards for exchange - only three cards can be raised
	 */

	//CAN WE MAKE THIS A LITTLE CLEARER?
	public void mouseReleased(MouseEvent event) {
		//System.out.println(event.getSource());
		Boolean actionCarriedOut = false;

		JLabel clickedCard = (JLabel)event.getSource();
		///setCardDisplay(clickedCard);

		//if the image displayed is not the back of a card
		if (!getCardName(clickedCard).contains("b")) {
			if (guiController.getIsPlayerTurn()) {
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
			}
		}
	}


	/**
	 * getter method - determines whether the given card (1 - 5) has been raised to exchange
	 * @param the number of the card requested
	 * @return true if card has been raised to exchange
	 */
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


	/**
	 * getter method - ...
	 * @param cardDisplay
	 * @return
	 */

	public String getCardName(JLabel cardDisplay) {
		return cardDisplay.getIcon().toString().substring(cardDisplay.getIcon().toString().lastIndexOf("/")+1,29);
		//return "" + cardDisplay.getIcon().toString().lastIndexOf("/");
	}

	/**
	 * setter method - enables panel to see the GUI controller
	 * @param the guiControl reference
	 */
	public void setControl(GUIController guiController) {
		this.guiController = guiController;		
	}

	/**
	 * setter method - sets the card display to the image of a card
	 * @param location of the image of the card
	 * @param the card display to update
	 */
	public void setCardDisplay(String iconName, int cardDisplay) {

		switch (cardDisplay){
		case 1: cardDisplay1.setIcon(new ImageIcon(iconName));
		case 2: cardDisplay2.setIcon(new ImageIcon(iconName));
		case 3: cardDisplay3.setIcon(new ImageIcon(iconName));
		case 4: cardDisplay4.setIcon(new ImageIcon(iconName));
		case 5: cardDisplay5.setIcon(new ImageIcon(iconName));

		}

	}

	/**
	 *  TOM ANNOTATE
	 */
	public void setLabelBorders() {
		cardDisplay1.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay2.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay3.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay4.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay5.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
