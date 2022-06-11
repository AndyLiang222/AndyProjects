package contest;
import java.io.*;
import java.util.*;

public class CCC01S3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		boolean [][] graph = new boolean [26][26];
		String in = readLine();
		while(!in.equals("**")) {
			int u = in.charAt(0)-'A', v = in.charAt(1)-'A';
			graph[u][v] = graph[v][u] = true;
			in = readLine();
		}
		int count = 0;
		for(int i =0;i<26;i++) {
			for(int j = i ;j<26;j++) {
				if(graph[i][j]) {
					graph[i][j] = graph[j][i] = false;
					if(!bfs(graph)) {
						count++;
						System.out.println((char)(i+'A') + "" + (char)(j+'A'));
					}
					graph[i][j] = graph[j][i] = true;
				}
			}
		}
		System.out.println("There are "+ count + " disconnecting roads.");
	}
	static boolean bfs(boolean[][] graph) {
		Queue<Integer> q = new LinkedList();
		q.add(0);
		boolean [] vis = new boolean [26];
		vis[0] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 0;i<26;i++) {
				if(graph[cur][i] && !vis[i]) {
					vis[i] = true;
					q.add(i);
				}
			}
		}
		return vis[1];
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


