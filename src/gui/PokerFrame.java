package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PokerFrame extends JFrame{
	
	final int FRAME_WIDTH = 1280;
	final int FRAME_HEIGHT = 800;
	
	private JPanel panel;
	
	public PokerFrame(){
		
		drawTable();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
	}
	
		
	private void drawTable() {
		
		panel = new BackgroundPanel();
		
		add(panel);
		
	}

}
