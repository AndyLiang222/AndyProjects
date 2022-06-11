package contest;
import java.io.*;
import java.util.*;

public class AAC5P3B {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt();
		ArrayList<edge>adj[] = new ArrayList[n+1];
		for(int i = 1;i<=n;i++)adj[i] = new ArrayList();
		for(int i = 0;i<n-1;i++) {
			int x = readInt(), y = readInt(), d = readInt();
			adj[x].add(new edge(y,d));
		}
		int[]visits = new int[n+1];
		for(int i = 0;i<k;i++) {
			int p = readInt();
			int cur = 1;
			visits[cur]++;
			while(adj[cur].size()!= 0) {
				
				int nB = 0;
				int d = 0;
				int dif = Integer.MAX_VALUE;
				for(edge nex:adj[cur]) {
					int dis = Math.abs(p-nex.d);
					if((dis == dif && (d > nex.d))||dis < dif) {
						nB = nex.v;
						d = nex.d;
						dif = dis;
					}
				}
				cur = nB;
				visits[cur]++;
			}
		}
		for(int i = 1;i<=n;i++) {
			System.out.print(visits[i] + ((i == n)?"":" "));
		}
		System.out.println();
	}
	static class edge{
		int v, d;
		edge(int v0, int d0){
			v = v0; d = d0;
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