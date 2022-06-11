package contest;
import java.io.*;
import java.util.*;

public class AQTGraphs {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int ans[], n, m;static ArrayList<Integer> adj[];
	public static void main(String[] args) throws IOException {
		n = readInt();m = readInt();
		adj = new ArrayList[n+1];
		ans = new int[n+1];
		for (int i = 1; i<= n;i++)adj[i] = new ArrayList();
		for (int i = 0 ;i< m;i++) {
			int u = readInt(); int v = readInt();
			adj[v].add(u);
		}
		for (int i = n;i >= 1;i--) {
			if (ans[i] == 0) dfs(i,i);
			
		}
		boolean works = false;
		for (int i = n;i >= 1; i--) {
			if(ans[i] > i) {
				System.out.println(i + " " + ans[i]);
				works = true;
				break;
			}
		}
		if (!works)System.out.println(-1);
		
	}
	static void dfs(int u, int val) {
		ans[u] = val;
		for (int v : adj[u]) {
			if (ans[v] == 0) dfs(v, val);
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


