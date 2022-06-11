package contest;
import java.io.*;
import java.util.*;

public class BobVideoGame {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		int vil = readInt(), paths = readInt(), q = readInt();
		int[][] map = new int [vil+1][vil+1];
		for (int i = 1; i<vil+1; i++) {
			Arrays.fill(map[i],Integer.MAX_VALUE );
		}
		for (int i = 0; i<paths;i++) {
			int u = readInt(), v = readInt(), w = readInt();
			map[u][v] = w;
		}
		for (int x = 1; x <= vil;x++) {
			for (int y = 1; y<= vil;y++) {
				for (int mid = 1; mid<= vil;mid++) {
					map[y][mid] = Math.min(map[y][mid], Math.max(map[y][x], map[x][mid]));
				}
			}
		}
		for (int i = 0; i< q; i++) {
			int u = readInt(), v = readInt();
			if(map[u][v] != Integer.MAX_VALUE) {
				System.out.println(map[u][v]);
			}else {
				System.out.println(-1);
			}
		}
	}
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter () throws IOException {
		return next().charAt(0);
	}
	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
