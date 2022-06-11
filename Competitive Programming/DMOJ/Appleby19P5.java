package contest;
import java.io.*;
public class Appleby19P5 {
	static int [][] map; static int n; static int [][] dis;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map  = new int [n][n];
		dis = new int [n][n];
		for (int l = 0; l< n;l++) {
			String[] input = br.readLine().split(" ");
			for (int w = 0; w<n;w++) {
				map[l][w] = Integer.parseInt(input[w]);
			}
		}
		int max = Integer.MIN_VALUE;
		for (int l = 0;l< n;l++) {
			for (int w = 0; w<n;w++) {
				if (dis[l][w] == 0) {
					DFS(l,w);
					if (dis[l][w] > max) {
						max =dis[l][w];
					}
				}
			}
		}
//		for (int l = 0;l< n;l++) {
//			System.out.println();
//			for (int w = 0; w<n;w++) {
//				System.out.print(dis[l][w]);
//			}
//		}
		System.out.println(max);
	}
	static int DFS(int l, int w) {
		int cur = map[l][w];
		int maxl = Integer.MIN_VALUE;
		int temp = 0;
		boolean end = true;
		if (dis[l][w] != 0) {
			return dis[l][w] ;
		}else {
			
		
			if (l+1 < n) {
				if (map[l+1][w] > cur) {
					end = false;
					temp = 1 +DFS(l+1,w);
					if (temp > maxl) {
						maxl = temp;
						
					}
				}
			}
			if (w+1  < n) {
				if (map[l][w+1] > cur) {
					end = false;
					temp =1 + DFS(l,w+1);
					if (temp > maxl) {
						maxl = temp;
					}
				}
			}
			if(w-1 >= 0) {
				if (map[l][w-1] > cur) {
					end = false;
					temp =1+DFS(l,w-1);
					if (temp > maxl) {
						maxl = temp;
					}
				}
			}
			if (l-1 >= 0) {
				if (map[l-1][w] > cur) {
					end = false;
					temp =1+ DFS(l-1,w);
					if (temp > maxl) {
						maxl = temp;
					}
				}
			}
		}
		if (end == true) {
			dis[l][w] = 0;
			return 0;
		}
		dis[l][w] = maxl;
		return maxl;
	}

}
