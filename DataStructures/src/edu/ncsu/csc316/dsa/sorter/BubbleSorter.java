/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Unique sorting program which works by 
 * examining data in an array from left to right, switching their 
 * positions if they are not in order.
 * 
 * @author Charlie Austin (cjausti2)
 * @param <E> the generic type of data to sort.
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Method which handles the setting of a specific kind of comparator.
	 * @param comparator Comparator which will be used to sort student's by.
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Method which initializes the comparator as the default comparator.
	 * This comparator sorts students by their names.
	 */
	public BubbleSorter() {
		super(null);
	}
	
	/**
	 * Method which handles sorting student objects based of the
	 * specified comparator.
	 * @param data Array of student objects which needs to be sorted.
	 */
	public void sort(E[] data) {
		boolean r = true;
		while(r) {
			r = false;
			for(int i = 1; i < data.length; i++) {
				if(compare(data[i], data[i - 1]) < 0) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
	}
	
}
