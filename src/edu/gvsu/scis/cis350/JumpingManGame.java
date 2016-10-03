package edu.gvsu.scis.cis350;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main method to run the Jumping Man game.
 * 
 * @author Kelsey
 *
 */
public final class JumpingManGame extends JFrame {

	static final long serialVersionUID = 0;
	
	/** Width for window. */
	static final int FRAME_WIDTH = 1000;
	/** Height for window. */
	static final int FRAME_HEIGHT = 800;

	/** Help item for menu. */
	private JMenuItem help;
	/** Quit item for menu. */
	private JMenuItem quit;
	
	/** Background for game. */
	private ScrollingBackground back;
	
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
		setSize(FRAME_WIDTH, FRAME_HEIGHT);

		setJMenuBar(this.createMenuBar());

		back = new ScrollingBackground();
		((Component) back).setFocusable(true);
		getContentPane().add(back);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
			if (e.getSource() == help) {
				back.setScrolling(false);
				String message = "To jump over an obstacle: Press up arrow key";
				String[] options = {"OK"};
				int result = JOptionPane.showOptionDialog(null, message, 
						"Game Help", 0, 1, null, options, options[0]);
				
				// This is supposed to turn scrolling back on once the 
				// window is closed but it's not working
				if (result == 0) {
					back.setScrolling(true);
				}
			}

			if (e.getSource() == quit) {
				System.exit(0);
			}

		}
	}
	

}