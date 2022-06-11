package contest;

import java.io.*;
import java.util.*;
public class bruteForce {
	public static int count (int [] arr) {
		int total = 0;
//		System.out.println("ARR");
		for (int l: arr) {
			total = total +l;
			
//			System.out.print(l);
			
		}
		return total;
	}
	static int []ar;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [ ] input = br.readLine().split(" ");
		int length = Integer.parseInt(input[0]);
		int snaps = Integer.parseInt(input[1]);
		String [] input1 = br.readLine().split(" ");
		int [] nums = new int [length];
		
		for (int i = 0; i<length;i++) {
			nums[i] = Integer.parseInt(input1[i]);
			//System.out.print(input[i-1]);
		}
		ar = nums;
		for (int i = 0; i<snaps;i++) {
			int l = ar.length;
//			System.out.println(Arrays.toString(ar));
			int split = Integer.parseInt(br.readLine());
			int[] a = Arrays.copyOfRange(ar, 0, split);
	        int[] b = Arrays.copyOfRange(ar,split , l);
//	        System.out.println(Arrays.toString(a));
//	        System.out.println(Arrays.toString(b));
	        int first = count(a);
	        int second = count(b);
	        //System.out.println(first + " " + second);
	        if (first >= second) {
	        	System.out.println(first);
	        	ar = b;
	        }else {
	        	System.out.println(second);
	        	ar = a;
	        }
		}
			
        
	}

}
