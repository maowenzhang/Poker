package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PokerTable extends JFrame{
	
	final int FRAME_WIDTH = 1280;
	final int FRAME_HEIGHT = 800;
	
	private JPanel panel;
	
	public PokerTable(){
		
		drawTable();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
	}
	
		
	private void drawTable() {
		
		panel = new BackgroundPanel();
		
		add(panel);
		
	}

}
