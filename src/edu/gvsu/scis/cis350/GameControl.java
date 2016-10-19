package edu.gvsu.scis.cis350;

import javax.swing.JOptionPane;

/**
 * Used to synchronize the various classes for the game.
 * @author Kelsey
 *
 */
public final class GameControl {
	
	/** Indicates if the game window is scrolling. */
	private static boolean scrolling = false;
	
	/** Indicates if the game has been lost. */
	private static boolean gameLost = false;

	
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
	
	
	/**
	 * Return whether or not game has been lost.
	 * @return true if game has been lost, false otherwise
	 */
	public static boolean getGameLost() {
		return GameControl.gameLost;
	}
	
	
	/**
	 * Set game lost.
	 */
	public static void setGameLost() {
		GameControl.gameLost = true;
		GameControl.setScrolling(false);
		
		String message = "You ran into an obstacle. Game over.";
		String[] options = {"OK"};
		JOptionPane.showOptionDialog(null, message, 
				"Game over!", 0, 1, null, options, options[0]);
	}
	
}
