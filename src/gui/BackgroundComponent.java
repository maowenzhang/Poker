package gui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class BackgroundComponent extends JComponent {

	@Override
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
