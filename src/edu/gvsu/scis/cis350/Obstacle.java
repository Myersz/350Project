package edu.gvsu.scis.cis350;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Methods to control an obstacle in the game.
 * @author Kelsu
 *
 */
public class Obstacle extends JLabel {
	private static final long serialVersionUID = 0;

	/** x-coordinate for left edge of obstacle. */
	private int leftX;
	
	/** x-coordinate for right edge of obstacle. */
	private int rightX;
	
	/** y-coordinate for height of obstacle. */
	private int y;
	
	/** Image for obstacle. */
	private ImageIcon image;


	/**
	 * Create a new obstacle with the specified width and height.
	 * @param bg The dimensions of the background image
	 */
	public Obstacle(final Dimension bg) {

		try {
			image = new ImageIcon("Graphics/Obstacles/obstacle.png");
		} catch (Exception e) {
			System.out.println("Failed to load obstacle image");
			return;
		}

		this.setIcon(image);
		
		leftX = bg.width;
		rightX = bg.width + image.getIconWidth();
		y = bg.height - image.getIconHeight();
		
		this.setLocation(leftX, y);	
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
		
		this.setLocation(leftX, y);
		System.out.println(this.getLocation());
	}

	
	/** 
	 * Determines if the object is on the screen.
	 * @return true if the object is at least partially on the screen
	 */
	public final boolean isOnScreen() {
		return (rightX >= 0);
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
}
