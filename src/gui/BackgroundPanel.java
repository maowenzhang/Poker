package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel {
	
	private BackgroundComponent table;
	private BufferedImage carpet;
	
	private final int PANEL_WIDTH = 950;
	private final int PANEL_HEIGHT = 600;
	
	public BackgroundPanel(){
		
		try{
			carpet = ImageIO.read(new File("res/graphics/floor.jpg"));
		}
		
		catch(IOException exception){
			
			exception.printStackTrace();
			
		}
		
		finally{

			table = new BackgroundComponent();
			
			table.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
			//this.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
			
			//add(table);
		
		}
	

        add(table);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(carpet, 0, 0, null);
    }

}
