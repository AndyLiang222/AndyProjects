package contest;
import java.io.*;
import java.util.*;

public class BobTeam {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int [] l1 = new int[n];
		int []l2 = new int[n+1];
		int []l3 = new int[n+1];
		l2[n] = Integer.MAX_VALUE;
		l3[n] = Integer.MAX_VALUE;
		for(int i =0;i<n;i++) l1[i] = readInt();
		for(int i =0;i<n;i++) l2[i] = readInt();
		for(int i =0;i<n;i++) l3[i] = readInt();
		Arrays.sort(l1);
		Arrays.sort(l2);
		Arrays.sort(l3);
		
		int p1 = 0, p2 = 0;
		long [] pos = new long [n+2];
		for(int i = 0;i< n;i++) {
			int a = l2[i];
//			System.out.println(p2 + " " + l3[p2]);
			while(a >= l3[p2])p2++;
			
			pos[i+1] = n-p2;
		}
		for(int i = n;i> 0;i--) {
//			System.out.println(pos[i]);
			pos[i] +=pos[i+1];
			
		}
		long ans = 0;
		for(int i = 0;i<n;i++) {
			int a = l1[i];
			while(a >= l2[p1])p1++;
			ans+= pos[p1+1];
		}
		System.out.println(ans);
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


