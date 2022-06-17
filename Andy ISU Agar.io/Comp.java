package example;

import java.awt.Image;

public class Comp {
	private int x,  y,width, height;
	private Player p;
	private Image img;
	public Comp(int x, int y, int w, int h, Image img, Player p) {
		this.x = x+p.getRX();
		this.y = y+p.getRY();
		width = w;
		height = h;
		this.img = img;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return width;
	}
	public int getH() {
		return height;
	}
	public Image getImage() {
		return img;
	}
	//check if the button is clicked
	boolean check (int x1, int y1) {
		
		
		//System.out.println("Click location: " + x1 + " Lowest y: " + this.x + " Highest y: " + (x+this.width));
		if (x1 >= this.x && x1 <=this.x+this.width && y1 >= this.y && y1 <= y+this.height) {return true;}
		else return false;
	}
}
