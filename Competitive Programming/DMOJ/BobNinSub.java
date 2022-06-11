package contest;
import java.io.*;
import java.util.StringTokenizer;
public class BobNinSub {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		int n = readInt();int prev= Integer.MAX_VALUE; int []nums= new int[n];  int length = 1; int ans = 0;
		nums[0] = readInt();
		for (int i = 1; i<n;i++) {
			int temp = readInt();
			if (temp <= nums[i-1]) {
				length++;
			}else {
				length = 1;
			}
			nums[i] = temp;
			ans = Math.max(ans, length);
		}
		System.out.println(ans);
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
