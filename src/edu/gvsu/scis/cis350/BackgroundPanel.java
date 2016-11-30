package edu.gvsu.scis.cis350;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
		//Instantiate both background items
		backOne = new Background();
		backTwo = new Background(backOne.getImageWidth(), 0);

		scrolling = false;

		this.setOpaque(false);
		new Thread(this).start();
		setVisible(true);
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