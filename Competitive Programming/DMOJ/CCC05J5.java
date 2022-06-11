package contest;
import java.io.*;
import java.util.*;
public class CCC05J5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		String input = readLine();
		while(!input.equals("X")) {
			System.out.println((isWord(input) ==true)? "YES" : "NO");
			input = readLine();
		}
		
	}
	static boolean isWord(String s) {
		if (s .equals("A")) return true;
		if (s.charAt(0) == 'B' && s.charAt(s.length()-1) == 'S') {
			return isWord(s.substring(1, s.length()-1));
		}else {
			if (s.length() > 2) {
				if (s.charAt(0) == 'A' && s.charAt(1) == 'N') {
					return isWord(s.substring(2, s.length()));
					
				}
			}
			if(s.charAt(0) == 'A'){
				return isWord(s.substring(1, s.length()));
			}else {
				return false;
			}
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


