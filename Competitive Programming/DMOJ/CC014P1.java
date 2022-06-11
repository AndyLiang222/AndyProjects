package contest;

import java.io.*;
import java.util.*;
public class CC014P1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int n = readInt();
		
		int [] [] memo = new int[n+1][n+2];
		for (int i = 0;i< n;i++) {
			char[] in = br.readLine().toCharArray();
			for(int s = 1; s < n+1; s++) {
				if(in[s-1] == '#')memo[i][s] = 1;
				else memo[i][s] = 0;
				
			}
			
		}
		//System.out.println("bjflajflkajfa");
		long ans = 0;
		for(int i = n-1; i>= 0; i-- ) {
			for(int s = 1; s< n+1;s++) {
				if (memo[i][s] == 1) {
					memo[i][s] += Math.min(memo[i+1][s-1], Math.min(memo[i+1][s], memo[i+1][s+1]));
				}
			}
		}
		for(int [] i : memo) {
			
			for(int s : i) {
				ans+= s;
				
			}
		}
		System.out.println(ans);
		
		
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
