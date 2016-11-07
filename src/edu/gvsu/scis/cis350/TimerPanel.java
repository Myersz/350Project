package edu.gvsu.scis.cis350;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Panel to track the score of the game.
 * @author Kelsey
 *
 */
public class TimerPanel extends JPanel {

	private static final long serialVersionUID = 0;

	/** Label to hold background image. */
	private JLabel timerLabel;
	/** Timer object for the game. */
	private Timer timer;
	/** Width for window. */
	static final int PANEL_WIDTH = 200;
	/** Height for window. */
	static final int PANEL_HEIGHT = 50;
	/** Width for window. */
	static final int PANEL_LOCATION_WIDTH = 800;
	/** Height for window. */
	static final int PANEL_LOCATION_HEIGHT = 700;
	/** Delay for timer. */
	static final int DELAY = 100; //milliseconds
	/** Divisor for score. */
	static final int DIVISOR = 10;

	/** This the timer counter for the seconds. */
	private int count = 0;

	/**
	 * Default constructor for TimerPanel. 
	 */
	public TimerPanel() {
		this.setLayout(new BorderLayout());

		timer = new Timer(DELAY, new TaskPerformer());
		timerLabel = new JLabel();
		timerLabel.setText("Score: 00");
		this.add(timerLabel, BorderLayout.SOUTH);
		timerLabel.setVisible(true);
	}

	/**
	 * Listener for score.
	 */
	private class TaskPerformer implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent evt) {
				timerLabel.setText("Score: " + count / DIVISOR + (count++) 
						% DIVISOR);
				timerLabel.repaint();
			
		}
	};


	/**
	 * Start the timer.
	 */
	public final void timerStart() {
		timer.start();
	}


	/** 
	 * Pause the timer.
	 */
	public final void timerPause() {
		timer.stop();
	}


	/**
	 * Get the time. 
	 * @return the time
	 */
	public final int getTime() {
		return count;
	}
}

