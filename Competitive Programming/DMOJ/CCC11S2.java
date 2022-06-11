package contest;
import java.io.*;
public class CCC11S2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int ques = Integer.parseInt(br.readLine());
		String [] choice = new String[ques+1];
		int count = 0;
		for (int i = 0; i<ques; i++) {
			choice[i] = br.readLine();
		}
		for (int s = 0; s<ques; s++) {
			String ans = br.readLine();
			if (ans.equals(choice[s])) {
				count++;
			}
		}
		System.out.println(count);
	}

}
