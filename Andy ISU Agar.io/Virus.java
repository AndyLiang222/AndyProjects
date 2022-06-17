package example;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;

public class Virus extends Entity{
	//virus
	static Image i;
	private int counter = 0;
	public Virus(int x, int y, int r) {
		super(x,y,r, i, true, (int)(r*r*3.14));
	}
	public static void initImage(Image i0) {
		i = i0;
	}
	public boolean isLimit() {
		if(counter == 7) {
			counter = 0;
			return true;
		}
		return false;
	}
	public void setVal(int val) {
		foodVal = val;
		r = (int)Math.sqrt(foodVal/3.14);
	}
	public void eat(int val) {
		foodVal += val;
		r = (int)Math.sqrt(foodVal/3.14);
		counter++;
	}
}
