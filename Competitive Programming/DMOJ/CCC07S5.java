package contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC07S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		for(int i = readInt(); i>0 ; i--) {
			int n = readInt(), b = readInt(), s = readInt();
			int [] pre = new int [n+1]; int [][] dp = new int[b+1][n+1];
			for(int j = 1; j<n+1;j++) {
				pre[j] = readInt() + pre[j-1];
			}
			for(int x = 1; x < b+1;x++ ) {
				for(int y = 1;y < n+1; y++) {
					if(y <= s) dp[x][y]= pre[y];
					else dp[x][y] = Math.max(dp[x][y-1], dp[x-1][y-s]+pre[y]-pre[y-s]);
				}
			}
			System.out.println(dp[b][n]);
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
