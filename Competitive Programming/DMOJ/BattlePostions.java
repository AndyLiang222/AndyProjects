package contest;
import java.io.*;
import java.util.*;
public class BattlePostions {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int s = readInt();
		int m = readInt();
		int[] pos = new int[n+2];
		for(int i = 0;i< m;i++) {
			int a = readInt(), b = readInt(), v = readInt();
			pos[a]+= v;pos[b+1]-=v;
		}
		int ans = 0;
		for(int i = 1;i<=n;i++) {
			pos[i] += pos[i-1];
			if(pos[i] < s)ans++;
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


