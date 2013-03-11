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
import javax.swing.JPanel;

public class PlayerHandPanel extends JPanel implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RAISE_HEIGHT = 80;
	private int numberOfCardsSelected = 0;
	
	JButton btnDeal = new JButton("Deal");
	JButton btnCardExchange = new JButton("Exchange Cards");
	JButton btnShowDealerHand= new JButton("Exchange Cards");

	private JLabel cardDisplay1;
	private JLabel cardDisplay2;
	private JLabel cardDisplay3;
	private JLabel cardDisplay4;
	private JLabel cardDisplay5;

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

		ImageIcon testCard1 = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		cardDisplay1 = new JLabel(testCard1);
		cardDisplay1.addMouseListener(this);
		cardDisplay1.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		//cardDisplay1.setVisible(false);

		/*		cardDisplay1.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				System.out.println("Card: " + ", clicked at: " + e.getPoint());
				//testCard1.setIcon("wew");
			}
		});*/

		ImageIcon testCard2 = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		cardDisplay2 = new JLabel(testCard2);
		cardDisplay2.addMouseListener(this);
		cardDisplay2.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		ImageIcon testCard3 = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		cardDisplay3 = new JLabel(testCard3);
		cardDisplay3.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay3.addMouseListener(this);

		ImageIcon testCard4 = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		cardDisplay4 = new JLabel(testCard4);
		cardDisplay4.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		cardDisplay4.addMouseListener(this);

		ImageIcon testCard5 = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		cardDisplay5 = new JLabel(testCard5);
		cardDisplay5.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
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
		add(btnDeal);

		btnCardExchange.addActionListener(this);
		//btnCardExchange.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		btnCardExchange.setEnabled(false);
		add(btnCardExchange);
		
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

	public void exchangeCard(JLabel clickedCard) {
		clickedCard.setIcon(new ImageIcon("res/graphics/classic-cards/" + randomCard() + ".png"));
	}
	
	public int randomCard() {
		Random generator = new Random();
		int cardSelector = generator.nextInt(51) + 1;
		return cardSelector;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*		g.drawImage(playingCard1, 0, 0, null);
		g.drawImage(playingCard2, 100, 0, null);
		g.drawImage(playingCard3, 200, 0, null);
		g.drawImage(playingCard4, 300, 0, null);
		g.drawImage(playingCard5, 400, 0, null);*/
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
			
			if (numberOfCardsSelected>0) {
				btnCardExchange.setEnabled(true);
			} else {
				btnCardExchange.setEnabled(false);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if (actionEvent.getActionCommand().equals("Deal")) {
			btnDeal.setEnabled(false);
			//btnCardExchange.setEnabled(true);

			setCardDisplay(cardDisplay1);
			setCardDisplay(cardDisplay2);
			setCardDisplay(cardDisplay3);
			setCardDisplay(cardDisplay4);
			setCardDisplay(cardDisplay5);
		}
		
		if (actionEvent.getActionCommand().equals("Exchange Cards")) {
			btnCardExchange.setEnabled(false);
			btnDeal.setEnabled(true);

			if (getCardRaisedStatus(cardDisplay1)) {
				setCardDisplay(cardDisplay1);
			}

			if (getCardRaisedStatus(cardDisplay2)) {
				setCardDisplay(cardDisplay2);
			}

			if (getCardRaisedStatus(cardDisplay3)) {
				setCardDisplay(cardDisplay3);
			}

			if (getCardRaisedStatus(cardDisplay4)) {
				setCardDisplay(cardDisplay4);
			}

			if (getCardRaisedStatus(cardDisplay5)) {
				setCardDisplay(cardDisplay5);
			}
			
			numberOfCardsSelected = 0;
			
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
	}

	public void setCardDisplay(JLabel cardDisplay) {
		exchangeCard(cardDisplay);
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
