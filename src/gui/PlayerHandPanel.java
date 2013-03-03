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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerHandPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RAISE_HEIGHT = 80;
	private int numberOfCardsSelected = 0;

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
		JLabel testCardDisplay1 = new JLabel(testCard1);
		testCardDisplay1.addMouseListener(this);
		testCardDisplay1.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		//testCardDisplay1.setVisible(false);

		/*		testCardDisplay1.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				System.out.println("Card: " + ", clicked at: " + e.getPoint());
				//testCard1.setIcon("wew");
			}
		});*/

		ImageIcon testCard2 = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		JLabel testCardDisplay2 = new JLabel(testCard2);
		testCardDisplay2.addMouseListener(this);
		testCardDisplay2.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));

		ImageIcon testCard3 = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		JLabel testCardDisplay3 = new JLabel(testCard3);
		testCardDisplay3.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		testCardDisplay3.addMouseListener(this);

		ImageIcon testCard4 = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		JLabel testCardDisplay4 = new JLabel(testCard4);
		testCardDisplay4.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		testCardDisplay4.addMouseListener(this);

		ImageIcon testCard5 = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		JLabel testCardDisplay5 = new JLabel(testCard5);
		testCardDisplay5.setBorder(BorderFactory.createEmptyBorder(RAISE_HEIGHT, 0, 0, 0));
		testCardDisplay5.addMouseListener(this);


		add(testCardDisplay1);
		add(testCardDisplay2);
		add(testCardDisplay3);
		add(testCardDisplay4);
		add(testCardDisplay5);


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
		clickedCard.setIcon(new ImageIcon("res/graphics/classic-cards/" + randomCard() + ".png"));

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
	}
}
