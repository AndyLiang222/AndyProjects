package example;
import java.io.*;
import java.util.*;

public class SortByName implements Comparator<Score> {
	//sort the scores by name
	@Override
	public int compare(Score o1, Score o2) {
		// TODO Auto-generated method stub
		int c = o1.getName().compareTo(o2.getName());
		if(c == 0)return o1.compareTo(o2);
		return c;
	}
	

}
