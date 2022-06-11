package contest;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
public class AlexAnimals {
	static boolean [][]vis;static int length; static int width;static String [][] house; static boolean monkey;
	public static void BFS(int clength, int cwidth) {
		Queue <Integer>y = new LinkedList<>();
		Queue <Integer>x = new LinkedList<>();
		y.add(clength);
		x.add(cwidth);
		vis[clength][cwidth] = true;
		
		while (!y.isEmpty()) {
			int cY = y.poll();
			int cX = x.poll();
			
			
			if ("M".equals(house[cY][cX])) {
				monkey = true;
			}
			if(cY+1<length) {
				//System.out.println("ran" + cY + " " + cX);
				if (vis[cY+1][cX]==false && !"#".equals(house[cY+1][cX])) {
					y.add(cY+1);
					x.add(cX);
					vis[cY+1][cX] =true;
				}
				
			}
			if(cY-1>=0) {
				if (vis[cY-1][cX] == false && !"#".equals(house[cY-1][cX])) {
					y.add(cY-1);
					x.add(cX);
					vis[cY-1][cX] =true;
				}
			}
			if(cX+1<width) {
				if (vis[cY][cX+1]== false && !"#".equals(house[cY][cX+1])) {
					y.add(cY);
					x.add(cX+1);
					vis[cY][cX+1] =true;
				}
			}
			if(cX-1>=0) {
				if (vis[cY][cX-1] == false && !"#".equals(house[cY][cX-1])) {
					y.add(cY);
					x.add(cX-1);
					vis[cY][cX-1] =true;
				}
			}
		}
	}
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		length = Integer.parseInt(input[0]);
		width = Integer.parseInt(input[1]);
		house = new String [length][width];
		
		for (int i = 0; i< length ; i++) {
			house[i] = br.readLine().split("");
		}
		int holes = 0;
		vis = new boolean [length][width];
		for (int l  = 0; l<length;l++) {
			for (int w = 0; w<width;w++) {
				if (vis[l][w] == false && ! "#".equals(house[l][w])) {
					//System.out.println(l+ " " + w);
					BFS(l, w);
					if (monkey == true) {
						holes++;
					}
					monkey = false;
				}
			}
		}
		System.out.println(holes);
	}

}
