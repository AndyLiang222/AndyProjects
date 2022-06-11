package contest;
import java.io.*;
import java.util.*;

public class Assistant {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		pair [] a  = new pair [n+1];
		a[0] = new pair(0,0,0);
		
 		for(int i = 1; i < n+1 ;i++) {
 			long s = readLong();
			a[i] = new pair (s,s + readLong(), readLong());
		
		}
 		Arrays.sort(a);
// 		for(pair x : a) {
// 			System.out.println(x.s + " " + x.f + " " + x.v);
// 		}
 		int [] h =new int [n+1];
 		long [] dp  = new long[n+1];
 		for(int i = 1; i<= n;i++) {
 			int ans = 0;int hi = n; int lo = 0;
 			while(lo <= hi) {
 				int mid = (hi + lo)/2;
 				if (a[mid].f <= a[i].s) {
 					ans = mid; lo = mid+1;
 				}else {
 					hi = mid-1;
 				}
 			}
 			h[i]= ans;
 			
 			dp[i] = Math.max(dp[i-1], dp[h[i]] + a[i].v);
// 			System.out.println(dp[i]);
 		}
 		System.out.println(dp[n]);
// 		for (int x : h) {
// 			System.out.println(x);
// 		}
 		
	}
	static class pair implements Comparable<pair>{
		long s,f, v;
		pair (long s0,long f0, long v0) {s =  s0;f = f0; v = v0;}
		public int compareTo(pair x) { return -Long.compare(x.f,f );}
		
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


