package contest;
import java.io.*;
import java.util.*;

public class CCC03J1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int a = readInt(), b = readInt(), c = readInt();
		String spaces = "", asc = "*";
		for (int i = 0; i< 2*b+10; i++) {spaces+= " ";asc += "*";}
		
		for (int i = 0; i< a;i++) {
			System.out.println("*"+spaces.substring(0, b)+ "*" + spaces.substring(0,b)+ "*");
		}
		System.out.println(asc.substring(0, 2*b+3));
		for (int i = 0; i< c;i++) {
			System.out.println(spaces.substring(0, b+1) + "*" );
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


