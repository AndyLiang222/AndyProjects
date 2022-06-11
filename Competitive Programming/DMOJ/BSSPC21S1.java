package contest;
import java.io.*;
import java.util.*;

public class BSSPC21S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		char [][] grid = new char[n+1][m+1];
		
		for (int i = 1;i<=n;i++) {
			char [] c = readLine().toCharArray();
			for (int j = 1;j<=m;j++) {
				grid[i][j] = c[j-1];
			}
		}
		m++;
		n++;
		for(int i = 1;i<n;i++) {
			for(int j = 1;j<m;j++) {
				char c = grid[i][j];
				if (c== '.') {
					continue;
				}
				else {
					if ((grid[n-i][j] == c || grid[n-i][j] == '.') && (grid[i][m-j] == c || grid[i][m-j] == '.')&& (grid[n-i][m-j] == c || grid[n-i][m-j] == '.')) {
						grid[n-i][j] = c;
						grid[i][m-j] = c;
						grid[n-i][m-j] = c;
						
					}else {
						//System.out.println(i + " "  + j);
						System.out.println(-1);
						return;
					}
				}
			}
		}
		for(int i = 1;i<n;i++) {
			for(int j = 1;j<m;j++) {
				if(grid[i][j] == '.')grid[i][j] = 'a';
				System.out.print(grid[i][j]);
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
}


