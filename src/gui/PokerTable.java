package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PokerTable extends JFrame{
	
	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 600;
	
	private JPanel panel;
	private TableComponent table;
	
	public PokerTable(){
		
		drawTable();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
	}
	
		
	private void drawTable() {
		
		table = new TableComponent();
		panel = new JPanel();

		table.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
		
		panel.add(table);
		
		panel.setBackground(Color.BLACK);
		
		add(panel);
		
	}

}
