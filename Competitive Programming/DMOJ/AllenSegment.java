package contest;
import java.io.*;
import java.util.*;
public class AllenSegment {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int LOG = 17;
	static int []val, left, right, ans,dep,anc[];
	static ArrayList<Integer> adj[];
	public static void main(String[] args) throws IOException {
		int n = readInt();
		left = new int [n+1];
		right = new int [n+1];
		val = new int [n+1];
		ans = new int [n+1];
		dep = new int [n+1];
		anc = new int[LOG][n+1];
		for (int i = 0; i< LOG;i++)Arrays.fill(anc[i], -1);
		ArrayList<node> seg =new ArrayList();
		adj = new ArrayList[n+1];
		adj[0] = new ArrayList();
		for (int i = 1; i<=n;i++) {
			left[i] = readInt();
			right[i] = readInt();
			val[i] = readInt();
			seg.add(new node(i, left[i], 1));
			seg.add(new node(i, right[i],0));
			adj[i] = new ArrayList();
		}
		
		Collections.sort(seg);
		Stack<Integer> st =new Stack();
		st.add(0);
		for (node e: seg) {
			if(e.f == 1) {
				//System.out.println();
				adj[st.peek()].add(e.id);st.add(e.id);
			}else
			st.pop();
		}
		val[0] = (int)1e9;
		dfs(0,-1);
		int q = readInt();
		for (int i = 0; i< q;i++) {
			int l = readInt();
			int r = readInt();
			int a = lca(l,r);
			System.out.println((a == 0)?-1: ans[a]);
		}
	}
	public static void dfs(int u, int pa) {
		anc[0][u] = pa;
		if(u > 0)
			if (val[ans[pa]] < val[u]) {
				ans[u] = ans[pa];
			}else {
				ans[u] = u;
			}
		//System.out.println(u);
		for (int i = 1; i < LOG;i++) {
			int idx =anc[i-1][u];
			if (idx == -1)break;
			anc[i][u] = anc[i-1][idx];
		}
		for (int v : adj[u]) {
			if (v == pa)continue;
			dep[v] = dep[u]+1;
			dfs(v,u);
		}
		
	}
	public static int lca(int u, int v) {
		if (dep[u] < dep[v]) {
			int t = u;
			u = v;
			v = t;
		}
		for (int i = LOG-1; i >=0;i--) {
			if (anc[i][u] != -1 && dep[anc[i][u]] >= dep[v])u = anc[i][u];
		}
		if (u == v)return anc[0][v];
		for (int i = LOG-1;i >= 0;i--) {
			if (anc[i][u] != -1 && anc[i][v] != -1 && anc[i][u] != anc[i][v]) {
				u = anc[i][u]; v = anc[i][v];
			}
		}
		return anc[0][u];
		
	}
	static class node implements Comparable<node>{
		int id, p,f;
		node(int i, int p0, int f0){
			id = i;
			p = p0;
			f = f0;
		}
		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return Integer.compare(p, o.p);
		}
		
		
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


