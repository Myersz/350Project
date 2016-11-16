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
 * Main method to run the Jumping Man game.
 * 
 * @author Kelsey
 *
 */
public final class JumpingManGame extends JFrame {

	static final long serialVersionUID = 0;
	
	/** New game item for menu. */
	private JMenuItem newGame;
	
	/** Help item for menu. */
	private JMenuItem help;
	
	/** Quit item for menu. */
	private JMenuItem quit;
	
	/** Background for game. */
	private ScrollingBackground back;
	
	/** Timer for the game. */
	private TimerPanel timer;
	
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
	
	private Scoreboard gameScoreboard;
	
	
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
		
		this.scrolling = false;
		this.gameLost = false;

		this.setJMenuBar(this.createMenuBar());

		// Set up background, if background doesn't exist then shut down nicely.
		try {
			back = new ScrollingBackground();
			back.setSize(back.getWidth(), back.getHeight());
		} catch (MissingBackgroundException e) {
			String message = "Game failed to launch.";
			JOptionPane.showMessageDialog(this, message, "Error", 0);
			System.exit(0);
		}
		
		
		if (back != null) {
			this.setSize(back.getWidth(), back.getHeight() + HEIGHT_TO_ADD);
		}
		gameScoreboard = new Scoreboard("Scoreboard.txt");
		
		timer = new TimerPanel();		
		
		game = new GamePanel(back);
		game.setSize(back.getWidth(), back.getHeight());
				
		// Add keyboard listener to background
		back.addKeyListener(new GameKeyListener());
		((Component) back).setFocusable(true);
		
		// Set up timer to check game status
		gameStatus = new Timer(10, new TimerListener());
		gameStatus.start();
		
		// Add game panels to frame
		this.getContentPane().add(game);
		this.getContentPane().add(back);
		this.getContentPane().add(timer);
		this.getContentPane().add(gameScoreboard);
		
		// Set up game window options
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
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

		newGame = new JMenuItem("New game");
		newGame.getAccessibleContext().setAccessibleDescription("New game");
		newGame.addActionListener(listener);
		file.add(newGame);
		
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
	 * Listener class to carry out appropriate task 
	 * when a menu item is selected.
	 *
	 */
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			
			// Show help screen
			if (e.getSource() == help) {
				
				back.pauseScrolling();
				String message = "To jump over an obstacle: Press up arrow key";
				String[] options = {"OK"};
				JOptionPane.showOptionDialog(null, message, 
						"Game Help", 0, 1, null, options, options[0]);
			}

			if (e.getSource() == quit) {
				System.exit(0);
			}
			
			if (e.getSource() == newGame) {
				// TODO: Needs work to clear screen and start a nice new game
				new JumpingManGame();
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
					back.resumeScrolling();
					game.resumeScrolling();
					timer.timerStart();
					
					scrolling = true;
					
				} else if (scrolling) {
					back.pauseScrolling();
					game.pauseScrolling();
					timer.timerPause();
					
					scrolling = false;
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
			if (game.getGameLost()) {
				gameLost = true;
				
				back.pauseScrolling();
				game.pauseScrolling();
				timer.timerPause();
				
				scrolling = false;
				
				String message = "You ran into an obstacle. Game over.";
				String[] options = {"Exit", "New Game"};
				int result = JOptionPane.showOptionDialog(null, message, 
						"Game over!", 0, 1, null, options, options[0]);
				
				// TODO: Needs work
				if (result == 0) {
					System.exit(0);
				} else if (result == 1) {
					new JumpingManGame();
				} else {
					System.exit(0);
				}
			}
		}
	}
}