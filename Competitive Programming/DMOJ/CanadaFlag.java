package contest;
import java.io.*;
import java.util.*;

public class CanadaFlag {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int t = readInt();
		for(int v = 0; v< t;v++) {
			int n = readInt();
			String s = readLine();
			int [] red = new int [n];
			
			int [] white = new int[n];
			
			for (int i = 0; i< n;i++) {
				if (s.charAt(i) == 'R') red[i]++;
				else white[i]++;
				if (i != 0) {
					red[i] += red[i-1];
					white[i] += white[i-1];
				}
			}
			int minL = Integer.MAX_VALUE;
			int ans = Integer.MAX_VALUE;
			for (int i = 2 ;i< n;i++) {
				minL = Math.min(minL, white[i-2] - red[i-2]);
				ans = Math.min(ans,minL+ white[n-1]-white[i-1]+ red[i-1]);
			}
			System.out.println(ans);
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


