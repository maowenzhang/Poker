package gui;

import game.CardController;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import pokerLauncher.GameController;



public class PokerFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int FRAME_WIDTH = 1280;
	final int FRAME_HEIGHT = 800;

	JLayeredPane basePane;
	BackgroundPanel backgroundPanel;
	PlayerHandPanel playerHandPanel;
	DealerHandPanel dealerHandPanel;
	ControlPanel controlPanel;

	GUIController guiController;
	CardController cardController;
	GameController gameController;

	/**
	 * constructs a poker frame with all the various panels on it and establishes visibility between the panels
	 */
	public PokerFrame(){

		basePane = new JLayeredPane();
		backgroundPanel = new BackgroundPanel();
		playerHandPanel = new PlayerHandPanel();
		dealerHandPanel = new DealerHandPanel();
		controlPanel = new ControlPanel();

		drawGameLayout();

		establishController();

		try {
			infoBox();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void infoBox() throws IOException
	{
		String configSetting = "AI=DealerAISmart";

		Object response = JOptionPane.showInputDialog(this,
				"Which computer AI would you like to play against?",
				"Select an intelligence:", JOptionPane.QUESTION_MESSAGE,
				null, new String[] { "Smart Computer", "Random Computer"},"Smart Computer");

		if (response != null){	
			if(response.toString().equalsIgnoreCase("Random Computer")){
				configSetting = "AI=DealerAIRandom";	
			}

		}
		PrintStream configWriter = new PrintStream("properties/dealerAI.properties");
		configWriter.print(configSetting);

	}

	/**
	 * arranges the frame layout and the dimensions of the panels
	 */
	private void drawGameLayout() {

		setLayout(new BorderLayout());
		add(basePane, BorderLayout.CENTER);
		basePane.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

		backgroundPanel.setBackground(null);
		backgroundPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

		playerHandPanel.setBounds(400, 340, 500, 500);
		playerHandPanel.setOpaque(false);

		dealerHandPanel.setBounds(400, 190, 500, 200);
		dealerHandPanel.setOpaque(false);

		controlPanel.setBounds(300, 650, 700, 30);
		controlPanel.setOpaque(false);

		basePane.add(backgroundPanel, new Integer(0), 0);
		basePane.add(playerHandPanel, new Integer(1), 0);
		basePane.add(dealerHandPanel, new Integer(2), 0);
		basePane.add(controlPanel, new Integer(3), 0);

		pack();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}

	/**
	 * establishes visibility between the various panels and the GUI controller
	 */
	public void establishController(){

		guiController = new GUIController();

		controlPanel.setControl(guiController);
		dealerHandPanel.setControl(guiController);
		playerHandPanel.setControl(guiController);

		guiController.setControlPanel(controlPanel);
		guiController.setPlayerHandPanel(playerHandPanel);
		guiController.setDealerHandPanel(dealerHandPanel);

		cardController = new CardController();
		gameController = new GameController();

		gameController.addObserver(guiController);
		gameController.addObserver(cardController);

		controlPanel.setControl(gameController);
		cardController.setControl(gameController);

		cardController.setControl(guiController);

		gameController.newRound();

	}

}
