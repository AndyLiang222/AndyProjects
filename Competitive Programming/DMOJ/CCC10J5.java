package contest;

import java.io.*;
import java.util.*;
public class CCC10J5 {
	public static int fastest = Integer.MAX_VALUE;
	public static int[][] board = new int[9][9];
	
	public static void dfs (int x, int y, int moves, int fx, int fy) {
		
		if(x<=8 && x>0&& y<=8 && y>0){
			if (moves < board[y][x]) {
				board[y][x] = moves;
				//System.out.println(x + " " + y + ": " +  moves);
				if(x == fx && y == fy) {
					fastest = moves;
				}else {
					
					dfs(x+2, y+1, moves+1, fx, fy );
					dfs(x+2, y-1, moves+1, fx, fy );
					dfs(x-2, y+1, moves+1, fx, fy );
					dfs(x-2, y-1, moves+1, fx, fy );
					//System.out.println();
					dfs(x+1, y+2, moves+1, fx, fy );
					
					dfs(x+1, y-2, moves+1, fx, fy );
					dfs(x-1, y+2, moves+1, fx, fy );
					dfs(x-1, y-2, moves+1, fx, fy );
				}
			}
		}
	}
	public static void main (String [] args)throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String sPosition = br.readLine();
		int col = Integer.parseInt(sPosition.split(" ")[0]);
		int row = Integer.parseInt(sPosition.split(" ")[1]);
		String end = br.readLine();
		int eCol = Integer.parseInt(end.split(" ")[0]);
		int eRow = Integer.parseInt(end.split(" ")[1]);
		for (int i = 1; i < 9;i++) {
			for (int s = 1; s<9; s++) {
				board [i][s] = Integer.MAX_VALUE;
			}
		}
		
		dfs(col, row, 0, eCol, eRow );
		System.out.println(fastest);
//		LinkedList <Integer> Cqueue = new LinkedList<>();
//		LinkedList <Integer> Rqueue = new LinkedList<>();
//		while (Cqueue.size() != 0 && Rqueue.size() != 0) {
//			row = Rqueue.poll();
//			col = Cqueue.poll();
//			if (row == eRow && col == eCol) {
//				
//				break;
//			}else {
//				Cqueue
//			}
//		}
	}
}
