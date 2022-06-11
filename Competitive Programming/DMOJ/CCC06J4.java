package contest;
import java.util.*;
import java.io.*;
public class CCC06J4 {
	static int[] vis; static List<Integer>adj[]; static boolean cycle = false;
	public static void dfs(int node) {
			
			vis[node] = 1;
			for (int b : adj[node]) {
				if (vis[b] == 1) {
					cycle = true;
					return;
				}
				else if (vis[b] == 0) {
					dfs(b);
				}
				
			}
			vis[node] = 2;
		}
	static void top (int v , boolean [] visit,Stack <Integer> s ) {
		visit[v] = true;
		for (int next : adj[v]) {
			if (visit[next] == false) {
				top(next, visit, s);
			}
			
		}
		s.push(v);
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br  = new BufferedReader (new InputStreamReader(System.in));
		adj = new ArrayList[8];
		vis = new int [8];
		for (int i = 0; i<8; i++) {
			adj[i] = new ArrayList();
		}
		adj[1].add(7);
		adj[1].add(4);
		adj[2].add(1);
		adj[3].add(4);
		adj[3].add(5);
		int input = Integer.parseInt(br.readLine());
		int input2 = Integer.parseInt(br.readLine());
		while (input2 != 0) {
			adj[input].add(input2);
			input= Integer.parseInt(br.readLine());
			input2 = Integer.parseInt(br.readLine());
			
		}
		for (int h = 0; h<8;h++) {
			if (vis[h] == 0) {
				dfs(h);
			}
		}
		if (cycle == false) {
			Stack<Integer> s = new Stack<>();
			boolean visited[] = new boolean [8];
			Arrays.fill(visited, false);
			for (int n = 7; n>0; n--) {
				if (visited[n] == false) {
					top(n, visited, s);
				}
			}
			while (!s.isEmpty()) {
				System.out.print(s.pop() + " ");
			}
		}else {
			System.out.println("Cannot complete these tasks. Going to bed.");
		}
		
		
	}

}
