package contest;
import java.io.*;
import java.util.*;

public class CCC08S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean [][][][] winning;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		winning = new boolean [31][31][31][31];
		int [][] moves = {{2, 1, 0, 2}, {1, 1, 1, 1}, {0, 0, 2, 1}, {0, 3, 0, 0}, {1, 0, 0, 1}};
		for(int a = 0; a < 31; a++) {
			for(int b = 0; b < 31; b++) {
				for(int c = 0; c < 31; c++) {
					for(int d = 0; d < 31; d++) {
						for(int m = 0; m < 5; m++) {
							if(losing(a-moves[m][0], b-moves[m][1], c - moves[m][2], d - moves[m][3])) {
								winning[a][b][c][d] = true;
							}
						}
					}
				}
			}
		}
		int t = readInt();
		for(int i = 0; i<t;i++) {
			int a = readInt(), b = readInt(), c = readInt(), d = readInt();
			if(winning[a][b][c][d]) {
				System.out.println("Patrick");
			}else {
				System.out.println("Roland");
			}
		}
		
	}
	static boolean losing(int a , int b , int c , int d) {
		if (a < 0||b< 0|| c<0|| d<0) {
			return false;
		}else {
			return !winning[a][b][c][d];
		}
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
