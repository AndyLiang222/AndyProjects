package contest;
import java.io.*;
import java.util.*;
public class CCC07J5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int a; static int b; static int n; static long [] motel; static long [] memo;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		a = readInt();b = readInt();n = readInt();
		motel = new long[14+n];
		memo = new long[14+n];
		motel [1] = 990;
		motel [2] = 1010;
		motel [3] = 1970;
		motel [4] = 2030;
		motel [5] = 2940;
		motel [6] = 3060;
		motel [7] = 3930;
		motel [8] = 4060;
		motel [9] = 4970;
		motel [10] = 5030;
		motel [11] = 5990;
		motel [12] = 6010;
		motel [13] = 7000;

		for (int i = 1; i< n+1;i++) {
			motel[i+13] = readInt();
		}
		n += 13;
		Arrays.fill(memo, -1);
		System.out.println(pathfind(0));
		
	}
	static long pathfind(int dist ) {
		if (7000 == motel[dist]) {
			return  1;
		}else {
			if(memo[dist] == -1) {
				memo[dist] = 0;
				for (int i = 1; i<= n; i++) {
					long d = motel[i] - motel[dist];
					if (d >= a && d <= b) {
						//System.out.println(i);
						memo[dist] += pathfind(i);
					}
				}
			}
			return memo[dist];
		}
	}
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter () throws IOException {
		return next().charAt(0);
	}
	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
