package contest;
import java.io.*;
import java.util.*;

public class AAC3P5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n =readInt(), m =  readInt();int inf = 0x3F3F3F3F;
		int MM = 2003;
		int [] row = new int [MM], col = new int [MM]; 
		int [][] dis = new int [MM][MM];int [][]g = new int [MM][MM];
		ArrayList<pair> adj[][] = new ArrayList [MM][MM];
		for (int i = 0; i< n;i++) {
			for (int j = 0; j< m;j++) {
				adj[i][j] = new ArrayList();
			}
			Arrays.fill(dis[i], -1);
		}
		Arrays.fill(row, -1);Arrays.fill(col, -1);
		for (int i = 0; i< n;i++) {
			char [] a = br.readLine().toCharArray();
			for (int j = 0; j<n;j++) {
				g[i][j] = a[j];
				if(g[i][j] == '.') continue;
	            if(row[i] != -1) {
	                int w = row[i]==j-1? inf: j - row[i]-1;
	                adj[i][row[i]].add(new pair(i*m+j, w)); adj[i][j].add(new pair(i*m+row[i], w));
	            }
	            if(col[j] != -1) {
	                int w = col[j]==i-1? inf: i - col[j]-1;
	                adj[col[j]][j].add(new pair(i*m+j, w));  adj[i][j].add(new pair(col[j]*m+j, w));
	            }
	            row[i] = j; col[j] = i;
			}
		}
		PriorityQueue <pair> pq = new PriorityQueue();
		for (int i = 0; i< m;i++) {
			if (g[0][i] == '#')dis[0][i] = inf;pq.add(new pair(dis[0][i], 0, i));
		}
		while (!pq.isEmpty()) {
			pair e = pq.poll();int d = e.f; int r = e.s; int c = e.t;
			if (d <dis[r][c])continue;
			for (pair next : adj[r][c]) {
				int nr = next.f/m, nc = next.f%m, w = e.s;
				if(Math.min(d, w) > dis[nr][nc]){
	                dis[nr][nc] = Math.min(d, w); pq.add(new pair(dis[nr][nc], nr, nc));
	            }
			}
		}
		for (int i = 0; i< m;i++) {
			System.out.printf("%d%c", dis[n-1][i]==inf? 0:dis[n-1][i], i==m-1? '\n':' ');
		}
	}
	static class pair implements Comparable<pair>{
		int f, s, t;
		pair(int ff, int ss){
			f = ff; s = ss;
		}
		pair (int ff, int ss, int tt){
			f = ff; s = ss; t = tt;
		}
		public int compareTo(pair x){
			return -Integer.compare(f, x.f);
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


