package contest;
import java.io.*;
import java.util.*;
public class BalancedString {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int u = 0, l = 0;
		
		String s = readLine();
		for (int i = 0; i< s.length();i++) {
			char c= s.charAt(i);
			if (Character.isUpperCase(c)) {
				u++;
			}
			if(Character.isLowerCase(c)) {
				l++;
			}
		}
		if (u == l)System.out.println("YES");
		else System.out.println("NO");
		
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


