package contest;
import java.io.*;
import java.util.*;

public class Candy {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int k;
	public static void main(String[] args) throws IOException {
		k = readInt();
		if (check (1, 0, 1, "1")== false)System.out.println("Sad Chris"); ;
		
		
	}
	static boolean check (long uni, long dup, long sets, String set) {
		boolean i = false;
		if (sets<= k) {
			//System.out.println(sets+ " " + uni);
			if (sets == k && uni+dup < 100000) {
				System.out.println(uni+dup);
				System.out.println(set);
				return true;
			}else {
				i = check(uni+1, dup, sets*2 + 1, set+" " + (uni+1));
				if (i == false) {
					if ((k-sets) %uni == 0 && (k-sets)/uni +uni + dup < 100000) {
						//System.out.println("here");
						for(int j = 0; j< (k-sets)/uni;j++) {
							set+= " " + 1;
							
						}
						i = check(uni,(k-sets)/uni, k, set);
					}
				}
				
				return i;
			}
			
		}
		return false;
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


