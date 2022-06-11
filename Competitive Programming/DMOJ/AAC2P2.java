package contest;
import java.io.*;
import java.util.*;

public class AAC2P2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();int x = 0;
		int [] freq = new int[26];
		char [] ch = readLine().toCharArray();
		for(int i = 0; i< n;i++) {
			char c = ch[i];
			freq[c-97]++;
			if (freq[c-97]%2 ==1 )x++;
			else x--;
		}
		if (x== 0) x++;
		System.out.println(x);
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


