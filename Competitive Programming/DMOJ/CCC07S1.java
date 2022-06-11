package contest;

import java.util.*;
import java.io.*;

public class CCC07S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		int n = readInt();
		//1989 2 27
		for (int i = 0; i< n;i++) {
			int year = readInt(), month = readInt(), day = readInt();
			if (year < 1989) {
				System.out.println("Yes"); continue;
			}
			else if (year > 1989 ) {
				System.out.println("No"); continue;
			}
			else {
				if (month < 2) {
					System.out.println("Yes"); continue;
				}
				else if (month > 2) {
					System.out.println("No"); continue;
				}
				else {
					if (day <= 27) {
						System.out.println("Yes"); continue;
					}else {
						System.out.println("No"); continue;
					}
				}
			}
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