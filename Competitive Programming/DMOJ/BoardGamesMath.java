package contest;
import java.io.*;

public class BoardGamesMath {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		int start = Integer.parseInt(input[0]);
		int end = Integer.parseInt(input[1]);
		int moves = 0;
		if (start == end) {
			System.out.println(0);
		}
		while(start != end) {
			if (start > end) {
				
				if (start%2 == 0 && start / 2 >= end) {
					start = start/2;
					moves++;
				}else {
					if (Math.abs(start/2-end) <= Math.abs(start-3-end) ||Math.abs(start/2-end)<= Math.abs(start-1-end) && start%2 == 0 ) {
						start = start/2;
						moves++;
					}else {
						if (start-3>= end) {
							start = start-3;
							moves++;
							//System.out.println("3-");
						}else {
							if (start-1 == end) {
								moves++;
								start = start-1;
								//System.out.println("1-");
							}else {
								moves= moves+2;
								start = start-2;
								//System.out.println("2-");
							}
						}
				
					}
				}
				
//				System.out.println(moves);
			}else {
				
				//8-6 = 2 , 12-6 = 6
				if (start < end) {
					start = start*3;
					moves++;
					
					//System.out.println("3*");
				}else {
					if (Math.abs(start/2-end) <= Math.abs(start-3-end) ||Math.abs(start/2-end)<= Math.abs(start-1-end) && start%2 == 0 ) {
						start = start/2;
						moves++;
					}else {
						if (start-3>= end) {
							start = start-3;
							moves++;
							//System.out.println("3-");
						}else {
							if (start-1 == end) {
								moves++;
								start = start-1;
								//System.out.println("1-");
							}else {
								moves= moves+2;
								start = start-2;
								//System.out.println("2-");
							}
						}
				
					}
					
				}
			}
			//System.out.println(start);
		}
		if(moves == 375) {
			System.out.println(start + " " + end);
		}
		System.out.println(moves);
	}

}
