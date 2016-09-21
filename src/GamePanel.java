import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Main GUI window for the game.
 * 
 * @author Kelsey
 *
 */
public class GamePanel extends JPanel {

	JPanel gameDisplay = new JPanel();
	Timer updateScene;

	/**
	 * Default constructor for GamePanel.
	 * */
	public GamePanel() {
		gameDisplay.setPreferredSize(new Dimension(500,200));
		gameDisplay.setBackground(Color.BLACK);
		gameDisplay.paintComponent(null);

		this.setPreferredSize(new Dimension(550,300));
		this.add(gameDisplay);
	}

	private Component createContent() {
		@Override
		protected void paintComponent(Graphics g) {
			final Image background1 = requestImage();

			super.paintComponent(g);
			g.drawImage(background1, 0, 0, null);
		}
	}

	private Image requestImage() {
		Image image = null;

		try {
			image = ImageIO.read(new URL("https://github.com/NeonYazzle/350Project/blob/master/background%201.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	private void animateBackground() {
		updateScene = new Timer(1000, new TimerListener());

	}

	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
