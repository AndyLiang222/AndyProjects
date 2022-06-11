package contest;
import java.io.*;
import java.util.*;
public class CCC06S3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int x1 = readInt(), y1 = readInt(), x2 = readInt(), y2 = readInt();
		pair p1 = new pair(x1,y1);
		pair p2 = new pair(x2,y2);
		int n = readInt();
		int ans = 0;
		for(int i = 0;i<n;i++) {
			int k = readInt();
			pair [] c = new pair[k];
			for(int j = 0;j<k;j++) {
				int x = readInt(), y = readInt();
				c[j] = new pair(x,y);
			}
			for(int j = 0;j<k;j++) {
				if(check(p1, p2, c[j], c[(j+1)%k])) {
					ans++;
					
					//System.out.println("inter" + i);
					break;
				}
			}
		}
		System.out.println(ans);
	}
	public static boolean check(pair a1, pair a2, pair b1, pair b2) {
		if((rect(a1, a2, b1,b2)||rect(b1,b2,a1,a2)) && (long)cross(a1,a2,b1)* cross(a1,a2,b2)<= 0 & (long)cross(b1,b2,a1)* cross(b1,b2,a2)<= 0) {
			return true;
		}
		return false;
	}
	public static boolean rect(pair p1, pair p2, pair q1, pair q2) {
		int xl = Math.min(p1.x, p2.x), xr = Math.max(p1.x, p2.x), yb = Math.min(p1.y, p2.y), yt = Math.max(p1.y, p2.y);
	    return Math.min(q1.x, q2.x) <= xr && Math.max(q1.x, q2.x) >= xl && Math.min(q1.y, q2.y) <= yt && Math.max(q1.y, q2.y) >= yb;
	}
	public static int cross(pair a, pair b, pair c) {
		return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
	}
	static class pair{
		int x,y;
		pair(int x0, int y0){
			x = x0;
			y = y0;
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


