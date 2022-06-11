package contest;
import java.io.*;
import java.util.*;

public class BadNews {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static char [][] grid;static boolean [][] vis;static int n;
	public static void main(String[] args) throws IOException {
		n = readInt(); int q = readInt();
		grid = new char [n+1][n+1];
		
		for (int i = 1; i<= n;i++) {
			for (int j = 1; j<= n;j++) {
				grid[i][j] = readCharacter();
			}
		}
		for (int l = 0; l < q;l++) {
			String a = readLine();
			boolean works = false;
			vis = new boolean [n+1][n+1];
			for (int i = 1; i<=n && !works;i++) {
				for (int j = 1; j<= n && !works;j++) {
					if (check(a, 0, i,j)) {
						works = true;
						System.out.println("good puzzle!");
						
					}
				}
			}
			if (!works)System.out.println("bad puzzle!");
			
		}
	}
	static boolean check(String s, int p, int r, int c) {
		char ch = s.charAt(p);
		vis[r][c] = true;
		if (grid[r][c] == ch) {
			p++;
			if (p == s.length())return true;
			for (int dr = -1; dr<= 1; dr++) {
				for (int dc = -1; dc <= 1; dc++) {
					int nr = r+dr; int nc = c+dc;
					if(nr <= 0 || nr > n || nc <= 0 || nc > n || vis[nr][nc] ) continue;
					if (check(s, p, nr,nc)) return true;
				}
			}
		}
		vis[r][c] = false; return false;
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


