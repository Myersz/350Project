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
	
	// Test obstacle panel constructor
	@Test
	public void obstacleConstructorTest() throws MissingBackgroundException {
		ScrollingBackground bg = new ScrollingBackground();
		ObstaclePanel op = new ObstaclePanel(bg);
		op.addObstacle();
		
		assertEquals(op.getXCoord(), bg.getWidth());
		assertEquals(op.getYCoord(), bg.getHeight() - op.getObstacleHeight());
		
	}
	
	// Test isOnScreen method
	@Test
	public void testIsOnScreen() throws MissingBackgroundException {
		ScrollingBackground bg = new ScrollingBackground();
		ObstaclePanel op = new ObstaclePanel(bg);
		
		op.setXCoord(15);
		assertEquals(op.isOnScreen(), true);
		op.setXCoord(-1000);
		assertEquals(op.isOnScreen(), false);
	}
	
	// Test move obstacle
	@Test
	public void testMoveObstacle() throws MissingBackgroundException {
		ScrollingBackground bg = new ScrollingBackground();
		ObstaclePanel op = new ObstaclePanel(bg);
		
		op.setXCoord(100);
		op.moveObstacle();
		assertEquals(op.getXCoord(), 100 - ObstaclePanel.DISTANCE_TO_MOVE);
		
		op.setXCoord(-1000);
		op.moveObstacle();
		assertEquals(op.getObstacleExists(), false);
	}
	
	// Test adding obstacle
	@Test
	public void testAddObstacle() throws MissingBackgroundException {
		ScrollingBackground bg = new ScrollingBackground();
		ObstaclePanel op = new ObstaclePanel(bg);
		
		op.addObstacle();
		assertEquals(op.getXCoord(), bg.getWidth());
		assertEquals(op.getYCoord(), bg.getHeight() - op.getObstacleHeight());
		assertEquals(op.getObstacleExists(), true);
	}
	
	// Test counting time
	@Test
	public void testCountTime() throws MissingBackgroundException {
		ScrollingBackground bg = new ScrollingBackground();
		ObstaclePanel op = new ObstaclePanel(bg);
		
		GameControl.setScrolling(true);
		op.countTime();
		assertEquals(op.getTime(), 1);
		
		op.countTime();
		op.countTime();
		op.countTime();
		op.countTime();

		assertEquals(op.getObstacleExists(), true);
	}
	
	// Test pausing and resuming scrolling
	@Test
	public void testPauseResumeScrolling() throws MissingBackgroundException {
		ScrollingBackground bg = new ScrollingBackground();
		ObstaclePanel op = new ObstaclePanel(bg);
		
		GameControl.setScrolling(true);
		bg.pauseScrolling();
		assertEquals(GameControl.getScrolling(), false);
		bg.resumeScrolling();
		op.resumeMoving();
		assertEquals(GameControl.getScrolling(), true);
	}
	
	@Test
	// Test launching full game
	public void testGame() throws MissingBackgroundException {
		new JumpingManGame();
	}
	

}
