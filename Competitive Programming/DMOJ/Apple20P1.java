package contest;

import java.io.*;
import java.util.*;

public class Apple20P1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int t = readInt();
		for(int i  = 0; i< t ;i++) {
			int side1 = readInt(), side2 = readInt(), side3 = readInt();
			int hy = Math.max(side1, Math.max(side2, side3));
			
			int leg1 = Math.max(side1, side2);
			if(leg1 > side3) leg1 = Math.max(Math.min(side1, side2), side3);
			
			int leg2 = Math.min(side1, Math.min(side2, side3));
			//System.out.println(hy+ " " + leg1 + " " + leg2);
			if((int)Math.pow(hy, 2) == (int)Math.pow(leg2, 2) + (int)Math.pow(leg1, 2)) System.out.println("R");
			else if((int)Math.pow(hy, 2) < (int)Math.pow(leg2, 2) + (int)Math.pow(leg1, 2)) System.out.println("A");
			else if((int)Math.pow(hy, 2) > (int)Math.pow(leg2, 2) + (int)Math.pow(leg1, 2)) System.out.println("O");
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
