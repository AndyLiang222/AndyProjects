package contest;
import java.io.*;
import java.util.*;
public class CCC12S5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n; static int [][] memo;static int [][] moves;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int r = readInt(), c = readInt();
		int [][] memo = new int[r+1][c+1];
		boolean [][] cats  = new boolean [r+1][c+1];
		int n = readInt();
		for(int i =  0; i< n ;i++) {
			int x = readInt(), y = readInt();
			cats[x][y] = true;
		}
		memo[1][1] = 1;
		cats[1][1] = true;
		for (int y = 1; y < r +1;y++) {
			for(int x = 1 ; x < c+1; x++) {
				if(cats[y][x] == false)memo[y][x] = memo[y-1][x] + memo[y][x-1];
			}
		}
		System.out.println(memo[r][c]);
		
	}
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter () throws IOException {
		return next().charAt(0);
	}
	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
