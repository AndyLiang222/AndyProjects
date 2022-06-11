package contest;
import java.io.*;
import java.util.*;
public class CCC03S3 {
	static boolean [][]vis;static String[][]house;static LinkedList<Integer> size = new LinkedList<>();static int length; static int width;
	public static void BFS(int clength, int cwidth) {
		Queue <Integer>y = new LinkedList<>();
		Queue <Integer>x = new LinkedList<>();
		y.add(clength);
		x.add(cwidth);
		vis[clength][cwidth] = true;
		int area = 0;
		while (!y.isEmpty()) {
			int cY = y.poll();
			int cX = x.poll();
			
			area++;
			if(cY+1<length) {
				//System.out.println("ran" + cY + " " + cX);
				if (vis[cY+1][cX]==false && !"I".equals(house[cY+1][cX])) {
					y.add(cY+1);
					x.add(cX);
					vis[cY+1][cX] =true;
				}
				
			}
			if(cY-1>=0) {
				if (vis[cY-1][cX] == false && !"I".equals(house[cY-1][cX])) {
					y.add(cY-1);
					x.add(cX);
					vis[cY-1][cX] =true;
				}
			}
			if(cX+1<width) {
				if (vis[cY][cX+1]== false && !"I".equals(house[cY][cX+1])) {
					y.add(cY);
					x.add(cX+1);
					vis[cY][cX+1] =true;
				}
			}
			if(cX-1>=0) {
				if (vis[cY][cX-1] == false && !"I".equals(house[cY][cX-1])) {
					y.add(cY);
					x.add(cX-1);
					vis[cY][cX-1] =true;
				}
			}
		}
		//System.out.println(area);
		size.add(area);
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int floor = Integer.parseInt(br.readLine());
		length = Integer.parseInt(br.readLine());
		width = Integer.parseInt(br.readLine());
		house = new String [length][width];
		
		for (int i = 0; i< length ; i++) {
			house[i] = br.readLine().split("");
		}
		vis = new boolean [length][width];
		for (int l  = 0; l<length;l++) {
			for (int w = 0; w<width;w++) {
				if (vis[l][w] == false && ! "I".equals(house[l][w])) {
					//System.out.println(l+ " " + w);
					BFS(l, w);
				}
			}
		}
		size.sort(null);
		//System.out.println(size);
		int rooms = 0;
		while (floor >0&& size.size()>0) {
			int next = size.pollLast();
			
			//System.out.println(next);
			if (floor >= next) {
				rooms++;
				floor = floor - next;
			}else {
				break;
			}
		}
		if (rooms == 1) {
			System.out.println(rooms + " room, " + floor+ " square metre(s) left over");
		}else {
			System.out.println(rooms + " rooms, " + floor+ " square metre(s) left over");
		}
		
		
	}

}
