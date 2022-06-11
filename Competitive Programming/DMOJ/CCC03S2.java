package contest;
import java.io.*;
import java.util.*;
public class CCC03S2 {
	public static Set<String> vowels = new HashSet <> ();
	
	public static void type (String [] poem) {
		String [] endings = new String[4];
		for (int x = 0; x<4;x++) {
			String[] temp = poem[x].split("");
			for (int y = temp.length-1 ; y>=0;y--) {
				
				if (vowels.contains(temp[y])) {
					endings[x] = poem[x].substring(y);
					//System.out.println(endings[x]);
					break;
					//System.out.println(endings[x]);
				}
				else if (y== 0) {
					endings[x] = poem[x];
				}
			}
		}
		if (endings [0].equalsIgnoreCase(endings[1]) && endings[1].equalsIgnoreCase(endings[2]) && endings[1].equalsIgnoreCase(endings[3])) {
			System.out.println("perfect");
		}
		else if (endings[0] .equalsIgnoreCase(endings[3]) && endings[1].equalsIgnoreCase(endings[2]) ) {
			System.out.println("shell");
		}
		else if (endings[0].equalsIgnoreCase(endings[2])&& endings[1].equalsIgnoreCase(endings[3])) {
			System.out.println("cross");
		}
		else if (endings[0].equalsIgnoreCase(endings[1]) && endings[2].equalsIgnoreCase(endings[3])) {
			System.out.println("even");
		}else {
			System.out.println("free");
		}
		
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		int num = Integer.parseInt(br.readLine());
		ArrayList <String[]> poems = new ArrayList<>();
		vowels.add("A");
		vowels.add("a");
		vowels.add("O");
		vowels.add("o");
		vowels.add("I");
		vowels.add("i");
		vowels.add("E");
		vowels.add("e");
		vowels.add("U");
		vowels.add("u");
		
		for (int i = 0; i<num; i++) {
			String [] temp = new String [4];
			String []temp1 = br.readLine().split(" ");
			String []temp2 = br.readLine().split(" ");
			String []temp3 = br.readLine().split(" ");
			String []temp4 = br.readLine().split(" ");
			temp[0] = temp1[temp1.length-1];
			temp[1] = temp2[temp2.length-1];
			temp[2] = temp3[temp3.length-1];
			temp[3] = temp4[temp4.length-1];
			poems.add(temp);
			
		}
		for (int h = 0; h<num; h++) {
//			for (String g: poems.get(h)) {
//				System.out.println(g);
//			}
			type(poems.get(h));
		}
	}

}
