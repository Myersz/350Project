package edu.gvsu.scis.cis350;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Model class for obstacles.
 * @author Kelsey
 *
 */
public class Obstacle {

	/** The resized obstacle image. */
	private Image obstacle;

	/** X-coordinate of the obstacle. */
	private int x;

	/** Y-coordinate of the obstacle. */
	private int y;

	/** Width of the obstacle. */
	private int obstacleWidth;

	/** Height of the obstacle. */
	private int obstacleHeight;

	/** Width of the background area. */
	private int bgWidth;

	/** Height of the background area. */
	private int bgHeight;

	/** Amount to scale obstacle image by. */
	static final int SCALE = 1;

	/** Distance to move obstacle by. */
	static final int DISTANCE_TO_MOVE = 3;

	/**
	 * Constructor for a new obstacle.
	 * @param backgroundWidth the width of the background area
	 * @param backgroundHeight the height of the background area
	 */
	public Obstacle(final int backgroundWidth, final int backgroundHeight) {
		// Load a random image from the obstacle graphics
		int num = ((int) Math.ceil(Math.random() * 10)) % 2;
		
		try {
			obstacle = ImageIO.read(new File(
					"Graphics/Obstacles/obstacle" + num + ".png"));
		} catch (IOException e) {
			System.out.println("Error loading obstacle image");
		}

		// Set sizes of background area
		bgWidth = backgroundWidth;
		bgHeight = backgroundHeight;

		// Set sizes of obstacle 
		obstacleWidth = obstacle.getWidth(null);
		obstacleHeight = obstacle.getHeight(null);

		// Set initial x and y coordinates
		x = bgWidth;
		y = bgHeight - obstacleHeight;

		//System.out.println("X: " + x + " Y: " + y);
	}


	/** 
	 * Update the obstacle's position.
	 * @return whether or not the obstacle is still on the screen
	 */
	public final boolean moveObstacle() {
		if (x + obstacleWidth >= 0 && x <= bgWidth) {
			x = x - DISTANCE_TO_MOVE;
			return true;
		}
		return false;
	}


	/**
	 * Return the x coordinate of the obstacle.
	 * @return an int representing the x coordinate of the obstacle
	 */
	public final int getXCoord() {
		return this.x;
	}


	/**
	 * Set the x coordinate.
	 * @param newX the new x coordinate
	 */
	public final void setXCoord(final int newX) {
		this.x = newX;
	}


	/**
	 * Return the y coordinate of the obstacle.
	 * @return an int representing the y coordinate of the obstacle
	 */
	public final int getYCoord() {
		return this.y;
	}


	/**
	 * Set the y coordinate.
	 * @param newY the new y coordinate
	 */
	public final void setYCoord(final int newY) {
		this.y = newY;
	}


	/**
	 * Return the width of the obstacle.
	 * @return an int representing the width of the obstacle
	 */
	public final int getObstacleWidth() {
		return this.obstacleWidth;
	}


	/**
	 * Set the width of the obstacle.
	 * @param newWidth the new width of the obstacle
	 */
	public final void setObstacleWidth(final int newWidth) {
		if (newWidth > 0 && newWidth <= bgWidth) {
			this.obstacleWidth = newWidth;
		}
	}

	/**
	 * Return the height of the obstacle.
	 * @return an int representing the height of the obstacle
	 */
	public final int getObstacleHeight() {
		return this.obstacleHeight;
	}


	/**
	 * Set the height of the obstacle.
	 * @param newHeight the new height of the obstacle
	 */
	public final void setObstacleHeight(final int newHeight) {
		if (newHeight > 0 && newHeight <= bgHeight) {
			this.obstacleHeight = newHeight;
		}
	}


	/**
	 * Return the obstacle's image.
	 * @return the Image object for the obstacle
	 */
	public final Image getImage() {
		return this.obstacle;
	}


	/**
	 * Set the image for the obstacle.
	 * @param newImage the new image for the obstacle
	 */
	public final void setImage(final Image newImage) {
		this.obstacle = newImage;
	}
}

