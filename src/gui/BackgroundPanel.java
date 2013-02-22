package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	
	private TableComponent table;
	private BufferedImage carpet;
	
	private final int PANEL_WIDTH = 1000;
	private final int PANEL_HEIGHT = 600;
	
	public BackgroundPanel(){
		
		try{
			carpet = ImageIO.read(new File("floor.jpg"));
		}
		
		catch(IOException exception){
			
			exception.printStackTrace();
			
		}
		
		finally{
		
			table = new TableComponent();
			
			table.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
			
			add(table);
		
		}
	
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(carpet, 0, 0, null);
    }

}
