package contest;
import java.io.*;
import java.util.*;
public class CCC02S1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int [] costs ; static int lowest;boolean [][][][] memo;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		costs = new int[4];
		int max = 0;
		for (int i = 0; i< 4; i++) {
			costs[i] = readInt();
			
		}
		int min = Integer.MAX_VALUE;
		int total = readInt();
		int cou = 0;
		for (int p = 0; p <=total/costs[0]; p++) {
			for (int g = 0; g <=total/costs[1]; g++) {
				for (int r = 0; r <=total/costs[2]; r++) {
					for (int o = 0; o <=total/costs[3]; o++) {
						if (total == p*costs[0] + g * costs[1] + r * costs[2] + o*costs[3] ) {
							cou++;
							min = Math.min(min, p+g+r+o);
							System.out.println("# of PINK is " +  p + " # of GREEN is " +  g +  " # of RED is " +  r + " # of ORANGE is " + o);
						}
					}
				}
			}
		}
		System.out.println("Total combinations is " +  cou + ".");
		System.out.println("Minimum number of tickets to print is " +  min + ".");
		
	}
//	static int count (int m, int p, int g, int r, int o, int move) {
//		if (m < 0) {
//			return 0;
//		}
//		if (m == 0) {
//			lowest = Math.max(lowest, move);
//			System.out.println("# of PINK is " +  p + " # of GREEN is " +  g +  " # of RED is " +  r + " # of ORANGE is " + o);
//			return 1;
//			
//			
//		}else {
//			int t = 0;
//			if (g == 0)t += count(m- costs[0], p+1, g, r, o, move++);
//			if(r == 0)t += count(m- costs[1], p, g+1, r, o, move++);
//			if (o == 0)t += count(m- costs[2], p, g, r+1, o, move++);
//			t += count(m- costs[3], p, g, r, o+1, move++);
//			return t;
//		}
	
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter () throws IOException {
		return next().charAt(0);
	}
	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
