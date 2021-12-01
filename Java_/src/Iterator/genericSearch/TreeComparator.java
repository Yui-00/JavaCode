package Iterator.genericSearch;

import java.util.Comparator ;

public class TreeComparator implements Comparator<Integer> {
	public int compare(Integer i1, Integer i2) {
		return i1 - i2 ;
	}
};
