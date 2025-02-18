package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }

	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		ensureCapacity(size() + 1);
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = element;
		size++;
	}
	
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimum capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }

	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E removedElement = data[index];
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		} 
		data[size - 1] = null;
		size--;
		return removedElement;
	}

	@Override
	public E set(int index, E element) {
		checkIndex(index);
		E original = data[index];
		data[index] = element;
		return original;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
    
	private class ElementIterator implements Iterator<E> {
		
		/** Position of the cursor. */
	    private int position;
	    /** A check to see if it is ok to remove an element from the list. */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        position = 0;
	        removeOK = false;
	    }

	    @Override
	    public boolean hasNext() {
	    	return position < size;
	    }

	    @Override
	    public E next() {
	    	if (!hasNext()) {
	    		throw new NoSuchElementException();
	    	}
	    	removeOK = true;
	    	return data[position++];
	    	
	    }
	        
	    @Override
	    public void remove() {
	    	if (!removeOK) {
	    		throw new IllegalStateException();
	    	}
	    	ArrayBasedList.this.remove(position - 1);
	    	position--;
	    	removeOK = false;
	    }
	}
    
}
