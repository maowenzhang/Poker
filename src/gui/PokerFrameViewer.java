package gui;

import javax.swing.JFrame;

public class PokerFrameViewer {

	public static void main(String[] args) {
		
		JFrame frame = new PokerFrame();
		
		frame.setTitle("Poker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}

}
