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

		BufferedImage playingCard1 = null;
		BufferedImage playingCard2 = null;
		BufferedImage playingCard3 = null;
		BufferedImage playingCard4 = null;
		BufferedImage playingCard5 = null;

		try {
			playingCard1 = ImageIO.read(new File("res/graphics/classic-cards/1.png"));
			playingCard2 = ImageIO.read(new File("res/graphics/classic-cards/2.png"));
			playingCard3 = ImageIO.read(new File("res/graphics/classic-cards/3.png"));
			playingCard4 = ImageIO.read(new File("res/graphics/classic-cards/4.png"));
			playingCard5 = ImageIO.read(new File("res/graphics/classic-cards/5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		g.setColor(new Color(102,51,0));
		g.fillOval(90, 110,780,470);

		g.setColor(new Color(0,100,0));
		g.fillOval(100,120,760,450);

		g.setColor(Color.RED);
		g.setFont(new Font("serif",Font.ITALIC,50));
		g.drawString("T&J",13,60);
		g.drawString("Poker",0,110);


		g.drawImage(playingCard1, 250, 400, null);
		g.drawImage(playingCard2, 350, 400, null);
		g.drawImage(playingCard3, 450, 400, null);
		g.drawImage(playingCard4, 550, 400, null);
		g.drawImage(playingCard5, 650, 400, null);

	}


}
