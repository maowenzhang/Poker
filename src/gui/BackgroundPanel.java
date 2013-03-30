package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The background panel class contains the background images - wallpaper, poker table and logo - of the GUI
 * @author Tom & Jonathan
 *
 */
@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel {
	
	private BackgroundComponent table;
	private BufferedImage floor;
	
	private final int PANEL_WIDTH = 950;
	private final int PANEL_HEIGHT = 600;
	
	/** 
	 * constructs background panel with image of floor
	 */
	public BackgroundPanel(){
		
		try{
			floor = ImageIO.read(new File("res/graphics/floor.jpg"));
		}
		
		catch(IOException exception){
			
			exception.printStackTrace();
			
		}
		
		finally{

			table = new BackgroundComponent();
			
			table.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));

		}
	

        add(table);
	}
	
	@Override
	/**
	 * paints image
	 */
    public void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        
        g.drawImage(floor, 0, 0, null);
        
    }

}
