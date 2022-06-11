package contest;

import java.util.*;
import java.io.*;

public class CCC06S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		HashMap<String , String> decode = new HashMap<>();
		String[] pl = br.readLine().split("");
		String[] d =  br.readLine().split("");
		for (int i = 0; i< pl.length; i++) {
			if (decode.containsKey(d[i])) {
				continue;
			}
			decode.put(d[i], pl[i]);
			
		}
		String[] word = br.readLine().split("");
		String ans = "";
		for (String x : word) {
			if (decode.get(x) == null) {
				ans+= ".";
			}else {
				ans+= decode.get(x);
			}
		}
		System.out.println(ans);
		
		
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
