package contest;
import java.io.*;
import java.util.*;

public class AAC5P2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		long[] v = new long[n+1];
		long min = Integer.MAX_VALUE;
		long max = 0;
		for(int i = 2;i<=n;i++) {
			System.out.println("? 1 "+i );
			System.out.flush();
			v[i] = Long.parseLong(br.readLine());
			min = Math.min(v[i], min);
			max = Math.max(v[i], max);
		}
		long a = (max == n)?1: min;
		System.out.print("! "+a);
		for(int i = 2;i<=n;i++) {
			System.out.print(" " + (v[i]/a));
		}
		System.out.println();
		return;
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


