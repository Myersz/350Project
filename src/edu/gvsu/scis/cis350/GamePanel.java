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
<<<<<<< HEAD
		ScrollingBackground background = new ScrollingBackground();
		((Component)background).setFocusable(true);
		getContentPane().add(background);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		//add Key Listener to background
		background.addKeyListener(new keyListener());
		
		bg = new JPanel();
=======
//		try {
//			background = new GameBackground();
//		} catch (MissingBackgroundException e) {
//			System.out.println("ERROR: Game cannot start without background "
//					+ "images. Check that Graphics/Backrounds/ folder has at "
//					+ "least one .png file within it.");
//			throw new MissingBackgroundException();
//		}
		
		bg = new JLabel();
		//bg.setIcon(background.getCurrentBackground());
>>>>>>> origin/ScrollingBackground-Ella
		gameDisplay.add(bg);
		
		this.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
<<<<<<< HEAD
	}
=======
		this.add(gameDisplay);
		this.add(startButton);
		this.add(stopButton);
	}

	/** 
	 * Timer listener to animate background for display.
	 * @author Kelsey
	 *
	 */
//	private class TimerListener implements ActionListener {
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			background.setBackground();
//			bg.setIcon(background.getCurrentBackground());
//		}
//	}
>>>>>>> origin/ScrollingBackground-Ella
	
	/**
	 * Key listener to control animation of background.
	 */
	private class keyListener implements KeyListener {
		@Override
<<<<<<< HEAD
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
=======
		public void actionPerformed(final ActionEvent e) {
			if (e.getSource() == startButton) {
				// Only begin animation if it is not already being animated.
//				if (!background.isAnimated()) {
//					this.animateBackground();
//				}
			}
			
//			if (e.getSource() == stopButton) {
//				if (background.isAnimated()) {
//					this.endAnimation();
//				}
//			}
		}
		
		/** 
		 * Instantiates timer and begins animation of background.
		 */
		private void animateBackground() {
//			updateScene = new Timer(ANIMATION_FREQ, new TimerListener());
//			updateScene.start();
//			background.setIsAnimated(true);
>>>>>>> origin/ScrollingBackground-Ella

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
<<<<<<< HEAD

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
=======
		
		/**
		 * Stops the background from changing.
		 */
//		private void endAnimation() {
//			updateScene.stop();
//			background.setIsAnimated(false);
//		}
>>>>>>> origin/ScrollingBackground-Ella
	}
}
