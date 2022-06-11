package contest;
import java.util.*;
import java.io.*;
public class CCC00S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		double[] streams = new double[101];
		for(int i = 1 ;i<= n;i++) {
			streams[i] = readInt();
		}
		int sC = n;
		int op = readInt();
		while(op != 77) {
			if (op == 88) {
				int pos = readInt();
				streams[pos] += streams[pos+1];
				for (int i = pos+1; i <= sC;i++ ) {
					streams[i] = streams[i+1];
				}
				sC--;
			}else if (op == 99) {
				int pos = readInt();
				int per = readInt();
				
				for (int i = sC; i> pos;i--) {
					streams[i+1]= streams[i];
				}
				
				sC ++;
				
				double hold = streams[pos];
				streams[pos] = (streams[pos]*((per)/100.0));
				
				streams[pos+1] = hold-streams[pos];
				
			}
			
			
			op = readInt();
		}
		for (int i = 1; i<= sC;i++) {
			System.out.print(Math.round(streams[i])+ " ");
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


