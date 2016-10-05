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
public class ScrollingBackground extends Canvas implements Runnable {
	
	/**
	 * Two copies of the background image
	 */
	private GameBackground backOne;
	private GameBackground backTwo;
	
	/**
	 * Buffered Image Item
	 */
	private BufferedImage back;
	
	/**
	 * set the background to move or not
	 */
	private boolean scrolling;
	
	/**
	 * Constructor for ScrollingBackground Class.
	 * Loads two background images and starts a thread to move the background
	 */
	public ScrollingBackground() {
		//Instantiate both background items
		backOne = new GameBackground();
		backTwo = new GameBackground(backOne.getImageWidth(), 0);
		
		//Start with imaged not scrolling
		scrolling = true;
		
		new Thread(this).start();
		setVisible(true);
	}
	
	/**
	 * Thread to control the movement of the background.
	 */
	@Override
    public void run() {
        try {
            while (this.scrolling) {
                Thread.currentThread().sleep(10);
                repaint();
            }
        }
        catch (Exception e) {}
    }
 
	/**
	 * Update the window.
	 */
    @Override
    public void update(Graphics window) {
        paint(window);
    }
 
    /**
     * Puts the graphics on the window.
     */
    public void paint(Graphics window) {
        Graphics2D twoD = (Graphics2D)window;
    
        if (back == null)
            back = (BufferedImage)(createImage(getWidth(), getHeight()));
 
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
     * Set Scroll action.
     * @param scrolling
     */
    public void setScrolling(Boolean scrolling) {
    	this.scrolling = scrolling;
    }
 
}