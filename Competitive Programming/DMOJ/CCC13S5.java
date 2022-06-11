package contest;
import java.io.*;
public class CCC13S5 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int cost = 0;
		while (num >1) {
			int factor = 2;
			int range =(int) Math.sqrt(num)+1;
			while (factor<= range && num%factor != 0) {
				factor+=1;
			}
			if (num%factor == 0 && factor<num) {
				int temp = num/factor;
				num = num-temp;
				cost = cost +num/temp;
			}else {
				num --;
				cost = cost + num;
			}
		}
		System.out.println(cost);
			
		
	}

}
