package contest;
import java.io.*;
public class CCC05S4 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int tests= Integer.parseInt(br.readLine());
		
		for(int i = 0; i< tests;i++) {
			int n = Integer.parseInt(br.readLine());
			String [] hold = new String[n];
			int length = 0;
			for (int k = 0; k< n ; k++) {
				hold[length++] = br.readLine();
			}
			int max = 0;
			String [] s = new String[n];
			s[0] = hold[length-1];
			length = 0;
			for (int node = 0; node<n;node++) {
				int l = 0;
				while(length>= l&& !hold[node].equals(s[l])) {
					l++;
				}
				s[l] = hold[node];
				length = l;
				if (length >= max) {
					max = length;
				}
			}
			System.out.println(n*10-2*max*10);
			
		}
	}

}
