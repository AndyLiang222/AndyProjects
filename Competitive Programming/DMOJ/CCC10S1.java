package contest;
import java.io.*;
public class CCC10S1 {
	static class computer {
		int ram;
		int speed;
		int space;
		String name;

		public computer (String name,int R, int S, int D) {
			this.name= name;
			this.ram = R;
			this.speed = S;
			this.space = D;
			
		}
		public int getRam() {
			return this.ram;
		}
		public int getSpeed() {
			return this.speed;
		}
		public int getSpace() {
			return this.space;
		}
		public int getVal () {
			return ram*2 + speed*3 + space;
		}
		public String getName () {
			return name;
		}
	}
	public static void sort (computer [] arr) {
		int len = arr.length;
		for (int s = 0; s<len-1;s++) {
			int min = s;
			for (int j = s+1;j<len;j++) {
				if (arr[j].getVal() < arr[min].getVal()) {
					min = j;
				}
				else if (arr[j].getVal() == arr[min].getVal()) {
					if (arr[min].getName().compareTo(arr[j].getName()) < 0) {
						//System.out.println("lol");
						min = j;
					}
				}
			}
			computer temp = arr[min];
			arr[min] = arr[s];
			arr[s] = temp;
			//System.out.println(arr[0].getName() + " " + arr[1].getName());
		}
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int com = Integer.parseInt(br.readLine());
		computer [] comput = new computer [com];
		
		for (int i = 0; i<com; i++) {
			String []tem = br.readLine().split(" ");
			comput[i] = new computer(tem[0], Integer.parseInt(tem[1]), Integer.parseInt(tem[2]), Integer.parseInt(tem[3]));
		}
		if (com == 0) {
			System.out.println();
		}
		else if (comput.length == 1) {
			System.out.println(comput[0].getName());
		}else {
			sort(comput);
			//System.out.println(comput[0].getName() + " " + comput[1].getName());
			System.out.println(comput[com-1].getName());
			System.out.println(comput[com-2].getName());
		}
		
		
		
		
	}

}
