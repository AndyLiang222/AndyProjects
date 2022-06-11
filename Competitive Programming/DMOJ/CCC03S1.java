package contest;
import java.io.*;
import java.util.*;
public class CCC03S1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		HashMap <Integer, Integer> Snakes = new HashMap<>();
		HashMap <Integer, Integer> Ladders= new HashMap<>();
		int place = 1;
		Snakes.put(54, 19);
		Snakes.put(90 , 48);
		Snakes.put(99, 77);
		Ladders.put(9,34);
		Ladders.put(40,64);
		Ladders.put(67,86);
		boolean end = false;
		while (end == false) {
			int move = Integer.parseInt(br.readLine());
			if (move== 0) {
				System.out.println("You Quit!");
				break;
			}
			if (place + move <= 100) {
				place = place+move;
				
			}
			if (Snakes.get(place) != null) {
				place = Snakes.get(place);
			}else if (Ladders.get(place) != null) {
				place = Ladders.get(place);
			}
			System.out.println("You are now on square " + place);
			if (place == 100) {
				System.out.println("You Win!");
				break;
			}
			
		}
	}

}
