package edu.gvsu.scis.cis350;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/**
 * Manages background of game.
 * Controls the start and stop of the scrolling
 * 
 * @author Ella
 */
public final class ScrollingBackground extends Canvas implements Runnable {

	private static final long serialVersionUID = 0;

	/** First copy of the background image. */
	private GameBackground backOne;
	/** Second copy of the background image. */
	private GameBackground backTwo;

	/** Holds the buffered background image. */
	private BufferedImage back;

	/** Set the background to move or not. */
	private boolean scrolling;
	
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
		scrolling = true;

		new Thread(this).start();
		setVisible(true);
	}

	/**
	 * Makes the background move.
	 */
	@Override
	public void run() {
		System.out.println("Start running");
		scrollBackground();
		System.out.println("Done running");

	}

	/** 
	 * Method to start the background scrolling.
	 */
	public void scrollBackground() {
		System.out.println("Start scrolling");
		try {
			while (this.scrolling) {
				Thread.sleep(SLEEP_TIME);
				repaint();
			}
		} catch (Exception e) { }

		System.out.println("Done scrolling");
	}

	/**
	 * Updates the window.
	 */
	@Override
	public void update(final Graphics window) {
		paint(window);
	}

	/**
	 * Puts the graphics on the window!!
	 * @param window The graphics window to be painted.
	 */
	public void paint(final Graphics window) {
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
	 * Get if the screen is currently scrolling.
	 * @return boolean value of scrolling
	 */
	public boolean getScrolling() {
		return scrolling;
	}

	/**
	 * Make the background scroll.
	 * @param pScrolling The new value for scrolling.
	 */
	public void setScrolling(final Boolean pScrolling) {
		this.scrolling = pScrolling;
	}

	/**
	 * Pause the thread and tell the background to stop scrolling.
	 */
	public void pauseScrolling() {
		System.out.println("I'm paused.");
		this.setScrolling(false);
	}

	/**
	 * Resume the thread and tell the background to start scrolling again.
	 */
	public void resumeScrolling() {
		System.out.println("I've resumed.");
		this.setScrolling(true);	
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

