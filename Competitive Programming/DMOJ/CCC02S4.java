package contest;
import java.io.*;
import java.util.*;

public class CCC02S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean [][][][] winning;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int m = readInt();
		int n = readInt();
		
		int [] time = new int[n];
		String [] name = new String [n];
		
		for(int i = 0; i< n ;i++) {
			name[i] = readLine();
			
			time[i] = readInt();
		}
		int []best = new int [n+1];
		int[]group = new int[n+1];
		Arrays.fill(best, (int)1e9);
		Arrays.fill(group, -69);
		group[0]= 0;
		best[0] = 0;
		for(int i = 0 ;i<n+1; i++) {
			int cur = 0;
			for(int j = 1; j<=m&& j+i-1 < n;j++) {
				cur = Math.max(cur, time[i+j-1]);
				if (best[i]+ cur < best[i+j]) {
					best[i+j] = best[i]+cur;
					group[i+j] = j;
				}
			}
		}
		System.out.println("Total Time: " + best[n]);
//		for(int i : group) System.out.println(i);
		int []people = new int[n+1];
		int x = 0;
		int y = n;
		while(y!= 0) {
			people[x++] = group[y];
			y= y-group[y];
		}
		//System.out.println("\\\\\\\\" + x);
//		for(int i : people) System.out.println(i);
		int cou = 0;
		for(int i = x-1; i >=0; i--) {
			for(int j = 0; j<people[i];j++) {
				System.out.print(name[cou+j] + " ");
			}
			cou+= people[i];
			System.out.println();
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
