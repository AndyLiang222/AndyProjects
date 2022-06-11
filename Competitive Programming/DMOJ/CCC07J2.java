package contest;
import java.io.*;
import java.util.*;

public class CCC07J2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	/**
	 * CU	see you
:-)	I'm happy
:-(	I'm unhappy
;-)	wink
:-P	stick out my tongue
(~.~)	sleepy
TA	totally awesome
CCC	Canadian Computing Competition
CUZ	because
TY	thank-you
YW	you're welcome
TTYL	talk to you later
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		HashMap<String, String> map = new HashMap();
		map.put("CU", "see you");
		map.put(":-)", "I'm happy");
		map.put(":-(", "I'm unhappy");
		map.put(";-)", "wink");
		map.put(":-P", "stick out my tongue");
		map.put("(~.~)", "sleepy");
		map.put("TA", "totally awesome");
		map.put("CCC", "Canadian Computing Competition");
		map.put("CUZ", "because");
		map.put("TY", "thank-you");
		map.put("YW", "you're welcome");
		map.put("TTYL", "talk to you later");
		String in = readLine();
		while(true) {
			String[] re = in.split(" ");
			boolean done = false;
			for(String s: re) {
				if(map.containsKey(s)) {
					System.out.print(map.get(s) + " ");
				}else {
					System.out.print(s +  " ");
				}
				if(s.equals("TTYL"))done = true;
			}
			if(done)break;
			System.out.println();
			in = readLine();
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


