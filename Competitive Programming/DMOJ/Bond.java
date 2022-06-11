package contest;
import java.io.*;
import java.util.*;

public class Bond {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int n; static double  [] dp; static double [][] p;
	
	public static void main(String[] args) throws IOException {
		n = readInt();
		p = new double [n][n]; dp = new double [1<<n];
		Arrays.fill(dp, -1);
		for(int i = 0; i<n;i++) {
			for (int j = 0; j< n;j++) {
				p[i][j] = readDouble()/100.0;
			}
		}
		System.out.printf("%.9f\n", fun(0,0)*100);
		
	}
	static double fun(int b , int mask) {
		if (dp[mask] >= 0) return dp[mask];
		if (b >= n) return 1.0;
		double r = 0.0;
		
		for(int i = 0 ; i < n;i++) {
			if (((1<< i) & mask) == 0) {
				r = Math.max(r, p[b][i]* fun(b+1, mask|1<<i));
			}
		}
		return dp[mask] = r;
	}
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}


