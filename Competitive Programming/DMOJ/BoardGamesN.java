package contest;
import java.io.*;
import java.util.*;
public class BoardGamesN {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int max = (int)1e7+5;
	public static void main(String[] args) throws IOException {
		int n = readInt(), m = readInt();
		int [] dis = new int[max];
		boolean[] iq = new boolean[max];
		Queue<Integer> q = new LinkedList();
		q.add(n);
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[n] = 0;
		if(n == m) {
			System.out.println(0);
			return;
		}
		while(!q.isEmpty()) {
			int cur = q.poll();
			int [] a = new int[4];
			a[0] = cur*3;
			a[1] = cur-1;
			a[2] = cur-3;
			a[3] =(cur%2 == 1)?Integer.MAX_VALUE: cur/2;
			for(int i = 0;i< 4;i++) {
				int next = a[i];
				if(next == Integer.MAX_VALUE)continue;
				if(next >= 0 && next < max && !iq[next]) {
					q.add(next);
					dis[next]= dis[cur]+1;
					iq[next]= true;
					if(next == m) {
						System.out.println(dis[next]);
						return;
					}
				}
			}
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


