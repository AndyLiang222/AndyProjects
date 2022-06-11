package contest;
import java.io.*;
import java.util.*;

public class AAC3P1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int a = readInt(), b = readInt(), c = readInt(), d = readInt();
		int d1 = b -a; int d2 = d-c;
		if (d1 >0 && d2 >0) {
			System.out.println("Go to the department store");
		}else if (d1 > 0) {
			System.out.println("Go to the grocery store");
		}else if (d2 >0) {
			System.out.println("Go to the pharmacy");
		}else {
			System.out.println("Stay home");
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


