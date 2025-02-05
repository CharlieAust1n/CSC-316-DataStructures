package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }

	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		if (front.getElement() == null) {
			front = new LinkedListNode<E>(element, tail);
		} else if(index == 0) {
			front = new LinkedListNode<E>(element, front);
		} else {
			LinkedListNode<E> current = front;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new LinkedListNode<E>(element, current.next);
		}
		LinkedListNode<E> current = front;
		while(current.next != null) {
			current = current.next;
		}
		tail = current;
		size++;
	}
	
	/**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.getElement();
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
    	LinkedListNode<E> node = new LinkedListNode<E>(element);
    	if(isEmpty()) {
    		front = node;
    	} else {
    		tail.next = node;
    	}
    	tail = node;
    	size++;
    }

	@Override
	public E get(int index) {
		checkIndex(index);
		LinkedListNode<E> current = front;
		if(index == size) {
			return tail.element;
		} else if(index == 0) {
			return front.element;
		} else {
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.element;
		}	
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E elementBeingRemoved = null;
		if (index == 0) {
			elementBeingRemoved = front.element;
			front = front.next;
		} else {
			LinkedListNode<E> current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			elementBeingRemoved = current.next.element;
			current.next = current.next.next;
			if (index == size - 1) {
				tail = current;
			}
		}
		size--;
		return elementBeingRemoved;
	}

	@Override
	public E set(int index, E element) {
		checkIndex(index);
		LinkedListNode<E> current = front;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		E elementToBeChanged = current.getElement();
		current.element = element;
		return elementToBeChanged;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
    
    private static class LinkedListNode<E> {
    	/** The element of the node. */
    	private E element;
    	/** The next node in the list. */
    	private LinkedListNode<E> next;
    	
    	public LinkedListNode(E element) {
    		this(element, null);
    	}
    	
    	public LinkedListNode(E element, LinkedListNode<E> next) {
    		this.element = element;
    		this.next = next;
    	}
    	
    	public E getElement() {
    		return element;
    	}
    	
    	public LinkedListNode<E> getNext(){
    		return next;
    	}
    }
    
    private class ElementIterator implements Iterator<E> {
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        
        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            current = front;
        }

        @Override
        public boolean hasNext() {
            if(isEmpty()) {
            	return false;
            }
            return current != null;
        }

        @Override
        public E next() {
            if(!hasNext()) {
            	throw new NoSuchElementException();
            }
            E rtn = current.getElement();
            current = current.getNext();
            return rtn;
        }
         
        @Override    
        public void remove() {
    	    // DO NOT CHANGE THIS METHOD
            throw new UnsupportedOperationException(
                "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
        }
    }
}
