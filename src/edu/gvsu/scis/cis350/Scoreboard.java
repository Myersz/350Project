package edu.gvsu.scis.cis350;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Scoreboard panel that holds current score and list of high scores.
 * @author Kelsey
 *
 */
public class Scoreboard extends JPanel{

	private static final long serialVersionUID = 0;
	
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


	/** This the timer counter for the seconds. */
	private int count = 0;
	/** This contains all of the highscores */
	ArrayList<Integer> highScores = new ArrayList<Integer>(10);
	/** The filename of the scores */
	String filename;
	/** Label for the Scoreboard */
	private JLabel scoreBoardLabel;
	
	/** Controls where score should be updated. */
	private boolean scrolling = false;


	/**
	 * Constructor for Scoreboard. Sets up label with scores.
	 */
	public Scoreboard() {
		this.setLayout(new BorderLayout());

		timer = new Timer(DELAY, new TaskPerformer());
		scoreBoardLabel = new JLabel();
		scoreBoardLabel.setText("Score: " + (count++) + "       " + printScoresToLabel());

		getDataFromFile();
		updateScores();

		this.filename = "Scoreboard.txt";

		this.add(scoreBoardLabel, BorderLayout.SOUTH);
		scoreBoardLabel.setVisible(true);
	}


	/** 
	 * Pulls in the top 5 score from the file nfile and
	 * saves them into highScores
	 **/
	private void getDataFromFile() {
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new File("Scoreboard.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(fileIn.hasNextLine()) {
			highScores.add(fileIn.nextInt());
		}
		while(highScores.size() < 6) {
			highScores.add(0);
		}
	}

	/** 
	 * This updates the scores where score is first added to highScores.
	 * Then the 6th element of highScores is removed in the list
	 * leaving a list of the top 5 scores. 
	 * @param score the new score to be checked for a highscore
	 * @return highScores the updated top 5 highscore list
	 */
	public ArrayList<Integer> updateScores() {
		
		highScores.add(getTime());
		Collections.sort(highScores);
		Collections.reverse(highScores);
		highScores.remove(6);
		sendDataToFile();
		printScoresToLabel();
		return highScores;
	}

	/** 
	 * Prints highScores to file
	 **/
	public void sendDataToFile() {
		try{
			PrintWriter writer = new PrintWriter(this.filename, "UTF-8");
			for(int i = 0; i < highScores.size(); i++){
				writer.print("\n" + highScores.get(i));
			}
			writer.close();
		} catch(Exception e) { }
	}


	/** 
	 * Formats the string of highscores into a nice simple list,
	 * and repaints the GUI to the updated text.
	 **/
	public String printScoresToLabel() {
		String str = "HighScores: ";
		for (int i = 0; i < highScores.size() - 1; i++) {
			str = str + "#" + (i + 1) + ": " + highScores.get(i) + "  ";
		}

		return str;
	}

	/** 
	 * returns the current highscores of the game
	 * @return highScores the current highscores of the game
	 */
	public ArrayList<Integer> getHighScores() {
		return highScores;
	}


	/**
	 * Listener for score.
	 */
	private class TaskPerformer implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent evt) {
			scoreBoardLabel.setText("Score: " + getTime() + "       " + printScoresToLabel());
			scoreBoardLabel.repaint();
		}
	}


	/**
	 * Start the timer.
	 */
	public final void timerStart() {
		timer.start();
		scrolling = true;
	}


	/** 
	 * Pause the timer.
	 */
	public final void timerPause() {
		timer.stop();
		scrolling = false;
	}


	/**
	 * Get the time. 
	 * @return the time
	 */
	public final int getTime() {
		if (scrolling) {
			count++;
		}
		return count;
	}

}
