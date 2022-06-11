package contest;
import java.io.*;
public class CCC00S1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		int coin = Integer.parseInt(br.readLine());
		int machine1 = Integer.parseInt(br.readLine());
		int machine2 = Integer.parseInt(br.readLine());
		int machine3 = Integer.parseInt(br.readLine());
		int turn = 0;
		int turns = 0;
		while (coin>0) {
			coin--;
			if (turn ==0) {
				machine1++; 
				if (machine1 == 35) {
					machine1 =0;
					coin += 30;
				}
			}
			else if (turn == 1) {
				machine2 ++;
				if (machine2 == 100) {
					machine2 = 0;
					coin += 60;
				}
			}
			else if (turn == 2) {
				machine3++;
				if (machine3 == 10) {
					machine3 = 0;
					coin += 9;
				}
			}
			if (turn == 2) {
				turn= 0;
			}else {
				turn++;
			}
			turns++;
			
		}
		System.out.println("Martha plays "  + turns + " times before going broke.");
	}

}
