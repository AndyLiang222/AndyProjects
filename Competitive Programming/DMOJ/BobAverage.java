package contest;
import java.io.*;
import java.util.*;
public class BobAverage {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int n,x, a[]; 
	static long dp[][][];
	public static void main(String[] args) throws IOException {
		n = readInt();
		x = readInt();
		a = new int[n];
		for (int i = 0; i< n;i++) {
			a[i] = readInt();
		}
		dp = new long[n][2505][n];
		for (int i = 0; i< n;i++) {
			for (int j = 0; j< dp[i].length;j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(check(0, 0, 0));
	}
	static long check(int i, int pSum, int nums) {
		
		long count = 0;
		if (i == n)return 0;
		if (dp[i][pSum][nums] >= 0)return dp[i][pSum][nums];
		if (pSum+a[i] == (nums+1)*x) {
			
			count+= 1;
		}
		
		count+= check(i+1,pSum+a[i],nums+1)+check(i+1, pSum, nums);
		dp[i][pSum][nums] = count;
		return dp[i][pSum][nums];
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


