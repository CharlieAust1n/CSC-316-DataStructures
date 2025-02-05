/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Superclass of all the sorting algorithms. Handles all the 
 * repetitive algorithms that the sorters need to work.
 * 
 * @author Charlie Austin (cjausti2)
 * 
 * @param <E> the generic type of data to sort
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	/** Comparator object which can be used as a custom comparator. */
    private Comparator<E> comparator;
    
    /**
     * Method which calls the method that actually sets the comparator.
     * @param comparator Specified custom comparator for sorting information.
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Method that handles the actualization of the comparator.
     * @param comparator The comparator the user wishes to use to sort data.
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
     * Private inner class that handles the comparison when using the comparator.
     * @author Charlie Austin (cjausti2)
     */
    private class NaturalOrder implements Comparator<E> {
    	/**
    	 * Compares two elements to each other. Returns a 
    	 * negative number (if the first element is less than the second),
    	 * a zero if the two elements are equal, 
    	 * and a positive number (if the first elements is greater than the second).
    	 * 
    	 * @param first First element to be compared
    	 * @param second Second element to be compared
    	 * @return The comparison of the two elements.
    	 */
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
  	/**
	 * Compares two elements to each other. Returns a 
	 * negative number (if the first element is less than the second),
	 * a zero if the two elements are equal, 
	 * and a positive number (if the first elements is greater than the second).
	 * 
	 * @param first First element to be compared
	 * @param second Second element to be compared
	 * @return The comparison of the two elements.
	 */
    public int compare(E first, E second) {
        return comparator.compare(first,  second);
    }
}
