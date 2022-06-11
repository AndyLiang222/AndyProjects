package contest;
import java.io.*;
import java.util.*;

public class CCC00S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		double [] x = new double [n];
		double [] y = new double [n];
		boolean [] out = new boolean [n];
		
		for (int i = 0; i< n;i++) {
			x[i]  = readDouble();
			y[i] = readDouble();
		}
		for (int i = 0; i<n;i++) {
			double l = 0, r = 1000;
			for (int j = 0; j< n;j++) {
				if (out[i] == false && out[j] == false && i != j) {
					double xm = (x[i]+x[j])/2;
					double ym = (y[i]+ y[j])/2;
					double s = (x [i] - x [j]) / (y [j] - y [i]);
					
					if (s == 0) {
						if (y[i] > y[j]) {
							out[i] = true;
						}else {
							out[j] = true;
						}
					}else {
						double p = -ym / s + xm;
						if (x[j] < x[i]) {
							l = Math.max(l, p);
						}else {
							r = Math.min(r, p);
						}
					}
				}
				if (l > r) {
					out[i] = true;
				}
			}
		}
		for (int i = 0; i< n;i++) {
			if (!out[i])  System.out.printf ("The sheep at ("+ x[i] + ", " + y[i]+ ") might be eaten.\n");
		}
	}
	static double round(double x) {
		return Math.floor(x*100)/100.0;
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


