package contest;
import java.io.*;
import java.util.*;

public class CCC10S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int [][] graph;
	static node [][] fence;
	static int[] pt,sz;
	public static void main(String[] args) throws IOException {
		int n =readInt();
		pt = new int[n+1];
		sz = new int[n+1];
		graph = new int [n+1][n+1];
		fence = new node[1001][1001];
		for(int i = 0;i<1001; i++) {
			for(int j = 0;j<1001;j++) {
				fence[i][j] = new node(-1, -1);
			}
		}
		for (int i = 0;i<=n;i++) {
			for(int j = 0;j <=n;j++) {
				graph[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0;i<=n;i++) {
			graph[i][i] = 0;
		}
		ArrayList<pair>path = new ArrayList<>();
		for(int i = 0;i<n;i++) {
			int nE = readInt();
			int [] corner = new int [nE];
			int [] len = new int [nE];
			for(int j = 0;j<nE;j++) {
				corner[j] = readInt();
			}
			for(int j = 0;j<nE;j++) {
				len[j] = readInt();
			}
			for(int j = 0;j<nE;j++) {
				int k = (j+1)%nE;
				int c1 = corner[j];
				int c2 = corner[k];
				if (fence[c1][c2].d >= 0) {
					if(fence[c1][c2].d > len[j]) {
						graph[i][fence[c1][c2].a] = len[j];
						graph[fence[c1][c2].a][i] = len[j];
						
					}else {
						graph[i][fence[c1][c2].a] = fence[c1][c2].d;
						graph[fence[c1][c2].a][i] = fence[c1][c2].d;
					}
					path.add(new pair(i, fence[c1][c2].a, graph[fence[c1][c2].a][i]));
					fence[c1][c2].a = -1;
					fence[c2][c1].a = -1;
				}else {
					fence[c1][c2].a = i;
					fence[c2][c1].a = i;
					fence[c1][c2].d = len[j];
					fence[c2][c1].d = len[j];
				}
			}
		}
		for(int i = 0;i<1001;i++) {
			for(int j = 0;j<1001;j++) {
				if (fence[i][j].a>= 0) {
					graph[fence[i][j].a][n] = fence[i][j].d;
					graph[n][fence[i][j].a] = fence[i][j].d;
					path.add(new pair(fence[i][j].a, n, fence[i][j].d));
				}
			}
		}
		Collections.sort(path);
//		System.out.println(path);
		System.out.println(Math.min(solve(n, path), solve(n-1, path)));
		
		
	}
	public static int solve(int max,ArrayList<pair> path) {
		int val = 0;
		
		for(int i = 0;i<=max;i++) {
			pt[i] = i; sz[i] = 1;
		}
		int edges = 0;
		for(pair p : path) {
			int a = p.u;
			int b = p.v;
			if(Math.max(a, b) >max)continue;
			if(union(a,b)) {
				val+= p.w;
				edges++;
			}
		}
		return (edges == max)?val:Integer.MAX_VALUE;
	}
	public static int find(int x) {
		if(pt[x] == x)return x;
		return pt[x]= find(pt[x]);
	}
	public static boolean union(int a, int b) {
		int x = find(a);
		int y = find(b);
		if(x == y)return false;
		if(sz[y] > sz[x]) {
			int temp = x;
			x = y;
			y = temp;
		}
		pt[y] = x;
		sz[y] += sz[x];
		return true;
	}
	static class node{
		int a, d;
		node(int animal, int len){
			a = animal;
			d = len;
		}
	}static class pair implements Comparable<pair>{
		int u,v,w;
		pair(int u0, int v0, int w0){
			u = u0;v = v0; w = w0;
		}
		public int compareTo(pair p) {return Integer.compare(w,  p.w);}
		public String toString() {return "" + u + "," + v + "," + w;}
	}
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}


