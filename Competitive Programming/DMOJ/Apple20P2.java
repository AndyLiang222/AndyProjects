package contest;

import java.io.*;
import java.util.*;

public class Apple20P2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int n = readInt(), q = readInt();
		long []  nums = new long[(int)10e5+1];
		for(int i = 0 ;i< n ;i++) {
			nums[readInt()]++;
			
		}
		for(int i = 0; i<q; i++) {
			int op = readInt(), weight = readInt();
			if(op ==1 ) {
				long amount =  (nums[weight]);
				int split = weight/2;
				nums[weight] = 0;
				nums[split] += amount;
				nums[split+weight%2] += amount;
				
				
			}
			if(op == 2) System.out.println(nums[weight]);
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
