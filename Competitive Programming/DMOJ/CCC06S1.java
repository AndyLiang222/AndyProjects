package contest;

import java.util.*;
import java.io.*;

public class CCC06S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		char [] p1 = br.readLine().toCharArray();
		char [] p2 = br.readLine().toCharArray();
		int n = readInt();
		for (int i = 0; i< n;i++) {
			char[] child = br.readLine().toCharArray();
			boolean works = false;
			for (int idx = 0; idx< n;idx++) {
				works = true;
				boolean c = Character.isUpperCase(child[idx]);
				boolean[] a1 = {Character.isUpperCase(p1[2*idx]),Character.isUpperCase(p1[ 2*idx+1])};
				boolean[] a2 = {Character.isUpperCase(p2[2*idx]),Character.isUpperCase(p2[ 2*idx+1])};
				if (c == false) {
					if((a1[0] == true || a2[0] == true) == true&&(a1[1] == true || a2[1] == true) == true) {
						works = false;
//						System.out.println(a1[0]);
//						System.out.println(a2[0]);
//						System.out.println(a1[1]);
//						System.out.println(a2[1]);
//						System.out.println(Boolean.toString((a1[0] == true || a2[0] == true) == true )+ " " + Boolean.toString((a1[1] == true || a2[1] == true) == true));
					}
				}
				else if (c == true) {
					
					if(a1[0] == false && a2[0] == false&&a1[1] == false && a2[1] == false) {
						works = false;
//						System.out.println(idx + " " + 2);
					}
					
				}
				if (works == false) break;
				
			}
			System.out.println(works == false? "Not their baby!" :"Possible baby.");
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
