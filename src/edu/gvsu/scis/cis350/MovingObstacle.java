package edu.gvsu.scis.cis350;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Moves an obstacle across the game screen.
 * @author Kelsey
 *
 */
public final class MovingObstacle extends JLabel implements Runnable {
	private static final long serialVersionUID = 0;

	/** Holds obstacle image. */
	private BufferedImage image;

	/** Speed that obstacle moves across screen at. */
	private int speed;

	/** Obstacle for drawing. */
	private Obstacle obstacle1;
	
	/** Second copy of obstacle for drawing. */
	private Obstacle obstacle2;

	/** Timer to track running time of game. */
	private Timer timer;

	/** Running time of game in seconds. */
	private int time;

	/** Size of background image. */
	private Dimension bgDimension;
	
	/** Number of seconds it takes for a new obstacle to appear. */
	static final int OBSTACLE_FREQ = 5;
	
	/** Frequency of timer update. */
	static final int TIMER_LENGTH = 1000;
	
	/** Length of time that thread will sleep. */
	static final int SLEEP_TIME = 10;

	/**
	 * Create a new obstacle width the specificed width and height.
	 * @param bg the background on which the obstacle will appear
	 */
	public MovingObstacle(final ScrollingBackground bg) {
		super();
		
		bgDimension = new Dimension(bg.getWidth(), bg.getHeight());
		
		time = 0;
		timer = new Timer(TIMER_LENGTH, new TimerListener());
		timer.start();
		speed = 1;

		new Thread(this).start();
		this.setVisible(true);
		this.setOpaque(false);
	}

	/** 
	 * Increment the time counter while the background is scrolling, 
	 * add a new obstacle every five seconds.
	 */
	public void countTime() {
		if (GameControl.scrolling) {
			time = time + 1;			
			System.out.println(time);
			
			if (time > 0 && time % OBSTACLE_FREQ == 0) {
				this.addObstacle(speed);
			}
		}
	}

	
	/**
	 * Create a new obstacle.
	 * @param pSpeed Speed for obstacles to move at
	 */
	public void addObstacle(final int pSpeed) {
		obstacle1 = new Obstacle(bgDimension, pSpeed);
		obstacle2 = obstacle1;
		System.out.println("Obstacle added");
	}

	
	/**
	 * Run to paint screen.
	 */
	@Override
	public void run() {
		try {
			while (GameControl.scrolling) {
				Thread.sleep(SLEEP_TIME);
				repaint();
			}
			
		} catch (Exception e) { }		
	}
	

	/**
	 * Paint obstacle on window.
	 * @param window The graphics window to be painted.
	 */
	@Override
	public void paintComponent(final Graphics window) {
		//super.paintComponent(window);

		Graphics2D twoD = (Graphics2D) window;

		if (image == null) {
			image = (BufferedImage) (createImage(getWidth(), getHeight()));
		}

		// Create a buffer to draw to
		Graphics2D buffer = image.createGraphics();

		if (obstacle1 != null && obstacle1.isOnScreen()) {
			// Put the two copies of the obstacle image onto the buffer
			obstacle1.draw(buffer);
			obstacle2.draw(buffer);
	
			// Draw the image onto the window
			twoD.drawImage(image, null, 0, 0);
		}
	}
	
	
	/**
	 * Start a new thread to keep obstacles moving after game is resumed.
	 */
	public void resumeMoving() {
		new Thread(this).start();
	}
	
	
	/** 
	 * Timer listener.
	 * @author Kelsey
	 *
	 */
	public class TimerListener implements ActionListener {
		@Override
		public final void actionPerformed(final ActionEvent e) {
			countTime();
		}
	}

}
