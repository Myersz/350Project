package edu.gvsu.scis.cis350;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Main method to run the Jumping Man game. Manages GUI.
 * 
 * @author Kelsey
 *
 */
public final class JumpingManGame extends JFrame {

	static final long serialVersionUID = 0;

	/** Help item for menu. */
	private JMenuItem help;

	/** Quit item for menu. */
	private JMenuItem quit;

	/** Background for game. */
	private BackgroundPanel back;

	/** Timer for the game. */
	private Scoreboard score;

	/** Panel with obstacles and character for game. */
	private GamePanel game;

	/** Whether or not the game is scrolling. */
	private boolean scrolling;

	/** Whether or not the game has been lost. */
	private boolean gameLost;

	/** Height to fix screen display. */
	private static final int HEIGHT_TO_ADD = 100;

	/** Timer to repeatedly check if game has been lost. */
	private Timer gameStatus;

	/** Timer delay. */
	private static final int TIMER_DELAY = 10;
	
	/**
	 * Main method for game.
	 * @param args Arguments
	 */
	public static void main(final String[] args) {
		new JumpingManGame();
	}	


	/**
	 * Default constructor.
	 */
	public JumpingManGame() {
		super("Jumping Man");

		this.setJMenuBar(this.createMenuBar());
		
		this.setUpGame();
		
		// Set up game window options
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
	}


	/**
	 * Call methods to set up game.
	 */
	private void setUpGame() {
		this.scrolling = false;
		this.gameLost = false;

		// Set up panels		
		back = new BackgroundPanel();
		back.setSize(back.getWidth(), back.getHeight());

		score = new Scoreboard();	
		score.setSize(back.getWidth(), back.getHeight());

		game = new GamePanel(back);
		game.setSize(back.getWidth(), back.getHeight());
		
		// Add keyboard listener to background
		back.addKeyListener(new GameKeyListener());
		((Component) back).setFocusable(true);

		// Set up timer to check game status
		gameStatus = new Timer(TIMER_DELAY, new TimerListener());
		gameStatus.start();

		// Add game panels to frame
		this.getContentPane().add(game);
		this.getContentPane().add(back);
		this.getContentPane().add(score);
		
		this.setSize(back.getWidth(), back.getHeight() + HEIGHT_TO_ADD);
		back.requestFocusInWindow();
	}


	/**
	 * Remove panels from JFrame to get rid of old game.
	 */
	private void clearOldGame() {
		this.getContentPane().removeAll();

		game = null;
		back = null;
		score = null;
	}


	/**
	 * Sets up menu bar for game window.
	 * @return a menu bar item
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu file = new JMenu("File");	
		menuBar.add(file);

		ButtonListener listener = new ButtonListener();

		help = new JMenuItem("Help");
		help.getAccessibleContext().setAccessibleDescription("Help");
		help.addActionListener(listener);
		file.add(help);

		quit = new JMenuItem("Quit");
		quit.getAccessibleContext().setAccessibleDescription("Quit");
		quit.addActionListener(listener);
		file.add(quit);

		return menuBar;
	}


	/**
	 * Call pause methods.
	 */
	private void pause() {
		back.pauseScrolling();
		game.pauseScrolling();
		score.timerPause();

		scrolling = false;
	}


	/**
	 * Call resume methods.
	 */
	private void resume() {
		back.resumeScrolling();
		game.resumeScrolling();
		score.timerStart();

		scrolling = true;
	}


	/**
	 * Quit game.
	 */
	private void quit() {
		score.updateScores();
		System.exit(0);
	}


	/**
	 * Listener class to carry out appropriate task 
	 * when a menu item is selected.
	 *
	 */
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {

			// Show help screen
			if (e.getSource() == help) {
				pause();
				String message = "To pause/resume game: Press \"enter\" key \n"
						+ "To jump over an obstacle: Press \"up\" arrow key \n"
						+ "To start a new game after losing: Press \"enter\" "
						+ "key";
				String[] options = {"OK"};
				JOptionPane.showOptionDialog(null, message, 
						"Game Help", 0, 1, null, options, options[0]);
			}

			// Confirm quit
			if (e.getSource() == quit) {
				pause();
				String message = "Are you sure you want to quit?";
				int result = JOptionPane.showConfirmDialog(
						null, message, "Quit", 0);
				if (result == 0) {
					quit();
				}
			}
		}
	}


	/**
	 * Listener class to carry out appropriate task 
	 * when a menu item is selected.
	 */
	private class GameKeyListener implements KeyListener {
		@Override
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (!scrolling && !gameLost) {
					resume();
				} else if (scrolling) {
					pause();
				}	
			}

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				game.jump();
			}
		}

		@Override
		public void keyReleased(final KeyEvent e) {
		}

		@Override
		public void keyTyped(final KeyEvent e) {
		}
	}	

	/**
	 * Repeatedly check if the game has been lost and handle it when it happens.
	 * @author Kelsey
	 *
	 */
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (game != null && game.getGameLost() && !gameLost) {
				pause();
				gameLost = true;

				//Updates the score
				score.updateScores();
				
				String message = "You ran into an obstacle. Game over.";
				String[] options = {"New Game", "Exit"};
				int result = JOptionPane.showOptionDialog(null, message, 
						"Game over!", 0, 1, null, options, options[0]);

				if (result == 1) {
					// Exit
					quit();	
				} else if (result == 0) {
					// Start a new game
					clearOldGame();
					setUpGame();
				} else {
					// Close dialog box 
					game.setGameLost(false);
				}
			}
		}
	}
}
