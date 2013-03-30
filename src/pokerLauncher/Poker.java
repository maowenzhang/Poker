package pokerLauncher;

import gui.PokerFrame;

import javax.swing.JFrame;

/**
 * the main method which launches the game
 * @author Tom and Jonathan
 *
 */
public class Poker {
	
	public static void main(String[] args) {

		PokerFrame frame = new PokerFrame();
		
		frame.setTitle("Poker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	
	}
	
}
