package contest;
import java.io.*;
import java.util.*;

public class BinaryIndexTree {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int [] num;static int n;
	public static void main(String[] args) throws IOException {
		n = readInt();
		int m = readInt();
		
		num = new int[n+1];
		long []tree = new long [100001];
		long []t2 = new long [100001];
		for (int i = 1; i<=n;i++) {
			num[i] = readInt();
			update(i, num[i],tree);
			update(num[i],1, t2);
//			System.out.print(tree[i] + " ");
		}
		
		for (int i = 0; i< m;i++) {
			char c = readCharacter();
			if (c == 'C') {
				int x = readInt(), v = readInt();
				
				update(num[x], -1,t2);
				update(x,v-num[x],tree);
				update(v,1,t2);
				num[x] = v;
				
			}
			else if (c == 'Q') {
				int v = readInt();
				
				System.out.println(freqTo(v,t2));
			}
			else {
				int l = readInt(), r = readInt();
				System.out.println((freqTo(r,tree)-freqTo(l-1,tree)));
			}
		}
		
		
	}
	
	static void update(int idx, long val, long[]tree) {
		while(idx < 100001) {
			tree[idx]+= val;
			idx+= (idx&-idx);
		}
	}
	static long freqTo (int idx, long[]tree) {
		long sum = 0;
		while (idx > 0) {
			sum+= tree[idx];
			idx -=(idx &-idx);
		}
		return sum;
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


