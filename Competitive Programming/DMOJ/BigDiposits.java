package contest;
import java.io.*;
import java.util.*;
public class BigDiposits {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static long p; static long y;static long t;
	public static void main(String[]args)throws IOException{
		p = readLong(); y = readLong(); t = readLong();
		long lo = 0; long hi = t; long ans = 0;
		while (lo <= hi) {
			long mid = (lo+hi)/2;
			if (check(mid)) {hi = mid-1;ans = mid;}
			else lo = mid +1; 
		}
		System.out.println(ans);
	}
	static boolean check(long x) {
		long amount = 0;
		for (int i = 1; i<= y; i++) {
			amount += x;
			amount += amount*p/100;
			if (amount >= t) {
				return true;
			}
		}
		return false;
	}
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter () throws IOException {
		return next().charAt(0);
	}
	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
