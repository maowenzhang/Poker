package gui;

import javax.swing.JFrame;

public class PokerTableViewer {

	public static void main(String[] args) {
		
		JFrame frame = new PokerTable();
		
		frame.setTitle("Poker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}

}
