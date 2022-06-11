package contest;
import java.io.*;
import java.util.*;
public class CCC08S3 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		HashMap<String, LinkedList<Integer>> di = new HashMap<>();
		LinkedList<Integer>all = new LinkedList<>();
		all.add(-1);all.add(1);all.add(-2);all.add(2);
		di.put("+", all);
		LinkedList<Integer>up = new LinkedList<>();
		up.add(-1);up.add(1);
		di.put("|", up);
		LinkedList<Integer>side = new LinkedList<>();
		side.add(-2);side.add(2);
		di.put("-", side);
		int tests = Integer.parseInt(br.readLine());
		for (int i = 0; i< tests; i++) {
			
			int length = Integer.parseInt(br.readLine());
			int width = Integer.parseInt(br.readLine());
			int [][] dis = new int[length][width];
//			System.out.println("Length: " + length + " Width: " + width);
			String[][] map = new String[length][width];
			for (int l = 0; l<length;l++) {
				String [] input1 = br.readLine().split("");
				for (int s = 0; s< width;s++) {
					map[l][s] = input1[s];
					dis[l][s] = Integer.MAX_VALUE;
//					System.out.println(input1[s]);
				}
			}
			
//			for (int s = 0; s<length;s++) {
//				System.out.println();
//				for (int l = 0; l<width;l++) {
//					System.out.print(map[s][l]);
//				}
//			}
			Queue <Integer> y = new LinkedList<>();
			Queue <Integer> x = new LinkedList<>();
			y.add(0);
			x.add(0);
			
			int eY = length-1;
			int eW = width-1;
			dis[0][0] = 1;
			
			boolean complete = false;
			while (!y.isEmpty()) {
				int cY = y.poll();
				int cX = x.poll();
//				System.out.println(cY + " " + cX);
				if (cY == eY && cX == eW) {
					complete = true;
					System.out.println(dis[cY][cX]);
					break;
				}
				
				String inter = map[cY][cX];
//				System.out.println(inter);
				if (di.get(inter) != null) {
					for (int d: di.get(inter)) {
//						System.out.println(d+ " bruh" );
						if (d == 2) {
							if (cX+1 < width) {
								if (dis[cY][cX+1]>dis[cY][cX]+1 &&! "*".equals(map[cY][cX+1])) {
									dis[cY][cX+1] = dis[cY][cX]+1;
									y.add(cY);
									x.add(cX+1);
								}
								
							}
						}
						else if (d == -2) {
							if (cX > 0) {
								if (dis[cY][cX-1]>dis[cY][cX]+1&& !"*".equals(map[cY][cX-1])) {
									dis[cY][cX-1] = dis[cY][cX]+1;
									y.add(cY);
									x.add(cX-1);
								}
							}
						}
						else if(d == 1){
							if (cY+1 < length) {
								if (dis[cY+1][cX]>dis[cY][cX]+1&& !"*".equals(map[cY+1][cX])) {
									dis[cY +1][cX] = dis[cY][cX]+1;
									y.add(cY+1);
									x.add(cX);
								}
								
							}

						}
						else if (d == -1) {
							if (cY-1 >= 0) {
								if (dis[cY-1][cX]>dis[cY][cX]+1&& !"*".equals(map[cY-1][cX])) {
									dis[cY-1][cX]= dis[cY][cX]+1;
									y.add(cY-1);
									x.add(cX);
								}
							}
						}
					}
				}
				
			}
			if(complete == false) {
				System.out.println(-1);
			}
			
		}
	}

}
