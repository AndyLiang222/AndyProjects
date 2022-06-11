package contest;
import java.io.*;
import java.util.*;

public class CCC15S3N {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		TreeSet<Integer>pls = new TreeSet();
		for(int i =1;i<=n;i++)pls.add(i);
		int parks = 0;
		for(int i = 0;i<m;i++) {
			int mG = readInt()+1;
			
			if(pls.lower(mG)!= null) {
				parks++;
				pls.remove(pls.lower(mG));
			}else {
				break;
			}
		}
		System.out.println(parks);
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


