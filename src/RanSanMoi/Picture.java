package RanSanMoi;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Picture {
	
	public static Image Head1;
	public static Image Head2;
	public static Image Head3;
	public static Image Head4;
	public static Image Body;
	public static Image Food;
	public static Image Food1;
	public static Image Food2;
	public static Image Food3;
	public static FoodAnm FoodAnimation;
	public static Image BackGround;
	
	public static Image Menu;
	public static Image Play;
	public static Image Continue;
	public static Image Exit;
	public static Image Record;
	
	public static void loadImage(){
		try {
			Menu = ImageIO.read(new File("res/menu.png"));
			
			BackGround = ImageIO.read(new File("res/2.png"));
			Head1 = ImageIO.read(new File("res/head1.png"));
			Head3 = ImageIO.read(new File("res/head3.png"));
			Head2 = ImageIO.read(new File("res/head2.png"));
			Head4 = ImageIO.read(new File("res/head4.png"));
			Body = ImageIO.read(new File("res/body.png"));
			Food = ImageIO.read(new File("res/food.png"));
			Food1 = ImageIO.read(new File("res/food1.png"));
			Food2 = ImageIO.read(new File("res/food2.png"));
			Food3 = ImageIO.read(new File("res/food3.png"));
		}
		catch(Exception e) {
		}
	}
	public static void loadFoodAni(){
		FoodAnimation = new FoodAnm();
		FoodAnimation.addImage(Food1);
		FoodAnimation.addImage(Food);
		FoodAnimation.addImage(Food3);
		FoodAnimation.addImage(Food2);
		FoodAnimation.addImage(Food3);
		FoodAnimation.addImage(Food);
	}
}
