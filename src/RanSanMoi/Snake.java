package RanSanMoi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Snake {
	int dodai = 1;
	int x[];
	int y[];
	public static int GO_UP = 1;
	public static int GO_DOWN = -1;
	public static int GO_LEFT = 2;
	public static int GO_RIGHT = -2;
	int vector = Snake.GO_RIGHT;
	long t1 = 0;
	public Snake() {
		x = new int[28*39];
		y = new int[28*39];
		x[0] = 5;
		y[0] = 4;
	}
	public Point Food(){
		Random random = new Random();
		int x = random.nextInt(38);
		int y = random.nextInt(27);
		return new Point(x, y);
	}
	public void setVector(int v) {
		if(vector != -v)
		vector = v;
	}
	public void move() {
		if(System.currentTimeMillis()-t1>100) {
			if(Screen.map[x[0]][y[0]]==5) {
				dodai++;
				Screen.map[x[0]][y[0]]=0;
				Screen.map[Food().x][Food().y]=5;
			}
			for(int i=dodai; i>0; i--) {
				x[i]=x[i-1];
				y[i]=y[i-1];
			}
			if(vector == Snake.GO_UP)
				y[0]--;
			if(vector == Snake.GO_DOWN)
				y[0]++;
			if(vector == Snake.GO_LEFT)
				x[0]--;
			if(vector == Snake.GO_RIGHT)
				x[0]++;
			if (x[0] == 39)
				x[0] = 0;
			if (x[0] == -1)
				x[0] = 38;
			if (y[0] == 28)
				y[0] = 0;
			if (y[0] == -1)
				y[0] = 27;
			t1 = System.currentTimeMillis();
		}
	}
	public void drawSnake(Graphics g) {

		g.setColor(Color.red);
		for(int i=0; i<dodai; i++) {
			g.fillRect(x[i]*20+1, y[i]*20+1, 19, 19);			
		}
	}
}
