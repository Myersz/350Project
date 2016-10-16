package edu.gvsu.scis.cis350;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class TimerPanel extends JPanel {
	
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
	int count = 0;
	
	/**
	 * Default constructor for GamePanel.
	 * @throws MissingBackgroundException if background images failed to load. 
	 */
	public TimerPanel() {
		int delay = 1000; //milliseconds
		this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		this.setLocation(PANEL_LOCATION_WIDTH, PANEL_LOCATION_HEIGHT);
		timer = new Timer(delay, taskPerformer);
		timerLabel = new JLabel();
		this.add(timerLabel);
		timerLabel.setVisible(true);
		timer.start();
		}

	ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	    	timerLabel.setText(count++ + " sec");
	    	timerLabel.repaint();
	    }
	};
}

