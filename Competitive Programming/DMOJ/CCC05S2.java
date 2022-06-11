package contest;
import java.io.*;
import java.util.*;
public class CCC05S2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int r = readInt(); int c = readInt();
		int x = 0; int y = 0;
		int mX = readInt(); int mY = readInt();
		while (!(mX == 0 && mY == 0)) {
			boolean  oX = false;
			boolean oY = false;
			if (x + mX >= r) { x = r;oX = true;}
			if (x + mX <= 0) {x = 0;oX = true;}
			if (y + mY >= c) {y = c;oY = true;}
			if (y + mY <= 0) {y = 0;oY = true;}
			if (oX == false) {
				
				x+= mX;
				
			}
			if (oY == false) {
				y += mY;
			}
			System.out.println(x + " " + y);
			mX = readInt(); mY = readInt();
			
		}
	}
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter () throws IOException {
		return next().charAt(0);
	}
	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
