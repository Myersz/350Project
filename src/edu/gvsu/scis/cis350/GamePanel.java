package edu.gvsu.scis.cis350;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Main GUI window for the game.
 * 
 * @author Kelsey
 *
 */
public class GamePanel extends JPanel {

	static final long serialVersionUID = 0; 
	
	/** The panel for the game display. */
	private JPanel gameDisplay = new JPanel();
	/** Label to hold background image. */
	private JLabel bg;
	/** Timer object to animate background. */
	private Timer updateScene;
	/** Button to begin animation. */
	private JButton startButton;
	/** Button to stop animation. */
	private JButton stopButton;
	/** Background manager. */
	private GameBackground background;
	
	
	/** Width for display. */
	static final int DISPLAY_WIDTH = 900;
	/** Height for display. */
	static final int DISPLAY_HEIGHT = 768;
	/** Frequency of background changes. */
	static final int ANIMATION_FREQ = 500; // 0.5 seconds
	
	/**
	 * Default constructor for GamePanel.
	 * @throws MissingBackgroundException if background images failed to load. 
	 */
	public GamePanel() throws MissingBackgroundException {
		
		gameDisplay.setPreferredSize(
				new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Configure initial background for game
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
		gameDisplay.add(bg);
		
		startButton = new JButton("Start");
		ButtonListener buttonListener = new ButtonListener();
		startButton.addActionListener(buttonListener);
		
		stopButton = new JButton("Stop");
		stopButton.addActionListener(buttonListener);
		
		this.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
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
	
	/**
	 * Button listener to begin animation of background.
	 * @author Kelsey
	 *
	 */
	private class ButtonListener implements ActionListener {
		@Override
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

		}
		
		/**
		 * Stops the background from changing.
		 */
//		private void endAnimation() {
//			updateScene.stop();
//			background.setIsAnimated(false);
//		}
	}
}
