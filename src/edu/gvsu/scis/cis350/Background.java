package edu.gvsu.scis.cis350;
import java.io.File;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Manages background of game.
 * Controls the speed with which the image moves across the screen
 * 
 * @author Ella
 *
 */
public class Background {
	
	/** x coordinate for location of image. */
	private int x;
	/** y coordinate for location of image. */
	private int y;
	
	/** Set initial speed of scroll to 5. */
	static final int INITIAL_SPEED = 2;
	
	/** Speed of scrolling. */
	private int speed;
	
	/** Contains Background Image. */
	private BufferedImage image;

	/**
	 * Constructor for GameBackground class. 
	 * Sets coordinates for x and y to 0
	 */
	public Background() {
		this(0, 0);
	}
	
	/**
	 * Constructor for GameBackground class.
	 * Loads background image and accepts coordinate for 
	 * placement of background.
	 * @param xCoordinate coordinate for image placement
	 * @param yCoordinate coordinate for image placement
	 * @throws MissingBackgroundException throws MissingBackgroundException 
	 * if bg failed to load
	 */
	public Background(final int xCoordinate, final int yCoordinate) {
		this.x = xCoordinate;
		this.y = yCoordinate;
		
		this.speed = INITIAL_SPEED;
		
		// Retrieve background image from file
		try {
			image = ImageIO.read(new File(
					"Graphics/Backgrounds/citybackground.png"));
		} catch (Exception e) {
			System.out.println("Error loading background");
		}
	
	}
	
	
	/**
	 * Re-Draw background shifted over.
	 * @param window - window graphics will be drawn on
	 */
	public final void draw(final Graphics window) {
        // Draw the image onto the Graphics reference
        window.drawImage(image, getX(), getY(), image.getWidth(), 
        		image.getHeight(), null);
 
		// Move the x position left for next time
        this.x -= speed;
 
        // Check to see if the image has gone off stage left
        if (this.x <= -1 * image.getWidth()) {
 
            // If it has, line it back up so that its left edge is
            // lined up to the right side of the other background image
            this.x = this.x + image.getWidth() * 2;
        }
	}
	
	
	/**
	 * Set x coordinate.
	 * @param xCoordinate coordinate x is to be set to
	 */
	public final void setX(final int xCoordinate) {
		this.x = xCoordinate;
	}
	
	
	/**
	 * Get x coordinate.
	 * @return x coordinate
	 */
	public final int getX() {
		return this.x;
	}
	
	
	/**
	 * Get y coordinate.
	 * @return y coordinate
	 */
	public final int getY() {
		return this.y;
	}
	
	
	/**
	 * Set the y coordinate.
	 * @param yCoordinate the new y coordinate
	 */
	public final void setY(final int yCoordinate) {
		this.y = yCoordinate;
	}
	
	
	/**
	 * Get background image width.
	 * @return integer value of width of image
	 */
	public final int getImageWidth() {
		return image.getWidth();
	}
	
	/**
	 * Get background image height.
	 * @return integer value of height of image
	 */
	public final int getImageHeight() {
		return image.getHeight();
	}
	
	
	/**
	 * Get Speed of scrolling.
	 * @return integer value for speed of scrolling
	 */
	public final int getSpeed() {
		return this.speed;
	}
	
	
	/**
	 * Set Speed of scrolling.
	 * @param pSpeed - new speed for scrolling
	 */
	public final void setSpeed(final int pSpeed) {
		this.speed = pSpeed;
	}
}
