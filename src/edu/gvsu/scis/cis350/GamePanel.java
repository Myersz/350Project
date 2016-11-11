package edu.gvsu.scis.cis350;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Control class for the character and obstacles.
 * @author Kelsey
 *
 */
public final class GamePanel extends JPanel {

	private static final long serialVersionUID = 0;

	/** The character for the game. */
	private Character character;
	
	/** The obstacles for the game. */
	private Obstacle obstacle;
	
	/** The timer to control adding obstacles. */
	private Timer obstacleAddTimer;
	
	/** The timer to control moving obstacles. */
	private Timer obstacleMoveTimer;
	
	/** The timer to control moving the character. */
	private Timer characterTimer;

	/** The number of seconds that have passed while the game is running. */
	private int time;
	
	/** The number of seconds between obstacles being added. */
	private static final int OBSTACLE_FREQ = 5;
	
	/** Whether or not the game is scrolling. */
	private boolean scrolling;

	/** The dimensions of the background image. */
	private Dimension bgSize;
	
	/** Whether or not the game has been lost. */
	private boolean gameLost;

	
	/**
	 * Constructor for the game panel.
	 * @param bg the background of game
	 */
	public GamePanel(final ScrollingBackground bg) {

		scrolling = false;
		gameLost = false;
		
		bgSize = new Dimension(bg.getWidth(), bg.getHeight());

		character = new Character(bgSize.height);

		TimerListener timerListener = new TimerListener();

		time = 0;
		obstacleAddTimer = new Timer(1000, timerListener);
		obstacleAddTimer.start();

		obstacleMoveTimer = new Timer(10, timerListener);
		obstacleMoveTimer.start();

		characterTimer = new Timer(30, timerListener);
		characterTimer.start();

		this.setOpaque(false);
		this.setDoubleBuffered(true);
		this.setVisible(true);
	}


	/**
	 * Count the seconds that have gone by and add an 
	 * obstacle at appropriate intervals.
	 */
	public void countTime() {
		time = time + 1;	
		System.out.println(time);

		if (time > 0 && time % OBSTACLE_FREQ == 0) {
			this.addObstacle();

		}
	}


	/**
	 * Create a new obstacle.
	 */
	public void addObstacle() {
		obstacle = new Obstacle(bgSize.width, bgSize.height);		

		System.out.println("Obstacle added");
	}


	/**
	 * Paint the character and the obstacles on the screen.
	 */
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(character.getImage(), character.getXCoord(), 
				character.getYCoord(), null);  

		// If there is an obstacle on the screen, draw it, check for collision,
		// then move it
		if (obstacle != null) {
			g2d.drawImage(obstacle.getImage(), obstacle.getXCoord(), 
					obstacle.getYCoord(), null); 
			haveCollided();
			obstacle.moveObstacle();
		}
	}

	
	/**
	 * Resume scrolling of obstacles and moving of character.
	 */
	public void resumeScrolling() {
		this.scrolling = true;
	}

	
	/**
	 * Pause scrolling of obstacles and moving of character.
	 */
	public void pauseScrolling() {
		this.scrolling = false;
	}

	
	/**
	 * Make the character jump.
	 */
	public void jump() {
		character.jump();
	}


	/**
	 * Check the bounds of character and obstacle and see if they have collided.
	 */
	private void haveCollided() {
		// Set up obstacle coordinates
		int obstacleLeftX = obstacle.getXCoord();
		int obstacleRightX = obstacle.getXCoord() 
				+ obstacle.getObstacleWidth();
		int obstacleY = obstacle.getYCoord();

		// Set up character coordinates
		int characterLeftX = character.getXCoord();
		int characterRightX = character.getXCoord() 
				+ character.getImage().getWidth(null) - 70;
		int characterY = character.getYCoord() 
				+ character.getImage().getHeight(null) - 25;
		
		// Check for collision
		if (!(characterRightX < obstacleLeftX 
				|| characterLeftX > obstacleRightX || characterY < obstacleY)) {
			this.gameLost = true;
		}

	}

	
	/**
	 * Return whether or not the game has been lost.
	 * @return true if game has been lost, false otherwise
	 */
	public boolean getGameLost() {
		return this.gameLost;
	}

	
	/**
	 * Set the value of gameLost.
	 * @param b the new value of gameLost
	 */
	public void setGameLost(final boolean b) {
		this.gameLost = b;
	}
	
	
	/**
	 * Count time for obstacle movement.
	 */
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			// Only update things if the game is scrolling.
			if (scrolling) {
				if (e.getSource() == obstacleAddTimer) {
					countTime();
				}
				if (e.getSource() == obstacleMoveTimer) {
					repaint();
				}
				if (e.getSource() == characterTimer) {
					character.moveCharacter();
				}
			}
		}
	}

}
