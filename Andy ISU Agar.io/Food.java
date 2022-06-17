package example;

import java.awt.Image;
import java.awt.Toolkit;
/**
 * basic entity class as stationary  entities that can be eaten
 * @author andyl
 *
 */
public class Food extends Entity{
	static Image[] imgs;
	
	private int idx = 0;
	
	static int nC;
	public Food(int x, int y, int col, int r, int fV, int i) {
		//Initialize the entity
		super(x, y, r, imgs[col],true, fV);
		idx = i;
		vX = 0;
		vY = 0;
		isFood = true;
		// TODO Auto-generated constructor stub
	}
	//create images for colored foods
	static void initColors(int nC) {
		imgs = new Image[nC];
		for(int i = 0;i<nC;i++) {
			imgs[i] = Toolkit.getDefaultToolkit().getImage("Foods/c"+i+".png");
		}
	}
	//getters and setters
	public int getIdx() {
		return idx;
	}
	public void setIdx(int i) {
		idx = i;
	}
}
