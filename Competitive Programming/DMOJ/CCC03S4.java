package contest;
import java.io.*;

import java.util.Arrays;
public class CCC03S4 {
	public static int LCP (String s, String t) {
		int min = Math.min(s.length(), t.length());
		for (int l = 0; l<min;l++) {
			char s1 = s.charAt(l);
			char s2 = t.charAt(l);
			//System.out.println("------------" + s1 + " " + s2 + " __________");
			if (s1 == s2) {
				
			}else {
				return l;
			}
			
		}
		return min;
	}
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i<n;i++) {
			String string = br.readLine();
			String [] s = new String [string.length()];
			for (int x = 0; x<string.length();x++) {
				s[x] = string.substring(x,string.length());
				///System.out.println(s[x]);
			}
			Arrays.sort(s);
			
			int count = s[0].length() + 1;
			for (int y = 1; y<s.length;y++) {
				
				int lcp = LCP(s[y] , s[y-1]);
				//System.out.println(s[y].length()-lcp + "  " + count);
				count += s[y] .length() - lcp;
			}
			System.out.println(count);
			
		}
	}

}
