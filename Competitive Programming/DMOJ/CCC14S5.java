package contest;
import java.io.*;
import java.util.*;
public class CCC14S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static long mask1 = (1<<22)-1, mask2 = (1<<11)-1;
	public static void main(String[] args) throws IOException {
		int n = readInt();
		int idx = 0;
		long[]paths = new long[(n+1)*(n)/2];
		int[][] points = new int[n+1][2];
		for(int i = 1;i<=n;i++) {
			points[i][0] = readInt();
			points[i][1] = readInt();
		}
		for(int i = 0;i<=n;i++) {
			for(int j = i+1;j<=n;j++) {
				int dx = points[i][0] - points[j][0];
				int dy = points[i][1] - points[j][1];
				long d= ((((long)(dx*dx + dy*dy))<<22)|(i<<11)|j);
				paths[idx++] = d;
			}
		}
		
		Arrays.sort(paths);
		int[] dp = new int[n+1];
		int[] pd = new int[n+1];
		int[] pb = new int[n+1];
		for(int i =0;i< idx;i++) {
			long nex = paths[i];
			int d = (int)(nex >> 22);
			int a = (int)((nex&mask1)>>11);
			int b = (int)(nex&mask2);
			//System.out.println(d + " "  +a +  " " + b);
			if (pd[a] != d) {
				pd[a] = d;
				pb[a] = dp[a];
			}
			if(pd[b] != d) {
				pd[b] = d;
				pb[b] = dp[b];
			}
			if(a == 0) {
				dp[a] = Math.max(dp[a], pb[b]);
			}else {
				dp[a] = Math.max(dp[a], pb[b]+1);
				dp[b] = Math.max(dp[b], pb[a]+1);
			}
		}
		System.out.println(dp[0]+1);
	}
	static class node implements Comparable<node>{
		int a, b, d;
		node(int a0, int b0, int d0){
			a = a0; b = b0; d = d0;
		}
		public int compareTo(node n) {
			return Integer.compare(d, n.d);
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
}


