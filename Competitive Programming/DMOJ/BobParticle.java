package contest;
import java.io.*;
import java.util.*;

public class BobParticle {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		par[] black =  new par[n];
		par[] white = new par[n];
		for(int i = 0;i<n;i++) {
			black[i] = new par(readInt(), readInt());
		}
		for(int i = 0;i< n;i++) {
			white[i] = new par(readInt(), readInt());
		}
		Arrays.sort(black);
		Arrays.sort(white);
		
	}
	static class par implements Comparable<par>{
		int x, y;
		par(int x0, int y0){
			x = x0;
			y = y0;
		}
		public int compareTo(par a) {
			if(x == a.x)return Integer.compare(y, a.y);
			return Integer.compare(x,a.x);
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


