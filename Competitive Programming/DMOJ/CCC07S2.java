package contest;

import java.util.*;
import java.io.*;

public class CCC07S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static box []boxes;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		int n = readInt();
		boxes = new box [n];
		for (int i = 0; i< n;i++) {
			boxes[i] = new box(readInt(), readInt(), readInt());
		}
		Arrays.sort(boxes);
		int m = readInt();
//		for (box  i : boxes) {
//			for (int d : i.d)System.out.print(d + " ");
//			System.out.println();
//		}
		for (int i = 0; i< m;i++) {
			//int hi = n-1 ; int lo = 0;
			long ans = Integer.MAX_VALUE; 
			box hold = new box(readInt(), readInt(), readInt());
			//long ans = Integer.MAX_VALUE;
			for (int s = 0; s < n;s++) {
				if (boxes[s].fits(hold)) {ans = boxes[s].v;break;}
//				System.out.println(s);
			}
//			while (hi >= lo) {
//				int mid = (hi+lo)/2;
//				if (boxes[mid].fits(hold)) { hi = mid-1;ans = Math.min(boxes[mid].v,ans);}
//				else lo = mid+1;
//			}
			System.out.println(ans == Integer.MAX_VALUE? "Item does not fit.": ans);
		}
		
		
	}

	static class box implements Comparable<box>{
		int [] d = new int[3]; long v;
		box(int l, int w, int h) {d[0] = l; d[1] = w; d[2] = h; Arrays.sort(d);v = l*h*w;}
		public int compareTo(box x) {return -Long.compare(x.v, v);}
		public boolean fits(box x) {
			for (int i = 0; i< 3;i++) {
				if (d[i] < x.d[i]) return false;
			}
			return true;
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
