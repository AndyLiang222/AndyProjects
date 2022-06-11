package contest;
import java.io.*;
import java.util.*;
public class BoardGames {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		int start = Integer.parseInt(input[0]);
		int end = Integer.parseInt(input[1]);
		HashMap<Integer, Integer> vis = new HashMap<>();
		HashMap<Integer, Integer> dis = new HashMap<>();
		Queue <Integer>q = new LinkedList<>();
		
		if (start == end) {
			System.out.println(0);
		}else {
			q.add(start);
			dis.put(start, 0);
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == end) {
				System.out.println(dis.get(cur));
				break;
			}else {
				//System.out.println(cur);
				if (3*cur < 10000000 && cur-3> 0 ) {
					if (vis.get(cur*3) == null) {
						
						q.add(3*cur);
						vis.put(3*cur, 1);
						dis.put(cur*3, dis.get(cur)+1);
						
					}
					if (cur%2 == 0 && vis.get(cur/2) == null) {
						q.add(cur/2);
						vis.put(cur/2, 1);
						dis.put(cur/2, dis.get(cur)+1);
						
					}
					if (vis.get(cur-3) == null) {
						q.add(cur-3);
						vis.put(cur-3, 1);
						dis.put(cur-3, dis.get(cur)+1);
						
					}
					if (vis.get(cur-1) == null) {
						q.add(cur-1);
						vis.put(cur-1, 1);
						dis.put(cur-1, dis.get(cur)+1);
						
					}
					
				}
			}
				
		}
	}

}
