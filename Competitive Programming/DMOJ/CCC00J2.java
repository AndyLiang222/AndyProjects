package contest;
import java.io.*;
import java.util.*;

public class CCC00J2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static Deque <Integer> d = new ArrayDeque();
	public static void main(String[] args) throws IOException {
		int n = readInt(), m = readInt(), count = 0;
		for (int i = n;i<= m;i++) {
			digits(i);boolean works = true;
			
			while (!d.isEmpty()) {
				
				int f = d.pollFirst();
				int l = 0;
				if (d.size() == 0)l = f;
				else l = d.pollLast();
				
				
				
				if ((f == 6 && l == 9)||(f == 9 && l == 6)||(f == 8 && l == 8)||(f == 0 && l == 0)||(f==1 && l == 1)) continue;
				else {
					works = false;
					
				}
				
			}
			if (works) {
				
				count++;
			}
			
		}
		System.out.println(count);
	}
	static void digits(int x ) {
		while (x > 0) {
			d.addFirst(x%10);
			x = x/10;
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


