package pokerLauncher;

import gui.PokerFrame;

import javax.swing.JFrame;

public class Poker {
	
	public static void main(String[] args) {

		PokerFrame frame = new PokerFrame();
		
		frame.setTitle("Poker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	
	}
	
}
