package contest;
import java.io.*;
public class CCC02S2 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int numerator = Integer.parseInt(br.readLine());
		int d = Integer.parseInt(br.readLine());
		if (numerator == 0) {
			System.out.println(0);
		}else {
			int whole = numerator/d;
			numerator = numerator-whole*d;
			int simp = 0;
			for (int i = 1; i<=Math.min(numerator,d);i++) {
				//System.out.println(numerator%i + " " + d%i + " " +  simp);
				if (numerator%i == 0&& d%i == 0) {
					//System.out.println(i);
					simp = i;
				}
			}
			if (whole >0 && numerator >0) {
				System.out.println(whole + " " + numerator/simp+ "/" +d/simp);
			}else if (whole >0) {
				System.out.println(whole);
			}else {
				System.out.println(numerator/simp+ "/" + d/simp);
			}
		}
		
		
		
	}

}
