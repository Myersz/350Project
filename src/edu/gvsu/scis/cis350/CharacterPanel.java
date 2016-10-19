package edu.gvsu.scis.cis350;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Displays an obstacle moving across the game screen.
 * @author Katie, Zach
 *
 */
public class CharacterPanel extends JPanel {

	private static final long serialVersionUID = 0;
	
	/** X-coordinate for obstacle. */
	private int x;
	
	/** Y-coordinate for character. */
	private int y;
	
	/** Width of background area. */
	private int bgWidth;
	
	/** Height of background area. */
	private int bgHeight;
	
//	/** Size of obstacle. */
//	static final int CHARACTER_SIZE = 0;
	
	/** Image for character. */
    private Image image;
    
    /** Count for keeping track of seconds for jump. */
    int count = 0;

	/** Timer to track running time of game. */
	private Timer timer;
	
	/** Flag for jumping. */
	private Boolean isJumping;
	
	/** Flag for falling. */
	private Boolean isFalling;
	
	/** Height of the character */
	private int characterHeight;

	/**
	 * Constructor to set up new obstacle panel and initial positions.
	 * @param bg the background image to set sizes
	 */
	public CharacterPanel(final ScrollingBackground bg) {

		bgWidth = bg.getWidth();
		bgHeight = bg.getHeight();
		
        ImageIcon ii = new ImageIcon("Graphics/Characters/yesclearbackground.gif");
        image = ii.getImage();
        
        characterHeight = image.getHeight(null);
        
        x = -50;
        y = bgHeight - characterHeight +60;

    	timer = new Timer(20, characterPerformer);
    	timer.start();
    	
    	isJumping = false;
    	isFalling = false;
    	
		this.setOpaque(false);
		this.setDoubleBuffered(true);
		this.repaint();
		setVisible(true);
	}

	public final Image getImage(){
		return image;
	}
	
	@Override
	public final void paintComponent(final Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(getImage(), x, y, null);  
	}
	
	public final void jump() {
		if(!isJumping && !isFalling){
			isJumping = true;
		}
	}
	
	/**
	 * Check the bounds of character and obstacle and see if they have collided.
	 */
	private void haveCollided() {
		int obstacleLeftX;
		int obstacleRightX;
		int obstacleY;
		
		int characterLeftX;
		int characterRightX;
		int characterY;
		
		while (GameControl.getScrolling()) {
			
			obstacleLeftX = ObstaclePanel.getXCoord();
			obstacleRightX = ObstaclePanel.getXCoord() 
					+ ObstaclePanel.getObstacleWidth();
			obstacleY = ObstaclePanel.getYCoord();
			
			characterLeftX = this.x;
			characterRightX = this.x + image.getWidth(null);
			characterY = this.y - 60;
			
			if (obstacleY < characterY && obstacleLeftX < characterLeftX 
					&& obstacleRightX > characterLeftX) {
				
				GameControl.setGameLost();
			}
		}

	}
	
	ActionListener characterPerformer = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			if(isJumping) {
				if(y > bgHeight - characterHeight -170 ) {
					System.out.println("We are here, we are here, we are HERE");
					y = y - 10;
					repaint();
				}
				if(y <= bgHeight - characterHeight -160 ){ 
					isFalling=true;
					isJumping=false;
					//System.out.println("here!!!!!!");
				}

			}
			if(isFalling) {
				if(y<bgHeight){
					y = y + 10;
					repaint();
				}
				if(y>=bgHeight-characterHeight+60){
					isFalling = false;
					isJumping = false;
				}
			}
		}
	};
	

}
