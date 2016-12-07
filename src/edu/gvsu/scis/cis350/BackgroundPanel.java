package edu.gvsu.scis.cis350;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Manages background of game.
 * Controls the start and stop of the scrolling
 * 
 * @author Ella
 */
public final class BackgroundPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 0;

	/** First copy of the background image. */
	private Background backOne;
	/** Second copy of the background image. */
	private Background backTwo;

	/** Holds the buffered background image. */
	private BufferedImage back;
	
	/** Sleep time of thread. */
	private static final int SLEEP_TIME = 10;
	
	/** Whether or not the backround is scrolling. */
	private boolean scrolling;


	/**
	 * Constructor for ScrollingBackground Class.
	 * Loads two background images and starts a thread to move the background
	 * @throws MissingBackgroundException throws MissingBackgroundException 
	 * if game failed to load background image
	 */
	public BackgroundPanel() {
		//Retrieve background image from file
		String backgroundSelection = getBackgroundImage();

		String filename = "citybackground.png"; 

		if(backgroundSelection == "Purple Forest"){
			filename = "buymemorejewelry.jpg";
		}else if(backgroundSelection == "Boring City"){
			filename = "citybackground.png";
		}else if(backgroundSelection == "Cave of Death"){
			filename = "creepycave.png";
		}else if(backgroundSelection == "Desert of Sorrow"){
			filename = "desert.png";
		}else if(backgroundSelection == "Futuristic"){
			filename = "duregmureg.png";
		}else if(backgroundSelection == "Fricken Mushrooms"){
			filename = "frickenmushrooms.png";
		}
		
		//Instantiate both background items
		backOne = new Background(filename);
		backTwo = new Background(backOne.getImageWidth(), 0, filename);

		scrolling = false;

		this.setOpaque(false);
		new Thread(this).start();
		setVisible(true);
	}

	/**
	 * Method to prompt the user to chose a background for the game.
	 * @return scene string name of background chosen
	 */
	public String getBackgroundImage(){
		Object[] possibilities = {"Purple Forest", "Boring City", "Cave of "
				+ "Death", "Desert of Sorrow", "Futuristic", "Fricken "
						+ "Mushrooms"};
		String scene = (String)JOptionPane.showInputDialog(null,"Select a scene"
				+ " pls", "Customized Dialog",
				JOptionPane.PLAIN_MESSAGE, null, possibilities, "Boring City");
		return scene;
	}

	/**
	 * Makes the background move.
	 */
	@Override
	public void run() {
		try {
			while (scrolling) {
				Thread.sleep(SLEEP_TIME);
				repaint();
			}
		} catch (Exception e) { }
	}

	/**
	 * Puts the graphics on the window!!
	 * @param window The graphics window to be painted.
	 */
	public void paintComponent(final Graphics window) {
		super.paintComponent(window);
		Graphics2D twoD = (Graphics2D) window;

		if (back == null) {
			back = (BufferedImage) (createImage(getWidth(), getHeight()));
		}

		// Create a buffer to draw to
		Graphics buffer = back.createGraphics();

		// Put the two copies of the background image onto the buffer
		backOne.draw(buffer);
		backTwo.draw(buffer);

		// Draw the image onto the window
		twoD.drawImage(back, null, 0, 0);
	}


	/**
	 * Pause the thread and tell the background to stop scrolling.
	 */
	public void pauseScrolling() {
		this.scrolling = false;
	}

	/**
	 * Resume the thread and tell the background to start scrolling again.
	 */
	public void resumeScrolling() {
		this.scrolling = true;	
		new Thread(this).start();		
	}
	
	/**
	 * Return height of background.
	 * @return integer value of height of background image
	 */
	public int getWidth() {
		return backOne.getImageWidth();
	}
	
	/**
	 * Return width of background.
	 * @return inteer value of width of background image
	 */
	public int getHeight() {
		return backOne.getImageHeight();
	}

}