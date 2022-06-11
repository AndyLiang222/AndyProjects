package contest;
import java.io.*;
import java.util.*;
public class CCC07S4 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Integer> adj [];
	static int []memo; 
	static boolean vis[];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int nodes = readInt();
		adj = new ArrayList[nodes+1];
		memo = new int[nodes+1];
		vis = new boolean[nodes+1];
		for (int i = 1; i<nodes+1; i++) {
			adj[i] = new ArrayList<>();
			
		}
		int in = readInt();int ic = readInt();
		while(in != 0) {
			adj[in].add(ic);
			in = readInt();ic = readInt();
		}
		memo[nodes] = 1;
		Set<Integer> used = new HashSet<>();
		used.add(1);
		System.out.println(DFS(1)); 
		
		
		
	}
	static int DFS (int pos) {
		int t = 0;
		if (memo[pos] != 0) {
			vis[pos] = false;
			return memo[pos];
		}
		for (int next : adj[pos]) {
			if (vis[next]!= true) {
				vis[next] = true;
				
				t = t+DFS(next);
				
			}
		}
		memo[pos] = t;
		vis[pos] = false;
		return t;
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
