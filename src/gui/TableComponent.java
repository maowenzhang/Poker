package gui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;

public class TableComponent extends JComponent {
	
	@Override
	public void paintComponent(Graphics g) {
		
		g.setColor(new Color(102,51,0));
		g.fillOval(95, 115,770,460);
		
		g.setColor(new Color(0,100,0));
		g.fillOval(100,120,760,450);
		
		g.setColor(Color.RED);
		g.setFont(new Font("serif",Font.ITALIC,50));
		g.drawString("T&J",13,60);
		g.drawString("Poker",0,110);
				
	}
	
	

}
