package contest;
import java.io.*;
import java.util.*;
public class CCC13S4 {
	static int n; static int m; 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		HashMap<Integer, LinkedList<Integer>> taller = new HashMap<>();
		
		for (int i = 0; i< m; i++) {
			String [] input1 = br.readLine().split(" ");
			int node = Integer.parseInt(input1[0]);
			int connect = Integer.parseInt(input1[1]);
			if (taller.get(node) == null) {
				LinkedList <Integer> temp = new LinkedList<>();
				temp.add(connect);
				taller.put(node, temp);
			}else {
				taller.get(node).add(connect);	
			}
			
			
		}
		//System.out.println(taller);
		
		String [] input3 = br.readLine().split(" ");
		int start = Integer.parseInt(input3[0]);
		int end = Integer.parseInt(input3[1]);
		Queue <Integer> q = new LinkedList<>();
		q.add(start);
		boolean unknown = true;
		boolean [] vis = new boolean [n+1];
		vis[start] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			if (taller.get(cur) != null) {
				for (int next : taller.get(cur)) {
					//System.out.println(cur + " " + next);
					if (next == end) {
						unknown = false;
						System.out.println("yes");
						q.clear();
					}else {
						if (vis[next] == false) {
							vis[next] = true;
							q.add(next);
						}
							
					}
				}
			}
		}
		q.add(end);
		Arrays.fill(vis, false);
		vis[end] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (taller.get(cur) != null) {
				for (int next : taller.get(cur)) {
					if (next == start) {
						unknown = false;
						System.out.println("no");
						q.clear();
					}else {
						if (vis[next] == false) {
							vis[next] = true;
							q.add(next);
						}
					}
				}
			}
		}
		if(unknown == true) {
			System.out.println("unknown");
		}
	}

}
