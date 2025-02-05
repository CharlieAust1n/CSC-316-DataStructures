package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

    /**
     * Recursive sorting method that splits the initial array down
     * into separate arrays until they are into a length of 1.
     */
	@Override
	public void sort(E[] data) {
		if(data.length < 2) {
			return;
		}
		int mid = data.length / 2;
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, data.length);
		
		sort(left);
		sort(right);
		
		merge(left, right, data);
	}
    
	/**
	 * Merges the sorted halves of the initial array and combines them.
	 * @param s1 The sorted left half of the initial array.
	 * @param s2 The sorted right half of the initial array.
	 * @param s The initial array.
	 */
	private void merge(E[] s1, E[] s2, E[] s) {
		int leftIndex = 0;
		int rightIndex = 0;
		while(leftIndex + rightIndex < s.length) {
			if(rightIndex == s2.length || (leftIndex < s1.length && compare(s1[leftIndex], s2[rightIndex]) < 0)) {
				s[leftIndex + rightIndex] = s1[leftIndex];
				leftIndex++;
			} else {
				s[leftIndex + rightIndex] = s2[rightIndex];
				rightIndex++;
			}
		}
	}
}