package contest;
import java.io.*;
import java.util.*;
public class CCC07S3 {
	static int node1; static int node2;static HashMap<Integer,Integer> friends;int[] vis;
	static int dfs (int cur) {
		int friend = friends.get(cur);
		if(vis[friend] == true) {
			
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int roads = Integer.parseInt(br.readLine());
		HashMap<Integer,Integer> friends = new HashMap<>();
		
		for (int i = 0; i< roads;i++) {
			String []input = br.readLine().split(" ");
			int node = Integer.parseInt(input[0]);
			int connect = Integer.parseInt(input[1]);
			friends.put(node, connect);
			
		}
		int input = Integer.parseInt(br.readLine());
		int input2 = Integer.parseInt(br.readLine());
		while (input != 0) {
			
		}
	}

}
