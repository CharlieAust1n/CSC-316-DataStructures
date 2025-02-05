package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 * 
 * @param <E> the generic type of data to sort
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Method which handles the setting of a specific kind of comparator.
	 * @param comparator Comparator which will be used to sort student's by.
	 */
	public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
	/**
	 * Method which initializes the comparator as the default comparator.
	 * This comparator sorts students by their names.
	 */
    public InsertionSorter() {
        super(null);
    }
	
    /**
     * Method which handles the sorting of objects based on the 
     * specified comparator.
     * @param data Array of objects that are needed to be sorted.
     */
	@Override
	public void sort(E[] data) {
		for (int i = 1; i < data.length; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j--;
			}
			data[j + 1] = x;
		}
	}
}
