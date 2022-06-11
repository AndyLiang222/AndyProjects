package contest;
import java.io.*;
import java.util.*;

public class AAC5P3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int [][]interval;
	static ArrayList<Long>adj[];
	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt();
		adj = new ArrayList[n+1];
		for(int i = 1;i<=n;i++)adj[i] = new ArrayList();
		for(int i = 0;i<n-1;i++) {
			int x = readInt(), y = readInt(), d = readInt();
			adj[x].add();
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


