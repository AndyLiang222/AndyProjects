package contest;
import java.io.*;
public class CCC15J5 {
	public static int [][][] visited= new int [251][251][251];
	public static void main(String[] args) throws IOException{
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
	public static int piWays(int n, int k, int min) {
		//checks to see if 
		if (visited[n][k][min] == 0) {
			if(n==k) {
				visited[n][k][min]= 1;
			}
			else if (k== 1) {
				visited[n][k][min] = 1;
			
			}else {
				int t = 0;
				for (int i = min; i<(n/k)+1; i++) {
					//System.out.println(i);
					t = t+ piWays(n-i,k-1, i);
				}
				visited[n][k][min]= t;
			}
		}
		return(visited[n][k][min]);
	}

}
