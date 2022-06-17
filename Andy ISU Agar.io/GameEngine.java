package example;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.util.*;
/**
 * Class where the game environment is stored as well as all the calculations are ran
 * @author andyl
 *
 */
public class GameEngine extends Thread implements Runnable{
	//variables
	//dimensions of the game plane
	private boolean run = true;
	private int width, height;
	//number of each entity to generate
	private int numBots, numFoods, numVirus;
	//number of colors
	static int colors = 8;
	//current time
	private int time = 0;
	//seconds it takes to re-spawn a bot
	static int respawnT = 120;
	static double radiusMulti = 0.2;
	//number of character sprites
	static int characters = 20;
	//radius size range
	static int maxVSize = 40, minVSize = 27;
	//array of the images
	static Image[] sprites;
	static Image[] colorChar;
	//try to avoid concurrent modification
	private boolean inUpdate = false;
	private boolean rendering = false;
	// organize all they types of entities
	private LinkedList<Player> players;
	private LinkedList<Entity> movableEntities;
	private LinkedList<Food> food;
	private HashMap<Integer,Player> playerIndex;
	private HashMap<Integer, PriorityQueue<Blob>> playerSplits;
	private LinkedList<Virus> viruses;
	private LinkedList<Blob> bots;
	private HashSet<String> usedNames;
	//array of possble names for the bots
	private String [] names;
	//holds the new idx for new players
	private int idx;
	//fps, min value to split, most number of splits
	static int FPS = 6, sSizeLim = 1000, sNumLim = 4;
	public GameEngine(int nB,int nF,int nV, int Width, int Height) throws FileNotFoundException {
		//iniitialize the variables
		numBots = nB;
		numFoods = nF;
		numVirus = nV;
		width = Width;
		height = Height;
		Entity.width = width;
		Entity.length = height;
		idx = 0;
		
		//Sprites images
		colorChar = new Image[colors];
		for(int i = 0;i<colors;i++) {
			colorChar[i] = Toolkit.getDefaultToolkit().getImage("Foods/c"+i+".png");
		}
		
		sprites = new Image[characters+1];
		for(int i = 1; i<= characters;i++) {
			sprites[i] = Toolkit.getDefaultToolkit().getImage("Sprites/s"+i+".png");
		}
		//read in the names
		Scanner sc = new Scanner(new File("data/names.txt"));
		int len = Integer.parseInt(sc.nextLine());
		names = new String[len];
		for(int i = 0;i<len;i++) {
			names[i]= sc.nextLine();
		}
		sc.close();
		
		
	}
	/**
	 * runs the thread for updating and running the game
	 */
	public void run() {
		//create the entities
		init();
		// TODO Auto-generated method stub
		while (run) {
			//increase time
			time++;
			
			try {
				//update the game
				//sleep and fps count
				update();
				Thread.sleep(1000/FPS);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * initialize all the entities for the game
	 * @param nB number of bots to have
	 */
	public void init() {
		food = new LinkedList();
		players = new LinkedList();
		playerIndex = new HashMap();
		movableEntities = new LinkedList();
		playerSplits = new HashMap();
		viruses =new LinkedList();
		bots = new LinkedList();
		usedNames = new HashSet();
		//LinkedList<Bots> bots = new LinkedList();
		//make new food
		for(int i = 0;i<numFoods;i++) {
			//int x, int y, int col, int r, int fV
			food.add(newFood(-1));
			//System.out.println("Food X: " + x + " " + y);
			//System.out.println("Food : X: " + x + " Y: "+ y);
		}
		//make the viruses
		for(int i =0;i<numVirus;i++) {
			viruses.add(newVirus(randInt(minVSize,maxVSize)));
		}
		//make the bots
		for(int i = 0;i< numBots;i++) {
			bots.add(randBot());
		}
		
	}
	/**
	 * adds the player object to the game and give the player their index
	 * @param o player object
	 * @return index of the player
	 */
	public int addPlayer(Player o) {
		players.add(o);
		playerIndex.put(idx, o);
		playerSplits.put(idx, new PriorityQueue<Blob>(Collections.reverseOrder()));
//		System.out.println(o);
		return idx++;
	}
	/**
	 * removes the player from the game
	 * @param o the player to remove
	 */
	public void removePlayer(Player o) {
		//removes player from the data structures
		players.remove(o);
		playerIndex.remove(o.getIdx());
		playerSplits.remove(o.getIdx());
	}
	/**
	 * mouse input from the player
	 * @param mouseX mouse location of x
	 * @param mouseY mouse location of y
	 * @param idx index of the player
	 */
	public void playerInput(int mouseX, int mouseY, int idx) {
		//get player
		Player p = playerIndex.get(idx);
		//relative location of player
		int x = mouseX-Player.gx;
		int y = mouseY-Player.gy;
		int rx = x;
		int ry = y;
		//gets quadrant
		int q= 1;
		if(y < 0) {
			if(x >= 0) q = 4;
			if(x < 0) q = 3;
		}else {
			if(x < 0)q = 2;
			else q = 1;
		}
		//gets the magnitude of move
		x = Math.abs(x);
		y = Math.abs(y);
		int scale = 3;
		
		double angle = Math.atan(((double)y/x ));
		//System.out.println("Angle: "+ angle );
		
		double hyp = Math.sqrt(x*x + y*y);
		int speed = (int)(Math.log10(hyp)/Math.log10(scale));
		int my = (int)(Math.sin(angle)*speed);
		int mx = (int)(Math.cos(angle)*speed);
		//translate direction based on the quadrant
		if (q == 2 || q == 3) {
			mx *= -1;
			x*= -1;
		}
		if (q == 2 || q == 1) {
			my *= -1;
			y *= -1;
		}
		//move the player
		if(p != null)p.move(mx, my);
		//loop through all the split and move them to the mouse too 
		for (Iterator<Blob> bI = playerSplits.get(idx).iterator(); bI.hasNext();) {
			Blob b = bI.next();
			//System.out.println("Mouse: X: "+ mouseX + " Y:"+  mouseY);
			//covert the blob location to be relative distance from the player, then compare that location to the mouse on the screen
			entityMove(b, p.getPosX() + rx*p.getScale(), p.getPosY()+ -ry*p.getScale(),false);
		}
		//System.out.println(mx + " " +my);
		
		
	}
	/**
	 * update the game
	 */
	
	public void update() {
		//System.out.println("running");
		//try to avoid concurrent modification
		if(rendering)return;
		inUpdate = true;
		//add new bot
		if(bots.size() == 0) {
			bots.add(randBot());
			
		}
		if(time == FPS*respawnT) {
			time = 0;
			bots.add(randBot());
			
		}
		
		LinkedList<Food>replace = new LinkedList<>();
		//look through the players
		for (Player p : playerIndex.values()) {
			boolean dead = false;
			if(!p.isAlive())continue;
			//loop through food that player can eat
			for(Player p2: playerIndex.values()) {
				if(p.getR() > p2.getR() && edible(p.getPosX(), p.getPosY(), p2.getPosX(), p2.getPosY(), p.getR(), p2.getR())) {
					p.inCSize(p2.getCVal(), false);
					p2.kill();
					for(Blob b : playerSplits.get(p2.getIdx())) {
						food.add(new Food(b.getX(), b.getY(), p2.getColor(), b.getR(), b.getVal(), -1));
					}
				}
			}
			for(Iterator<Food> fI = food.iterator();fI.hasNext();) {
				Food f = fI.next();
				//if food is not the players food and is edible 
				if(p.getIdx() != f.getIdx() && edible(p.getPosX(), p.getPosY(), f.getX(), f.getY(),p.getR(), f.getR())) {
					//increase the size of the current blob of the player
					p.inCSize(f.foodVal,false);
					//remove the food
					fI.remove();
					//add to replace list
					replace.add(newFood(-1));
				}
			}
			//loop through the bots
			for(Iterator<Blob>bI = bots.iterator();bI.hasNext();) {
				Blob b = bI.next();
				if(edible(p.getPosX(), p.getPosY(),b.getX(), b.getY(), p.getR(), b.getR())) {
					//I think making it based on  radius rather than value, it should make it so u have to be significantly higher
					if(p.getR() > b.getR()) {
						p.inCSize(b.getVal(), false);
						bI.remove();
					}else if(b.getR() > p.getR()) {
						b.inSize(p.getCVal());
						//I think if I don't edit player splits, once the main ball is eaten, it should stay still and basically act as a food
						p.kill();
						dead = true;
						break;
					}
				}
			}
			if(dead)continue;
			//loop through the player split
			for(Iterator<Blob> sI = playerSplits.get(p.getIdx()).iterator();sI.hasNext();) {
				Blob b = sI.next();
				//check for foods that can be eaten
				for(Iterator<Food> fI = food.iterator();fI.hasNext();) {
					Food f = fI.next();
					
					if(b.getParent() != f.getIdx() && edible(b.getX(), b.getY(), f.getX(), f.getY(),b.getR(), f.getR())) {
						//increase the split blob as well as the total size of the player
						b.inSize(f.foodVal);
						p.inSize(f.foodVal);
						fI.remove();
						replace.add(newFood(-1));
					}
				}
				
				//check if the player can its split
				if(edible(b.getX(), b.getY(), p.getPosX(), p.getPosY(),b.getR(), p.getR())) {
					//System.out.println("ran");
					sI.remove();
					//System.out.println("Value first: " + p.getCVal());
					p.inCSize(b.getVal(),false);
					//System.out.println("Value first: " + p.getCVal());
					p.inSize(-b.getVal());
					
				}
				
			}
			//if Player eats a virus, pop the player and remove the virus
			for(Iterator <Virus> vI = viruses.iterator(); vI.hasNext();) {
				Virus v = vI.next();
				
				if(p.getR() > v.getR() && edible(p.getPosX(), p.getPosY(), v.getX(), v.getY(), p.getR(), v.getR())) {
					movableEntities.addAll(pop(p,randInt(2,6)));
					vI.remove();
				}
			}
			
		}
		//remove Player
		for(Iterator<Player> pI = playerIndex.values().iterator();pI.hasNext();) {
			Player p = pI.next();
			if(!p.isAlive()) {
				pI.remove();
				p.reset();
			}
		}
		ArrayList<Food>splits =new ArrayList();
		//loop through the bots
		for(Iterator<Blob> bI = bots.descendingIterator();bI.hasNext();) {
			Blob b = bI.next();
			//if the bot is already eaten
			if(!b.getStatus())continue;
			//get the view of the entities that the bot can see
			TreeSet<Entity> view = getVision(b);
			//get the largest blob the bot can eat
			Entity e = view.floor(b);
			
			int cDist = (int)1e9;
			//make a sorted set that holds all potential dangers as well as the largest to eat
			if (view.size() == 0)continue;
			TreeSet<Entity> targets;
			if(e == null) {
				targets = view;
			}
			else targets = (TreeSet<Entity>) view.subSet(e,view.last());
			//loop through targets and find the closest one
			for(Entity t : targets) {
				
				int d = distE(t,b);
				if (d < cDist) {
					cDist = d;
					e = t;
				}
			}
			b.setTarget(e);
			//set the bot's target to e
			if(e.getVal() >= b.getVal()) {
				//if it is larger move away from the entity
				//System.out.print("move away");
				entityMove(b, e.getX(), e.getY(), true);
			}else {
				//move towards
				entityMove(b, e.getX(), e.getY(),false);
			}
			//loop through food to eat
			for(Iterator<Food> fI = food.iterator();fI.hasNext();) {
				Food f = fI.next();
				
				if(edibleE(b,f)) {
					b.inSize(f.foodVal);
					fI.remove();
					replace.add(newFood(-1));
				}
			}
			//Loop through the viruses and see if the bot will eat one
			for(Iterator<Virus> vI = viruses.iterator();vI.hasNext();) {
				Virus v = vI.next();
				if(v.getR() < b.getR()) {
					if(edibleE(v,b)) {
						//System.out.println("Spliting");
						int numSplits= randInt(2, 6);
						
						splits.addAll(pop(b,numSplits));
						vI.remove();
						
					}
				}
			}
			//loop through bots to eat
			//this sometimes throws Concurrent modification error but doesn't affect the game
			for (Iterator<Blob> bI2 = bots.iterator();bI2.hasNext();) {
				Blob b2 = bI2.next();
				//not the same
				if(b2.equals(b)) {
					continue;
				}
				//if edible
				if(edibleE(b,b2)) {
					//larger one gains the score, and smaller one is killed
					if(b.getVal() > b2.getVal()) {
						b.inSize(b2.getVal());
						b2.kill();
						
					}
					else if(b2.getVal() > b.getVal()) {
						b2.inSize(b.getVal());
						b.kill();
						//stop looping
						break;
					}
					
				}
				
			}
			
		}
		//remove killed bots
		for(Iterator<Blob> bI = bots.iterator();bI.hasNext();)if(!bI.next().getStatus())bI.remove();
		movableEntities.addAll(splits);
		Collections.sort(bots);
		for (Iterator <Entity> fI = movableEntities.iterator(); fI.hasNext();) {
			//update the movable entity
			Entity f = fI.next();
			if(!f.update()) {
				//if the entity is done moving
				//remove from movable objects
				fI.remove();
				if (f.isFood) {
					//add to food list
					Food ff = (Food)f;
					food.add(ff);
					ff.setIdx(-1);
				}else {
					//add blob to the play split
					Blob b = (Blob)f;
					playerSplits.get(b.getParent()).add(b);
					
				}
				
			}
			//if not food and is a blob
			if(!f.isFood) {
				Blob b = (Blob)f;
				//loop through the food to eat
				for(Iterator<Food> foI = food.iterator();foI.hasNext();) {
					Food fo = foI.next();
					//eat the food
					if( edible(b.getX(), b.getY(), fo.getX(), fo.getY(),b.getR(), fo.getR())) {
						//System.out.println("eaten");
						b.inSize(fo.foodVal);
						if(playerIndex.get(b.getParent())!= null)playerIndex.get(b.getParent()).inSize(fo.foodVal);
						foI.remove();
						replace.add(newFood(-1));
					}
				}
				//allow bots to be eaten by player splits
				for(Iterator<Blob> bI = bots.iterator();bI.hasNext();) {
					Blob b1 = bI.next();
					if(edible(b.getX(), b.getY(), b1.getX(),b1.getY(), b.getR(), b1.getR())) {
						if(b.getR() > b1.getR()) {
							b.inSize(b1.getVal());
							playerIndex.get(b.getParent()).inSize(b1.getVal());
							bI.remove();
						}
						else if(b1.getR()>b.getR()) {
							b1.inSize(b.getVal());
							playerIndex.get(b.getParent()).inSize(-b.getVal());
							fI.remove();
						}
					}
				}
			}
		}
		//add the replacements
		food.addAll(replace);
		ArrayList<Virus> newVirus = new ArrayList();
		//check for food the viruses can eat
		for(Iterator<Virus> vI = viruses.iterator();vI.hasNext();) {
			Virus v = vI.next();
			v.update();
			for (Iterator <Entity> fI = movableEntities.iterator(); fI.hasNext();) {
				Entity f = fI.next();
				if (f.isFood && edible(f.getX(), f.getY(),v.getX(), v.getY(), f.getR(), v.getR())) {
					fI.remove();
					Food ff = (Food)f;
					v.eat(ff.getVal());
					if(v.isLimit()) {
						Virus v2 = new Virus(v.getX(), v.getY(), (int)Math.sqrt(Math.min(1000, v.getVal()/2)/3.14));
						v.setVal(v.getVal()-Math.min(1000, v.getVal()/2));
						v2.setVel(ff.getVX()*3, ff.getVY()*3);
						newVirus.add(v2);
					}
				}
			}
			
		}
		viruses.addAll(newVirus);
		//not in update
		inUpdate = false;
	}
	/*
	 * runs the game
	 */
	public void runGame() {
		//System.out.println("running");
		run = true;
	}
	/**
	 * move the entity
	 * @param e entity to move
	 * @param mouseX location of mouse x
	 * @param mouseY location of mouse y
	 * @param reverse if move away
	 */
	public void entityMove(Blob e, int mouseX, int mouseY, boolean reverse) {
		//the mouse location is the location in the plane rather than location on the screen
	
		//distance relative to the entity
		int x = mouseX- e.getX();
		int y = mouseY -e.getY();
		//quadrant
		int q= 1;
		if(y < 0) {
			if(x >= 0) q = 4;
			if(x < 0) q = 3;
		}else {
			if(x < 0)q = 2;
			else q = 1;
		}
		//get magnitude
		x = Math.abs(x);
		y = Math.abs(y);
		int scale = 3;
		
		double angle = Math.atan(((double)y/x ));
		double hyp = Math.sqrt(x*x + y*y);
		int speed = (int)(Math.log10(hyp)/Math.log10(scale));
		int my = (int)(Math.sin(angle)*speed);
		int mx = (int)(Math.cos(angle)*speed);
		//move the entity
		if (q == 2 || q == 3) {
			mx *= -1;
		}
		if (q == 3 || q == 4) {
			my *= -1;
		}
		//toward or away
		//System.out.println("Blob: X: "+ e.getX() + " Y: "+ e.getY());
		if (reverse)e.move(-mx,-my);
		else e.move(mx, my);
	}
	/**
	 * player request a split
	 * @param idx index of the player
	 * @param mX mouse location x
	 * @param mY mouse location y
	 */
	public void playerSplit(int idx, int mX, int mY) {
		//make sure there isn't too many splits
		if(playerSplits.size()+1 >= sNumLim)return;
		Player p = playerIndex.get(idx);
		//largest split
		Blob spliter = playerSplits.get(idx).peek();
		boolean pS = false;
		//if no current splits 
		if (spliter == null) pS = true;
		//choose the larger of 2
		else if (spliter.getVal() <p.getCVal())pS = true;
		Blob split;
		//if the player is largest out of all
		if (pS) {
			//if size is large enough
			if(p.getCVal() <= sSizeLim)return;
			//create a split blob and edit the current blob size
			int val = p.getCVal()/2;
			//System.out.println("Value: " + val);
			split = new Blob(p.getPosX(), p.getPosY(), (int)Math.sqrt(val/3.14), p.getImage(), val, idx,false);
			p.inCSize(-val, true);
		}else {
			//make sure large enough and split the blob
			if(spliter.getVal() <= sSizeLim)return;
			int val = spliter.getVal()/2;
			spliter.inSize(-val);
			split = new Blob(spliter.getX(), spliter.getY(), (int)Math.sqrt(val/3.14), p.getImage(), val,idx, false);
			
		}
		//set parent, split the entity, and add to movable
		split.setParent(idx);
		//System.out.println(split.getX() + " "+ split.getY());
		//change speed later
		entitySplit(split, mX, mY, p.getFeedspeed());
		movableEntities.add(split);
		
		
	}
	/**
	 * player releases food pellets 
	 * @param idx index of the player
	 * @param mX get mouse location x
	 * @param mY get mouse location y
	 */
	public void playerFeed(int idx, int mX, int mY) {
		//get the player object
		
		Player p = playerIndex.get(idx);
		//get the size of the food
		int size = (int)(p.getVal()*0.1);
		//lower the size
		p.inCSize(-size, false);
		//make food
		Food f =new Food(p.getPosX(), p.getPosY(), p.getColor(), (int)Math.sqrt(size/3.14), size, idx);
		//move the food
		entitySplit(f, mX, mY, p.getFeedspeed());
		//add to movable foods
		movableEntities.add(f);
	}
	/**
	 * splits the food into multiple food parts
	 * @param b the bot
	 * @param nS number of splits
	 * @return array list of foods
	 */
	public ArrayList<Food> pop(Blob b,int nS) {
		ArrayList<Food> ret = new ArrayList();
		int dec= 0;
		for(int i = 0;i<nS-1;i++) {
			int val = (int)(b.getVal()/nS);
			dec+=val;
			Food f = new Food(b.getX(), b.getY(), 0, (int)(Math.sqrt(val/3.14)), val, b.getIdx());
			f.setVel(randInt(-b.getR(), b.getR()), randInt(-b.getR(), b.getR()));
			ret.add(f);
		}
		b.inSize(-dec);
		return ret;
	}
	public ArrayList<Food>pop(Player p, int nS){
		System.out.println("{POFJPSFFK");
		ArrayList<Food> ret = new ArrayList();
		int dec= 0;
		for(int i = 0;i<nS-1;i++) {
			int val = (int)(p.getCVal()/nS);
			dec+=val;
			Food f = new Food(p.getPosX(), p.getPosY(), 0, (int)(Math.sqrt(val/3.14)), val, p.getIdx());
			f.setVel(randInt(-p.getR(), p.getR()), randInt(-p.getR(), p.getR()));
			ret.add(f);
		}
		p.inCSize(-dec,false);
		return ret;
	}
	public void entitySplit(Entity e, int mX, int mY, int sp) {
		//get quadrant of direction vector
		int q= 1;
		if(mY < 0) {
			if(mX >= 0) q = 4;
			else q = 3;
		}else {
			if(mX < 0)q = 2;
			else q = 1;
		}
		//get the magnitude
		mX = Math.abs(mX);
		mY = Math.abs(mY);
		double angle = Math.atan((double)mY/mX);
		int speed = sp;
		double xV = Math.cos(angle) * speed;
		double yV = Math.sin(angle) * speed;
		//get direction
		if (q == 2 || q == 3) {
			xV *= -1;
		}
		if (q == 2 || q == 1) {
			yV *= -1;
		}
		//set the velocity of the entity
		
		e.setVel(xV, yV);
	}
	/**
	 * given a bot find all the entities it can see
	 * @param b bot
	 * @return a treeset of the entities it can see
	 */
	public TreeSet<Entity> getVision(Blob b){
		int x = b.getX();
		int y = b.getY();
		int s = b.getR()*b.getVisionRange();
		//treeset sorted by size then the location
		TreeSet<Entity>view = new TreeSet();
		//add all entites on a screen of side range
		view.addAll(getScreen( x-s, y-s, x+s, y+s, true));
		//remove self
		view.remove(b);
		return view;
	}
	/**
	 * given a box, make a list of all entities in the box
	 * @param minX 
	 * @param minY
	 * @param maxX
	 * @param maxY
	 * @param bot
	 * @return
	 */
	public ArrayList<Entity> getScreen( int minX, int minY, int maxX,int maxY, boolean bot){
		//make list
		ArrayList<Entity> render = new ArrayList();
		//if in update and not for a bot which would be already in the update function
		if(inUpdate && !bot)return render;
		if(!bot) {
			rendering = true;
		}
		//loop though foods
		for (Iterator <Food> fI = food.iterator(); fI.hasNext();) {
			Food f = fI.next();
			int x = f.x;
			int y = f.y;
			if(inRange(x,y, minX, maxX, minY, maxY)) {
				render.add(f);
			}
		}
		//don't add virus to give advantage to the player and allows the player to trick the bot
		if(!bot) {
			//add viruses
			for (Iterator <Virus> fI = viruses.iterator(); fI.hasNext();) {
				Virus f = fI.next();
				int x = f.x;
				int y = f.y;
				if(inRange(x,y, minX, maxX, minY, maxY)) {
					render.add(f);
				}
			}
		}
		//loop through movable enetities and add if in range
		for (Iterator <Entity> fI = movableEntities.iterator(); fI.hasNext();) {
			Entity f = fI.next();
			int x = f.x;
			int y = f.y;
			if(inRange(x,y, minX, maxX, minY, maxY)) {
				render.add(f);
			}
		}
		//add player splits
		for(Iterator<PriorityQueue<Blob>> pI = playerSplits.values().iterator();pI.hasNext();) {
			PriorityQueue<Blob> lis = pI.next();
			for(Iterator<Blob> bI = lis.iterator();bI.hasNext();) {
				Blob b = bI.next();
				int x = b.x;
				int y = b.y;
				
				if (inRange(x,y, minX, maxX, minY, maxY)) {
					render.add(b);
				}
			}
		}
		//adds the bots
		for(Iterator<Blob> bI = bots.iterator();bI.hasNext();) {
			Blob b = bI.next();
			int x = b.getX();
			int y = b.getY();
			
			if (inRange(x,y, minX, maxX, minY, maxY)) {
				render.add(b);
			}
		}
		//adds the player
		for(Player p : playerIndex.values()) {
			if(inRange(p.getPosX(), p.getPosY(), minX,maxX, minY, maxY)) render.add(new Entity(p.getPosX(), p.getPosY(), p.getR(), p.getImage(),true, p.getCVal() ));
		}
		//System.out.println(render.size());
		rendering = false;
		return render;
	}
	/**
	 * makes score for the leader 
	 * @return list of scores sorted by score
	 */
	public ArrayList<Score> getLeaders(){
		ArrayList<Score> ret = new ArrayList();
		//adds a score for the bots
		for(Iterator<Blob>bI = bots.iterator();bI.hasNext();) {
			Blob b = bI.next();
			ret.add(new Score(b.getName(), b.getVal()));
		}
		//adds the scores for the players
		for(Iterator<Player>pI = playerIndex.values().iterator();pI.hasNext();) {
			Player p = pI.next();
			ret.add(new Score(p.getName(), p.getVal()));
		}
		//sort the list
		Collections.sort(ret);
		return ret;
	}
	/**
	 * gives random int in the range of max and min exclusive with max
	 * @param min lower
	 * @param max higher
	 * @return integer
	 */
	public static int randInt (int min , int max) {
		return (int)((Math.random()*(max - min))+ min);
	}
	/**
	 * given a box defined by 2 points, return whether a given point is inside the box
	 * @param x position of the point to check
	 * @param y position of the point to check
	 * @param minX lowest x
	 * @param maxX largest x
	 * @param minY lowest y
	 * @param maxY largest y
	 * @return if poiint is valid
	 */
	public boolean inRange(int x,int y, int minX, int maxX, int minY, int maxY) {
		if (minX <= x && maxX >= x && minY <= y && maxY >= y)return true;
		return false;
	}
	/**
	 * gets the distance between 2 points
	 * @param x position of the first point
	 * @param y position of the first point
	 * @param x1 x position of the second point
	 * @param y1 y position of the second point
	 * @return the distance of between the 2 points
	 */
	public int dist(int x, int y, int x1, int y1) {
		return (int) Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
		
	}
	/**
	 * gets distance between the 2 entities
	 * @param a entity 1
	 * @param b entity 2
	 * @return distance between them
	 */
	public int distE(Entity a, Entity b) {
		return dist(a.getX(), a.getY(),b.getX(),b.getY());
	}
	/**
	 * check if entities can be eaten
	 * @param a entity 1
	 * @param b entity 2
	 * @return if can be eaten
	 */
	public boolean edibleE(Entity a, Entity b) {
		return edible(a.getX(),a.getY(), b.getX(), b.getY(), a.getR(), b.getR());
	}
	/**
	 * check for overlapping circles
	 * @param x position of center 1
	 * @param y position of center 1
	 * @param x1 position of center 2
	 * @param y1 position of center 2
	 * @param r radius 1
	 * @param r1 radius 2
	 * @return if overlapping
	 */
	public boolean edible(int x, int y, int x1, int y1, int r, int r1) {
		int d =dist(x,y, x1,y1);
		if (d <= Math.max(r1, r))return true;
		return false;
	}
	/**
	 * if 2 entities touch each other
	 * @param a entity 1
	 * @param b entity 2
	 * @return if the two entities are touching
	 */
	public boolean touchE(Entity a, Entity b) {
		return touch(a.getX(),a.getY(), b.getX(), b.getY(), a.getR(), b.getR());
	}
	/**
	 * given 2 circles, check if they are overlapping
	 * @param x position of circle 1 center
	 * @param y position of circle 2 center
	 * @param x1 x position of  circle 1 center
	 * @param y1 y position of circle 2 center
	 * @param r radius of circle 1
	 * @param r1 radius of circle 2
	 * @return if the circles are touching
	 */
	public boolean touch(int x, int y, int x1, int y1, int r, int r1) {
		int d =dist(x,y, x1,y1);
		if (d <=r1+r)return true;
		return false;
	}
	/**
	 * make new random food
	 * @param idx of the food
	 * @return food object
	 */
	public Food newFood(int idx) {
		//make random position in the plane
		int x = randInt(0, width);
		int y = randInt(0, height);
		//random color 
		int color = randInt(0, colors);
		//make food
		return new Food(x, y, color, 5, (int)(5*3.14), idx);
	}
	/**
	 * make new virus
	 * @param r size
	 * @return virus object
	 */
	public Virus newVirus(int r) {
		//random position in plane
		int x = randInt(0, width);
		int y = randInt(0, height);
		
		//return the new virus
		return new Virus(x, y, r);
	}
	/**
	 * make a random bot
	 * @return return a bot object
	 */
	public Blob randBot() {
		//random position in plane
		int x = randInt(0, width);
		int y = randInt(0, height);
		//random radius
		int r = randInt(10, 35);
		int i = randInt(0,2);
		Image img;
		if(i == 0) {
			//get random color image
			img = colorChar[randInt(0, colors)];
		}else {
			//get random sprite image
			img= sprites[randInt(1, characters)];
		}
		String name = names[randInt(0, names.length)];
		while (usedNames.contains(name)) {
			name = names[randInt(0,names.length)];
		}
		
		//return the new blob object
		return new Blob(x, y, r, img, (int)(3.14*r*r), -1, true,name,idx++);
	}
	
}
