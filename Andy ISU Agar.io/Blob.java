package example;
import java.util.*;

import java.awt.Image;
/**
 * Entity type for bots as well as player splits
 * @author andyl
 *
 */
public class Blob extends Entity {
	//index of the parent
	private int parent;
	//time when spawned
	private int sTime;
	//the range multiplier based on the radius for the bots visions
	private int visionRange = 6;
	private int idx;
	//name of the blob
	private String name = "player";

	//target of entity
	
	static HashMap<String, Entity> target = new HashMap();
	public Blob(int x, int y, int r, Image i, int fV, int pa, boolean a) {
		//initialize it as its parent entity
		super(x, y, r, i, true, fV);
		//sets parent and Ai
		parent = pa;
		isFood = false;
		setAi(a);
		// TODO Auto-generated constructor stub
	}
	
	public Blob(int x, int y, int r, Image i, int fV, int pa, boolean a,String name, int idx) {
		//Constructor but giving name
		super(x, y, r, i, true, fV);
		//basically the constructor for the bot
		this.idx = idx;
		parent = pa;
		isFood = false;
		setAi(a);
		this.name = name;
		
	}
	//getters and setters
	public int getIdx() {
		return idx;
	}
	public Entity getTarget() {
		return target.get(name);
	}
	public void setTarget(Entity e) {
		target.put(name, e);
	}
	public int getVisionRange() {
		return visionRange;
	}
	public void setTime(int t) {
		sTime = t;
	}
	public int getTime() {
		return sTime;
	}
	public String getName() {
		return name;
	}
	public void setParent(int pa) {
		parent = pa;
	}
	public int getParent() {
		return parent;
	}
	//edit size
	public void inSize(int val) {
		foodVal += val;
		r = (int)(Math.sqrt(foodVal/3.14));
	}
//	public void move(int x, int y) {
//		//System.out.println("Move: " + x + " " + y);
//		this.x += x;
//		this.y += y;
//	}
	
	

	

}
