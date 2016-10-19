package edu.gvsu.scis.cis350;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
	
	/** Help item for menu. */
	private JMenuItem help;
	/** Quit item for menu. */
	private JMenuItem quit;
	
	/** Background for game. */
	private ScrollingBackground back;
	/** Obstacle for game. */
	private ObstaclePanel obstacle;
	
	/** Timer for the game. */
	private TimerPanel timer;
	
	/** Height to add to panel to fix image. */
	private static final int HEIGHT_TO_ADD = 100;
	
	/**
	 * Main method for game GUI.
	 * @param args Arguments
	 */
	public static void main(final String[] args) {
		new JumpingManGame();
	}	
	

	/**
	 * Default constructor.
	 */
	private JumpingManGame() {
		super("Jumping Man");

		this.setJMenuBar(this.createMenuBar());

		// Set up bakcground, if background doesn't exist then shut down nicely.
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

		timer = new TimerPanel();

		// Set up obstacle panel
		//obstacle = new MovingObstacle(back);
		obstacle = new ObstaclePanel(back);
		obstacle.setSize(back.getWidth(), back.getHeight());		
				
		// Add keyboard listener to background
		back.addKeyListener(new GameKeyListener());
		((Component) back).setFocusable(true);
		
		// Add game panels to frame
		this.getContentPane().add(obstacle);
		this.getContentPane().add(back);
		this.getContentPane().add(timer);
				
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
				int result = JOptionPane.showOptionDialog(null, message, 
						"Game Help", 0, 1, null, options, options[0]);
				

				if (result == 0) {
					back.resumeScrolling();
					obstacle.resumeMoving();
				}
			}

			if (e.getSource() == quit) {
				System.exit(0);
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
				System.out.println("Enter has been pressed");
				if (!GameControl.getScrolling()) {
					back.resumeScrolling();
					obstacle.resumeMoving();
				} else if (GameControl.getScrolling()) {
					back.pauseScrolling();
				}
			}
		}
		@Override
		public void keyReleased(final KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyTyped(final KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}	
}