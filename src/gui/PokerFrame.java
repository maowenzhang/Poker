package gui;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PokerFrame extends JFrame{
	
	final int FRAME_WIDTH = 1280;
	final int FRAME_HEIGHT = 800;

	private JPanel mainPanel;
	private JPanel playerHandPanel;
	
	public PokerFrame(){
		
		drawTable();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		
	}
	
		
	private void drawTable() {
		
		mainPanel = new BackgroundPanel();
		add(mainPanel);
		
		//playerHandPanel = new playerHandPanel();
		//add(playerHandPanel);
		
	}

}
