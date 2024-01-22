package RanSanMoi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Snake {
	int dodai = 2;
	int speed = 250;
	int length = 4;
	
	int x[];
	int y[];
	
	public static int GO_UP = 1;
	public static int GO_DOWN = -1;
	public static int GO_LEFT = 2;
	public static int GO_RIGHT = -2;
	
	int vector = Snake.GO_DOWN;
	
	long t1 = 0;
	
	boolean checkVector = true;
	
	public Snake() {
		x = new int[28*39];
		y = new int[28*39];
		x[0] = 5;
		y[0] = 4;
		x[1] = 5;
		y[1] = 3;
	}
	
	public boolean checkFoodLocation(int a, int b) {
		for(int i=0; i<dodai; i++) {
			if(x[i]==a && y[i]==b) {
				return true;
			}
		}
		return (a == 0 && b < 8) || (a < 8 && b == 0) || (a >= 9 && a <= 29 && (b == 13 || b == 14))
		        || (a == 38 && b < 8) || (a > 30 && b == 38) || (a == 0 && b > 19) || (a < 8 && b == 27)
		        || (a == 38 && b > 19) || (a > 30 && b == 27);
	}
	
	public Point Food(){
		Random random = new Random();
		int foodX, foodY;
		do {
			foodX = random.nextInt(38);
			foodY = random.nextInt(27);
		}while(checkFoodLocation(foodX, foodY));
		
		return new Point(foodX, foodY);
	}
	
	public void Reset() {
		
		dodai = 2;
		speed = 250;
		
		x = new int[28*39];
		y = new int[28*39];
		
		x[0] = 5;
		y[0] = 4;
		x[1] = 5;
		y[1] = 3;
		
		Screen.currentScore = 0;
		
		vector = Snake.GO_DOWN;
		
	}
	
	public void setVector(int v) {
		if(vector != -v && checkVector)
		vector = v;
		checkVector = false;
	}
	
	public void move() {
		
		for(int i=1; i<dodai; i++) {
			if(x[0]==x[i]&&y[0]==y[i]) {
				Screen.isPlaying = false;
				Screen.isOver = true;
			}
		}
		for(int i=0; i<8; i++) {
			if(x[0]==0 && y[0]==i) {
				Screen.isPlaying = false;
				Screen.isOver = true;
			}
			else if(x[0]==i && y[0]==0) {
				Screen.isPlaying = false;
				Screen.isOver = true;
			}
		}
		for(int i=0; i<8; i++) {
			if(x[0]==38 && y[0]==i) {
				Screen.isPlaying = false;
				Screen.isOver = true;
			}
		}
		for(int i=38; i>=31; i--) {
			if(x[0]==i && y[0]==0) {
				Screen.isPlaying = false;
				Screen.isOver = true;
			}
		}
		for(int i=27; i>=20; i--) {
			if(x[0]==38 && y[0]==i) {
				Screen.isPlaying = false;
				Screen.isOver = true;
			}
		}
		for(int i=38; i>=31; i--) {
			if(x[0]==i && y[0]==27) {
				Screen.isPlaying = false;
				Screen.isOver = true;
			}
		}
		for(int i=0; i<8; i++) {
			if(x[0]==i && y[0]==27) {
				Screen.isPlaying = false;
				Screen.isOver = true;
			}
		}
		for(int i=27; i>=20; i--) {
			if(x[0]==0 && y[0]==i) {
				Screen.isPlaying = false;
				Screen.isOver = true;
			}
		}
		for(int i=9; i<=29; i++) {
			if(x[0]==i && y[0]==13 || x[0]==i && y[0]==14) {
				Screen.isPlaying = false;
				Screen.isOver = true;
				
			}
		}
		if(System.currentTimeMillis()-t1>speed) {
			checkVector = true;
			if(Screen.map[x[0]][y[0]]==5) {
				dodai++;
				Screen.map[x[0]][y[0]]=0;
				Point newFood = Food();
				Screen.map[newFood.x][newFood.y]=5;
				Screen.Score+=100;
				Screen.currentScore=Screen.Score;
				if(length == dodai) {
					speed = speed-10;
					length =length + 3;
					if(speed<50)
						speed = speed+10;
				}
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

		for(int i=1; i<dodai; i++) {
			g.drawImage(Picture.Body, x[i]*20+1, y[i]*20+1, null);
		}			
		if(vector == Snake.GO_UP)
			g.drawImage(Picture.Head3, x[0]*20-4, y[0]*20-4, null);
		if(vector == Snake.GO_DOWN)
			g.drawImage(Picture.Head1, x[0]*20-4, y[0]*20-4, null);;
		if(vector == Snake.GO_LEFT)
			g.drawImage(Picture.Head2, x[0]*20-4, y[0]*20-4, null);;
		if(vector == Snake.GO_RIGHT)
			g.drawImage(Picture.Head4, x[0]*20-4, y[0]*20-4, null);;
	}
}
