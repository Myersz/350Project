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
public final class ScrollingBackground extends JPanel implements Runnable {

	private static final long serialVersionUID = 0;

	/** First copy of the background image. */
	private GameBackground backOne;
	/** Second copy of the background image. */
	private GameBackground backTwo;

	/** Holds the buffered background image. */
	private BufferedImage back;
	
	/** Sleep time of thread. */
	private static final int SLEEP_TIME = 10;


	/**
	 * Constructor for ScrollingBackground Class.
	 * Loads two background images and starts a thread to move the background
	 * @throws MissingBackgroundException throws MissingBackgroundException 
	 * if game failed to load background image
	 */
	public ScrollingBackground() throws MissingBackgroundException {
		//Instantiate both background items
		backOne = new GameBackground();
		backTwo = new GameBackground(backOne.getImageWidth(), 0);

		//Start imaged not scrolling
		/** Detect enter key pushed to change this */
		//GameControl.scrolling = false;

		new Thread(this).start();
		setVisible(true);
	}

	/**
	 * Makes the background move.
	 */
	@Override
	public void run() {
		try {
			while (GameControl.getScrolling()) {
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
		System.out.println("I'm paused.");
		GameControl.setScrolling(false);
	}

	/**
	 * Resume the thread and tell the background to start scrolling again.
	 */
	public void resumeScrolling() {
		System.out.println("I've resumed.");
		GameControl.setScrolling(true);	
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