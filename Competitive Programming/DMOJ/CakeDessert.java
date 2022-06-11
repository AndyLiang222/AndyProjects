package contest;
import java.io.*;
import java.util.*;

public class CakeDessert {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt(), m = readInt(), k = readInt();
		long [][] cake = new long [m+2][n+2];
		for (int i = 0 ;i< k ;i++) {
			int x = readInt(), y = readInt(), x1 = readInt(), y1 = readInt();
			cake[y][x] += 1; cake[y][x1 +1] -=1; cake[y1+1][x] -= 1;cake[y1+1][x1+1] += 1;
			
		}
		
		psa(cake);
		
		psa(cake);
		
		int q = readInt();
		for (int i = 0; i < q; i++) {
			int x = readInt(), y = readInt(), x1 = readInt(), y1 = readInt();
			
			System.out.println(cake[y1][x1] -  cake[y1][x-1] - cake[y-1][x1]+cake[y-1][x-1]);
		}
		
		
	}
	static void psa (long [][] arr){
		for (int i = 1; i< arr.length; i++) {
			for (int j = 1; j < arr[i].length;j++) {
				arr[i][j] =arr[i][j]+ arr[i-1][j]+ arr[i][j-1] - arr[i-1][j-1];
				
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


