package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SinglyLinkedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 */
public class SinglyLinkedListTest {

    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.addLast("two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.last());
        assertFalse(list.isEmpty());
        
        list.addLast("two");
        assertEquals(2, list.size());
        assertEquals("two", list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertEquals("one", list.get(1));
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals("one", list.first());
        assertFalse(list.isEmpty());
        
        list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals("two", list.first());
        assertEquals("one", list.last());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An UnsupportedOperationException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
 
        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());

        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));
        
        list.add(4, "five");
        assertEquals(5, list.size());
        assertEquals("five", list.get(4));
        
        // Removing element from middle of list.
        assertEquals("three", list.remove(2));
        assertEquals(4, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("four", list.get(2));
        assertEquals("five", list.get(3));
        
        // Removing element from end of list.
        assertEquals("five", list.remove(3));
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("four", list.get(2));
        
        // Removing element from front of list.
        assertEquals("one", list.remove(0));
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertEquals("four", list.get(1));
        
        // Removing last two elements.
        assertEquals("two", list.remove(0));
        assertEquals(1, list.size());
        assertEquals("four", list.get(0));
        assertEquals("four", list.remove(0));
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Trying to remove element from empty list.
        Exception exception = assertThrows(IndexOutOfBoundsException.class,
        		() -> list.remove(0));
        assertEquals("Index is invalid: 0 (size=0)", exception.getMessage());
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());

        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));
        
        list.add(4, "five");
        assertEquals(5, list.size());
        assertEquals("five", list.get(4));
        
        assertEquals("one", list.removeFirst());
        assertEquals(4, list.size());
        assertEquals("two", list.get(0));
        assertEquals("three", list.get(1));
        assertEquals("four", list.get(2));
        assertEquals("five", list.get(3));
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());

        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));
        
        list.add(4, "five");
        assertEquals(5, list.size());
        assertEquals("five", list.get(4));
        
        assertEquals("five", list.removeLast());
        assertEquals(4, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("three", list.get(2));
        assertEquals("four", list.get(3));
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        // Trying to change element in empty list.
        Exception emptyList = assertThrows(IndexOutOfBoundsException.class,
        		() -> list.set(0, "DNE"));
        assertEquals("Index is invalid: 0 (size=0)", emptyList.getMessage());
        
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Changing element at index 0.
        assertEquals("one", list.set(0, "six"));
        assertEquals(1, list.size());
    }
}

