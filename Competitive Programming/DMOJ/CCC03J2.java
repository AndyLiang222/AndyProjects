package contest;
import java.io.*;
import java.util.*;

public class CCC03J2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int v = readInt();
		int l = 0, w = 0;
		while(v != 0) {
			for (int i = (int)Math.sqrt(v); i> 0;i--) {
				if (v%i == 0) {
					w = v/i; l = i;
					break;
				}
			}
			System.out.println("Minimum perimeter is " + (l*2 + w*2) + " with dimensions " + l + " x " + w);
			v = readInt();
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


