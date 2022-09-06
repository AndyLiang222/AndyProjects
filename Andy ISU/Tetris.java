package example;

import java.awt.*;

import java.util.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
//Name: Andy Liang
//Date: June 20th, 2021
//Assignment: Tetris ISU
//Description: A Tetris clone to play

public class Tetris extends JPanel implements Runnable, ActionListener, MouseListener, KeyListener{
	static JFrame frame;
	static Thread thread;
	static int WIN_WIDTH = 900;
	static int WIN_HEIGHT = 600;
	static Color [] colors = {};
	static int status = 0;
	static int rStatus = 0;
	static int FPS = 12;
	static int swtch = -1;
	static int FPScount = 0;
	static int curSong = 0;
	static boolean left, right, up,down,hardDrop,hold,settings;
	static Image gameBack,box,gbackdrop,logo, loading,gameEnd,nexBox,backS;
	static button startB, battleB,settingB, ruleB,back, previous, next,stopM, playM, nextR, about;
	static button [] buttons;
	static int [] controls = {37,38,39,40,67,32};
	static String [] nCont = {"left", "Rotate", "right", "down", "hold", "hard Drop"};
	static button [] cButtons;
	static soundTrack [] music;
	static Image [] rule;
	
	static boolean inGame, over, playing,rules, aboat;
	
	
	//0 = dead piece, 1 = line, 2 = blue L, 3 = orange L, yellow = square, 5 = green z , 6 = T, 7 = red z
	static Image [] pieceImage;
	
	//the first game will always be player controlled, second can be bot
	static game[] games;
	static int [] scoreChart = {0,40,100,300,1200};
	static int[][]gridPosX = {{0},{0,1,2,3},{1,2,3,3},{1,1,2,3},{1,2,1,2},{1,2,2,3},{1,2,3,2},{1,2,2,3}};
	static int[][]gridPosY = {{0},{1,1,1,1},{1,1,1,2},{2,1,1,1},{1,2,2,1},{1,1,2,2},{1,1,1,2},{2,2,1,1}};
	public Tetris() {
		pieceImage =new Image [8];
		for (int i = 0; i<= 7;i++) {
			pieceImage [i] = Toolkit.getDefaultToolkit().getImage("Piece/"+ i +".png");
		}
		//Lot of the images for the game being intialized
		gameBack = Toolkit.getDefaultToolkit().getImage("gameBack.png");
		box = Toolkit.getDefaultToolkit().getImage("box.png");
		gbackdrop = Toolkit.getDefaultToolkit().getImage("backdrop.png");
		loading = Toolkit.getDefaultToolkit().getImage("loading.gif");
		logo = Toolkit.getDefaultToolkit().getImage("logo.gif");
		gameEnd = Toolkit.getDefaultToolkit().getImage("gameEnd.png");
		nexBox = Toolkit.getDefaultToolkit().getImage("nextBack.png");
		
		//all of the buttons for the game initialized
		startB = new button(550, 50, 260, 150,Toolkit.getDefaultToolkit().getImage("buttons/startB.png") , 1);
		battleB = new button(550, 225, 260, 150,Toolkit.getDefaultToolkit().getImage("buttons/battleB.png") , 2);
		settingB = new button(550, 400, 260, 150,Toolkit.getDefaultToolkit().getImage("buttons/settingB.png") , 3);
		ruleB = new button(75, 450, 100, 60,Toolkit.getDefaultToolkit().getImage("buttons/ruleB.png") , 4);
		back = new button(750, 450, 130, 75, Toolkit.getDefaultToolkit().getImage("buttons/back.png"), -1);
		previous = new button(70, 350, 40, 40, Toolkit.getDefaultToolkit().getImage("buttons/previous.png"), 13);
		next = new button(180, 350, 40, 40, Toolkit.getDefaultToolkit().getImage("buttons/next.png"), 14);
		stopM = new button(130, 350, 30, 30, Toolkit.getDefaultToolkit().getImage("buttons/stopM.png"), 15);
		playM = new button(130, 350, 30, 30, Toolkit.getDefaultToolkit().getImage("buttons/startM.png"), 16);
		nextR = new button(75,450,100,60, Toolkit.getDefaultToolkit().getImage("buttons/nextR.png"),69420);
		about = new button(225, 450, 100, 60, Toolkit.getDefaultToolkit().getImage("buttons/about.png"), 5);
		backS = Toolkit.getDefaultToolkit().getImage("backS.png");
		//button array for the title screen
		buttons = new button [5];
		buttons[0] = startB;
		buttons[1] = battleB;
		buttons[2] = settingB;
		buttons[3] = ruleB;
		buttons[4] = about;
		//buttons for control change
		cButtons = new button[6];
		//array to hold the rule screens
		int rSize = 3;
		rule = new Image[rSize];
		for (int i = 1 ;i<= rSize;i++) {
			rule [i-1] = Toolkit.getDefaultToolkit().getImage("rules/rule" + i + ".png");
			
		}
		//array to hold the musics
		music = new soundTrack[4];
		// 1 = classic, 2 = Positions, 3 = Robbery, 4= Cat
		String[] names = {"1. Classic theme" , "2. Positions - Ariana Grande", "3. Robbery - Juice Wrld" , "4. Kiss me more - Doja Cat"};
		for(int i  = 0;i< music.length;i++) {
			Clip song = null;
			try {
				
				AudioInputStream sound = AudioSystem.getAudioInputStream(new File ("soundTrack/" + i + "s.wav"));
				song= AudioSystem.getClip();
				song.open(sound);
				
				
			} 
			catch (Exception e) {
			}
			Image cover = Toolkit.getDefaultToolkit().getImage("soundTrack/"+ i + "c.png");
			
			music[i] = new soundTrack(i, names[i], song, cover );
		}
		//tells if music is playing
		
		playing = false;
		
		
		
		

	}
	/**
	 * initialize the games based on the mode
	 * @param mode what mode: Single player or multiplayer
	 */
	public void init(int mode) {
		
		//mode 1 = single player, mode 2 = battle
		if (mode == 1) {
			games = new game [1];
			games[0] = new game(50,100, 20, false);
		}
		if (mode == 2) {
			games = new game[1];
			
			games[0] = new game(300,100, 20, true);
		}
		//set ingame to true
		inGame = true;
	}
	/**
	 * draws the homescreen and the buttons
	 * @param g the graphics to draw to
	 */
	public void drawHome(Graphics g) {
		//tells program what screen the game is in
		status = 0;
		g.drawImage(loading, 0, 0, WIN_WIDTH, WIN_HEIGHT,this);
		g.drawImage(logo,50,50,400,300,this);
		drawButton (startB,g);
		drawButton (battleB,g);
		drawButton(settingB, g);
		drawButton(ruleB, g);
		drawButton(about, g);
		
//		frame.add(box);
	}
	/**
	 * draws the rule screen
	 * @param g graphics to draw
	 */
	public void drawRules(Graphics g) {
		g.drawImage(rule[rStatus], 0, 0,WIN_WIDTH, WIN_HEIGHT,this);
		drawButton(back, g);
		drawButton(nextR,g);
	}
	/**
	 * draws the button
	 * @param b the button
	 * @param g the graphics to draw to
	 */
	public void drawButton(button b,Graphics g) {
		
		//draws the button image
		g.drawImage(b.button, b.x, b.y,b.width, b.height,this);

		
	}
	public void aboutScreen(Graphics g) {
		Font f =new Font("Comic Sans MS", Font.BOLD, 100);
		g.drawImage(loading, 0, 0, WIN_WIDTH, WIN_HEIGHT, this);
		g.setFont(f);
		drawButton(back, g);
		g.drawString("By: Andy Liang", 50, 250);
	}
	/**
	 * runs the game with the thread
	 */
	public void run() {
		
		
		//while the game is running
		while(inGame) {
			// game loop
			//update the game and repaint
			update();
			this.repaint();
			
			try {
				//sleep and fps count
				FPScount++;
				Thread.sleep(1000/FPS);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * draws the game over screen
	 * @param g graphics
	 */
	public void gameOver(Graphics g) {
		status = 4;
		drawButton(back, g);
		g.drawImage(gameEnd, WIN_WIDTH/4,WIN_HEIGHT/4, WIN_WIDTH/2,WIN_HEIGHT/2, this);
	}
	/**
	 * draws the setting screen
	 * @param g the graphics for the game
	 */
	public void changeKey(Graphics g) {
		status = 2;
		
		//startB = new button(550, 50, 260, 150,Toolkit.getDefaultToolkit().getImage("buttons/startB.png") , 1);
		//draws the button for the controls
		int x = 25;
		int y = 30;
		int len = 100;
		int border = 25;
		int hi = 50;
		g.setColor(Color.WHITE);
		g.drawImage(backS, 0, 0, WIN_WIDTH, WIN_HEIGHT, this);
		for (int i =0 ;i< 6;i++) {
			cButtons[i] = new button(x,y, len, hi, Toolkit.getDefaultToolkit().getImage("cButtons/" + i+ ".png"), 69 + i );
			x+= (len+border);
			if (x+25+len >WIN_WIDTH ) { x = 25; y+= hi+ 20;}
			drawButton(cButtons[i],g);
		}
		drawButton(back, g);
		drawButton(next, g);
		drawButton(previous,g);
		//draw the sound track gui
		g.drawString("Soundtracks", 60, 190);
		g.drawImage(music[curSong].cover,85, 200, 125, 125, this);
		g.drawString(music[curSong].name, 60, 340);
		if (playing) {
			drawButton(stopM, g);
		}else {
			
			drawButton(playM,g);
		}
		//repaint
		this.repaint();
		
	}
	/**
	 * updates the game
	 */
	public void update() {
		//go through the games
		for(game g : games) {
			//move down every ~half a second
			if (FPScount%5 == 0)
				if (g.moveV() == false) {
					g.place(false);
				}
			//if not ai check the controls
			if (!g.auto) {
				if (left) {
					g.moveH(-1);
				}
				if (right) g.moveH(1);
				if (down) g.moveV();
				if (up)g.rotatePiece();
				if (hardDrop) {while(g.moveV());hardDrop = false;g.place(false);}
				if (hold) g.hold();
			}else {
				//check the bot's move queue or calculate the new move 
				if (g.moves.size() >0) {
					if (g.moves.peek() == 3) {
						if (!g.moveV()) g.moves.poll();
					}else {
						 if (g.moves.peek() == 2) {
							 g.moveV();
							 g.moves.poll();
						 }else {
							 if (g.moves.peek() == 4) {
								 g.rotatePiece();
								 g.moves.poll();
							 }
							 else g.moveH(g.moves.poll());
						 }
					}
				}else {
					g.autoMove();
				}
			}
		}
	}
	/**
	 * draws the graphics
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//if in the game, draw the game boards
		if (inGame == true) {
			for (game gam : games) {
				if (gam != null)drawBoard(g, gam, gam.xx, gam.yy,gam.len);
			}
		}else {
			//draws a screen
			//System.out.println(settings + " " + rules);
			if (over == true) gameOver(g);
			else if (settings) changeKey(g);
			else if ( rules) drawRules(g);
			else if (aboat == true) aboutScreen(g);
			else if (status == 0) drawHome(g);
			
			
			
		}
		
	}
	/**
	 * draws the game board
	 * @param g the graphics
	 * @param gam the game object
	 * @param x location
	 * @param y location
	 * @param len length of each square
	 */
	public void drawBoard(Graphics g, game gam, int x, int y, int len) {
		//set status of game to 1
		status = 1;
		//get board and make the text
		game gamee = gam;
		block [][]board = gamee.board;
		Font f = new Font("Comic Sans MS", Font.BOLD, len);
		g.setFont(f);
		int mX = 0;
		if (!gam.auto)g.drawImage(gbackdrop, 0,0, WIN_WIDTH,WIN_HEIGHT, this);
		g.drawImage(box, x-len, y-len, 7* len, 7*len, this);
		drawButton(back, g);
		g.drawString("Level: " + gamee.level,x , y-50);
		g.drawString("Score: "+gamee.score, x, y-30);
		//go through the hold grid to draw
		for (int i = 0 ;i<gamee.holdGrid.length;i++) {
			for(int j = 0; j< gamee.holdGrid[i].length;j++) {
				if (gamee.holdGrid[i][j]!= 0) g.drawImage(pieceImage[gamee.holdGrid[i][j]], x+ j*len, y+i*len, len,len, this);
			}
		}
		//draws teh game board
		g.drawImage(gameBack, x+5*len, y-len, 12*len, 22*len, this);
		for (int i = 0; i< board.length; i++ ) {
			for(int j = 0; j< board[i].length;j++) {
				if (board[i][j] != null)g.drawImage(pieceImage[board[i][j].getType()], x+(j+6)*len, y+(i)*len, len, len, this);
				mX = Math.max(mX, x+(j+6)*len);
			}
			
		}
		//draws the next grids 
		g.drawImage(nexBox, mX+2*len, y-len, 6*len, 18*len,this);
		
		for(int n = 0; n< 3;n++) {
			for (int i = 0; i< gamee.nexGrid[n].length; i++ ) {
				for(int j = 0; j< gamee.nexGrid[n][i].length;j++) {
					if (gamee.nexGrid[n][i][j] != 0)g.drawImage(pieceImage[gamee.nexGrid[n][i][j]], x+(j)*len+mX, y+(i)*len+n*4*len, len, len, this);
					
				}
				
			}
		}
	}
	/**
	 * gets a random number in a certain range
	 * @param min the smallest number possible
	 * @param max the largest number possible -1
	 * 
	 */
	public static int ranNum (int min , int max) {
		return (int)((Math.random()*(max - min))+ min);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	/**
	 * checks to see the user movements
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		//if it is in change key mode
		if (swtch != -1) {
			//changes the switching key to the key pressed and send message, reset.
			
			controls[swtch] = key;
			JOptionPane.showMessageDialog(this, "Your key has succesfully binded to " + key+ "\n Idk how to translate that to a key press", "NEW KEY", JOptionPane.INFORMATION_MESSAGE);
			swtch = -1;
		}else {
			//for each control check if it is pressed the do the move
			if(key == controls[0]) {
				left = true;
				right = false;
			}else if(key == controls[2]) {
				
				
				right = true;
				left = false;
			}else if(key == controls[1]) {
				up = true;
				down = false;
			}else if(key == controls[3]) {
				down = true;
				up = false;
			}else if(key ==controls[5]) {
				hardDrop = true;
			}else if(key == controls[4]) {
				hold = true;
			}
		}
			
	}
	/**
	 * check if the move is released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//based on the key reset the move
		int key = e.getKeyCode();
		if(key == controls[0]) {
			left = false;
		}else if(key == controls[2]) {
			right = false;
		}else if(key == controls[1]) {
			up = false;
		}else if(key == controls[3]) {
			down = false;
		}
	}
	/**
	 * if the mouse is clicked do something
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		//if in the main menu
		if(status == 0)
			//check array
			for (button c : buttons) {
				if(c.check(x, y)) {
					//do the function
					int func = c.function;
					if (func == 1) {
						init(1);
						thread = new Thread(this);
						thread.start();
					}
					if (func == 2) {
						init(2);
						thread = new Thread(this);
						thread.start();
					}
					if (func == 3) {
						
						settings = true;
						this.repaint();
					}
					if (func == 4) {
						rules = true;
						status = 3;
						this.repaint();
					}
					if (func == 5) {
						aboat = true;
						status = 5;
						this.repaint();
					}
				}
				
			}
		
		//if in the settings menu
		if (status == 2) {
			//message user and get the index of control to change
			for (button keyChange : cButtons) {
				if (keyChange.check(x, y)) {
					JOptionPane.showMessageDialog(this, "Press the key you want to bind to?", "CHANGE KEY", JOptionPane.INFORMATION_MESSAGE);
					int func = keyChange.function-69;
					
					swtch = func;
					
				}
				
				//pop up message maybe saying "press key you want to change to"
			}
			//change the music
			if (playing) {
				
				if (stopM.check(x, y)) {
					playing = false;
					music[curSong].stop();
				}
				else if(next.check(x, y)) {
					music[curSong].stop();
					
					if(curSong == music.length-1) {
						curSong = 0;
					}else curSong++;
					music[curSong].play();
					
				}	
				else if (previous.check(x, y)) {
					
					
					music[curSong].stop();
					if(curSong == 0) {
						curSong = music.length-1;
					}else curSong--;
					music[curSong].play();
					
				}
			}else {
				if (playM.check(x,y)) {
					
					playing = true;
					music[curSong].play();
				}
				else if (previous.check(x, y)) {
					if(curSong == 0) {
						curSong = music.length-1;
					}else curSong--;
				}
				else if(next.check(x, y)) {
					if(curSong == music.length-1) {
						curSong = 0;
					}else curSong++;
				}
			}
			
			
			
		}
		//check the next button in the rule screen
		if (status == 3) {
			if (nextR.check(x, y)) {
				if (rStatus == rule.length-1 ) rStatus = 0;
				else rStatus ++;
				this.repaint();
			}
		}
		//if not in main menu check the back button
		if (status != 0) {
			if(back.check(x, y)) {
				
				status = 0;
				settings = false;
				inGame = false;
				over = false;
				rules = false;
				aboat = false;
				this.repaint();
			}
		}
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
	//block class for the game array
	static class block{
		//checks if it is placed or current piece
		boolean placed = false;
		//the type for the color
		int type = 0;
		//intializer
		block(int ty, boolean pla){
			type = ty; placed = pla;
		}
		//functions that change the variables
		void setPlaced(boolean mode) {placed = mode;};
		void setType(int ty) {type = ty;}
		int getType() {return type;}
		boolean getPlaced() {return placed;}
	}
	//piece class
	static class piece {
		
		int type;
		int rotation = 1;
		boolean held = false;
		int top, bottom, left, right;
		int []x,y,shapeX, shapeY;
		block [][] board;
		block myBlock;
		//type of piece and the gameboard associated with
		piece(int type, block [][] bo){
			//System.out.println("new piece");
			board = bo;
			//make a the block to refrence in the board
			myBlock = new block(type, false);
			//based on type, create shape and location
			if (type == 1) {
				int [] x1 = {4,5,6,7};
	            int [] y1 = {0, 0, 0,0};
	            x = x1;
	            y = y1;
	            left = 4;
	            right = 7;
	            bottom = 0;
			}
			if (type ==2) {
				int[] x1 = {5, 6, 7,7};
	            int[] y1 = {0, 0, 0,1};
	            x = x1;
	            y = y1;
	            left = 5;
	            right = 7;
	            bottom = 1;
			}
			if (type == 3) {
				int[] x1 = {5, 6, 7,5};
	            int[] y1 = {0, 0, 0,1};
	            x = x1;
	            y = y1;
	            left = 5;
	            right = 7;
	            bottom = 1;
			}
			if (type == 4) {
				int[] x1 = {5, 6, 5,6};
	            int[] y1 = {0, 0, 1,1};
	            x = x1;
	            y = y1;
	            left = 5;
	            right = 6;
	            bottom = 1;
			}
			if (type == 5) {
				int[] x1 = {5, 6, 6,7};
	            int[] y1 = {0, 0, 1,1};
	            x = x1;
	            y = y1;
	            left = 5;
	            right = 7;
	            bottom = 1;
			}
			if (type == 6) {
				int[] x1 = {5, 6, 7,6};
	            int[] y1 = {0, 0, 0,1};
	            x = x1;
	            y = y1;
	            left = 5;
	            right = 7;
	            bottom = 1;
			}
			if (type == 7) {
				int[] x1 = {7, 6, 6,5};
	            int[] y1 = {0, 0, 1,1};
	            x = x1;
	            y = y1;
	            left = 5;
	            right = 7;
	            bottom = 1;
			}
			this.type = type;
			//
			shapeX = x.clone();
			shapeY = y.clone();
			
			//places intial piece in the board
			
		}
		//moves the piece to the top/ for the bot
		public void top() {
			for (int i = 0 ;i< y.length;i++) {
				y[i] = shapeY[i];
			}
		}
		/**
		 * places the piece first and check if it can fit
		 * @return if false than game over
		 */
		public boolean first() {
			boolean end = false;
			
			for(int n = 0; n < x.length;n++) {
				
				int Y = y[n];
				int X = x[n];
				
				if (board[Y][X] != null) {
					
					end = true;
					break;
				}
			}	
			if (end == false)
				for (int i = 0; i < x.length;i++)board[y[i]][x[i]] = myBlock;
//			for (block [] b : board) {
//				for (block f: b) {
//					if (f == null) System.out.print(".");
//					else System.out.print("X");
//				}
//				System.out.println();
//			}
			//System.out.println("raklflkjaflafkalf");
			
			return end;
		}
		/**
		 * removes the piece from the board
		 */
		public void remove() {
			for (int i = 0; i< x.length;i++) {
				board[y[i]][x[i]] = null;
			}
		}
		/**
		 * finalize the piece place 
		 */
		public void fin() {
			for (int i = 0; i< x.length;i++) {
				board[y[i]][x[i]].setPlaced(true);
			}
		}
		/**
		 * moves the current piece downwards
		 * @return returns if possible
		 */
		public boolean moveV() {
			//check collisions
			if (!checkCol(2))return false;
			//increase bottom and moves the piece down in both board and y locations
			bottom ++;
			for(int i = 0; i< x.length;i++) {
				
				board[y[i]][x[i]] = null; 
				y[i]+= 1;
				
			}
			for(int i = 0; i< x.length;i++) {
				
				board[y[i]][x[i]] = myBlock; 
				
				
			}
			return true;
			
		}
		/*
		 * moves the current piece horizontally
		 */
		public boolean moveH(int di) {
			//check the collision
			if (!checkCol(di))return false;
			//moves the piece horizontally in the board and x position
			for(int i = 0; i< x.length;i++) {
				
				board[y[i]][x[i]] = null; 
				x[i]+= di;
			}
			for(int i = 0; i< x.length;i++) {
				
				board[y[i]][x[i]] = myBlock; 
				
			}
			left+= di;
			right+= di;
			return true;
		}
		/**
		 * checks if the piece can move in that direction
		 * @param di the direction moving(1 = right, -1 = left, 2 = down)
		 * @return
		 */
		public boolean checkCol(int di) {
//			for(block[] p : board) {
//				for(block h : p) {
//					if (h == null) System.out.print(".");
//					else System.out.print("X");
//					
//				}
//				System.out.println();
//			}
//			System.out.println("\n");
			if (di == 2) {
				//check if at the bottom
				if (bottom == 19)return false;
				//check if the block moving to is nothing or part of the current piece
				for(int i = 0; i< y.length;i++) {
					if (board[y[i]+1][x[i]] != null &&board[y[i]+1][x[i]].getPlaced() != false ) {
						return false;
					}
				}
				return true;
			}
			
			if (di != 2) {
				//check if it is either sides of the board
				if (left+di >= 0 && right +di <10) {
					//check if the block moving to is nothing or part of the current piece
					for(int i = 0; i< y.length;i++) {
						if (board[y[i]][x[i]+di] != null &&board[y[i]][x[i]+di].getPlaced() != false ) {
							return false;
						}
					}
					return true;
				}
				return false;
				
			}
			return false;
			
		}
		/**
		 * rotates the piece
		 * @return if possible to rotate
		 */
		public boolean rotatePiece() {
			//array to hold the rotated locations
			int [] rY = new int [4];
			int [] rX = new int[4];
			//if it is a square because square
			if (type == 4) return true;
			//rotate through each block
			for(int i = 0; i< x.length;i++) {
				//i is the rotation point
				if(i == 1) {
					rY[1] = y[1];
					rX[1]= x[1];
				}else {
					//move the location based on the rotation point
					int dx = x[1]-x[i];
					int dy = y[1]-y[i];
					dy *= -1;
					rY[i] = y[1]+ dx;
					rX[i] = x[1]+dy;
					//check if it is out of boounds
					if(rY[i] < 0 || rY[i] >19 || rX[i] < 0 || rX[i] >9) {
						return false;
					}
					//check if it not open space or not own piece
					if(board[rY[i]][rX[i]] == null || board[rY[i]][rX[i]].placed== false) {
						
					}else return false;
				}
			}
			//change the right, left, and bottom from rotations
			right = Integer.MIN_VALUE; left = Integer.MAX_VALUE; bottom = Integer.MIN_VALUE;
			for(int i = 0; i< y.length;i++) {
				
				board[y[i]][x[i]] = null;
				y[i] = rY[i];
				x[i] = rX[i];
				left = Math.min(rX[i], left);
				right = Math.max(rX[i], right);
				bottom = Math.max(rY[i], bottom);
				
			}
			//change the board
			for(int i = 0; i< y.length;i++) {
				board[rY[i]][rX[i]] = myBlock;
			}
			return true;
		}
		/**
		 * returns the type of the piece
		 * @return type
		 */
		public int getType() {return type;};
		//0 = dead piece, 1 = line, 2 = blue L, 3 = orange L, yellow = square, 5 = green z , 6 = T, 7 = red z
		
	}
	//class for the game
	static class game{
		
		block [][] board;
		int [][] holdGrid = new int[4][4];
		Queue<Integer> nex = new LinkedList();
		Queue <Integer> moves = new LinkedList();
		
		int [][][] nexGrid = new int[3][4][4];
		piece curPiece;
		int heldType,mode;
		int totalLines = 0;
		int level = 1;
		int score = 0;
		int len;
		boolean auto = false;
		int best;
		int xx, yy;
		
		game(int x, int y, int l, boolean a){
			//variables
			xx = x;yy = y; 
			len = l;
			board = new block[20][10];
			//next piece and create the next piece
			nex.add(ranNum(1,8));
			nextPiece(true);
			//if the game is automated
			auto = a;
		}
		/**
		 * creates the next piece 
		 * @param start if it is the start of the game
		 */
		public void nextPiece(boolean start) {
			//current piece is now the next piece in the queue
			curPiece = new piece(nex.poll(), board);
			//if the piece can't be placed end the game
			if (curPiece.first() == true) {
				
				
				inGame = false;
				//System.out.println("ran");
				over = true;
			}
			
			int next;
			//if it is the start of the game
			if (start == true) {
				//generate 3 new pieces
				for(int i = 0; i< 3;i++) {
					next = ranNum(1,8);
					//add to the next grid and the queue
					for(int l = 0; l<4;l++) {
						
						nexGrid[i][gridPosY[next][l]][gridPosX[next][l]] = next;
						
					}
					nex.add(next);
					
				}
			}else {
				//creates one new piece to add to the next 
				next = ranNum(1,8);
				//hold the array for the first
				int [][] temp = nexGrid[0];
				//push the grid up one
				nexGrid[0] = nexGrid[1];
				nexGrid[1] = nexGrid[2];
				//set the last to the previous first
				nexGrid[2] = temp;
				//change the last grid to the new piece
				for(int i = 0; i<4; i++)
					for(int h = 0; h<4;h++) nexGrid[2][i][h] = 0;
				for(int i = 0; i< 4;i++) {
					nexGrid[2][gridPosY[next][i]][gridPosX[next][i]] = next;
					
				}
				//add next to the queue
				nex.add(next);
			}
//			for (int [][] bruh:  nexGrid) {
//				for (int [] bruu: bruh) {
//					for(int q: bruu) {
//						System.out.print(q);
//						
//					}
//					System.out.println();
//				}
//				System.out.println("\n");
//			}
			
			
		}
		/**
		 * places the piece and checks for completed lines
		 * @param test if it is automated
		 * @return for automation, the score the placement is
		 */
		public int place(boolean test) {
			//if it is not a test, finalize
			if (test==false)curPiece.fin();
			int completed = 0;
			// check through 4 times as that is the maximum lines completed using one piece
			for(int i = 0; i< 4;i++) {
				//go through each row
				for(int q = 0; q < board.length;q++) {
					//see if line should be removed
					boolean remove = true;
					for(block point : board[q]) {
						//if open space don't remove
						if (point == null) {
							remove = false;
							
							break;
						}
					}
					//if remove is true, erase each block in the row
					if (remove == true) {
						for(int b = 0; b< board[q].length;b++) {
							board[q][b]= null;
						}
						//forgot to add this which led to when ever a row was removed the rows would be assigned to the same row address. 
						//Took me like 20 minutes to decode T_T
						//temp to hold memory location of the removed row
						block [] temp = board[q];
						//add to complete
						completed++;
						//for auto don't remove the lines
						if (!auto) {
							//shit all rows above the the removed row down one
							for(int b = q-1; b >= 0;b--) {
								
								board[b+1] = board[b];
								
							}
							//set the blank row to the top
							board[0] = temp;
						}
					}
				}
			}
			//if not automated
			if (test==false) {
				//new piece
				nextPiece(false);
				//add the stats
				score+= (level)*scoreChart[completed];
				totalLines+= completed;
				level = totalLines/10;
				level++;
				
				return 0;
			}else {
				//returns the value of the board
				return totalLines;
			}
		}
		/**
		 * holds the piece for the game
		 */
		public void hold() {
			//if the piece hasn't been held yet
			if (curPiece.held == false) {
				//reset the hold grid
				for (int i = 0; i< holdGrid.length;i++) {
					for(int b = 0; b< holdGrid[i].length;b++) {
						holdGrid[i][b] = 0;
					}
				}
				//remove the piece from the board
				curPiece.remove();
				//get the type of the current piece
				int curType = curPiece.getType();
				//set the hold grid based on type
				for (int i = 0; i< 4; i++) {
					holdGrid[gridPosY[curType][i]][gridPosX[curType][i]] = curType;
				}
				//if there isn't any piece in the hold
				if (heldType == 0) {
					
					//set held type as current type and make next piece
					heldType = curType;
					nextPiece(false);
					
				}else {
					//curPiece is new piece as the held type
					curPiece = new piece(heldType,board);
					//held type is current type and set held to true
					heldType = curType;
					curPiece.held = true;
				}
			}
			
			hold = false;
		}
		/**
		 * function to generate move for the bot
		 */
		public void autoMove() {
			
			//best horizontal shift for the piece
			int bHor = 0;
			//current best score
			best = 0;
			//rotation for the best 
			int bRot = 0;
			//horizontal shift of current piece
			int h = 0;
			
			//move the piece all the way to the left 
			while (moveH(-1)) h --;
			
			do {
				
				
				//for each x position, check each rotation
				for (int i = 0; i< 4;i++) {
					//make a clone of the real board and sets it to the current piece
					block[][] test = board.clone();
					curPiece.board = test;
					//moves the piece to the top
					curPiece.top();
					
					
					//move 4 down to give enough space for each rotation
					curPiece.moveV();
					curPiece.moveV();
					curPiece.moveV();
					curPiece.moveV();
					moves.add(3);
					moves.add(3);
					moves.add(3);
					moves.add(3);
					//if cannot be rotated, don't check rest of rotations
					
					//move the piece to the bottom
					while(curPiece.moveV());
					//check the line completed
					int lines = place(true);
					//count the number of holes
					int holes = checkHoles(test);
					//finds the height of the tallest point 
					int tallest = curPiece.top;
					//calculates the value
					int val = lines*6+holes*10+ tallest*1;
					//if it is better than the best, change the best locations
					if (val > best) {
						best = val;
						bHor = h;
						bRot = i;
					}
					
					
				}
				
			}while(moveH(1));
			//add rotations to the move  queue
			while (bRot != 0) {
				moves.add(4);
				bRot--;
			}
			//resets the current piece for actual move
			curPiece = new piece(curPiece.getType(), board);
			//add the horizontal movements to the move queue
			while (bHor != 0) {
				if (bHor< 0) {
					bHor++;
					moves.add(-1);
				}
				else {
					bHor--;
					moves.add(1);
				}
				
			}
			//add 2 to move the piece to the bottom
			moves.add(2);
			
			
		}
		/**
		 * counts the open space not able to be reached from hard drop
		 * @param boar board to check
		 * @return number of holes
		 */
		public int checkHoles(block [][] boar) {
			//boolean array to see if the column is blocked
			boolean [] blocked = new boolean [10];
			//counter variable
			int holes = 0;
			//for each block in the board
			for (int i = 0 ; i < 20;i++) {
				for (int j = 0 ;j < 10;j++) {
					//if space is empty and if the column is blocked, add to the holes
					if(boar[i][j] == null) {
						if (blocked[j]) holes++;
					}else {
						//if it is a block, set block to true
						blocked [j]= true;
						
					}
				}
			}
			return holes;
		}
		/**
		 * moves the piece horizontally 
		 * @param di direction of the movements
		 * @return if can be moved
		 */
		public boolean moveH(int di) {
			return curPiece.moveH(di);
		}
		/**
		 * moves the piece down
		 * @return if can be moved
		 */
		public boolean moveV() {
			if (curPiece != null) {
				return curPiece.moveV();
			}
			return false;
		}
		/**
		 * rotates the current piece
		 */
		public void rotatePiece() {
			curPiece.rotatePiece();
		}
		
	}
	//button class
	static class button{
		int function;
		Image button;
		int x,y,width, height, clicked;
		//sets the variables
		public button(int x0, int y0, int width0,int height0, Image button0, int func0) {
			this.x = x0; this.y = y0; this.width = width0; this.height = height0; button = button0; function = func0;clicked = -1;
		}
		//check if the button is clicked
		boolean check (int x1, int y1) {
			//idk why my buttons are always offset by 30 pixels. I cropped the image so that there was no blank space
			x1-= 10;
			y1 -=30;
			//System.out.println("Click location: " + x1 + " Lowest y: " + this.x + " Highest y: " + (x+this.width));
			if (x1 >= this.x && x1 <=this.x+this.width && y1 >= this.y && y1 <= y+this.height) {this.clicked = 0;return true;}
			else return false;
		}
		
	}
	// sound tracks for the game
	static class soundTrack{
		String name;
		int idx;
		Clip song;
		Image cover;
		//sets the variables
		public soundTrack(int i0, String n0, Clip s0, Image c0 ) {
			idx = i0;
			song = s0;
			cover = c0;
			name = n0;
		}
		//plays the song in looped and set playing to true
		void play() {
			song.setFramePosition(1);
			song.start();
			song.loop(Clip.LOOP_CONTINUOUSLY);
			playing = true;
		}
		//stops teh song and set playing to false
		void stop() {
			song.stop();
			playing = false;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame = new JFrame ("Tetris");
		Tetris myPanel = new Tetris ();

		frame.add (myPanel);
		
		frame.addKeyListener(myPanel);
		//so you can actually get mouse input
		frame.addMouseListener(myPanel);
		//self explanatory. You want to see your frame
		frame.setVisible(true);
		//some weird method that you must run
		frame.pack();
		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		
		//place your frame in the middle of the screen
		frame.setLocationRelativeTo(null);
		//without this, your thread will keep running even when you windows is closed!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//self explanatory. You don't want to resize your window because
		//it might mess up your graphics and collisions
		frame.setResizable(false);
	}

}
