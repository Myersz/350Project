package edu.gvsu.scis.cis350;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Displays an obstacle moving across the game screen.
 * @author Katie, Zach
 *
 */
public class CharacterPanel extends JPanel {

	private static final long serialVersionUID = 0;

	/** X-coordinate for obstacle. */
	private int x;

	/** Y-coordinate for character. */
	private int y;

	/** Height of background area. */
	private int bgHeight;

	/** Image for character. */
	private Image image;

	/** Count for keeping track of seconds for jump. */
	private static final int TIMER_DELAY = 30;

	/** Timer to track running time of game. */
	private Timer timer;

	/** Flag for jumping. */
	private Boolean isJumping;

	/** Flag for falling. */
	private Boolean isFalling;

	/** Height of the character. */
	private int characterHeight;

	/**
	 * Constructor to set up new obstacle panel and initial positions.
	 * @param bg the background image to set sizes
	 */
	public CharacterPanel(final ScrollingBackground bg) {

		bgHeight = bg.getHeight();

		ImageIcon ii = new ImageIcon(
				"Graphics/Characters/childgirl.gif");
		image = ii.getImage();

		characterHeight = image.getHeight(null);

		x = 0;
		y = bgHeight - characterHeight;

		timer = new Timer(TIMER_DELAY, characterPerformer);
		timer.start();

		isJumping = false;
		isFalling = false;

		this.setOpaque(false);
		this.setDoubleBuffered(true);
		setVisible(true);
	}

	
	/**
	 * Get image.
	 * @return image
	 */
	public final Image getImage() {
		return image;
	}

	@Override
	public final void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(getImage(), x, y, null);  
		haveCollided();
	}

	
	/**
	 * Make the character jump if it's not already.
	 */
	public final void jump() {
		if (!isJumping && !isFalling) {
			isJumping = true;
		}
	}

	/**
	 * Check the bounds of character and obstacle and see if they have collided.
	 */
	private void haveCollided() {
		int obstacleLeftX;
		int obstacleRightX;
		int obstacleY;

		int characterLeftX;
		int characterRightX;
		int characterY;

		obstacleLeftX = ObstaclePanel.getXCoord();
		obstacleRightX = ObstaclePanel.getXCoord() 
				+ ObstaclePanel.getObstacleWidth();
		obstacleY = ObstaclePanel.getYCoord();

		characterLeftX = this.x;
		characterRightX = this.x + image.getWidth(null) - 70;
		characterY = this.y + image.getHeight(null) - 25;

		if (!(characterRightX < obstacleLeftX 
				|| characterLeftX > obstacleRightX || characterY < obstacleY)) {
			GameControl.setGameLost();
		}


	}

	
	/**
	 * ActionListener for jumping.
	 */
	private ActionListener characterPerformer = new ActionListener() {
		public void actionPerformed(final ActionEvent evt) {
			if (GameControl.getScrolling()) {
				if (isJumping) {
					if (y > bgHeight - characterHeight - 230) {
						System.out.println(y);
						y = y - 5;
						repaint();
					}
					if (y <= bgHeight - characterHeight - 220) { 
						isFalling = true;
						isJumping = false;
					}

				}
				if (isFalling) {
					if (y < bgHeight) {
						System.out.println(y);

						y = y + 5;
						repaint();
					}
					if (y >= bgHeight - characterHeight) {
						isFalling = false;
						isJumping = false;
					}
				}
			}
		}
	};


}
