package gui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;

public class TableComponent extends JComponent {
	
	@Override
	public void paintComponent(Graphics g) {
		
		g.setColor(new Color(0,100,0));
		g.fillOval(120,60,750,450);
		
		g.setColor(Color.RED);
		g.setFont(new Font("serif",Font.ITALIC,16));
		g.drawString("TJ Poker",60,30);
				
	}
	
	

}
