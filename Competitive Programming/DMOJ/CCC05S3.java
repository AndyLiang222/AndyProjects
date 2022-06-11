package contest;
import java.io.*;
public class CCC05S3 {
	static int n, i, distance, x, y;
    static int[] friend;
    static boolean[] visited;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		friend = new int [10000];
		visited = new boolean [10000];
		int n = Integer.parseInt(br.readLine());
		for (int i = 0 ; i < 10000 ; i++)
		    friend [i] = 0;
		for (int i = 0 ; i < n ; i++) {
			String[] input = br.readLine().split(" ");
		    friend [Integer.parseInt(input[0])] = Integer.parseInt(input[1]);
		}
		// get the student pairs and determine distance between them
		String [] input1 = br.readLine().split(" ");
//		System.out.println(input1[0]);
		x = Integer.parseInt(input1[0]);
		y = Integer.parseInt(input1[1]);
		while (!(x == 0 && y == 0))
		{
		    for (int i = 0 ; i < 10000 ; i++)
			visited [i] = false;
		    distance = -1;
		    while (!visited [x] && x != y)
		    {
			visited [x] = true;
			distance++;
			x = friend [x];
		    }
		    if (x == y)
			System.out.println ("Yes " + distance);
		    else
			System.out.println ("No");
		    String [] input2 = br.readLine().split(" ");
			x = Integer.parseInt(input2[0]);
			y = Integer.parseInt(input2[1]);
		}
	}

}
