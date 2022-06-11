package contest;
import java.io.*;
public class CCC12S1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine())-1;
		System.out.println(n*(n-1)*(n-2)/6);
	}

}
