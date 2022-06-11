package contest;
import java.util.*;

public class bruh {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);

		int lowest = 0;
		do{
			int one = Integer.parseInt(sc.nextLine());
			int two = Integer.parseInt(sc.nextLine());
			int three = Integer.parseInt(sc.nextLine());
			lowest = Math.min(one, Math.min(two, three));
			int sum = one;
			if (one != two) sum += two;
			if(two != three && three != one) sum += three;
			System.out.println(sum);
		}while(lowest >= 0);
	}
}
