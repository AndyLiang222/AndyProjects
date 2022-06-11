package contest;
import java.io.*;
public class CCC11J5 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		int mark = Integer.parseInt(br.readLine());
		int [] ways = {1,1,1,1,1,1,1};
		for (int i = 1; i<mark;i++) {
			int invitedBy = Integer.parseInt(br.readLine());
			ways[invitedBy] = ways[invitedBy]* (ways[i]+1);
		}
		System.out.println(ways[mark]);
	}

}
