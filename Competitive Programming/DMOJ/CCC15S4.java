package contest;
import java.io.*;
import java.util.*;


public class CCC15S4 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		int k = readInt(), n = readInt(), m = readInt();
		ArrayList<edge> adj [] = new ArrayList[n+1];
		for(int i = 1; i< n+1; i++)adj[i] = new ArrayList();
		
		for (int i = 0; i< m;i++) {
			int u = readInt(), v = readInt(), w = readInt(), h = readInt();
			adj[u].add(new edge(v, w, h));
			adj[v].add(new edge(u, w, h));
		}
		int a = readInt(), b = readInt();
		PriorityQueue <edge> q = new PriorityQueue();
		int [][] dis = new int [n+1][k+1];
		for (int [] d : dis) {
			Arrays.fill(d, Integer.MAX_VALUE);
		}
		dis[a][k] = 0; q.add(new edge(a, 0,0));
		while (!q.isEmpty()) {
			edge e = q.poll(); int u = e.v; int d = e.w; int h = e.h;
			if (dis[u][h] < d) continue;
			for (edge nex : adj[u]) {
				int v = nex.v; int w = nex.w; int hull = nex.h;
				if (h+hull >= k)continue;
				if (dis[v][h+hull] > d+w) {
					dis[v][h+hull] = d+w;
					q.add(new edge(v,dis[v][h+hull] , h+hull));
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int dist : dis[b]) {
			ans = Math.min(dist, ans);
		}
		System.out.println(ans == Integer.MAX_VALUE? -1 : ans);
		
	}
	static class edge implements Comparable<edge>{
		int v, w, h;
		edge(int v0, int w0, int h0){
			v = v0; w = w0; h = h0;
		}
		public int compareTo(edge x) { return Integer.compare(w, x.w); }
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
