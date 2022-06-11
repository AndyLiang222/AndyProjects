package contest;
import java.io.*;
import java.util.*;

public class AAC3P4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt(), m = readInt();
		int [] rows = new int[n+1];
		int [] cols = new int[m+1];
		Map<Integer, Integer> vc = new HashMap<>();
		Map<Integer, Integer> vr = new HashMap<>();
		for (int i = 1 ;i<= n;i++) {
			int v = readInt();
			if (v == -1)rows[i] = Integer.MIN_VALUE;
			else {
				rows[i] =v  - i;
				vr.put(rows[i], vr.getOrDefault(rows[i], 0)+1);
			}
			
		}
		for (int i = 1 ;i <= m;i++) {
			int v = readInt();
			if (v == -1)cols[i] = Integer.MIN_VALUE;
			else{
				cols[i] = v-i;
				vc.put(cols[i], vc.getOrDefault(cols[i], 0)+1);
			}
			
		}
		
		int ans = 0;
		for (int i = 1;i <= n;i++) {
			if (rows[i] != Integer.MIN_VALUE)ans+= Math.min(vr.getOrDefault(rows[i], 0),vc.getOrDefault(rows[i], 0)) ;
			vr.remove(rows[i]);
			
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


