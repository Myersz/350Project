package edu.gvsu.scis.cis350;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Scoreboard {
	
	ArrayList<Integer> highScores = new ArrayList<Integer>();
	String filename;
	
	public Scoreboard(String nfile) {
		this.filename = nfile;
		getDataFromFile();
	}
	
	private void getDataFromFile() {
		Scanner fileIn = new Scanner(this.filename);
		while(fileIn.hasNextLine()) {
			highScores.add(fileIn.nextInt());
		}
	}
	
	public ArrayList<Integer> updateScores(int score) {
		highScores.add(score);
		Collections.sort(highScores);
		Collections.reverse(highScores);
		highScores.remove(10);
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
	    	System.out.println(highScores.get(i));
	    }
	}
	
	public ArrayList<Integer> getHighScores() {
		return highScores;
	}
	
	public void setHighScores(ArrayList<Integer> highScores) {
		this.highScores = highScores;
	}
}
