package contest;
import java.util.*;
import java.io.*;

public class AAC4P2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt(), q = readInt();
		long [] p =new long[n+1];
		p[0] = 1;
		for(int i  =1;i<=n;i++) {
			int x = readInt();
			if(p[i-1] == -1)p[i] = -1;
			else p[i] = LCM(p[i-1], x);
			if (p[i] > (int)10e9)p[i] = -1;
			//System.out.println(p[i]);
		}
		for(int i = 0;i<q;i++) {
			int v = readInt();
			if (v == 0) { 
				System.out.println(-1);
				continue;
			}
			int lo = 1, hi = n, ans = 0;
			while(lo <= hi) {
				
				int mid = (lo+hi)/2;
				
				//System.out.println("Mid" + mid);
				if (p[mid] == -1 || p[mid] > v || v%p[mid] != 0) {
					hi = mid-1;
				}
				else {
					lo = mid+1;
					ans = mid;
				}
				
			}
			if(ans+1 == n+1)System.out.println(-1);
			else System.out.println(ans+1);
		}
	}
	static long LCM(long a, long b) {
		return a*b/GCD(a,b);
	}
	static long GCD(long a, long b) {
		if (b == 0) return a;
		return GCD(b, a%b);
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


