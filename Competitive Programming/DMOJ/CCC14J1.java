package contest;
import java.io.*;
import java.util.*;

public class CCC14J1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int a = readInt();
		int b = readInt();
		int c = readInt();
		if (a + b + c != 180)System.out.println("Error");
		else {
			if (a == 60 && b == c)System.out.println("Equilateral");
			else if (a == b || b == c|| c == a)System.out.println("Isosceles");
			else {
				System.out.println("Scalene");
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


