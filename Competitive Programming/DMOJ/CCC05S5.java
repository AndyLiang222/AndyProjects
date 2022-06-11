package contest;
import java.io.*;
import java.util.*;

public class CCC05S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int [] bit;

	static int n;
	static long ans;
	public static void main(String[] args) throws IOException {
		n = readInt();
		ans = 0;
		int [] a = new int [100002];
		int [] c = new int [100002];
		bit = new int [100002];
		TreeSet<Integer> set = new TreeSet();
		for (int i = 0; i< n;i++) {
			a[i] = readInt();
			set.add(a[i]);
		}
		int cnt = 0;
		for (int nex : set)c[cnt++] = nex;
		for (int i = 0; i< n;i++) {
			int rank = Arrays.binarySearch(c, 0, cnt, a[i])+1;
			
			ans+= i-query(rank)+1;
			update(rank);
		}
		System.out.printf("%.2f", (ans*100/n)/100.0);
	}
	static int query(int rank) {
		int sum = 0;
		for(int i = rank;i > 0;i -= (i&-i)) {
			sum+= bit[i];
		}
		return sum;
	}
	static void update (int rank) {
		for (int i = rank; i <= n;i += (i & -i)) {
			bit[i]++;
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


