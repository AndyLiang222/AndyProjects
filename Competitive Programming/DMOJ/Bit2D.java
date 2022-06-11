package contest;
import java.io.*;
import java.util.*;

public class Bit2D {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int n, bit[][];
	public static void main(String[] args) throws IOException {
		bit = new int[1026][1026];
		int input = readInt();
		while(input != 3) {
			if (input == 0) {
				n = readInt();
				
			}
			else if(input == 1) {
				int x = readInt();
				int y = readInt();
				int val = readInt();
				
				update(x+1,y+1, val);
			}
			else if (input == 2) {
				int r1 = readInt();
				int c1= readInt();
				int r2 = readInt();
				int c2 = readInt();
				

				System.out.println((rangeTo(r2+1, c2+1)-rangeTo(r2+1, c1)-rangeTo(r1, c2+1)+rangeTo(r1, c1)));
			}
			input = readInt();
		}
	}
	static void update(int row, int col , int val) {
		for (int i = row; i <= n; i += i & -i) {
	        for (int j = col; j <= n; j += j & -j) {
	            bit[i][j] += val;
	        }
	    }
		
	}
	static long rangeTo(int row, int col) {
		
		long sum = 0;
		for (int i = row; i > 0; i -= i & -i) {
	        for (int j = col; j > 0; j -= j & -j) {
	            sum += bit[i][j];
	        }
	    }
		return sum;
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


