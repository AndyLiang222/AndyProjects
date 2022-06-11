package contest;
import java.io.*;
import java.util.*;
public class BFS {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		ArrayList<Integer> adj[] = new ArrayList[n+1];
		for (int i = 1; i <= n;i++)adj[i] = new ArrayList();
		for (int i = 0;i< m;i++) {
			int u = readInt(), v = readInt();
			adj[u] .add(v);
		}
		for (int i = 1; i<= n;i++) {
			System.out.println(i + " " + adj[i]);
		}
		Queue <Integer>q = new LinkedList();
		int [] dis = new int [n+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		q.add(1);
		dis[1]= 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			System.out.println(cur);
			for (int nex : adj[cur]) {
				if (dis[nex] > dis[cur]+ 1) {
					dis[nex] = dis[cur]+1;
					q.add(nex);
				}
			}
		}
		for(int i = 1; i<= n;i++) {
			System.out.println(i + "dis: " + dis[i]);
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


