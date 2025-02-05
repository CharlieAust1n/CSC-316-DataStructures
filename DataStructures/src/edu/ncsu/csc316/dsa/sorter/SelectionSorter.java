package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Method which handles calling the super class with the custom comparator.
	 * @param comparator Custom comparator user wishes to use
	 */
	public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
	/**
	 * Calls the super class with the default comparator
	 */
    public SelectionSorter() {
        super(null);
    }
	
    /**
     * Method which handles sorting information into ordered 
     * form depending on which comparator is used.
     * @param data Array of information to be sorted.
     */
	public void sort(E[] data) {
		for(int i = 0; i < data.length; i++) {
			int min = i;
			for (int j = i + 1; j < data.length; j++) {
				if(compare(data[j], data[min]) < 0) {
					min = j;
				}
			} if (i != min) {
				E x = data[i];
				data[i] = data[min];
				data[min] = x;
			}
		}
	}
}
