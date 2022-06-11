package contest;

import java.io.*;
import java.util.*;

public class CCC04S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dis,clubs; static int n;static int d;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		d = readInt();n = readInt();
		clubs= new int[n];
		
		for(int i = 0; i< n;i++) {
			clubs[i] = readInt();
			
		}
		dis = new int[d+1];
		Arrays.fill(dis, (int) 1e9);
		dis[0] = 0;
		solve(0, 0);
//		for(int i : dis) {
//			System.out.print(i + " ");
//		}
		System.out.println(dis[d] == 1e9? "Roberta acknowledges defeat." :"Roberta wins in " + dis[d] + " strokes." );
		
	}
	static void solve (int dist, int hits) {
		
		
		for(int i = 0; i< n;i++) {
			if(dist+clubs[i] <= d && dis[dist+clubs[i]] > hits+1) {
				dis[dist+clubs[i]] = hits+1;
				solve(dist+clubs[i], hits+1);
			}
		}
		
	}
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
