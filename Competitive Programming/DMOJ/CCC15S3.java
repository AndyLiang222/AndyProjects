package contest;
import java.io.*;
import java.util.*;
public class CCC15S3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static TreeSet<Integer> set;
	public static void main(String[] args) throws IOException {
		int n = readInt();
		int p = readInt();
		set = new TreeSet();
		for(int i = 1;i<=n;i++) {
			set.add(i);
		}
		boolean [] gates = new boolean [n+1];
		int ans = 0;
		for (int i = 0;i<p;i++) {
			int g = readInt();
			if(check(g,gates)) {
				ans++;
			}else break;
		}
		System.out.println(ans);
	}
	static boolean check(int g, boolean [] gates) {
		if (set.lower(g+1) == null)return false;
		return gates[set.lower(g+1)] = set.remove(set.lower(g+1));
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


