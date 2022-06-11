package contest;
import java.io.*;
import java.util.*;

public class AAC4P1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		String s = readLine();
		String ans = s.replaceAll("0", "");
		
		int [] vals = new int [k];
		int max = 0;
		int idx = 0;
		int split = 0;
		for(int i= 0;i<k;i++) {
			vals[i] = readInt();
		}
		for(int i =0;i<s.length();i++) {
			char c = s.charAt(i);
			//System.out.println(i + " " + split);
			if (c == '0') {
				if(vals[idx] >= max) {
					max = vals[idx];
					
					split = i-idx;
				}
				idx++;
				
				
			}
		}
		//System.out.println(split);
		System.out.println(ans.substring(split) + ans.substring(0,split));
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


