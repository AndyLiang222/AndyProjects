package contest;
import java.io.*;
import java.util.*;

public class CCC14J2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int a = 0, b = 0, n = Integer.parseInt(readLine());
		String s = readLine();
		for (int i = 0; i< n;i++) {
			char c = s.charAt(i);
			if(c =='A') a++;
			else b++;
		}
		if (a == b)System.out.println("Tie");
		else if (a < b)System.out.println("B");
		else System.out.println("A");
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


