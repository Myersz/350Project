import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Main GUI window for the game.
 * 
 * @author Kelsey
 *
 */
public class GamePanel extends JPanel {

	JPanel gameDisplay = new JPanel();
	
	/**
	 * Default constructor for GamePanel.
	 * */
	public GamePanel() {
		gameDisplay.setPreferredSize(new Dimension(500,200));
		gameDisplay.setBackground(Color.BLACK);
	
		this.setPreferredSize(new Dimension(550,300));
		this.add(gameDisplay);
	}
	
}
