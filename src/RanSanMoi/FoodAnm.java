package RanSanMoi;

import java.awt.Image;

public class FoodAnm {
	public Image image[];
	public int n;
	public int currentImage;
	public FoodAnm() {
		n = 0;
		currentImage = 0;
	}
	public void addImage(Image image2) {
		Image arr[] = image;
		image = new Image[n+1];
		for(int i=0; i<n; i++)
			image[i] = arr[i];
		image[n] = image2;
		n++;
	}
	public void update() {
		currentImage++;
		if(currentImage>=n)
			currentImage=0;
	}
	public Image getCurrentImage() {
		return image[currentImage];
	}
}
