package contest;
import java.io.*;
import java.util.*;

public class Acorn {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int max = 0;
		int [] freq = new int [1000003];
		for (int i = 0; i< n;i++) {
			int val = readInt();
			max = Math.max(val, max);
			freq[val]++;
		}
		long sum = 0;
		int acorns = 0;
		for (int i = max; i > 0;i--) {
			if (freq[i] > acorns) {
				sum +=(freq[i]-acorns)*(i);
				acorns = freq[i];
			}
		}
		System.out.println(sum);
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


