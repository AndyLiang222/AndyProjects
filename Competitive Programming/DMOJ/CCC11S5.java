package contest;
import java.util.*;
import java.io.*;

public class CCC11S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int[] dis = new int[1<<n+5];
		int board = 0;
		for(int i = 0;i<n;i++) {
			int b = readInt();
			board = board & (b<<i);
		}
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[board] = 0;
		Queue<Integer> q = new LinkedList();
		q.add(board);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 0;i<n;i++) {
				int 
				if(dis[])
			}
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


