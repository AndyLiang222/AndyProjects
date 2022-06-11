package contest;
import java.io.*;
import java.util.*;

public class Autocomplete {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int t = readInt();int base = 131;
		for (int i = 1; i<= t;i++) {
			int n = readInt();
			Map<Long, Integer> map = new HashMap<>();
			long h = 0;long pw = 1;
			int total = 0;
			for (int j = 0; j< n;j++) {
				String s = readLine();
				h = 0;
				int ans = s.length();
				for(int k = 0; k< s.length();k++) {
					h = h*base + s.charAt(k);
					if (map.getOrDefault(h, 0) == 0 ) {
						if (k< ans) {
							ans = k+1;
							//System.out.println(s.charAt(k));
						}
						map.put(h, 0);
					}
					map.put(h, map.get(h)+1);
					
				}
				total += ans;
				
			}
			System.out.println("Case #"+i + ": " + total);
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


