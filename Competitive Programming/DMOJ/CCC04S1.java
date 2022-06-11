package contest;

import java.io.*;
import java.util.*;

public class CCC04S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		int n = readInt();
		for (int i = 0; i< n;i++) {
			String w1= readLine();
			String w2 = readLine();
			String w3 = readLine();
			boolean result = false;
			if (w1.startsWith(w2) || w2.startsWith(w1)) result = true;
			if (w2.startsWith(w3) || w3.startsWith(w2)) result = true;
			if (w1.startsWith(w3) || w3.startsWith(w1)) result = true;
			if(w1.endsWith(w2)|| w2.endsWith(w1)) result = true;
			if(w3.endsWith(w2)|| w2.endsWith(w3)) result = true;
			if(w1.endsWith(w3)|| w3.endsWith(w1)) result = true;
			System.out.println(result == true ? "No" : "Yes");
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
