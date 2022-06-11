package contest;
import java.io.*;
import java.util.*;
public class CCC12S3N {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n  =readInt();
		int[] freq = new int[1001];
		for(int i= 0;i<n;i++) {
			freq[readInt()]++;
		}
		int m1 = 0;
		int f1 = 0;
		int m2 = 0;
		int f2 = 0;
		for(int i = 1;i<=1000;i++) {
			if(freq[i] >= f1) {
				m1 = i;
				f1 = freq[i];
			}
		}
		int temp = freq[m1];
		freq[m1] = 0;
		for(int i = 1000;i>=1;i--) {
			if(freq[i] >= f2) {
				m2 = i;
				f2 = freq[i];
			}
		}
		freq[m1] = temp;
		int d1 = Math.abs(m1-m2);
		m1 = 0;
		f1 = 0;
		m2 = 0;
		f2 = 0;
		for(int i = 1000;i>=1;i--) {
			if(freq[i] >= f1) {
				m1 = i;
				f1 = freq[i];
			}
		}
		freq[m1] = 0;
		for(int i = 1;i<=1000;i++) {
			if(freq[i] >= f2) {
				m2 = i;
				f2 = freq[i];
			}
		}
		System.out.println(Math.max(d1, Math.abs(m1-m2)));
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


