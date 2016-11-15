package edu.gvsu.scis.cis350;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.Test;

public class CharacterTest {
	
	// Test constructor
	@Test
	public void constructor() {
		new Character(100,100);
	}

	// Test moveCharacter when not jumping
	@Test
	public void moveCharacterNoJump() {
		Character c = new Character(100,100);
		int firstY = c.getYCoord();
		c.moveCharacter();
		int secondY = c.getYCoord();
		assertEquals(firstY, secondY);
	}
	
	// Test moveCharacter when jumping
	@Test
	public void moveCharacterWithJump() {
		Character c = new Character(100,100);
		int firstY = c.getYCoord();
		c.jump();
		c.moveCharacter();
		int secondY = c.getYCoord();
		assertNotEquals(firstY, secondY);
	}
	
	// Test setting height within bounds
	@Test
	public void testSetHeightWithinBounds() {
		Character c = new Character(1000,1000);
		int firstHeight = c.getHeight();
		c.setHeight(500);
		assertNotEquals(firstHeight, c.getHeight());
		assertEquals(c.getHeight(), 500);
	}
	
	// Test setting height outside bounds
	@Test
	public void testSetHeightOutsideBounds() {
		Character c = new Character(1000,1000);
		int firstHeight = c.getHeight();
		c.setHeight(2000);
		assertNotEquals(c.getHeight(), 2000);
		assertEquals(c.getHeight(), firstHeight);
		c.setHeight(-10);
		assertNotEquals(c.getHeight(), -10);
		assertEquals(c.getHeight(), firstHeight);
	}
	
	// Test setting width within bounds
	@Test
	public void testSetWidthWithinBounds() {
		Character c = new Character(1000,1000);
		int firstWidth = c.getWidth();
		c.setWidth(500);
		assertNotEquals(firstWidth, c.getWidth());
		assertEquals(c.getWidth(), 500);
	}
	
	// Test setting width outside bounds
	@Test
	public void testSetWidthOutsideBounds() {
		Character c = new Character(1000,1000);
		int firstWidth = c.getWidth();
		c.setHeight(2000);
		assertNotEquals(c.getWidth(), 2000);
		assertEquals(c.getWidth(), firstWidth);
		c.setHeight(-10);
		assertNotEquals(c.getWidth(), -10);
		assertEquals(c.getWidth(), firstWidth);
	}
	
	// Test setting y coordinate
	@Test
	public void testSettingY() {
		Character c = new Character(1000,1000);
		int firstY = c.getYCoord();
		c.setYCoord(400);
		assertNotEquals(firstY, c.getYCoord());
		c.setYCoord(999);
		assertEquals(999, c.getYCoord());
	}
	
	// Test setting x coordinate
	@Test
	public void testSettingX() {
		Character c = new Character(1000,1000);
		int firstX = c.getXCoord();
		c.setXCoord(100);
		assertEquals(c.getXCoord(), 100);
		assertNotEquals(firstX, c.getXCoord());
		c.setXCoord(999);
		assertEquals(c.getXCoord(), 999);
	}
	
	// Test setting image
	@Test
	public void testSettingImage() {
		Character c = new Character(1000,1000);
		Image firstImage = c.getImage();
		try {
			c.setImage(ImageIO.read(new File(
					"Graphics/Obstacles/obstacle.png")));
		} catch (IOException e) {
			System.out.println("Error");
		}
		assertNotEquals(firstImage.getWidth(null), c.getWidth());
		assertNotEquals(firstImage.getHeight(null), c.getHeight());
	}
	
	// Test moving character while falling
	@Test
	public void testMoveCharacterFalling() {
		Character c = new Character(1000,1000);
		c.setYCoord(10);
		c.jump();
		c.moveCharacter();
		assertTrue(10 < c.getYCoord());
	}
}
