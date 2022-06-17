package example;

import java.awt.*;

public class Textbox extends Comp{
	//string that holds the text
	private String s = "";
	//images for text boxes
	static Image img = Toolkit.getDefaultToolkit().getImage("Backgrounds/TextboxImg.png");
	static Image searchImg = Toolkit.getDefaultToolkit().getImage("Buttons/Search.png");
	private Button search;
	static int maxLen = 8;
	public Textbox(int x, int y, int w, int l, Player p) {
		super(x,y,w,l, img,p);
		//make button for search that is next to the textbox
		search = new Button(x+w,y,30, l,searchImg, 8,p);
	}
	public Textbox(int x, int y, int w, int l, Player p,String s) {
		super(x,y, w,l,img, p);
		this.s = s;
		//make button for search that is next to the textbox
		search = new Button(x+w,y,30, l,searchImg, 8,p);
		
	}
	//getters and setters
	public int len() {
		return s.length();
	}
	public String toString() {
		return s;
	}
	public void decStr() {
		if(len() > 0)s = s.substring(0, len()-1);
	}
	public void addStr(String a) {
		if(len() != maxLen)s+= a;
		
	}
	//search button
	public Button getSearch() {
		return search;
	}
	//check if search button is clicked
	public boolean checkSearch(int x1, int y1) {
		return search.check(x1, y1);
	}
	
}
