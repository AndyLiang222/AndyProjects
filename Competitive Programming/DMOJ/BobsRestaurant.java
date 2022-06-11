package contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BobsRestaurant {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int n = readInt(), k = readInt();
		
		int v = 0; int sum = 0;
		res [] stores = new res[n];
		for(int i = 0; i< n ;i++) {
			int m = readInt(), x = readInt();
			stores[i] = new res(m, x);
		}
		Arrays.sort(stores);
		int [] road = new int [stores[n-1].x+1];
		for(int i = 0; i<n ;i++) {
			
			int m = stores[i].m, x = stores[i].x;
//			System.out.println(" fjfalf" + m + " " + x);
			for(int j = v; j < x;j++) road[j] = sum;
			sum += m;
			v= x+1;
			road[x] = sum;
		}
//		System.out.println();
//		for(int b : road) {
//			System.out.print(b + " ");
//		}
		int ans = 0;
		for(int i = 0; i<=stores[n-1].x;i++ ) {
			if(i  < 2*k+1)ans = Math.max(road[i], ans);
			else ans = Math.max(road[i]-road[i-2*k-1], ans);
		}
		System.out.println(ans);
		
	}
	static class res implements Comparable<res>{
		int m, x;
		res(int m0, int x0){m = m0; x = x0;}
		public int compareTo(res z) {return -Integer.compare(z.x, x);}
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
