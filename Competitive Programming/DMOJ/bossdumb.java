package contest;
import java.io.*;
import java.util.Stack;
public class bossdumb {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int values = Integer.parseInt(br.readLine());
		Stack<Integer> numbers = new Stack<>();
		int fina = 0;
		int num = 0;
		for (int i = 0; i<values;i++) {
			num = Integer.parseInt(br.readLine());
			if (num == 0) {
				numbers.pop();
			}else {
				numbers.push(num);
			}
	}
		for (int i : numbers) {
			fina= fina + i;
		}
		System.out.println(fina);

}}
