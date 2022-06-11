package contest;
import java.io.*;
import java.util.*;
public class BirthdayGame {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int[] dis;
	public static void main(String[] args) throws IOException {
		int n = readInt();
		int [] adj = new int [n+1];
		int [] in = new int [n+1];
		for(int i =1;i<=n;i++) {
			int u = readInt();
			in[u] ++;
			adj[i] = u;
			
		}
		dis = new int[n+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		int ans = Integer.MAX_VALUE;
		for(int i= 1;i<= n;i++) {
			if(in[i] == 0) {
				dis[i] = 0;
				int cur = i;
				for(cur = i;dis[adj[cur]] == Integer.MAX_VALUE;cur =adj[cur]) {
					dis[adj[cur]] = dis[cur]+1;
				}
				ans = Math.min(ans, dis[cur]-dis[adj[cur]]+1);
			}
		}
		if(ans == Integer.MAX_VALUE)System.out.println(n);
		else System.out.println(ans);
		
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


