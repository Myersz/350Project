package edu.gvsu.scis.cis350;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Timer panel class.
 * @author Kelsey
 *
 */
public class TimerPanel extends JPanel {
	
	private static final long serialVersionUID = 0;
	
	/** Label to hold background image. */
	private JLabel timerLabel;
	/** Timer object to animate background. */
	private Timer timer;
	/** Width for window. */
	static final int PANEL_WIDTH = 200;
	/** Height for window. */
	static final int PANEL_HEIGHT = 50;
	/** Width for window. */
	static final int PANEL_LOCATION_WIDTH = 800;
	/** Height for window. */
	static final int PANEL_LOCATION_HEIGHT = 700;
	/** This the timer counter for the seconds. */
	private int count = 0;
	
	/** Delay for timer. */
	static final int DELAY = 1000; //milliseconds

	
	/**
	 * Default constructor for GamePanel.
	 * @throws MissingBackgroundException if background images failed to load. 
	 */
	public TimerPanel() {
		this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		this.setLocation(PANEL_LOCATION_WIDTH, PANEL_LOCATION_HEIGHT);
		timer = new Timer(DELAY, taskPerformer);
		timerLabel = new JLabel();
		this.add(timerLabel);
		timerLabel.setVisible(true);
		timer.start();
		}

	/**
	 * Listener for timer.
	 */
	private ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(final ActionEvent evt) {
	    	timerLabel.setText(count++ + " sec");
	    	timerLabel.repaint();
	    }
	};
}

