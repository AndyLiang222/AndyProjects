package contest;
import java.io.*;
import java.util.*;

public class CCC13J2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		String s = readLine();
		boolean [] v = new boolean [1000];
		// I, O, S, H, Z, X, and N
		char [] c = { 'I', 'O', 'S', 'H', 'Z', 'X','N'};
		for (char b : c) v[b] = true;
		for (char b : s.toCharArray()) {
			if (!v[b]) {System.out.println("NO");return;}
			
		}
		System.out.println("YES");
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


