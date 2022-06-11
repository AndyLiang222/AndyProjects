package contest;
import java.io.*;
public class CCC09S1 {
	public static void main (String [] args) throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int start = Integer.parseInt(br.readLine());
		int end = Integer.parseInt(br.readLine());
		int count = 0;
		int leng = (int)Math.cbrt(end);
		
		for (int i = 1; i<=leng; i++) {
			
			int cubed = (int)Math.pow(i, 3);
			int base = (int)Math.sqrt(cubed);
			int squared = (int)Math.pow(base, 2);
			if (cubed == squared && cubed >= start) {
				//System.out.println(cubed + " " + squared);
				//System.out.println(i);
				count++;
			}
		}
		System.out.println(count);
	}
}
