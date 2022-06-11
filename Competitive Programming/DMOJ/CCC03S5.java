package contest;

import java.io.*;
import java.util.*;

public class CCC03S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		int n = readInt(), m = readInt(),k = readInt();
		ArrayList<edge> adj [] = new ArrayList[n+1];
		for (int i = 1; i< n+1; i++) adj[i] = new ArrayList();
		for (int i = 0; i< m ;i++) {
			int u = readInt(), v = readInt(), w = readInt();
			adj[u].add(new edge(v, w)); adj [v].add(new edge(u, w));
		}
		PriorityQueue <edge> q = new PriorityQueue();
		int [] weights = new int[n+1];
		q.add(new edge(1, Integer.MAX_VALUE));
		while (!q.isEmpty()) {
			edge e = q.poll(); int u = e.v; int w = e.w;
			if (weights[u] > w) continue;
			for (edge nex : adj[u]) {
				int v = nex.v, w1 = nex.w;
				if (weights[v] < Math.min(w1, w)) {
					weights[v] =  Math.min(w1, w);
					q.add(new edge(v, weights[v]));
					
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i< k;i++) {
			int b = readInt();
			ans = Math.min(ans, weights[b]);
		}
		System.out.println(ans);
	}
	static class edge implements Comparable<edge>{
		int v;
		int w;
		edge(int v0,int w0){
			v = v0; w = w0;
		}
		public int compareTo(edge x) {return -Integer.compare(w, x.w);}
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
