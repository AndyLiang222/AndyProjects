package contest;
import java.io.*;
import java.util.*;
public class CCC05S1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int n = readInt();
		for (int i = 0; i< n ;i++) {
			char [] phone = readLine().toCharArray();
			int cou = 0;
			int idx = 0;
			String pn = "";
			boolean dont = false;
			while(cou <= 9) {
				if ((cou)%3 == 0 && cou != 9&& cou != 0 && dont != true) {
					System.out.println("added dash " + cou);
					pn+='-';
				}
				
				if (phone[idx] == 45) {
					System.out.println("dash");
					dont = true;idx ++;continue;
				}
				else if (phone[idx] == 122) {
					System.out.println("added" + phone[idx]);
					pn+= '9';cou++;
				}
				else if (phone[idx] == 83) {
					pn+= '7';
				}
				else if (phone[idx] > 83) {
					System.out.println("added" + phone[idx]);
					int num = (phone[idx]-64)/3;
					pn+= num;cou++;
				}
				else if (phone[idx] > 44){
					System.out.println("added" + phone[idx]);
					pn+= phone[idx]; cou++;
				}
				idx ++;
				dont= false;
				
				
			}
			System.out.println(pn);
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
