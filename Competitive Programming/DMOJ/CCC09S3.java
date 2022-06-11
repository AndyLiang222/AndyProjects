package contest;
import java.io.*;
import java.util.*;
public class CCC09S3 {
	static List<Integer> adj [] = new ArrayList [51];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		
		for (int i = 1;i<19;i++ ) {
			adj[i] = new ArrayList<>();
		}
		add(1, 6);
		add(2, 6);
		add(3, 4);
		add(3, 5);
		add(3, 6);
		add(3, 15);
		add(4, 5);
		add(4, 6);
		add(5, 6);
		add(6, 7);
		add(7, 8);
		add(8, 9);
		add(9, 10);
		add(9, 12);
		add(10, 11);
		add(11, 12);
		add(12, 13);
		add(13, 14);
		add(13, 15);
		add(16, 17);
		add(16, 18);
		add(17, 18);
		String input = br.readLine();
		while(!"q".equals(input)) {
			
			if ("i".equals(input)) {
				int in1 = Integer.parseInt(br.readLine());
				int in2 = Integer.parseInt(br.readLine()); 
				add(in1, in2);
				
			}
			else if("d".equals(input)) {
				int in1 = Integer.parseInt(br.readLine());
				int in2 = Integer.parseInt(br.readLine()); 
				del(in1, in2);
			}
			else if("n".equals(input)) {
				int in1 = Integer.parseInt(br.readLine());
				System.out.println(adj[in1].size());
			}
			else if ("f".equals(input)) {
				int in1 = Integer.parseInt(br.readLine());
				System.out.println(ff(in1));
			}
			else if("s".equals(input)){
				int in2 = Integer.parseInt(br.readLine()); 
				int in1 = Integer.parseInt(br.readLine());
				
				int s = furthest(in1, in2);
				if (s == -1) {
					System.out.println("Not connected");
				}else {
					System.out.println(s);
				}
			}
			input = br.readLine();
		}
		
		
		
	}
	static int ff (int n) {
		int []dis = new int [51];
		Arrays.fill(dis, Integer.MAX_VALUE);
		Queue <Integer> q = new LinkedList<>();
		q.add(n);
		dis[n] = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if (adj[cur] != null) {
				for (int next: adj[cur]) {
					if(dis[next] > dis[cur]+1 && dis[cur]+1< 3) {
						dis[next]= dis[cur]+1;
						q.add(next);
					}
				}
			}
		}
		int count = 0;
		for (int d : dis) {
			if (d == 2) {
				count++;
			}
		}
		return count;
	}
	static int furthest (int n, int c) {
		int []dis = new int [51];
		Arrays.fill(dis, Integer.MAX_VALUE);
		Queue <Integer> q = new LinkedList<>();
		q.add(n);
		dis[n] = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if (adj[cur] != null) {
				for (int next: adj[cur]) {
					if(dis[next] > dis[cur]+1) {
						dis[next]= dis[cur]+1;
						q.add(next);
					}
				}
			}
		}
		if (dis[c] == Integer.MAX_VALUE) {
			return (-1);
		}else {
			return (dis[c]);
		}
		
	}
	static void del (int n , int c) {
		if (adj[n]!= null) {
			if (adj[n].contains(c)) {
				adj[n].remove(new Integer(c));
			}
		}
		if(adj[c]!= null) {
			if (adj[c].contains(n)) {
				adj[c].remove(new Integer(n));
			}
		}
	}
	static void add(int n, int c) {
		if (adj[n] == null) {
			adj[n] = new ArrayList<>();
			adj[n].add(c);
		}else {
			if (!adj[n].contains(c)) {
				adj[n].add(c);
			}
		}
		if (adj[c] == null) {
			adj[c] = new ArrayList<>();
			adj[c].add(n);
		}else {
			if (!adj[c].contains(n)) {
				adj[c].add(n);
			}
		}
	}

}
