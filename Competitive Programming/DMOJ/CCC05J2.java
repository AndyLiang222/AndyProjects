package contest;
import java.io.*;
public class CCC05J2 {
	public static int RSA(int num) {
		int factors = 0;
		for (int i = 1; i<=num; i++) {
			if (num%i == 0) {
				factors++;
			}
		}
		if (factors==4) {
			return(1);
		}else {
			return(0);
		}
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int start= Integer.parseInt(br.readLine());
		int end = Integer.parseInt(br.readLine());
		int RSAcount = 0;
		for (int i = start ; i<end+1; i++) {
			RSAcount = RSAcount + RSA(i);
		}
		System.out.println("The number of RSA numbers between " + start + " and " + end+ " is " + RSAcount);
	}
}
