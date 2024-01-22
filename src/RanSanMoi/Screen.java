package RanSanMoi;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JPanel;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

public class Screen extends JPanel implements Runnable{
	static int map[][] = new int [50][50];
	static boolean isPlaying = false;
	static boolean isOver = false;
	
	static String PlayerName = "";
	
	private Menu menu; 
	
	Snake snake;
	Thread thread;
	
	static int Score = 0;
	static int currentScore = 0;
	
	public Screen() {
		snake = new Snake();
		Picture.loadImage();
		Picture.loadFoodAni();
		map[10][8]=5;
		thread = new Thread(this);
		thread.start();
	}
	public void run(){
		long t = 0;
		while(true){
			if(isPlaying) {
				if(System.currentTimeMillis()-t>150) {
					Picture.FoodAnimation.update();
					t = System.currentTimeMillis();
				}
				snake.move();
			}				
			repaint();
			try {
				thread.sleep(1);
			}
			catch (InterruptedException ex) {}
		}
	}
	public void paintMap(Graphics g) {
		g.drawImage(Picture.BackGround, 0, 0, getWidth(), getHeight(), this);
		for(int i=0; i<39; i++)
			for(int j=0; j<28; j++) {
				if(map[i][j]==5) {
					g.drawImage(Picture.FoodAnimation.getCurrentImage(), i*20-1, j*20-1, null);
				}
			}
	}
	public void paint(Graphics g) {
		paintMap(g);
		snake.drawSnake(g);
		
		if(isOver) {
			
			g.setColor(Color.black);
			g.setFont((g.getFont().deriveFont(40.0f)));
			g.drawString("Game over!", 290, 120);
			g.setFont((g.getFont().deriveFont(20.0f)));
			g.drawString("Press ↑↓← → to play again", 285, 170);
			Screen.Score = 0;
			
		}
		
		FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
        int playerNameWidth = fontMetrics.stringWidth("Player: " + PlayerName);
        int scoreWidth = fontMetrics.stringWidth("Score: " + Score);

        int playerNameX = (getWidth() - playerNameWidth) / 2;
        int scoreX = (getWidth() - scoreWidth) / 2;
		
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(15.0f));
		g.drawString("Player: "+PlayerName, playerNameX, 13);
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(15.0f));
		g.drawString("Score: "+Score, scoreX, 28);
	}
	private static boolean isGamePaused = false;

    public static boolean isGamePaused() {
        return isGamePaused;
    }

    public static void setGamePaused(boolean paused) {
        isGamePaused = paused;
    }
    
    
}
