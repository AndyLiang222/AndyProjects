package contest;
import java.io.*;
import java.util.*;
public class ArtAuction {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt(), m =readInt();
		int [][] dp = new int[n+1][m+1];
		int [] a = new int[n+1];
		int [] b = new int[n+1];
		for(int i = 1;i<=n;i++) {
			a[i] = readInt();
		}
		for(int i = 1;i<=n;i++) {
			b[i] = readInt();
		}
		for(int i = 0;i<=n;i++)Arrays.fill(dp[i], Integer.MIN_VALUE);
		dp[0][0] = 0;
		for(int i = 1;i<=n;i++) {
			
			dp[i][0] = dp[i-1][0] + a[i];
			for(int j = 1;j<=Math.min(i, m);j++) {
				dp[i][j] = Math.max(dp[i-1][j]+a[i], dp[i-1][j-1] + b[i]);
			}
		}
		System.out.println(dp[n][m]);
//		for(int i = 0;i<=n;i++) {
//			System.out.println();
//			for(int j = 0;j<=m;j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//		}
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

	static class pair implements Comparable<pair> {
		int u, v;

		pair(int u0, int v0) {
			u = u0;
			v = v0;
		}

		public int compareTo(pair p) {
			return Integer.compare(u, p.u);
		}
	}
}


