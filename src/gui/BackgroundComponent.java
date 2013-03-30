package gui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;

/**
 * the background component class draws the poker table and the logo onto the background panel class
 * @author Tom & Jonathan
 *
 */
@SuppressWarnings("serial")
public class BackgroundComponent extends JComponent {

	@Override
	/**
	 * Draws poker table and "T&J" logo
	 */
	public void paintComponent(Graphics g) {

		g.setColor(new Color(102,51,0));
		g.fillOval(90, 110,780,470);

		g.setColor(new Color(0,100,0));
		g.fillOval(100,120,760,450);

		g.setColor(Color.RED);
		g.setFont(new Font("serif",Font.ITALIC,50));
		g.drawString("T&J",13,60);
		g.drawString("Poker",0,110);

	}

}
