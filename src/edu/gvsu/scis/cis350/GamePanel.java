package edu.gvsu.scis.cis350;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Main GUI window for the game.
 * 
 * @author Kelsey Brennan and Ella Konst
 *
 */
public class GamePanel extends JFrame {

	static final long serialVersionUID = 0; 
	
	/** The Frame for the game display. */
	private JFrame gameDisplay = new JFrame();
	/** Panel to hold background image. */
	private JPanel bg;
	/** Background manager. */
	private ScrollingBackground background;
	
	
	/** Width for display. */
	static final int DISPLAY_WIDTH = 1000;
	/** Height for display. */
	static final int DISPLAY_HEIGHT = 800;
	
	/**
	 * Default constructor for GamePanel.
	 */
	public GamePanel() {
		super("Jumping Man");
		setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		
		// Configure initial background for game
		ScrollingBackground background = new ScrollingBackground();
		((Component)background).setFocusable(true);
		getContentPane().add(background);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		//add Key Listener to background
		background.addKeyListener(new keyListener());
		
		bg = new JPanel();
		gameDisplay.add(bg);
		
		this.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
	}
	
	/**
	 * Key listener to control animation of background.
	 */
	private class keyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				System.out.println("Enter has been pressed");
				if (false/*!background.getScrolling()*/) {
					background.setScrolling(true);
					background.run();
				} else if (true/*background.getScrolling()*/) {
					background.setScrolling(false);
					//background.run();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	}
}
