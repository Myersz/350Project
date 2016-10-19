package edu.gvsu.scis.cis350;

/**
 * Used to synchronize the various classes for the game.
 * @author Kelsey
 *
 */
public final class GameControl {

	/** Indicates if the game has begun. */
	//private static boolean gameStarted = false;
	
	/** Indicates if the game window is scrolling. */
	private static boolean scrolling = false;
	
	/** Indicates if the game is paused. */
	//private static boolean paused = false;
	
	/** Indicates that the game has been lost. */
	//private static boolean gameLost = false;
	

	/** 
	 * Instantiate a new game control instance.
	 */
	private GameControl() {
	}
	
	
	/**
	 * Return boolean value of scrolling.
	 * @return true if game is scrolling, false otherwise
	 */
	public static boolean getScrolling() {
		return scrolling;
	}
	
	
	/**
	 * Set scrolling for game control.
	 * @param pScrolling the new state of scrolling
	 */
	public static void setScrolling(final boolean pScrolling) {
		GameControl.scrolling = pScrolling;
	}
	
}
