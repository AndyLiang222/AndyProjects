package contest;
import java.io.*;
import java.util.*;
public class BobXOR {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int []b1, b2, a;
	static int n;
	public static void main(String[] args) throws IOException {
		n = readInt();
		b1 = new int [n+1];
		a  = new int[n+1];
		b2 = new int[n+1];
		for (int i = 1;i <= n;i++) {
			int x = readInt();
			a[i] =x;
			update(i, x, 0, b1);
			
		}
		
	}
	static void update(int idx, int v, int pV, int [] bit) {
		for (int i  = idx; i <= bit.length;i+= -i&i) {
			bit[i] = bit[i]^pV^v;
		}
	}
	static int query(int idx, int []bit) {
		int ret = 0;
		for (int i = idx; i > 0;i-=-i&i) {
			ret = ret^bit[i];
		}
		return ret;
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


