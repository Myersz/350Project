package edu.gvsu.scis.cis350;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * Manages background of game.
 * 
 * Note that background images should be named in such a way 
 * that they will be ordered numerically in the file system,
 * i.e, "background1, background2" or "1_trees, "2_moretrees"
 * 
 * They also must be .png files.
 * 
 * @author Kelsey
 *
 */
public class GameBackground {

	/** An array containing all the possible backgrounds for the game. */
	private ArrayList<ImageIcon> backgrounds;
	/** The current background being displayed in the game. */
	private ImageIcon currentBackground;
	/** Boolean to indiciate if background is being animated. */
	private boolean isAnimated = false;

	/**
	 * Constructor for GameBackground class. Loads all the images in the 
	 * Backgrounds folder into the ArrayList.
	 * @throws MissingBackgroundException when background images fail to load.
	 */
	public GameBackground() throws MissingBackgroundException {
		backgrounds = new ArrayList<ImageIcon>();
		final File folder = new File("Graphics/Backgrounds/");

		final File[] files = folder.listFiles();

		// Ensure that there are some image files
		if (files != null) { 
			for (final File image : files) {
				String imageName = image.toString();
				if (imageName.endsWith(".png")) {
					backgrounds.add(new ImageIcon(image.getPath()));	
				}	
			}
		} else {
			throw new MissingBackgroundException();
		}

		if (backgrounds.size() > 0) {
			currentBackground = backgrounds.get(0);
		} else {
			currentBackground = null;
			throw new MissingBackgroundException();
		}
	}

	/**
	 * Returns the position in the array of the background.
	 * @param image The image we want to know the index of
	 * @return The index of that image in the ArrayList
	 */
	public final int currentBgPos(final ImageIcon image) {
		return backgrounds.indexOf(image);
	}

	/**
	 * Returns the current background.
	 * @return the background that is currently displayed
	 */
	public final ImageIcon getCurrentBackground() {
		return currentBackground;
	}

	/**
	 * Returns the ArrayList of background ImageIcons.
	 * @return the ArrayList of ImageIcons
	 */
	public final ArrayList<ImageIcon> getBackgrounds() {
		return backgrounds;
	}

	/**
	 * Returns the next background to be displayed in the game.
	 * @param currentBg The position in the ArrayList of the current background.
	 * @return The next background in the ArrayList, or the first background 
	 * if the current background is the last in the ArrayList.
	 */
	private ImageIcon getNextBackground(final int currentBg) {
		if (currentBg == backgrounds.size() - 1) {
			return backgrounds.get(0);
		} else {
			return backgrounds.get(currentBg + 1);
		}
	}

	/**
	 * Sets the background to the next background in the ArrayList.
	 */
	public final void setBackground() {
		this.currentBackground = this.getNextBackground(
				this.currentBgPos(currentBackground));
	}

	/**
	 * Sets the current background to the given image.
	 * @param image The new background image.
	 */
	public final void setBackground(final ImageIcon image) {
		this.currentBackground = image;
	}

	/**
	 * Returns true if background is animated and false otherwise.
	 * @return whether or not the background is animated.
	 */
	public final boolean isAnimated() {
		return isAnimated;
	}

	/**
	 * Sets the state of the isAnimated boolean variable.
	 * @param newVal The new state of the boolean variable
	 */
	public final void setIsAnimated(final boolean newVal) {
		isAnimated = newVal;
	}
}
