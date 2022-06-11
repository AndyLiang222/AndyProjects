package contest;
import java.io.*;
import java.util.*;
public class BobSurvival {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		int n = readInt(); int m = readInt(); int k = readInt(); 
		if (m > k) {
			System.out.println(n);
		}else {
			int []val = new int [n+1];
			int ans = 0;
			for (int i = 0; i< k;i++) {
				val[readInt()] ++;
			}
			for (int i = 0; i< n;i++) {
				//System.out.println(val[i]);
				if (m-k + val[i] >0) {
					ans++;
				}
			}
			System.out.println(ans);
			
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
