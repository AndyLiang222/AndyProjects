package contest;

import java.io.*;

public class CCC15J5Nomemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		//amount of pies slices at the start
		int n = Integer.parseInt(br.readLine());
		//amount of people at the start
		int k = Integer.parseInt(br.readLine());
		//calls the recursive method to get amount of total ways
		int ways = piWays(n,k,1);
		System.out.println(ways);
	}
	public static int piWays (int n, int k, int min) {
		int t = 0;
		if (k==1) {
			return 1;
		}
		for (int i = min; i<(n/k)+1; i++) {
			t = t+ piWays(n-i,k-1, i);
		}
		return t;
	}
}
