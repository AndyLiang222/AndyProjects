package contest;
import java.io.*;
import java.util.*;
public class AAC5P3N {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt();
		ArrayList<edge> adj[] = new ArrayList[n+1];
		for(int i =0;i<n-1;i++) {
			int a = readInt(), b = readInt(), d = readInt();
			adj[a].add(new edge(b,d));
		}
		int[] panda = new int[k];
		for(int i = 0;i<k;i++)panda[i] = readInt();
		Arrays.sort(panda);
		for(int i = 0;i<k;i++) {
			
		}
	}
	static class edge implements Comparable<edge>{
		int v, w;
		edge(int v0, int w0){
			v = v0; w = w0;
		}
		public int compareTo(edge e) {
			return Integer.compare(w, e.w);
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


