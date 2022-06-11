package contest;
import java.io.*;
import java.util.*;
public class CCC09S4 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int nodes =Integer.parseInt(br.readLine());
		int paths = Integer.parseInt(br.readLine());
		List<Edge> adj[] = new ArrayList[nodes+1];
		for (int n = 0; n<= nodes;n++) {
			adj[n] = new ArrayList();
		}
		for (int i = 0; i<paths ;i++) {
			String [] input = br.readLine().split(" ");
			int node1 = Integer.parseInt(input[0]);
			int node2 = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);
			adj[node1].add(new Edge(node2, cost));adj[node2].add(new Edge(node1,cost));
			
		}
		int stores = Integer.parseInt(br.readLine());
		
		Edge [] st= new Edge[stores];
		
		for (int s = 0; s<stores; s++) {
			String []input2 =br.readLine().split(" ");
			int store = Integer.parseInt(input2[0]);
			int c = Integer.parseInt(input2[1]);
			st[s] = new Edge(store, c); 
		}
		int d = Integer.parseInt(br.readLine());
		
		int dis[] = new int [nodes+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[d] = 0;
		int [] q = new Integer [nodes+1];
		int r = 1;
		int s = 0;
		q[s] = d;
		while (r>s) {
			int cur = q[s];
//			System.out.println(cur);
			for (Edge next: adj[cur]) {
				int v = next.v;
				int cost = next.cost;
//				System.out.println(v+ " " +  cost);
				if (dis[v] > dis[cur]+cost) {
					dis[v] = dis[cur]+cost;
					q[r] = cur;
					r++;
				}
				
			}
			s++;
		}
		int lowest = Integer.MAX_VALUE;
		for (Edge sto : st) {
			if (dis[sto.v]+sto.cost < lowest) {
				lowest = dis[sto.v]+sto.cost;
			}
		}
		System.out.println(lowest);
 	}
	static class Edge{
		int v; int cost;
		Edge(int v0, int c0){v=v0; cost=c0;}
	}

}
