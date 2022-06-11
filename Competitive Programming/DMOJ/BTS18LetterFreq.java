package contest;
import java.io.*;
import java.util.*;

public class BTS18LetterFreq {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		String s = readLine();
		int [][] l = new int[26][s.length()+2];
		for(int i = 0;i<s.length();i++) {
			int v = s.charAt(i)-'a';
			if (v >= 0 && v <= 25) {
				l[v][i+1] += 1;
			}
			for(int j = 0;j<=25;j++) {
				l[j][i+1] += l[j][i];
				//System.out.print(l[j][i+1] + " ");
			}
			//System.out.println();
		}
		for(int j = 0;j<=25;j++) {
			l[j][s.length()+1] += l[j][s.length()];
			//System.out.print(l[j][i+1] + " ");
		}
		//System.out.println();
		int q = readInt();
		for(int i = 0;i < q;i++) {
			int a = readInt(), b = readInt(), v = readCharacter();
			int c = v- 'a';
			System.out.println(l[c][b] - l[c][a-1]);
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


