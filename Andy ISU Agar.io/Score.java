package example;

public class Score implements Comparable<Score>{
	private int val;
	private String name;
	static int total;
	public Score(String n, int v) {
		name = n;
		if(name.length() > 8)name = name.substring(0, 8);
		val = v;
		total++;
	}
	public static void dec() {
		total--;
	}
	public int getScore() {
		return val;
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return (name + " " + val);
	}
	@Override
	public int compareTo(Score o) {
		// TODO Auto-generated method stub
		return Integer.compare(o.getScore(),val);
	}
}
