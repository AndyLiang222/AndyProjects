package contest;
import java.util.*;
import java.io.*;

public class CastleInvasion {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n =readInt();
		int [] front = new int [n+1];
		int [] right = new int[n+1];
		int [] f = new int [(int)1e6+4];
		
		for(int i = 1; i<= n;i++) {
			front[i] = readInt();
			f[front[i]]++;
		}
		for (int i = 1 ;i <=n;i++) {
			right[i] = readInt();
			
			
		}
		for(int i =1; i< f.length;i++) {
			f[i] += f[i-1];
		}
		Arrays.sort(front);
		Arrays.sort(right);
//		System.out.println(front[n]);
		if (front[n] != right[n])System.out.println(-1);
		else {
			long pre = 0;
			int pi = 0;
			long ans = 0;
			for (int i = 1; i<= n;i++) {
				//System.out.println(pi+ " " + f[right[i]-1]);
				for (int j = pi; j<= f[right[i]-1]; j++ ) {
					pre+= front[j];
					pi = j+1;	
				}
				
				//System.out.println(pre + " " +  (long)right[i]*(n-f[right[i]-1]));
				ans += (long)pre + (long)right[i]*(n-f[right[i]-1]);
			}
			System.out.println(ans);
		}
		
		
 	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}


