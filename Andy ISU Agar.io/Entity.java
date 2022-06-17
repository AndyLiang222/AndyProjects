package example;

import java.awt.Image;
/*
 * Parent class of objects in the game
 */
public class Entity implements Comparable<Entity>{
	//values
	protected int x,y, r, foodVal;
	//friction
	static double friction = 0.75;
	//size of the plane
	static int width, length;
	//velocity
	protected double vX, vY;
	protected Image img;
	protected boolean edible;
	protected boolean isFood;
	private boolean alive;
	//if the blob is a bot or a split
	private boolean Ai;
	public Entity(int x, int y, int r, Image i, boolean e, int fV) {
		this.r = r;
		this.x = x;
		this.y = y;
		img = i;
		edible = e;
		foodVal = fV;
		alive = true;
	}
	//getters and setters
	public void kill() {
		alive = false;
	}
	public boolean getStatus() {
		return alive;
	}
	boolean isAi() {
		return Ai;
	}
	void setAi(boolean b) {
		Ai = b;
	}
	public void setVel(double xV, double yV) {
		vX+= xV;
		vY += yV;
	}
	/**
	 * based on the velocity, move the object
	 * @return whether the entity is no longer moving
	 */
	public boolean update() {
		//move from velocity
		x += vX;
		y += vY;
		//out of bounds check
		if(x > width || x< 0) {
			x-= vX;
		}
		if(y > length|| y < 0) {
			y -= vY;
		}
		//check there are still velocity movements
		boolean wx= true;
		boolean wy = true;
		if (Math.abs(vY) < 1) {
			vY = 0;
			wy = false;
		}
		if (Math.abs(vX)< 1) {
			vX = 0;
			wx = false;
		}
		//change velocity based of friction
		if (vX != 0) {
			vX*=friction;
		}
		if(vY != 0) {
			vY*=friction;
		}
		//if there are no more velocity, return false
		return (wx || wy);
		
		
		
	}
	/**
	 * move the entity
	 * @param mx movement in the x axis
	 * @param my movement in the y axis
	 */
	public void move(int mx, int my) {
		
		//move the x and y
		x += mx;
		y += my;
		//in bounds check
		if(x > width || x< 0) {
			System.out.println("out");
			x-= mx;
		}
		if(y > length|| y < 0) {
			System.out.println("out");
			y -= my;
		}
	}
	//getters and setters
	public Image getImage() {
		return img;
	}
	public double getVX() {
		return vX;
	}
	public double getVY() {
		return vY;
	}
	public int getVal() {
		return foodVal;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getR() {
		return r;
	}
	@Override
	//compare the entities by value and the location
	public int compareTo(Entity o) {
		// TODO Auto-generated method stub
		int comp =  Integer.compare(foodVal, o.getVal());
		if (comp == 0) {
			int comp2 = Integer.compare(getX(), o.getX());
			if(comp2 == 0)return Integer.compare(getY(), o.getY());
			return comp2;
		}
		return comp;
	}
}
