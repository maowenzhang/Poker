package gui;


import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DealerHandPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int RAISE_HEIGHT = 80;

	
	JLabel CardDisplay1;
	JLabel CardDisplay2;
	JLabel CardDisplay3;
	JLabel CardDisplay4;
	JLabel CardDisplay5;
	JLabel test;
	private GUIController guiController;

	/**
	 * constructs dealer hand panel of 5 cards with their backs' visible
	 */
	public DealerHandPanel() {

		FlowLayout playingCardLayout = new FlowLayout(FlowLayout.CENTER,20,0);
		setLayout(playingCardLayout);

		CardDisplay1 = new JLabel();
		CardDisplay2 = new JLabel();
		CardDisplay3 = new JLabel();
		CardDisplay4 = new JLabel();
		CardDisplay5 = new JLabel();
		
		test = new JLabel();
		
		resetDealerHandDisplay();

		add(CardDisplay1);
		add(CardDisplay2);
		add(CardDisplay3);
		add(CardDisplay4);
		add(CardDisplay5);

	}

	public void setDealerCardsToOriginalPosition(){
		CardDisplay1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		CardDisplay2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		CardDisplay3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		CardDisplay4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		CardDisplay5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		repaint();
	}
	
	public void resetDealerHandDisplay(){
		ImageIcon cardBack = new ImageIcon("res/graphics/classic-cards/b2fv.png");
		
		CardDisplay1.setIcon(cardBack);
		CardDisplay2.setIcon(cardBack);
		CardDisplay3.setIcon(cardBack);
		CardDisplay4.setIcon(cardBack);
		CardDisplay5.setIcon(cardBack);
		
		setDealerCardsToOriginalPosition();
		
		repaint();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	

	/**
	 * setter method - enables panel to see the GUI controller
	 * @param the guiController reference
	 */
	public void setControl(GUIController guiController) {
		this.guiController = guiController;
		
	}

	/**
	 * setter method - sets the card display
	 * @param the image of the card to display
	 * @param the card display to change (1 - 5)
	 */
	public void setCardDisplay(String iconName, int cardDisplay) {
		
			switch (cardDisplay){
			case 1: CardDisplay1.setIcon(new ImageIcon(iconName));
			case 2: CardDisplay2.setIcon(new ImageIcon(iconName));
			case 3: CardDisplay3.setIcon(new ImageIcon(iconName));
			case 4: CardDisplay4.setIcon(new ImageIcon(iconName));
			case 5: CardDisplay5.setIcon(new ImageIcon(iconName));
	
		}
	}
}
