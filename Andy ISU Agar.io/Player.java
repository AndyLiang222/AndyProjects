package example;

//Name: Andy Liang
//Date: January 25th, 2022
//Assignment: ISU Agar.io
//Description: Blob eat other blob to survive

import javax.swing.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;

public class Player extends JPanel implements Runnable, ActionListener, MouseListener, KeyListener, MouseMotionListener{
	//dimensions of the window
	static int WIN_WIDTH = 640;
	static int WIN_HEIGHT = 480;
	static String dataPath = "data/scores.txt";
	//dimensions of the plane for the game
	static int width = 1000;
	static int length = 1000;
	static int numBots = 10;
	static int numVirus = 10;
	static int numFoods = 400;
	static int numPlayers = 1;
	static int maxPlayers = 2;
	static int curPanel = 0;
	static String[] controlNames = {"Bots:","Food:", "Viruses:", "Width:","Length:","Players:"};
	static int[] controls = {numBots,  numFoods,numVirus, width, length,numPlayers};
	//game players
	
	static int pCount =0;
	static Player[] players = new Player[maxPlayers];
	//fonts
	static Font titleF = new Font("Courier New", Font.BOLD, 16);
	static Font nameF = new Font("Courier New",Font.PLAIN, 12);
	//common images
	static Image Logo, Background, rules, scoreBg,settingBg, virus;
	private Image img;
	//images for the characters
	static Image[] sprites;
	static Image[] colorChar;
	//make buttons for the game gui
	private Button button1, info, back, settings, chooseChar, chooseColor,search1, search2,back2;
	//text boxes for the game
	private Textbox[]textBoxs;
	//current screen in the game
	private int status;
	//max length of game
	static int maxNameLen = 8;
	//static leader board classes
	static int colors = 8;
	static int characters = 19, setSize = 100;
	static ArrayList<Score> scores;
	static ArrayList<Score>scoresN;
	
	//make the game
	static JFrame frame;
	//panel
	static JPanel myPanel;
	//booleans for controls
	private boolean feed,split,dev,alive;
	//frequency of feeding
	static int feedFreq = 1;
	//frame name
	static String name = "Agar.io";
	//default radius and fps
	static int defaultR = 8;
	static int FPS = 12;
	//buttons
	private Button [] sButtons;
	private Button [] decreaseC;
	private Button [] increaseC;
	private Button newGame;
	static int maxSpeed = 5;
	//middle location
	static int gx = WIN_WIDTH/2, gy = WIN_HEIGHT/2;
	//private variables for the player
	private int x, y, r, scale, sX, sY, idx,foodVal,cVal,color, feedSpeed, fpsCount, mouseX, mouseY,pIdx;
	private int rx, ry;
	private String pName = "Player 1";
	private int curImage;
	private int maxX, maxY, minX, minY;
	//game
	static GameEngine game;
	private Thread thread;
	//number of colors and character images
	private int curText;
	private ArrayList<Score>show;
	private ArrayList<Entity> render;
	//change to be in game engine later
	private boolean movable = true;
	static PrintWriter scoreWriter;
	static Scanner scoreReader;
	public static void main(String[]args) throws FileNotFoundException {
		//Main menu stuff
		//	make the images
		Logo = Toolkit.getDefaultToolkit().getImage("Logo.png");
		Background = Toolkit.getDefaultToolkit().getImage("BackgroundA.png");
		rules = Toolkit.getDefaultToolkit().getImage("Screens/Rules.png");
		scoreBg = Toolkit.getDefaultToolkit().getImage("Backgrounds/scoreboardImg.png");
		virus = Toolkit.getDefaultToolkit().getImage("Sprites/virus.png");
		settingBg = Toolkit.getDefaultToolkit().getImage("Backgrounds/settingImg.png");
		//Sprites
		colorChar = new Image[colors];
		for(int i = 0;i<colors;i++) {
			colorChar[i] = Toolkit.getDefaultToolkit().getImage("Foods/c"+i+".png");
		}
		
		sprites = new Image[characters+1];
		for(int i = 1; i<= characters;i++) {
			sprites[i] = Toolkit.getDefaultToolkit().getImage("Sprites/s"+i+".png");
		}
		//high score reading
		scores = new ArrayList();
		scoresN = new ArrayList();
		
		try {
			scoreReader = new Scanner(new File(dataPath));
			while(scoreReader.hasNextLine()) {
				String in = scoreReader.nextLine();
				if(in.length() == 0)break;
				int split = in.indexOf(' ');
				int score = Integer.parseInt(in.substring(0, split));
				String name = in.substring(split+1);
				scores.add(new Score(name, score));
				scoresN.add(new Score(name, score));
			}
			Collections.sort(scores);
			Collections.sort(scoresN, new SortByName());
			scoreReader.close();
		}catch (FileNotFoundException e) {
			System.out.println("Score file cannot be found :(");
		}
		//Frame stuff
		frame =new JFrame(name);
		
		myPanel = new JPanel();
	
		
		//self explanatory. You want to see your frame
		frame.setVisible(true);
		//some weird method that you must run
		frame.setLocationRelativeTo(null);
		//without this, your thread will keep running even when you windows is closed!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//self explanatory. You don't want to resize your window because
		//it might mess up your graphics and collisions
		frame.setResizable(false);
		frame.pack();
		//place your frame in the middle of the screen
		frame.setLocationRelativeTo(null);
		//without this, your thread will keep running even when you windows is closed!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//self explanatory. You don't want to resize your window because
		//it might mess up your graphics and collisions
		frame.setResizable(false);
		//place your frame in the middle of the screen
		frame.setSize(WIN_WIDTH*Math.min(2, controls[5]), (int)(WIN_HEIGHT*Math.ceil(controls[5]/2.0)));
		
		myPanel.setLayout(new GridLayout((int)(Math.ceil(controls[5]/2.0)),Math.min(2, controls[5])));
		Food.initColors(8);
		Virus.initImage(virus);
//		myPanel = new Player(0,0,randInt(0,width),randInt(0,length));
		for(int i = 0;i<Math.min(2, maxPlayers);i++) {
			for(int j = 0;j<(int)(Math.ceil(maxPlayers/2.0));j++ ) {
				players[j*2+i] = new Player(0, 0,randInt(0,controls[3]),randInt(0,controls[4]) );
				
			}
		}
		frame.add(myPanel);
		for(int i = 0;i<controls[5];i++) {
			myPanel.add(players[i]);
			players[i].setFocusable(true);
			players[i].requestFocusInWindow();
			players[i].setVisible(true);
		}
		
		startGame();
		
		
		
	}
	/**
	 * resets the frame size spending on the number of players
	 * @param increase if you add player or subtract player
	 */
	public static void setFrame(boolean increase) {
		//add or remove player
		if(increase) {
			myPanel.add(players[controls[5]-1]);
		}else {
			myPanel.remove(players[controls[5]]);
		}
		//redraw and resize the frame/panel
		myPanel.revalidate();
		redraw();
		frame.setSize(WIN_WIDTH*Math.min(2, controls[5]), (int)(WIN_HEIGHT*Math.ceil(controls[5]/2.0)));
		
		myPanel.setLayout(new GridLayout((int)(Math.ceil((controls[5])/2.0)),Math.min(2, (controls[5]))));
		
		
	}
	/**
	 * goes through all the player panels and repaints
	 */
	public static void redraw() {
		for(int i = 0;i< controls[5];i++) {
			players[i].repaint();
		}
	}
	public Player(int x, int y,int x0, int y0) throws FileNotFoundException{
		
		rx = x;
		ry = y;
		pIdx = pCount++;
		x= x0;
		y = y0;
		r = defaultR;
		scale = r/defaultR;
		foodVal =(int)(r*r*3.14);
		cVal = foodVal;
		feedSpeed = 15;
		color = 0;
		curImage = 0;
		feed = false;
		fpsCount = 0;
		img = colorChar[0];
		scale = 1;
		curText = -1;
		alive = true;
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
//		Make the Player images
		
		//make buttons
		button1 = new Button(100, 280, 160, 70, Toolkit.getDefaultToolkit().getImage("buttons/Button1.png") , 1, this);
		info = new Button(550, 20, 60, 60, Toolkit.getDefaultToolkit().getImage("buttons/Info.png") , 2,this);
		back = new Button(550, 20, 60, 60, Toolkit.getDefaultToolkit().getImage("buttons/Back.png"), 3, this);
		settings = new Button(10, WIN_HEIGHT-100, 60,60,Toolkit.getDefaultToolkit().getImage("buttons/Settings.png"), 4,this);
		back2 = new Button(10, WIN_HEIGHT-100, 60,60,Toolkit.getDefaultToolkit().getImage("buttons/Back.png"), 10,this);
		
		//Make textbox
		textBoxs = new Textbox[6];
		textBoxs[0]= new Textbox(400, 360, 150, 30,this);
		textBoxs[4]= new Textbox(40, 360, 150,30,this);
//		textBoxs[1]= new Textbox();
		//buttons in the array
		//	start buttons
		sButtons =new Button[3];
		sButtons[0] = button1;
		sButtons[1] = info;
		sButtons[2] = settings;
		//	Settings Buttons
		chooseChar = new Button(2*WIN_WIDTH/3-50, WIN_HEIGHT-100, 100, 40, Toolkit.getDefaultToolkit().getImage("buttons/changeImage.png"), 5,this);
		chooseColor = new Button(2*WIN_WIDTH/3-50, WIN_HEIGHT-160, 100, 40, Toolkit.getDefaultToolkit().getImage("buttons/changeColor.png"), 5,this);
		
		//Control Buttons
		increaseC = new Button[controls.length];
		decreaseC = new Button[controls.length];
		for(int i = 20;i<20+controls.length;i++) {
			increaseC[i-20] = new Button (140, 80+40*(i-20), 30,30,Toolkit.getDefaultToolkit().getImage("buttons/increase.png"),i, this);
			decreaseC[i-20] = new Button (220, 80+40*(i-20), 30,30,Toolkit.getDefaultToolkit().getImage("buttons/decrease.png"),i+10, this);
		}
		newGame = new Button(40,310, 100, 40, Toolkit.getDefaultToolkit().getImage("buttons/newGame.png"),69420, this );
		show= new ArrayList();
		for(int i = 0;i < Math.min(10, scores.size());i++) {
			show.add(scores.get(i));
		}
	}
	/**
	 * reset the player values for the game and add to the score
	 */
	public void reset() { 
		//make score and add
		Score s = new Score(pName, foodVal);
		addScore(s);
		//reset the values and remove the player from the game and repaint
		fpsCount = 0;
		r = defaultR;
		foodVal = (int)(r*r*3.14);
		cVal = foodVal;
		x = randInt(0,width);
		y = randInt(0,length);
		status = 0;
		game.removePlayer(this);
		this.repaint();
		alive = true;
		
	}
	/**
	 * creates a new game and runs the game
	 * @throws FileNotFoundException
	 */
	public static void startGame() throws FileNotFoundException {
		game = new GameEngine (controls[0], controls[1], controls[2], controls[3], controls[4]);
		game.start();
	}
	/**
	 * run the player (basically renders the graphics and gives user inputs to the game)
	 */
	public void run() {
		// TODO Auto-generated method stub
		//while in game
		while(status == 2) {
			this.repaint();
			try {
				//sleep and fps count
				//if can move
				movable = true;
				//feed
				if (fpsCount % (FPS/feedFreq) == 0) {
					if (feed) {
						//feed the player
						game.playerFeed(idx,mouseX-gx , mouseY-gy);
						feed = false;
					}else if(split) {
						//split the player
						game.playerSplit(idx,mouseX-gx , mouseY-gy);
						split = false;
					}
					
				}
				//use mouse for player input
				game.playerInput(mouseX,mouseY, idx);
				Thread.sleep(1000/FPS);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * draw the graphics
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//System.out.println(this+"Drawn: "+rx + " " + ry);
		//if the starting screen
		
			if(status == 0) {
				//draw background and buttons
				g.drawImage(Background, rx + 0, ry+0, WIN_WIDTH, WIN_HEIGHT, this);
				setFont(titleF);
				g.drawImage(Logo, rx + 50, ry+50, 300, 80, this);
				drawComp(g, button1);
				drawComp(g,info);
				drawComp(g,settings);
				g.drawImage(scoreBg, rx + 400, ry+90, 180, 300, this);
				//draw highscore table
				int cx = rx + 400+10;
				int cy = ry+90+20;
				g.drawString("High Scores: ", cx, cy);
				int padding = 20;
				cy += padding*1.5;
				setFont(nameF);
				for(int i = 0;i < show.size();i++) {
					Score s = show.get(i);
					g.drawString(String.format("%3d   %8s   %d",i+1, s.getName(), s.getScore() ),cx, cy);
					cy+= padding;
				}
				
			}
			//if in info page
			if (status == 3) {
				//draw page and back button
				g.drawImage(rules, rx + 0,ry+0, WIN_WIDTH-10, WIN_HEIGHT-35, this);
				
				drawComp(g, back);
			}
			//draw the game
			if(status == 2) {
				
				//request the render, if in update the render will be empty
				ArrayList<Entity>request = game.getScreen(x-WIN_WIDTH/2, y - WIN_HEIGHT/2,x+WIN_WIDTH/2 , y + WIN_HEIGHT/2, false);
				if(request.size() != 0) {
					//if empty use the previous, else render and sort
					render = request;
					Collections.sort(render);
				}
				//System.out.println(render.size());
				
				//draw all entities, player is accounted for since the render will create copies of the player as an entity to draw
				
				for(Entity e : render) {
					
					g.drawImage(e.getImage(), rx +e.getX()-x + gx-e.getR(), ry+y-e.getY() + gy-e.getR(),e.getR()*2,e.getR()*2, this);
					if(dev) {
						//developer mode will show the score of the entity and target 
						g.drawString(""+e.getVal(),rx + e.getX()-x + gx, ry +y-e.getY() + gy);
						
						if(e.isAi() ) {
							Blob e1 = (Blob) e;
							if(e1.getTarget() == null)continue;
							g.drawLine(rx + e.getX()-x + gx-e.getR(),ry+ y-e.getY() + gy-e.getR(), rx + e1.getTarget().getX()-x + gx-e1.getTarget().getR(), ry+y-e1.getTarget().getY() + gy-e1.getTarget().getR());
						}
					}
				}
				//draw the statistics of the player
				g.drawString("X: " + x + " Y: " + y, rx + 30, ry+ 30);
				g.drawString("Total Score: " + getVal(),rx + 30, ry + 60 );
				g.drawString("Cur Blob: " + getCVal(),rx + 30, ry + 90);
				int cx = rx + WIN_WIDTH-140;
				int cy = ry + 15;
				//draw the leader board
				g.drawImage(settingBg,rx  +cx, ry+cy, 120, 210, this);
				cx+= 10;
				cy+=20;
				ArrayList<Score> leader = game.getLeaders();
				for(int i = 0;i<Math.min(leader.size(), 10);i++) {
					g.drawString((i+1) + leader.get(i).toString(),cx,cy);
					cy+=20;
				}
				//back button POGchamp lego pushin' p 
				drawComp(g,back2);
			}
			//the settings page
			if(status == 4) {
				//draw components
				g.drawImage(Background, rx + 0, ry + 0, WIN_WIDTH, WIN_HEIGHT, this);
				int pad = 30;
				drawComp(g,back);
				drawComp(g, chooseColor);
				drawComp(g, chooseChar);
				g.drawImage(img,rx + 2*WIN_WIDTH/3-setSize/2,ry+ WIN_HEIGHT- 350, setSize, setSize,this);
				int cx = rx + pad;
				int cy = ry + pad;
				g.drawImage(settingBg, cx, cy, 240, (int)(WIN_HEIGHT-3.2*pad), this);
				g.drawString(pName, rx + (int)(425-10*pName.length()/2.0),ry+ 275);
				setFont(titleF);
				cx+= pad/2;
				cy+= pad;
				
				g.drawString("Controls",cx , cy);
				for(int i = 0;i<controls.length;i++) {
					drawComp(g,decreaseC[i]);
					drawComp(g,increaseC[i]);
					g.drawString("" + controls[i], increaseC[i].getX()+40, decreaseC[i].getY()+15);
					g.drawString(controlNames[i], 60, decreaseC[i].getY()+15);
				}
				drawComp(g,newGame);
				
			}
			//draws the text box for this screens
			if(textBoxs[status]!= null) {
				drawComp(g,textBoxs[status]);
				drawComp(g,textBoxs[status].getSearch());
				g.drawString(textBoxs[status].toString(), textBoxs[status].getX()+5, textBoxs[status].getY()+20);
			}
	}
	/**
	 * move the player
	 */
	public void move(int mx, int my) {
		x += mx;
		y += my;
		//make sure inbounds
		if(x > controls[3] || x< 0) {
			//System.out.println("out");
			x-= mx;
		}
		if(y > controls[4]|| y < 0) {
			//System.out.println("out");
			y -= my;
		}
		//System.out.println("Food X: " + x + " " + y);
	}
	/**
	 * edits the size of the current blob
	 * @param val the value
	 * @param split if its from a split of the current player
	 */
	public void inCSize(int val, boolean split) {
		//if its from a split, don't edit the total score of the user
		if (!split)foodVal += val;
		//edit the current blob object as well as the radius
		cVal += val;
		r = (int)(Math.sqrt(cVal/3.14));
	}
	/**
	 * increment the total value
	 * @param val the value
	 */
	public void inSize(int val) {
		foodVal += val;
		
	}
	/**
	 * add the score to the leader boards
	 * @param s score object to add
	 */
	public void addScore(Score s) {
		//add both to the different sort methods
		scores.add(s);
		scoresN.add(s);
		//re-sort
		Collections.sort(scores);
		Collections.sort(scoresN, new SortByName());
		//reset the high-score table shown
		show.clear();
		for(int i = 0;i < Math.min(10, scores.size());i++) {
			show.add(scores.get(i));
		}
		try {
			PrintWriter pr = new PrintWriter(new FileWriter(dataPath,true));
			pr.println(""+s.getScore() + " " +s.getName());
			pr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	/**
//	 *
//	 * @return
//	 */
//	public int [] getScreen() {
//		int [] ret = {minX, minY, maxX, maxY};
//		return ret;
//	}
	//getters and setters
	public int getRX() {
		return rx;
	}
	public int getRY() {
		return ry;
	}
	public int getIdx() {
		return idx;
	}
	public int getVal() {
		return foodVal;
	}
	public int getCVal() {
		return cVal;
	}
	public int getFeedspeed() {
		return (int)(1.2*getR());
	}
	public Image getImage() {
		return img;
	}
	public int getScale() {
		return scale;
	}
	public String getName() {
		return pName;
	}
	//random integer, I made a different method comment in game engine class
	public static int randInt (int min , int max) {
		return (int)((Math.random()*(max - min))+ min);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//typing into the current text
		char key = e.getKeyChar();
		//checks if the last clicked panel is currently in a text box
		if (players[curPanel].getCurText() != -1) {
			//back space will remove characters
			if (key == 8)players[curPanel].getText()[players[curPanel].getCurText()].decStr();
			//add valid character to the string
			else if(key >= 48 && key <= 123)players[curPanel].getText()[players[curPanel].getCurText()].addStr(""+key);
			//redraw the page
			redraw();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseMoved(MouseEvent e) {
		if(status == 2) {
			if (movable) {
				//change the mouse locaton if in game
				mouseX = (int) e.getX();
				mouseY = (int) e.getY();
				//System.out.println(mx);
				
				movable = false;
			}
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int type = e.getButton();
		//location of the mouse
		int x = e.getX();
		int y = e.getY();
		//resets the text-box engaged into
		curPanel = pIdx;
		curText = -1;
		if(type == 4) {
			//turn into developer mode
			dev = !dev;
		}
		//check text-box in the screen
		if(textBoxs[status]!= null) {
			//if the text box is click
			if(textBoxs[status].check(x, y)) {
				//set the current text-box
				curText = status;
			}
			else if(textBoxs[status].checkSearch(x, y)) {
				//check if the search button is clicked
				if(status == 4) {
					//set player name
					pName = textBoxs[status].toString();
				}
				else if(status == 0) {
					//reset the shown scores
					show.clear();
					String search = textBoxs[status].toString();
					if(search.length() == 0) {
						//if nothing is searched, reset the scores
						for(int i = 0;i < Math.min(10, scores.size());i++) {
							show.add(scores.get(i));
						}
						
					}else {
						//binary seach for the name in the list sorted by the names
						int i = Collections.binarySearch(scoresN, new Score(search, Integer.MAX_VALUE), new SortByName());
						//finds the index of where it woould be inserted
						int idx = (i+1)*-1;
						//loop until the name not equal to the searched name
						while(idx < scoresN.size() && scoresN.get(idx).getName().equals(search)) {
							show.add(scoresN.get(idx));
							idx++;
						}
					}
					
				}
				//re-paint
				this.repaint();
			}
		}
		//if in the start screen
		if (status == 0) {
			//check all the buttons
			for(Button b: sButtons) {
				if (b.check(x, y)) {
					//Load the game
					if (b.getFunc() == 1) {
						status = 2;
						idx = game.addPlayer(this);
						thread = new Thread(this);
						thread.start();
						
					}
					//go to info page
					else if(b.getFunc() == 2) {
						//System.out.println("alfjlkafjfa");
						status = 3;
						this.repaint();
					}
					//go to the settings page
					else if(b.getFunc() == 4) {
						status = 4;
						this.repaint();
					}
				}
			}
			
		}
		//if in game 
		else if (status == 2) {
			
			//feed if left click
			if (type == 3)feed = true;
			//split if right clicked
			else if(type == 1)split = true;
			//if back button is clicked
			if(back2.check(x, y)) {
				reset();
			}
		}
		//if in the info page
		else if(status == 3) {
			if (back.check(x, y)) {
				//go back to the start page
				status = 0;
				this.repaint();
			}
		}
		//if in the settings page
		else if(status == 4) {
			//go back to start page if button clicked
			if (back.check(x, y)) {
				status = 0;
				
			}
			for(int i = 0;i<controls.length;i++) {
				if(decreaseC[i].check(x, y)) {
					controls[i]--;
					if(i>= 3 && i < 5)controls[i]-=9;
					if(i == 5) {
						if(controls[i]  == 0)controls[i]++;
						else setFrame(false);
					}
					redraw();
				}
				if(increaseC[i].check(x, y)) {
					controls[i]++;
					if(i>= 3&& i < 5)controls[i]+=9;
					if(i == 5) {
						if(controls[i] > maxPlayers)controls[i]--;
						else setFrame(true);
					}
					redraw();
				}
			}
			if(newGame.check(x, y)) {
				try {
					startGame();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//change the color of the player
			if (chooseColor.check(x, y)) {
				color++;
				//if reaches limit, then go back to index 0
				if(color == colors)color = 0;
				//if current image is 0, the image is of a color and not an actual image
				if (curImage == 0) {
					//change image
					img = colorChar[color];
				}
				//repaint
				this.repaint();
			}
			if (chooseChar.check(x, y)) {
				curImage++;
				//change the image
				//characters are 1 indexed, reset the current image to 0
				if(curImage == characters+1)curImage = 0;
				img = sprites[curImage];
				//set image to the sprite in the array
				//sprites[0] is null
				if(img == null) {
					//change image to the color
					img = colorChar[color];
				}
				//repaint
				this.repaint();
			}
			this.repaint();
		}
	}
	//getters and setters
	public int getPosX() {
		return x;
	}
	public int getPosY() {
		return y;
	}
	public int getR() {
		return r;
	}
	public int getColor() {
		return color;
	}
	public boolean isAlive() {
		return alive;
	}
	public void kill() {
		alive = false;
	}
	public Textbox[] getText() {
		return textBoxs;
	}
	public int getCurText() {
		return curText;
	}
	/**
	 * draws the component, either a button or a text-box
	 * @param g graphics
	 * @param b the component
	 */
	public void drawComp(Graphics g, Comp b) {
		//gets the data and draws to g
		g.drawImage(b.getImage(), b.getX(), b.getY(),b.getW(), b.getH(), this);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public String toString() {
		return "Player: "+pIdx;
	}
	
}
