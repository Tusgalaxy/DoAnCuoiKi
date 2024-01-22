package RanSanMoi;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JDialog;


public class Frame extends JFrame{
	Screen screen;
	
	public Menu menu;
	
	public Frame() {
		setSize(795, 596);
		setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		screen = new Screen();
		add(screen);
		
		this.addKeyListener(new Control());
		
		setResizable(false);
		setVisible(true);
		
		menu = new Menu(this);
		menu.setVisible(true);
		
		
	}
	
	public void startGame(String playerName) {
		Screen.isOver = false;
        Screen.isPlaying = true;
        screen.snake.Reset();
        Screen.PlayerName = playerName;
        menu.setVisible(false);
	}
	
	public void resumeGame() {
        if (Screen.isGamePaused()) {
            menu.setContinueButtonEnabled(false);
            Screen.setGamePaused(false);
            menu.setVisible(false);
            Screen.isPlaying = true;
        }
    }
	
	public static void main(String[] args) {
		Frame frame = new Frame();
	}
	private class Control implements KeyListener{
		Snake snake;
		
		public void keyTyped(KeyEvent e) {}

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				if(Screen.isPlaying) {
					Screen.isPlaying = !Screen.isPlaying;
					Screen.setGamePaused(true);
					menu.setContinueButtonEnabled(true);
					menu.setStartButtonEnabled(true);
					menu.setExitButtonEnabled(true);
					menu.setVisible(true);
						
				}
				else if(Screen.isGamePaused()){
					Screen.setGamePaused(false);
					menu.setContinueButtonEnabled(false);
					menu.setStartButtonEnabled(true);
					menu.setExitButtonEnabled(true);
					menu.setVisible(false);
				}
				else {
					Screen.setGamePaused(true);
					menu.setContinueButtonEnabled(true);
					menu.setStartButtonEnabled(true);
					menu.setExitButtonEnabled(true);
					menu.setVisible(true);
				}
				
			}
			
		
			else if(Screen.isOver) {
				
				savePlayerRecord(Screen.PlayerName, Screen.currentScore);
				Screen.isOver = false;
				Screen.isPlaying = true;
				screen.snake.Reset();
				
				}
				
			
			
				if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP) {
					screen.snake.setVector(Snake.GO_UP);
				}
				if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT) {
					screen.snake.setVector(Snake.GO_RIGHT);
				}
				if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN) {
					screen.snake.setVector(Snake.GO_DOWN);
				}
				if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT) {
					screen.snake.setVector(Snake.GO_LEFT);
				}
			
		}
			
		public void keyReleased(KeyEvent e) {}
		private static final String url = "jdbc:mysql://127.0.0.1:3306/record";
	    private static final String userName = "TU";
	    private static final String passWord = "ax00@1cm";
	    
	    private void savePlayerRecord(String playerName, int score) {
	    	
	    	try(Connection connection = DriverManager.getConnection(url, userName, passWord)){
	    		String query = "INSERT INTO record(name, score) VALUES (?, ?)";
	    		try(PreparedStatement pre = connection.prepareStatement(query)){
	    			pre.setString(1, playerName);
	    			pre.setInt(2, score);
	    			pre.executeUpdate();
	    		}
	    	}
	    	catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    		
	    }
		  
	}
}
