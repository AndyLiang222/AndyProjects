package contest;
import java.io.*;
import java.util.*;
public class CCC02S3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] di = {{0,1},{1,0},{0,-1}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int r = readInt();
		int c = readInt();
		boolean [][] a = new boolean[r][c];
		for(int i = 0;i<r;i++) {
			String s = readLine();
			for(int j = 0;j<c;j++) {
				char ch = s.charAt(j);
				if(ch == '.')a[i][j] = true;
			}
		}
		int m = readInt();
		int mx =0;
		int my = 0;
		int d = 0;
		boolean[][] v = new boolean[r][c];
		for(int i = 0;i<m;i++) {
			char ch = readCharacter();
			if(ch == 'R') {
				d++;
				d = (d%4);
			}else if(ch == 'L') {
				d--;
				if(d < 0)d+= 4;
			}else {
				mx += di[d][0];
				my += di[d][1];
				v[my][mx] = true;
			}
		}
		boolean[][] vis = new boolean[r][c];
		for(int i = 0;i<r;i++) {
			for(int j = 0;j<c;j++) {
				if(!a[i][j])continue;
				for(int k = 0;k<4;k++) {
					int nr = i;
					int nc = j;
					boolean vPath = true;
					for(int y = 0;y<r;y++) {
						for(int x = 0;x<c;x++) {
							if(v[y][x]) {
								if(k == 0) {
									nr = i + y;
									nc = j + x;
								}
								if(k == 1) {
									nr= i -x;
									nc= j + y;
								}
								if(k == 2) {
									nc = j - x;
									nr= i - y;
								}
								if(k == 3) {
									nc= j - y;
									nr= i + x;
								}
								if(Math.min(nr, nc) >= 0 && nr < r&& nc<c && a[nr][nc]) {
									continue;
								}else {
									vPath = false;
								}
							}
						}
					}
					if(!vPath)continue;
					nr = i;
					nc = j;
					if(k == 0) {
						nr+= my;
						nc += mx;
					}
					if(k == 1) {
						nr-= mx;
						nc+= my;
					}
					if(k == 2) {
						nc-= mx;
						nr-= my;
					}
					if(k == 3) {
						nc-=my;
						nr+= mx;
					}
					if(Math.min(nr, nc) >= 0 && nr < r&& nc<c) {
						vis[nr][nc] = true;
					}
				}
				
				
			}
		}
		for(int i = 0;i<r;i++) {
			for(int j = c-1;j>= 0;j--) {
				if(vis[i][j] == true && a[i][j] == true)System.out.print("*");
				else if(a[i][j] == true) {
					System.out.print(".");
				}else {
					System.out.print("X");
				}
			}
			System.out.println();
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
