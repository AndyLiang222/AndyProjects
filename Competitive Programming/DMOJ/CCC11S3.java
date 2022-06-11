package contest;
import java.io.*;
public class CCC11S3 {
	public static String glass(int mag, int x, int y) {
		int nX = 0;
		int nY = 0;
		if (mag >1) {
			nX = (int) (x/Math.pow(5, mag-1));
			nY = (int) (y/Math.pow(5, mag-1));
		}else {
			nX = x;
			nY = y;
		}
		int xR = (int) (x- Math.pow(5, mag-1)*(nX));
		int yR = (int) (y- Math.pow(5, mag-1)*(nY));
		//System.out.println(mag + " " +  nX + " " + nY + " " + xR + " " + yR);
		
				
		if (nX<4 && nX>0&&nY<1) {
			return ("crystal");
		}
		else if (nY == 1 && nX == 2) {
			return("crystal");
		}
		else if (mag == 1) {
			return ("empty");
		}
		else if (nY == 1&& nX == 1&&mag !=1){
			return (glass(mag-1, xR, yR));
		}else if (nY == 1&& nX == 3&&mag !=1){
			return (glass(mag-1, xR, yR));
		}else if (nY == 2&& nX == 2&&mag !=1){
			return (glass(mag-1, xR, yR));
		}else {
			return("empty");
		}
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int tests = Integer.parseInt(br.readLine());
		String []results = new String [tests +1];
		for (int i = 0; i<tests; i++) {
			results[i] = br.readLine();
			
		}
		for (int i = 0; i<tests; i++) {
			String[] result = results[i].split(" ");
			int mag = Integer.parseInt(result[0]);
			int x = Integer.parseInt(result [1]);
			int y = Integer.parseInt(result[2]);
			System.out.println(glass(mag, x,y));
		}
	}

}
