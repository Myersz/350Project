package edu.gvsu.scis.cis350;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Displays an obstacle moving across the game screen.
 * @author Kelsey
 *
 */
public class ObstaclePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 0;
	
	/** Image for obstacle. */
	private BufferedImage image;
	
	/** Resized obstacle image. */
	private Image obstacle;
	
	/** X-coordinate for obstacle. */
	private static int x;
	
	/** Y-coordinate for obstacle. */
	private static int y;
	
	/** Width of background area. */
	private static int bgWidth;
	
	/** Height of background area. */
	private int bgHeight;
	
	/** Width of obstacle. */
	private static int obstacleWidth;
	
	/** Height of obstacle. */
	private static int obstacleHeight;

	/** Control whether an obstacle is on the screen. */
	private boolean obstacleExists = false;

	/** Timer to track running time of game. */
	private Timer timer;

	/** Running time of game in seconds. */
	private int time;
	
	/** Number of seconds it takes for a new obstacle to appear. */
	static final int OBSTACLE_FREQ = 5;

	/** Frequency of timer update. */
	static final int TIMER_LENGTH = 1000;
	
	/** Distance to move obstacle. */
	static final int DISTANCE_TO_MOVE = 3;
	
	/** Milliseconds for thread to sleep. */
	static final int THREAD_SLEEP = 10;

	/** Image scaling. */
	static final int SCALE = 7;
	
	
	/**
	 * Constructor to set up new obstacle panel and initial positions.
	 * @param bg the background image to set sizes
	 */
	public ObstaclePanel(final ScrollingBackground bg) {
		
		try {
			image = ImageIO.read(new File(
					"Graphics/Obstacles/obstacle.png"));
		} catch (IOException e) {
			System.out.println("Error loading obstacle image");
		}
		
		if (image != null) {
			obstacle = image.getScaledInstance(image.getWidth() / SCALE, 
					image.getHeight() / SCALE, 0);
		}
		
		bgWidth = bg.getWidth();
		bgHeight = bg.getHeight();
		
		obstacleWidth = obstacle.getWidth(null);
		obstacleHeight = obstacle.getHeight(null);
		
		x = bgWidth;
		y = bgHeight - obstacleHeight;
		
		time = 0;
		timer = new Timer(TIMER_LENGTH, new TimerListener());
		timer.start();		
		
		this.setOpaque(false);
		this.setDoubleBuffered(true);
		this.setVisible(true);
	}

	
	/**
	 * Move the obstacle's position for painting.
	 */
	public final void moveObstacle() {
		if (isOnScreen()) {
			x = x - DISTANCE_TO_MOVE;
		} else {
			obstacleExists = false;
		}
	}

	
	/**
	 * Control whether or not an obstacle should be painted.
	 * @return true if an obstacle should be painted currently, false otherwise
	 */
	public final boolean isOnScreen() {
		int xPos = x;
		return (xPos + obstacleWidth >= 0 && obstacleExists); 
	}

	
	/**
	 * Paint the window.
	 */
	@Override
	public final void paintComponent(final Graphics g) {
		if (obstacleExists) {
			Graphics2D g2d = (Graphics2D) g;		
			g2d.drawImage(obstacle, x, y, null);
		}
		
	}

	
	/**
	 * Repaint the screen.
	 */
	@Override
	public final void run() {
		while (GameControl.getScrolling()) {
			this.moveObstacle();
			this.repaint();
			try {
				Thread.sleep(THREAD_SLEEP);
			} catch (InterruptedException e) {
				System.out.println("Error");
			}
		}

	}

	
	/** 
	 * Move the obstacle again after game was paused.
	 */
	public final void resumeMoving() {
		new Thread(this).start();		
	}


	/**
	 * Create a new obstacle.
	 */
	public final void addObstacle() {
		x = bgWidth;
		y = bgHeight - obstacleHeight;
		obstacleExists = true;		
		
		System.out.println("Obstacle added");
	}
	
	
	/**
	 * Get the current time.
	 * @return the number of seconds
	 */
	public final int getTime() {
		return this.time;
	}

	
	/** 
	 * Increment the time counter while the background is scrolling, 
	 * add a new obstacle every five seconds.
	 */
	public final void countTime() {
		if (GameControl.getScrolling()) {
			time = time + 1;	
			System.out.println(time);

			if (time > 0 && time % OBSTACLE_FREQ == 0) {
				this.addObstacle();
			}
		}
	}

	
	/**
	 * Set the x coordinate.
	 * @param newX the new x coordinate.
	 */
	public static final void setXCoord(final int newX) {
		ObstaclePanel.x = newX;
	}
	
	
	/** 
	 * Get the x coordinate.
	 * @return the x coordinate
	 */
	public static final int getXCoord() {
		return ObstaclePanel.x;
	}
	
	
	/**
	 * Return the value of obstacleExists.
	 * @return true if there is an obstacle, false otherwise
	 */
	public final boolean getObstacleExists() {
		return this.obstacleExists;
	}
	
	
	/**
	 * Return the width of the obstacle.
	 * @return the width of the obstacle
	 */
	public static final int getObstacleWidth() {
		return ObstaclePanel.obstacleWidth;
	}
	
	
	/**
	 * Return the height of the obstacle.
	 * @return the height of the obstacle
	 */
	public static final int getObstacleHeight() {
		return ObstaclePanel.obstacleHeight;
	}
	
	
	/**
	 * Get the y coordinate.
	 * @return the y coordinate
	 */
	public static final int getYCoord() {
		return ObstaclePanel.y;
	}
	
	
	/** 
	 * Timer listener.
	 * @author Kelsey
	 *
	 */
	public class TimerListener implements ActionListener {
		@Override
		public final void actionPerformed(final ActionEvent e) {
			if (e.getSource() == timer) {
				countTime();
			}
		}
	}

}
