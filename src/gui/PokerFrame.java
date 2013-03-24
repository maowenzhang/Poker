package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import pokerLauncher.GUIController;

@SuppressWarnings("serial")
public class PokerFrame extends JFrame implements ActionListener{

	final int FRAME_WIDTH = 1280;
	final int FRAME_HEIGHT = 800;
	JLayeredPane basePane = new JLayeredPane();
	BackgroundPanel backgroundPanel = new BackgroundPanel();
	PlayerHandPanel playerHandPanel = new PlayerHandPanel();
	DealerHandPanel dealerHandPanel = new DealerHandPanel();
	ControlPanel controlPanel = new ControlPanel();
	GUIController guiController = new GUIController();
	GUIController guiController2 = new GUIController();

	public PokerFrame(){		
		drawGameLayout();
	}


	private void drawGameLayout() {

		setLayout(new BorderLayout());
		add(basePane, BorderLayout.CENTER);
		basePane.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

		backgroundPanel.setBackground(null);
		backgroundPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		//backgroundPanel.setOpaque(true);

		//playerHandPanel.setBackground(Color.blue); // comment out line for proper display
		playerHandPanel.setBounds(400, 340, 500, 500);
		playerHandPanel.setOpaque(false); // change to "false" for proper display
		//playerHandPanel.setCardDisplay1(cardDisplay);

		dealerHandPanel.setBounds(400, 190, 500, 200);
		dealerHandPanel.setOpaque(false); // change to "false" for proper display

		//controlPanel.setBackground(Color.green); // comment out line for proper display
		controlPanel.setBounds(300, 650, 700, 30);
		controlPanel.setOpaque(false); // change to "false" for proper display

		basePane.add(backgroundPanel, new Integer(0), 0);
		basePane.add(playerHandPanel, new Integer(1), 0);
		basePane.add(dealerHandPanel, new Integer(2), 0);
		basePane.add(controlPanel, new Integer(3), 0);

		// THIS IS THE IMPORTANT LINE!!
		controlPanel.setControl(guiController);
		playerHandPanel.setControl(guiController2);
		
		guiController.setControlPanel(controlPanel);
		guiController.setPlayerHandPanel(playerHandPanel);
		guiController.setDealerHandPanel(dealerHandPanel);

		pack();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		this.setResizable(false);
		//Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(screenDimension.width/2-this.getSize().width/2, screenDimension.height/2-this.getSize().height/2);
		setVisible(true);



		//setLayout(new FlowLayout());

		//backgroundPanel = new BackgroundPanel();
		//playerHandPanel = new PlayerHandPanel();
		//(backgroundPanel);
		//add(playerHandPanel);

		//PlayerHandPanel = new PlayerHandPanel();
		//add(PlayerHandPanel);

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
