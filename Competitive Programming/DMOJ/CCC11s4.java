package contest;
import java.io.*;
public class CCC11s4 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String [] blood =br.readLine().split(" ");
		String [] patient = br.readLine().split(" ");
		
		int on = Integer.parseInt(blood[0]);
		int op = Integer.parseInt(patient[0]);
		for (int i = 0; i<4; i++) {
			int rn = Integer.parseInt(blood[i]);
			int rp = Integer.parseInt(blood[i+1]);
			int pn = Integer.parseInt(patient[i]);
			int pp = Integer.parseInt(patient[i+1]);
			if (rn>pn) {
				patient[i] = "0";
				
			}
		}
	}

}
