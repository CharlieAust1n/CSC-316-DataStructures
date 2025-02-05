package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.RedBlackTreeMap;

/**
 * The TreeSet is implemented as a [REPLACE THIS WITH THE DATA STRUCTURE TYPE YOU CHOSE
 * TO USE IN YOUR CONSTRUCTOR]
 * data structure to support efficient set abstract data type behaviors.
 * 
 * Using a [DATA STRUCTURE TYPE YOU CHOSE] tree ensures worst-case runtime of
 * O(logn) for {@link Set#add}, {@link Set#remove}, and {@link Set#contains};
 * O(nlogn) worst-case runtime for {@link Set#addAll}, {@link Set#removeAll},
 * and {@link Set#retainAll}; and O(1) worst-case runtime for {@link Set#size}
 * and {@link Set#isEmpty}.
 * 
 * The TreeSet class is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Charlie Austin (cjausti2) 
 *
 * @param <E> the type of elements stored in the set
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {
    // Since we will delegate to an existing balanced search tree, the entries will
    // be ordered.
    // As a result, we must also restrict our tree set to use Comparable elements.
    
	/** Generic map type of keys and values. */
    private Map<E, E> tree;

    /**
     * Constructs a new TreeSet
     */
    public TreeSet() {
        tree = new RedBlackTreeMap<E, E>();
    }

    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public void add(E value) {
    	if(!contains(value)) {
    		tree.put(value, value);
    	}
    }

    @Override
    public boolean contains(E value) {
        return tree.get(value) != null;
    }

    @Override
    public E remove(E value) {
    	if(contains(value)) {
    		return tree.remove(value);
    	}
        return null;
    }

    @Override
    public int size() {
        return tree.size();
    }
}
