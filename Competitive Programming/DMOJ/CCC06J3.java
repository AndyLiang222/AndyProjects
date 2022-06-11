package contest;
import java.io.*;
import java.util.*;
public class CCC06J3 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean end = false;
		LinkedList <String> words = new LinkedList <> ();
		
 		while (end == false) {
			String temp = br.readLine();
			if (temp.equals("halt")) {
				break;
			}else {
				words.add(temp);
			}
			
		}
		for  (String x : words) {
			System.out.println("         ");
			char [] letters = x.toCharArray();
			int time = 0;
			int Lbutton = -1;
			int button = 0;
			int ac = 0;
			for (char s : letters) {
				ac = (int) s-97;
				System.out.println(ac);
				button =ac/3;
				System.out.println("button " + button + " Last " + Lbutton);
				if (ac ==25 ) {
					time += 4;
					button = 7;
				}else {
					time += ac%3+1;
				}
				if (button == Lbutton||Lbutton!= -1) {
					System.out.println("same");
					time +=2;
				}
				
				Lbutton = ac/3;
				
				
				
			}
			System.out.println(time);
			
		}
		
	}

}
