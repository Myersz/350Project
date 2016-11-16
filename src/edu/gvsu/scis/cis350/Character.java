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
	
	/** Width of background area. */
	private int bgWidth;

	/** Image for character. */
	private Image image;
	
	/** Flag for jumping. */
	private Boolean isJumping;

	/** Flag for falling. */
	private Boolean isFalling;

	/** Height of the character. */
	private int characterHeight;

	/** Width of character. */
	private int characterWidth;
	
	/** Velocity of the character */
	private int velocity;
	
	/** Acceleration of the character */
	private int acceleration;
	/** 
	 * Constructor for the character.
	 * @param backgroundHeight height of game background
	 * @param backgroundWidth width of game background
	 */
	public Character(final int backgroundWidth, final int backgroundHeight) {
		bgHeight = backgroundHeight;
		bgWidth = backgroundWidth;
		
		ImageIcon ii = new ImageIcon(
				"Graphics/Characters/yesclearbackground.gif");
		image = ii.getImage();

		characterHeight = image.getHeight(null);
		characterWidth = image.getWidth(null);

		x = 0;
		y = bgHeight - characterHeight;
		velocity = 0;
		acceleration = -1;

		isJumping = false;
		isFalling = false;
	}
	
	
	/**
	 * Make the character jump if it's not already.
	 */
	public void jump() {
		if (!isJumping && !isFalling) {
			isJumping = true;
			velocity = 25;  
		}
	}

	
	/**
	 * Move the character's coordinates while it's jumping.
	 */
	public void moveCharacter() {
		// Rising
		if (isJumping) {
			if (y > bgHeight - characterHeight - 230) {
				y = y - velocity;
				velocity = velocity + acceleration;
			}
			if (y <= bgHeight - characterHeight - 220) { 
				isFalling = true;
				isJumping = false;
				velocity = 0;
			}

		}
		// Falling
		if (isFalling) {
			if (y < bgHeight) {
				y = y + velocity;
				velocity = velocity - acceleration;
			}
			if (y >= bgHeight - characterHeight) {
				isFalling = false;
				isJumping = false;
				velocity = 0;
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
	 * Set the x coordinate of the character.
	 * @param newX the new coordinate
	 */
	public void setXCoord(final int newX) {
		this.x = newX;
	}

	
	/**
	 * Return the y coordinate of the character.
	 * @return the y-coordinate
	 */
	public int getYCoord() {
		return this.y;
	}
	
	
	/**
	 * Set the y coordinate of the character.
	 * @param newY the new coordinate
	 */
	public void setYCoord(final int newY) {
		this.y = newY;
	}


	/**
	 * Return the width of the character.
	 * @return width of character
	 */
	public int getWidth() {
		return this.characterWidth;
	}
	
	
	/**
	 * Set the width of the character.
	 * @param newWidth new width for character
	 */
	public void setWidth(final int newWidth) {
		if (newWidth > 0 && newWidth <= bgWidth) {
			this.characterWidth = newWidth;
		}
	}

	
	/**
	 * Return the height of the character.
	 * @return height of character
	 */
	public int getHeight() {
		return this.characterHeight;
	}
	
	
	/**
	 * Set the height of the character.
	 * @param newHeight new character height
	 */
	public void setHeight(final int newHeight) {
		if (newHeight > 0 && newHeight <= bgHeight) {
			this.characterHeight = newHeight;
		}
	}
	
	
	/**
	 * Return the character's image.
	 * @return the character image
	 */
	public Image getImage() {
		return this.image;
	}
	
	
	/**
	 * Set the character image and update height and width.
	 * @param newImage new image for character
	 */
	public void setImage(final Image newImage) {
		this.image = newImage;
		this.setHeight(newImage.getHeight(null));
		this.setWidth(newImage.getWidth(null));
	}
}
