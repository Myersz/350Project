package edu.gvsu.scis.cis350;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Controls the character for the game.
 * @author Kelsey
 *
 */
public final class Character {

	/** X-coordinate for obstacle. */
	private int x;

	/** Y-coordinate for character. */
	private int y;

	/** Height of background area. */
	private int bgHeight;

	/** Image for character. */
	private Image image;

	/** Flag for jumping. */
	private Boolean isJumping;

	/** Flag for falling. */
	private Boolean isFalling;

	/** Height of the character. */
	private int characterHeight;

	
	/** 
	 * Constructor for the character.
	 * @param backgroundHeight height of game background
	 */
	public Character(final int backgroundHeight) {
		bgHeight = backgroundHeight;

		ImageIcon ii = new ImageIcon(
				"Graphics/Characters/yesclearbackground.gif");
		image = ii.getImage();

		characterHeight = image.getHeight(null);

		x = 0;
		y = bgHeight - characterHeight;

		isJumping = false;
		isFalling = false;
	}
	
	
	/**
	 * Make the character jump if it's not already.
	 */
	public void jump() {
		if (!isJumping && !isFalling) {
			isJumping = true;
		}
	}

	
	/**
	 * Move the character's coordinates while it's jumping.
	 */
	public void moveCharacter() {
		// Rising
		if (isJumping) {
			if (y > bgHeight - characterHeight - 230) {
				y = y - 5;
			}
			if (y <= bgHeight - characterHeight - 220) { 
				isFalling = true;
				isJumping = false;
			}

		}
		// Falling
		if (isFalling) {
			if (y < bgHeight) {
				y = y + 5;
			}
			if (y >= bgHeight - characterHeight) {
				isFalling = false;
				isJumping = false;
			}
		}

	}


	/**
	 * Return the x coordinate of the character.
	 * @return the x-coordinate
	 */
	public int getXCoord() {
		return this.x;
	}

	
	/**
	 * Return the y coordinate of the character.
	 * @return the y-coordinate
	 */
	public int getYCoord() {
		return this.y;
	}


	/**
	 * Return the width of the character.
	 * @return width of character
	 */
	public int getWidth() {
		return this.image.getWidth(null);
	}

	
	/**
	 * Return the height of the character.
	 * @return height of character
	 */
	public int getHeight() {
		return this.image.getHeight(null);
	}
	
	
	/**
	 * Return the character's image.
	 * @return the character image
	 */
	public Image getImage() {
		return this.image;
	}
}
