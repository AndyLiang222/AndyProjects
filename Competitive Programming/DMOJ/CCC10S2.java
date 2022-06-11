package contest;
import java.io.*;
import java.util.*;
public class CCC10S2 {	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		HashMap<String, String>encode = new HashMap<>();
		for (int i = 0; i<n;i++) {
			String [] input= br.readLine().split(" ");
			encode.put(input[1], input[0]);
		}
		String code = br.readLine();
		String word = "";
		int len = code.length();
		while(len>0) {
			for (int r = 0; r<=len;r++) {
				String pre = code.substring(0, r);
				if (encode.get(pre) != null) {
					word = word + encode.get(pre);
					len = len-r;
					code = code.substring(r);
				}
			}
		}
		System.out.println(word);
	}

}
