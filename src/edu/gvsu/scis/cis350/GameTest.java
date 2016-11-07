package edu.gvsu.scis.cis350;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest {

	// Test gameBg getting and setting speed
	@Test
	public void gameBgSetGetSpeedTest() throws MissingBackgroundException {
		GameBackground bg = new GameBackground();
		bg.setSpeed(10);
		assertEquals(bg.getSpeed(), 10);
		bg.setSpeed(20);
		assertEquals(bg.getSpeed(), 20);
		bg.setSpeed(0);
		assertEquals(bg.getSpeed(), 0);
	}
	
	// Test gameBg getting and setting X coordinate
	@Test
	public void gameBgGetSetXTest() throws MissingBackgroundException {
		GameBackground bg = new GameBackground();
		bg.setX(0);
		assertEquals(bg.getX(), 0);
		bg.setX(100);
		assertEquals(bg.getX(), 100);
	}
	
	// Test gameBg getting Y coordinate
	@Test
	public void gameBgGetYTest() throws MissingBackgroundException {
		GameBackground bg = new GameBackground();
		assertEquals(bg.getY(), 0);
	}
	
	// Test gameBg constructor with parameters
	@Test
	public void gameBgConstructorTest1() throws MissingBackgroundException {
		GameBackground bg = new GameBackground(0,10);
		assertEquals(bg.getX(), 0);
		assertEquals(bg.getY(), 10);
		assertEquals(bg.getSpeed(), GameBackground.INITIAL_SPEED);
	}
	
	// Test gameBg constructor without parameters
	@Test
	public void gamBgConstructorTest2() throws MissingBackgroundException {
		GameBackground bg = new GameBackground();
		assertEquals(bg.getX(), 0);
		assertEquals(bg.getY(), 0);
		assertEquals(bg.getSpeed(), GameBackground.INITIAL_SPEED);
	}
	
	

}
