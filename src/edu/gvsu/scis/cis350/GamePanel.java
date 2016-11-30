package edu.gvsu.scis.cis350;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private ArrayList<Obstacle> obstacles;

	/** The timer to control adding obstacles. */
	private Timer obstacleAddTimer;

	/** The timer to control moving obstacles. */
	private Timer obstacleMoveTimer;

	/** The timer to control moving the character. */
	private Timer characterTimer;

	/** The number of seconds that have passed while the game is running. */
	private int time;

	/** The initial number of milliseconds between obstacles being added. */
	private static final int INITIAL_FREQ = 60;
	
	/** The current number of milliseconds between obstacles being added. */
	private int obstacleFrequency;

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
	public GamePanel(final BackgroundPanel bg) {

		scrolling = false;
		gameLost = false;

		bgSize = new Dimension(bg.getWidth(), bg.getHeight());

		character = new Character(bgSize.width, bgSize.height);

		obstacles = new ArrayList<Obstacle>();
		obstacleFrequency = INITIAL_FREQ;
		
		TimerListener timerListener = new TimerListener();

		time = 0;
		obstacleAddTimer = new Timer(100, timerListener);
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
		//System.out.println(time);

		// Add an obstacle if it's the correct interval
		if (time > 0 && time % obstacleFrequency == 0) {
			this.addObstacle();

		}
		
		// Increase the frequency every eight seconds
		if (time > 0 && time % 80 == 0 && obstacleFrequency > 15) {
			obstacleFrequency -= 5;
		}
	}


	/**
	 * Create a new obstacle.
	 */
	public void addObstacle() {
		obstacles.add(new Obstacle(bgSize.width, bgSize.height));
		//System.out.println("Obstacle added");
	}


	/**
	 * Paint the character and the obstacles on the screen.
	 */
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// Draw the character
		g2d.drawImage(character.getImage(), character.getXCoord(), 
				character.getYCoord(), null);  

		// Draw and manage the obstacles
		if (obstacles.size() > 0) {
			// To hold obstacles that are now off screen
			ArrayList<Obstacle> remove = new ArrayList<Obstacle>();
			
			// Check for collisions
			this.haveCollided();
			
			// Iterate through obstacles and draw them
			for (Obstacle o : obstacles) {
				g2d.drawImage(o.getImage(), o.getXCoord(), 
						o.getYCoord(), null); 
				
				// Move the obstacle, if it returns false (offscreen), 
				// mark obstacle to be removed
				if (!o.moveObstacle()) {
					remove.add(o);
				}
			}
			
			// Remove any obstacles that are now off-screen
			for (Obstacle o : remove) {
				obstacles.remove(o);
			}
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
		int obstacleLeftX;
		int obstacleRightX;
		int obstacleY;

		// Set up character coordinates
		int characterLeftX = character.getXCoord() + 20;
		int characterRightX = character.getXCoord() 
				+ character.getImage().getWidth(null) - 50;
		int characterY = character.getYCoord() 
				+ character.getImage().getHeight(null) - 25;

		// Iterate through each of the obstacles on the screen
		for (Obstacle o : obstacles) {
			obstacleLeftX = o.getXCoord();
			obstacleRightX = o.getXCoord() + o.getObstacleWidth();
			obstacleY = o.getYCoord();

			// Check for collision
			if (!(characterRightX < obstacleLeftX 
					|| characterLeftX > obstacleRightX
					|| characterY < obstacleY)) {
				this.gameLost = true;
				return;
			}
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
