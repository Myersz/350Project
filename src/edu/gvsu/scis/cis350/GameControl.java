package edu.gvsu.scis.cis350;

/**
 * Used to synchronize the various classes for the game.
 * @author Kelsey
 *
 */
public final class GameControl {

	/** Indicates if the game has begun. */
	public static boolean gameStarted;
	
	/** Indicates if the game window is scrolling. */
	public static boolean scrolling;
	
	/** Indicates if the game is paused. */
	public static boolean paused;
	
	/** Indicates that the game has been lost. */
	public static boolean gameLost;
	

	/** 
	 * Instantiate a new game control instance.
	 */
	private GameControl() {
		gameStarted = false;
		scrolling = false;
		paused = false;
		gameLost = false;
	}
	
}
