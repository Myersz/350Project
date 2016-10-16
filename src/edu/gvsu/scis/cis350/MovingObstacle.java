package edu.gvsu.scis.cis350;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Moves an obstacle across the game screen.
 * @author Kelsey
 *
 */
public final class MovingObstacle extends JPanel {
	private static final long serialVersionUID = 0;

	/** Speed that obstacle moves across screen at. */
	private int speed;

	/** Obstacle for drawing. */
	private Obstacle obstacle;

	/** Timer to track running time of game. */
	private Timer timer;
	
	/** Timer to update position of obstacle. */
	private Timer labelTimer;

	/** Running time of game in seconds. */
	private int time;

	/** Size of background image. */
	private Dimension bgDimension;

	/** Number of seconds it takes for a new obstacle to appear. */
	static final int OBSTACLE_FREQ = 3;

	/** Frequency of timer update. */
	static final int TIMER_LENGTH = 1000;
	
	/** Frequency of label timer update. */
	static final int LABEL_LENGTH = 100;

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
		
		labelTimer = new Timer(LABEL_LENGTH, new TimerListener());
		labelTimer.start();

		this.setVisible(false);
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
		obstacle = new Obstacle(bgDimension);
		this.add(obstacle);
		this.setVisible(true);
		
		System.out.println("Obstacle added");
	}
	

	/**
	 * Move the obstacle.
	 */
	private void moveObstacle() {
		if (obstacle != null) {
			obstacle.move(10);
		}
	}
	

	/**
	 * Start a new thread to keep obstacles moving after game is resumed.
	 */
	public void resumeMoving() {
		labelTimer.start();
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
			if (e.getSource() == labelTimer) {
				moveObstacle();
			}
		}
	}

}
