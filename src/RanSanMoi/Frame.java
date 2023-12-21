package RanSanMoi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Frame extends JFrame{
	Screen screen;
	public Frame() {
		setLocation(375, 100);
		setSize(795, 596);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		screen = new Screen();
		add(screen);
		this.addKeyListener(new Control());
		setVisible(true);
	}
	public static void main(String[] args) {
		Frame frame = new Frame();	
	}
	private class Control implements KeyListener{
		Snake snake;
		
		public void keyTyped(KeyEvent e) {}

		public void keyPressed(KeyEvent e) {
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
	}
}
