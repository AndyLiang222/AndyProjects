package contest;
import java.io.*;
import java.util.*;
public class CCC10S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int pos, n,dp[][];
	static int[] l,r;
	static int M = 350;
	static char[] in;
	public static void main(String[] args) throws IOException {
		in = readLine().toCharArray();
		n = readInt();
		dp = new int[M][n+1];
		build(1);
		l = new int[n+1];
		r = new int[n+1];
		solve(1);
		System.out.println(dp[1][n]);
//		for(int i = 1;i<M;i++) {
//			System.out.println(i + " " + dp[i][0]);
//		}
		
	}
	public static void solve(int idx) {
		if(dp[idx][0] != 0)return;
		solve(2*idx); solve(2*idx+1);
		for(int i = 0;i<=n;i++) {
			l[i] = 0;
			r[i] = 0;
		}
		for(int i =0;i<=n;i++ ) {
			for(int j = 0;j<=i;j++) {
				l[i] = (int) Math.max(l[i], Math.min((j+1) * (j+1),dp[2*idx][i-j] ));
			}
		}
		for(int i =0;i<=n;i++ ) {
			for(int j = 0;j<=i;j++) {
				r[i] = (int) Math.max(r[i], Math.min((j+1) * (j+1),dp[2*idx+1][i-j] ));
			}
		}
		for(int i =0;i<=n;i++ ) {
			for(int j = 0;j<=i;j++) {
				dp[idx][i] = (int) Math.max(dp[idx][i], l[j]+ r[i-j]);
			}
		}
	}
	public static void build(int idx) {
		while(pos< in.length && in[pos]==' ' || in[pos] == ')')pos++;
		if(in[pos] == '(') {
			pos++;
			build(2*idx);
			build(2*idx+1);
		}
		else {
			int val = 0;
			while(pos< in.length && in[pos]-'0'< 10 && in[pos]-'0' >= 0) {
				val = val*10 + in[pos]- '0';
				pos++;
			}
			dp[idx][0] = val;
			for(int i = 1;i<=n;i++)dp[idx][i] = dp[idx][i-1] +1;
		}
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


