package edu.gvsu.scis.cis350;
import javax.swing.JFrame;
import java.awt.Component;

/**
 * Main method to run the Jumping Man game.
 * 
 * @author Kelsey
 *
 */
public final class JumpingManGame extends JFrame {

	/** Width for window. */
	static final int FRAME_WIDTH = 1000;
	/** Height for window. */
	static final int FRAME_HEIGHT = 800;

	/**
	 * Default constructor.
	 */
	private JumpingManGame() {
		super("Jumping Man");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		ScrollingBackground back = new ScrollingBackground();
		((Component)back).setFocusable(true);
		getContentPane().add(back);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Main method for game GUI.
	 * @param args Arguments
	 */
	public static void main(final String[] args) {
		new GamePanel();
	}
}