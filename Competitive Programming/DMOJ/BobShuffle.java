package contest;
import java.io.*;
import java.util.*;
public class BobShuffle {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int n;
	public static void main(String[] args) throws IOException {
		n = readInt();
		int [] a= new int[n];
		for (int i = 0; i< n;i++) {
			a[i] = readInt();
		}
		Arrays.sort(a);
		int lowest = 1;
		int highest = n-1;
		long val = 0;
		
		int left= a[0];
		int right = a[0];
		for (int i = 1; i< n;i++) {
			int v1 = Math.abs(a[highest]-left);
			int v2 = Math.abs(a[highest]-right);
			int v3 = Math.abs(a[lowest]-left);
			int v4 = Math.abs(a[lowest]-right);
			int max = Math.max(Math.max(v1, v2), Math.max(v3, v4));
			if (v1 == max) {
				val+= v1;
				left= a[highest];
				highest --;
			}else if(v2 == max) {
				val+= v2;
				right= a[highest];
				highest --;
			}else if(v3 == max) {
				val+= v3;
				left= a[lowest];
				lowest++;
			}else {
				val+= v4;
				right= a[lowest];
				lowest++;
			}
		}
		System.out.println(val);
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


