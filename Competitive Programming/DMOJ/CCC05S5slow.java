package contest;
import java.io.*;
import java.util.*;

public class CCC05S5slow {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt();
		Tree t = new Tree();
		long sum = 0;
		for (int i = 0; i< n;i++) {
			
			int v = t.add(readInt())+1;
			sum+=v ;
		}
		
		System.out.printf("%.2f",(sum*100/n)/100.0);
	} 
	static class node{
		node left, right;
		int val, rank;
		node(int v){
			val = v;
			left = null;
			right = null;
			rank = 0;
		}
	}
	static class Tree{
		static node root;
		public Tree(){
			root = null;
		}
		public int add(int n) {
			int rank = 0;
			if (root == null) {
				root = new node(n);
			}else {
				node cur = root;
				
				while (true) {
					if (n < cur.val) {
						rank+= cur.rank+1;
						if (cur.left == null) {
							cur.left = new node(n);
							
							return rank;
						}else {
							cur = cur.left;
						}
					}else {
						cur.rank++;
						if (cur.right == null) {
							cur.right = new node(n);
							
							return rank;
						}else {
							cur = cur.right;
						}
					}
				}
			}
			
			return rank;
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


