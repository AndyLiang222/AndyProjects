package contest;
import java.io.*;
import java.util.*;

public class BTS19Parkour {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int x = readInt(), y = readInt(), h = readInt(), v = readInt(), t = readInt();
		
		int ans = 0;
		while (x > 0 && y >0) {
			if (x >= y) {
				x-= 2; y-=2;
			}else {
				x-=1; y-=2;
			}
			if (x+ h <= 0|| y+v<= 0) {
				ans= Integer.MAX_VALUE;
				break;
			}
			ans++;
		}
		System.out.println((ans < t)?"YES": "NO");
		
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


