import javax.swing.*;

public class JumpingManGame {

	public static void main (String[] args) {
		JFrame frame = new JFrame ("Jumping Man");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		GamePanel panel = new GamePanel();
		frame.getContentPane().add(panel);
		frame.setSize(600, 700);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}