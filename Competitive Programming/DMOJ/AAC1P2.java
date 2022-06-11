package contest;
import java.io.*;
import java.util.*;

public class AAC1P2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		long n = readLong(), d = readLong();
		long k = readLong(), x = readLong();
		
		Queue<Long> q = new LinkedList();
		for (long i = 0; i < n;i++)q.add(readLong());
		long p = readLong();
		
		
		for(int i = 0; i < n;i++) {
			long speed = q.poll();
			if (p > speed)continue;
			else {
				//System.out.println(horse[i] * (100-x)/100);
				
				while(p <=  speed&& k >0) {
					speed = speed * (100-x)/100 ;
					k--;
				}
				if (speed >= p) {
					System.out.println("NO");
					return;
				}
			}
		}
		System.out.println("YES");
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


