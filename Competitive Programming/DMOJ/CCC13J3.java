package contest;
import java.io.*;
import java.util.*;
public class CCC13J3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int[] a = new int[6];
		for(int i = 2;i<6;i++)a[i]= a[i-1] + readInt();
		int[][] grid = new int[6][6];
		for(int i = 1;i<6;i++) {
			for(int j = 1;j<6;j++) {
				grid[i][j] = Math.abs(a[j] - a[i]);
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
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

	static class pair implements Comparable<pair> {
		int u, v;

		pair(int u0, int v0) {
			u = u0;
			v = v0;
		}

		public int compareTo(pair p) {
			return Integer.compare(u, p.u);
		}
	}
}


