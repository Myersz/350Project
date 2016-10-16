package edu.gvsu.scis.cis350;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Methods to control an obstacle in the game.
 * @author Kelsey
 *
 */
public class Obstacle {

	/** x-coordinate for left edge of obstacle. */
	private int leftX;
	
	/** x-coordinate for right edge of obstacle. */
	private int rightX;
	
	/** y-coordinate for height of obstacle. */
	private int y;
	
	/** Speed obstacle will move at. */
	private int speed;
	
	/** Image for obstacle. */
	private BufferedImage image;


	/**
	 * Create a new obstacle width the specificed width and height.
	 * @param bgDimension The dimensions of the background image
	 * @param pSpeed The speed the obstacle will move at
	 */
	public Obstacle(final Dimension bgDimension, final int pSpeed) {
		// Load the image for the obstacle.
		try {
			image = ImageIO.read(new File(
					"Graphics/Obstacles/obstacle.png"));
		} catch (Exception e) {
			System.out.println("Failed to load obstacle image");
			return;
		}
				
		// Assign the dimensions of the obstacle based on image.
		leftX = bgDimension.width;
		rightX = leftX + image.getWidth();
		y = bgDimension.height - image.getHeight();
		//y = image.getHeight();
		speed = pSpeed;
			
	}

	/**
	 * Move the obstacle towards the left of the screen by the increment.
	 * @param increment the desired amount to move by
	 */
	public final void move(final int increment) {
		if (this.isOnScreen()) {
			leftX = leftX - increment;
			rightX = rightX - increment;
		}	
	}

	
	/** 
	 * Determines if the object is on the screen.
	 * @return true if the object is at least partially on the screen
	 */
	public final boolean isOnScreen() {
		return (rightX >= 0);
	}
	
	
	/**
	 * Draws the obstacle on the screen and moves its position.
	 * @param window The window to draw the obstacle on.
	 */
	public final void draw(final Graphics window) {
		// Clear the previous drawing of the obstacle
		window.clearRect(this.getLeftX() + 1, this.getY(), 
				image.getWidth(), image.getHeight());
		
		// Draw the new obstacle
		window.drawImage(image, this.getLeftX(), this.getY(), image.getWidth(), 
        		image.getHeight(), null);
				
		// Move the obstacle into the next position
		this.move(speed);
	}
	
	/**
	 * Returns left x coordinate of obstacle.
	 * @return integer left x-coordinate
	 */
	public final int getLeftX() {
		return this.leftX;
	}
	
	/**
	 * Returns right x coordinate of obstacle.
	 * @return integer right x-coordinate
	 */
	public final int getRightX() {
		return this.rightX;
	}
	
	
	/**
	 * Returns y coordinate of obstacle.
	 * @return integer y-coordinate
	 */
	public final int getY() {
		return this.y;
	}
	
	/**
	 * Returns width of obstacle image.
	 * @return integer width
	 */
	public final int getWidth() {
		return image.getWidth();
	}
	
	/**
	 * Returns height of obstacle image.
	 * @return integer height
	 */
	public final int getHeight() {
		return image.getHeight();
	}
}
