package contest;
import java.io.*;
import java.util.*;
public class CCC12S3 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		HashMap <Integer, Integer> freq = new HashMap<>();
		int test = Integer.parseInt(br.readLine());
		for (int i = 0; i<test; i++) {
			int temp = Integer.parseInt(br.readLine());
			
			boolean val = freq.containsKey(temp);
			if(val == true) {
				freq.put(temp, freq.get(temp)+1);
				
			}else {
				freq.put(temp, 1);
			}
		}
		Set<Integer> vals = freq.keySet();
		int hF= 0;
		int hF2 = 0;
		int	[] va1 = new int [2];
		int []va2 = new int [2];
		int dif2 = -1;
		
		for (int s: vals) {
			int fre = freq.get(s);
			if (fre > hF) {
				hF2 = hF;
				va2 = va1.clone();
				hF = fre;
				va1[0] = s;
				va1[1] = 0;
				
			}else if (fre == hF) {
				if (s>va1[0]) {
					if (va1[1] == 0) {
						va1[1] = va1[0];
					}
					va1[0] = s;
				}
				else if(s<va1[1]) {
					va1[1] = s;
				}
 						
				}
			else if (fre > hF2) {
				hF2 = fre;
				
				va2[1] = 0;
				
			}
				
			else if (fre == hF2 && va2[1] == 0) {
				va2[1] = s;
			}
			else if (fre == hF2&& va2[1] !=0) {
				int dif = Math.abs(va1[0] -s);
				if (dif> dif2) {
					va2[1] = s;
					dif2 = dif;
				}
			}
			//System.out.println(va1[0] + " " + va1[1] + " " + va2[1] + " " + s + " " + dif2);
		}
		if (va1[1] == 0) {
			System.out.println(dif2);
		}else {
			System.out.println(va1[0] - va1[1]);
		}
		
	}

}
