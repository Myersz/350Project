package edu.gvsu.scis.cis350;


import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Scoreboard extends JPanel{
	
	ArrayList<Integer> highScores = new ArrayList<Integer>(10);
	String filename;
	/** Label for the Scoreboard */
	private JLabel ScoreBoardLabel;
	
	public Scoreboard(String nfile) {
		this.filename = nfile;
		ScoreBoardLabel = new JLabel();
		//ScoreBoardLabel.setVerticalAlignment(SwingConstants.TOP);
		ScoreBoardLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.setLayout(new BorderLayout());
		getDataFromFile();
		updateScores(0);
		this.add(ScoreBoardLabel, BorderLayout.SOUTH);
		ScoreBoardLabel.setVisible(true);
		printScoresToTerminal();
	}
	
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
	
	public ArrayList<Integer> updateScores(int score) {
		highScores.add(score);
		Collections.sort(highScores);
		Collections.reverse(highScores);
		highScores.remove(6);
		sendDataToFile();
		printScoresToLabel();
		return highScores;
	}
	
	public void sendDataToFile() {
		try{
		    PrintWriter writer = new PrintWriter(this.filename, "UTF-8");
		    for(int i = 0; i < highScores.size(); i++){
		    	writer.print("\n" + highScores.get(i));
		    }
		    writer.close();
		} catch(Exception e) { }
	}
	
	public void printScoresToTerminal() {
		for(int i = 0; i < highScores.size()-1; i++){
	    	System.out.println("#" + (i+1) + ":" + highScores.get(i) + "  ");
	    }
	}
	
	public void printScoresToLabel() {
		String str = "HighScores: ";
		for(int i = 0; i < highScores.size()-1; i++){
	    	str += "#" + (i+1) + ":" + highScores.get(i) + "  ";
	    	ScoreBoardLabel.setText(str);
	    }
		this.repaint();
	}
	
	public ArrayList<Integer> getHighScores() {
		return highScores;
	}
	
	public void setHighScores(ArrayList<Integer> highScores) {
		this.highScores = highScores;
	}
	
}



