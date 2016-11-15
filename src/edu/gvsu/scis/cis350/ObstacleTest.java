package edu.gvsu.scis.cis350;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ObstacleTest {

	// Test constructor
	@Test
	public void testConstructor() {
		Obstacle o = new Obstacle(100, 100);
		assertEquals(100, o.getXCoord());
	}

	// Test moveObstacle when on screen
	@Test
	public void testMoveObstacle() {
		Obstacle o = new Obstacle(100,100);
		int firstX = o.getXCoord();
		o.moveObstacle();
		int secondX = o.getXCoord();
		assertNotEquals(firstX, secondX);
	}

	// Test moveObstacle when not on screen
	@Test
	public void testMoveObstacleNotOnScreen() {
		Obstacle o = new Obstacle(100,100);
		o.setXCoord(o.getXCoord() + o.getObstacleWidth() - 100);
		int firstX = o.getXCoord();
		o.moveObstacle();
		int secondX = o.getXCoord();
		assertEquals(firstX, secondX);
	}

	// Test setting height of obstacle when it's within bounds
	@Test
	public void setObstHeightNoProblem() {
		Obstacle o = new Obstacle(100,100);
		int firstHeight = o.getObstacleHeight();
		o.setObstacleHeight(50);
		int secondHeight = o.getObstacleHeight();
		assertNotEquals(firstHeight, secondHeight);
		assertEquals(secondHeight, 50);
	}

	// Test setting height when it's outside bounds
	@Test
	public void setObstHeightOutsideBounds() {
		Obstacle o = new Obstacle(100,100);
		int firstHeight = o.getObstacleHeight();
		o.setObstacleHeight(150);
		assertEquals(firstHeight, o.getObstacleHeight());
		assertNotEquals(150, o.getObstacleHeight());
		o.setObstacleHeight(-10);
		assertEquals(firstHeight, o.getObstacleHeight());
		assertNotEquals(-10, o.getObstacleHeight());
	}

	// Test setting width when it's within bounds
	@Test
	public void setObstWidthNoProblem() {
		Obstacle o = new Obstacle(100,100);
		int firstWidth = o.getObstacleWidth();
		o.setObstacleWidth(50);
		int secondWidth = o.getObstacleWidth();
		assertNotEquals(firstWidth, secondWidth);
		assertEquals(secondWidth, 50);
	}

	// Test setting width when it's outside bounds
	@Test
	public void setObstWidthOutsideBounds() {
		Obstacle o = new Obstacle(100,100);
		int firstWidth = o.getObstacleWidth();
		o.setObstacleWidth(150);
		assertEquals(firstWidth, o.getObstacleWidth());
		assertNotEquals(150, o.getObstacleWidth());
		o.setObstacleWidth(-10);
		assertEquals(firstWidth, o.getObstacleWidth());
		assertNotEquals(-10, o.getObstacleWidth());
	}
	
	// Test setting y coordinate
	@Test
	public void testSettingY() {
		Obstacle o = new Obstacle(100,100);
		int firstY = o.getYCoord();
		o.setYCoord(95);
		int secondY = o.getYCoord();
		assertEquals(95, secondY);
		assertNotEquals(firstY, secondY);
	}
	
	// Test changing image
	@Test
	public void testImage() {
		Obstacle o = new Obstacle(100,100);
		Image firstImage = o.getImage();
		try {
			o.setImage(ImageIO.read(new File(
						"Graphics/Characters/yesclearbackground.gif")));
		} catch (IOException e) {
			System.out.println("Error");
		}
		assertNotEquals(firstImage.getHeight(null), o.getImage().getHeight(null));
		assertNotEquals(firstImage.getWidth(null), o.getImage().getWidth(null));
	}
}
