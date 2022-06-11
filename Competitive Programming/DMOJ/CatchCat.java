package contest;
import java.io.*;
import java.util.*;
public class CatchCat {
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int s = 0; s< n;s++) {
			int x = 0;
			int y = Integer.parseInt(br.readLine());
	
			boolean [] vis = new boolean [100001]; int []dis = new int[100001]; 
			Queue <Integer> q = new LinkedList<>();
			int v = 0; q.add(x);dis[x] = 0;vis[x] = true;
			while (!q.isEmpty()) {
				int u = q.poll();
				for (int i = 0; i<3;i++) {
					if (i== 0) {
						v = u+1;
					}else if (i == 1) {
						v = u-1;
					}else {
						v = 2*u;
					}
					if (v>= 0 && v <= 100000&&vis[v] == false) {
						dis[v] = dis[u]+1; vis[v] = true;q.add(v);
					}
					
				}
				
			}
			System.out.println(dis[y]);
		}
//		if (x>y) {
//			System.out.println(dist);
//		}else {
//			max = 0;
//			int temp = x;
//			
//			while (temp!= y) {
//				//System.out.println(temp);
//				//if (Math.abs(((temp-1)*2)) < )
//				if (temp == 0) {temp++;max++;}
//				else if(Math.abs(temp*2-y) >= Math.abs(temp-y)) {
//					if (temp < y) {temp++;}
//					else {temp--;}
//					max +=1;
//				}
//				else{temp = temp*2;max++;}
//				
//			}
//			//System.out.println(max);
//			dis = new int [100001];
//			dis[y] = max;
//			//System.out.println(max);
//			DFS(x, 0);
//			System.out.println(x + " " + y);
//			System.out.println(dis[50000]);
//			System.out.println(max);
		
		
	}
//	static void DFS(int x, int moves) {
//		if (x == y) {max = moves; dis[x] = moves;}
//		else {
//			if (moves<max) {
//				if (x<13502) {
//					if ((moves+1 < dis[x+1] || dis[x+1] == 0)) {
//						//System.out.println("+1 " +  x + " " + moves);
//						dis[x+1] = moves+1;
//						DFS(x+1, moves+1);
//					}
//				}
//				
//				if (x >0) {
//					if ((moves+1 < dis[x-1] || dis[x-1] == 0)  ) {
//						//System.out.println("-1 " +  x + " " + moves);
//						dis[x-1] = moves+1;
//						DFS(x-1, moves+1);
//					}
//				}
//				if (2*x < 13502) {
//					if ((moves+1 < dis[2*x]|| dis[2*x] == 0 ) ) {
//						//System.out.println("*2 " +  x + " " + moves);
//						dis[2*x] = moves+1;
//						DFS(2*x, moves+1);
//					}
//				}
//				
//			}
//		}
//	}

}
