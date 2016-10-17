package edu.gvsu.scis.cis350;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Displays an obstacle moving across the game screen.
 * @author Kelsey
 *
 */
public class ObstaclePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 0;
	
	/** X-coordinate for obstacle. */
	private int x;
	
	/** Y-coordinate for obstacle. */
	private int y;
	
	/** Width of background area. */
	private int bgWidth;
	
	/** Height of background area. */
	private int bgHeight;
	
	/** Size of obstacle. */
	static final int OBSTACLE_SIZE = 60;

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

	
	/**
	 * Constructor to set up new obstacle panel and initial positions.
	 * @param bg the background image to set sizes
	 */
	public ObstaclePanel(final ScrollingBackground bg) {
		
		x = bg.getWidth();
		y = bg.getHeight() - OBSTACLE_SIZE;
		bgWidth = bg.getWidth();
		bgHeight = bg.getHeight();
		
		time = 0;
		timer = new Timer(TIMER_LENGTH, new TimerListener());
		timer.start();		
		
		this.setOpaque(false);
		this.setDoubleBuffered(true);
	}

	
	/**
	 * Move the obstacle's position for painting.
	 */
	private void moveObstacle() {
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
	private boolean isOnScreen() {
		return (x + OBSTACLE_SIZE >= 0); 
	}

	
	/**
	 * Paint the window.
	 */
	@Override
	public final void paintComponent(final Graphics g) {
		if (obstacleExists) {
			//super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.fillRect(x, y, OBSTACLE_SIZE, OBSTACLE_SIZE);
		}
		
	}

	
	/**
	 * Repaint the screen.
	 */
	@Override
	public final void run() {
		while (GameControl.scrolling) {
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
		y = bgHeight - OBSTACLE_SIZE;
		obstacleExists = true;		
		System.out.println("Obstacle added");
	}

	
	/** 
	 * Increment the time counter while the background is scrolling, 
	 * add a new obstacle every five seconds.
	 */
	public final void countTime() {
		if (GameControl.scrolling) {
			time = time + 1;			
			System.out.println(time);

			if (time > 0 && time % OBSTACLE_FREQ == 0) {
				this.addObstacle();
			}
		}
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
