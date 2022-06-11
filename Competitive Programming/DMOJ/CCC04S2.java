package contest;

import java.io.*;
import java.util.*;

public class CCC04S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		int n = readInt(), m = readInt();
		int [] r = new int [n+1];
		int [] s = new int[n+1];
		int [] w = new int [n+1];
		
		for (int i = 0; i< m;i++) {
			for (int j = 1; j< n+1; j++) {
				s[j] += readInt();
				
			}
			for (int j = 1; j<n+1;j++) {
				int rank = 1;
				for (int b = 1; b <n+1;b++) {
					if (b != j && s[b] > s[j]) {
						rank++;
					}
				}
				r[j] = Math.max(r[j], rank);
				w[j] = rank;
				
			}
			
			
		}
		for (int i = 1; i< n+1; i++)System.out.println(w[i] == 1? "Yodeller " + i + " is the TopYodeller: score " + s[i] + ", worst rank " + r[i]:"");
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
