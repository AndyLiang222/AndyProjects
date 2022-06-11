package contest;
import java.util.*;

import contest.FunForag.edge;

import java.io.*;
public class BobRoadUp {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<edge> adj []; static int a; static int b; static int n; static long c; static long[] dis;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		char [] s =  br.readLine().toCharArray();
		
		char c = br.readLine().charAt(0);
		int count = 0;
		for(char i : s) {
			if (i == c) count++;
		}
		System.out.println(count);
				
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
