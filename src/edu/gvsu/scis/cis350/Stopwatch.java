package edu.gvsu.scis.cis350;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.text.*;

public class Stopwatch extends JFrame
{
	JLabel time;

	static long startTime = System.currentTimeMillis();

	Stopwatch()
	{
		setSize(225, 75);
		setTitle("Time");
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new GridBagLayout());

		time = new JLabel("");

		time.setFont(new Font("", Font.BOLD, 36));

		time.setForeground(Color.BLACK);

		add(time);

		//starting new Thread which will update time
		new Thread(new Runnable() {
			public void run() { 
				try {
					updateTime(); 
				} 
				catch (Exception ie) {
					
				}
			}
		}).start();
	}
	
	/**
	 * 
	 */
	public void updateTime() {
		try { 
			while (true) { 
				
				time.setText(getTimeElapsed());
				Thread.currentThread();
				//Thread sleeping for .1 sec
				Thread.sleep(100);
		
			}
		}
		catch (Exception e) { 
			System.out.println("Exception in Thread Sleep : " + e);
		}
	}

	/**
	 * @return 
	 */
	public String getTimeElapsed() { 
		long elapsedTime = System.currentTimeMillis() - startTime;
		

		String milliseconds = Integer.toString((int) (elapsedTime % 60));
		elapsedTime = elapsedTime / 1000;
		String seconds = Integer.toString((int) (elapsedTime % 60));
		String minutes = Integer.toString((int) ((elapsedTime % 3600) / 60));
		String hours = Integer.toString((int) (elapsedTime / 3600));

		if (milliseconds.length() < 2) { 
			milliseconds = "0" + milliseconds;
		}
		
		if (seconds.length() < 2) { 
			seconds = "0" + seconds;
		}

		if (minutes.length() < 2) { 
			minutes = "0" + minutes;
		}
			

		if (hours.length() < 2) { 
			hours = "0" + hours;
		}

		return hours + ":" + minutes + ":" + seconds + "." + milliseconds;
	}

	/**
	 * @param endTime
	 * @return The total time difference between the startTime of the Stopwatch
	 * and the endTime.
	 */
	public static long CalculateTotalTime(long endTime) {
		return (endTime - startTime);
	}
	
	
//	public static void main(String[] args) {
//		JFrame obj = new Stopwatch();
//		obj.setVisible(true);
//	}
}