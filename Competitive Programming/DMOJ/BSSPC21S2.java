package contest;
import java.io.*;
import java.util.*;

public class BSSPC21S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt(),k = readInt(), q = readInt();
		int []a = new int[k], b = new int[k], c = new int [k], d = new int [k];
		for (int i = 0;i<k;i++) {
			a[i] = readInt();
			b[i] = readInt();
			c[i] = readInt();
			d[i] = readInt();
		}
		for(int i = 0;i<q;i++) {
			int y = readInt();
			int x = readInt();
			for(int j = k-1;j>=0;j--) {
				if (a[j] <= y && c[j] >= y && b[j] <= x && d[j]>= x) {
					int dy = y-a[j];
					int dx = x-b[j];
					y = a[j] + dx;
					x = b[j] + dy;
				}
			}
			System.out.println((y-1)*n+x);
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


