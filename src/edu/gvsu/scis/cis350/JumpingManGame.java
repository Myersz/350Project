package edu.gvsu.scis.cis350;
import javax.swing.JFrame;

/**
 * Main method to run the Jumping Man game.
 * 
 * @author Kelsey
 *
 */
public final class JumpingManGame {

	/** Width for window. */
	static final int FRAME_WIDTH = 550;
	/** Height for window. */
	static final int FRAME_HEIGHT = 300;

	/**
	 * Default constructor.
	 */
	private JumpingManGame() {

	}

	/**
	 * Main method for game GUI.
	 * @param args Arguments
	 */
	public static void main(final String[] args) {
		try {			
			JFrame frame = new JFrame("Jumping Man");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			GamePanel panel = new GamePanel();

			frame.getContentPane().add(panel);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			frame.setResizable(false);
			
		} catch (MissingBackgroundException e) {
			return;
		}

		
	}
}