package edu.gvsu.scis.cis350;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scoreboard extends JPanel{
	
	ArrayList<Integer> highScores = new ArrayList<Integer>(10);
	String filename;
	/** Label for the Scoreboard */
	private JLabel ScoreBoardLabel;
	
	public Scoreboard(String nfile) {
		this.filename = nfile;
		printScoresToLabel();
		getDataFromFile();
	}
	
	private void getDataFromFile() {
		Scanner fileIn = new Scanner(this.filename);
		while(fileIn.hasNextLine()) {
			highScores.add(fileIn.nextInt());
		}
		while(highScores.size() < 10) {
			highScores.add(0);
		}
	}
	
	public ArrayList<Integer> updateScores(int score) {
		highScores.add(score);
		Collections.sort(highScores);
		Collections.reverse(highScores);
		highScores.remove(10);
		sendDataToFile();
		printScoresToLabel();
		return highScores;
	}
	
	public void sendDataToFile() {
		try{
		    PrintWriter writer = new PrintWriter(this.filename, "UTF-8");
		    for(int i = 0; i < 10; i++){
		    	writer.println(highScores.get(i));
		    }
		    writer.close();
		} catch(Exception e) { }
	}
	
	public void printScoresToTerminal() {
		for(int i = 0; i < 10; i++){
	    	System.out.println("#" + i + ":" + highScores.get(i));
	    }
	}
	
	public void printScoresToLabel() {
		String str = "";
		for(int i = 0; i < 10; i++){
	    	str += " #" + i + ":" + highScores.get(i);
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



