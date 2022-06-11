package contest;
import java.io.*;
import java.util.*;

public class BobMath {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		
		long n = readLong();
		long sum = 0, ans = 0;
		for(long i = 1;i<= n;i++) {
			
			if((n-sum)%i == 0) {
				//System.out.println(i);
				ans++;
			}
			sum+= i;
			if(sum>= n)break;
			
		}
		System.out.println(ans);
//		Deque <Long> a =new ArrayDeque();
//		
//		long s = 0, as = 0;
//		for(long i = 0;i<= n;i++) {
//			
//			s+= i;
//			a.add(i);
//			while(s > n)s-= a.poll();
//			if(s == n)as++;
//		}
//		System.out.println(as);
		
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


