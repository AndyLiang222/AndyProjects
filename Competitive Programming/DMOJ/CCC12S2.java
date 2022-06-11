package contest;
import java.io.*;
import java.util.*;
public class CCC12S2 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String [] num = br.readLine().split("");
		HashMap<String, Integer> baseValue = new HashMap<>();
		
		baseValue.put("I", 1);
		baseValue.put("V", 5);
		baseValue.put("X", 10);
		baseValue.put("C", 100);
		baseValue.put("D", 500);
		baseValue.put("M", 1000);
		baseValue.put("L", 50);
		
		int last = 0;
		int value = 0;
		for (int i = num.length-1; i>=0; i-=2) {
			int base = baseValue.get(num [i]);
			int val = Integer.parseInt(num[i-1]);
			if (last > base) {
				value = value - base*val;
			}else {
				value = value + base*val;
			}
			last = base;
			
			
			
			
			
		}
		System.out.println(value);
	
	}		

}
