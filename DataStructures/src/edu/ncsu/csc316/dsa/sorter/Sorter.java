package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 * @param <E> Type of element that will populate our array.
 */
public interface Sorter<E> {
	/**
	 * Method which handles sorting data into ordered form
	 * based of the comparator which the user wishes to use.
	 * @param data Array of information which is needed to be sorted.
	 */
	public void sort(E[] data);
}
