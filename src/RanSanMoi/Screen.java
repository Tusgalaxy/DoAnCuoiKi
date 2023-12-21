package RanSanMoi;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{
	static int map[][] = new int [50][50];
	Snake snake;
	Thread thread;
	public Screen() {
		snake = new Snake();
		map[13][10]=5;
		thread = new Thread(this);
		thread.start();
	}
	public void run() {
		while(true) {
			snake.move();
			repaint();
			try {
				thread.sleep(1);
			}
			catch (InterruptedException ex) {}
		}
	}
	public void paintMap(Graphics g) {
		g.setColor(Color.white);
		for(int i=0; i<39; i++)
			for(int j=0; j<28; j++) {
				g.fillRect(i*20+1, j*20+1, 19, 19);
				if(map[i][j]==5) {
					g.setColor(Color.yellow);
					g.fillRect(i*20+1, j*20+1, 19, 19);
					g.setColor(Color.white);
				}
			}
	}
	public void paint(Graphics g) {
		paintMap(g);
		snake.drawSnake(g);
	}
}
