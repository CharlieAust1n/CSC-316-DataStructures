package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 */
public class PositionalLinkedListTest {

    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        assertEquals(first, list.last());
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(second, list.last());
        assertEquals(first, list.first());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(first, list.first());
        
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        Position<String> tail = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(tail, list.last());
        assertEquals(first, list.first());
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBeforeAndAddBefore() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        Position<String> second = list.addBefore(first, "two");
        assertEquals(2, list.size());
        assertEquals(second, list.before(first));
        
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfterAndAddAfter() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        Position<String> second = list.addAfter(first, "two");
        assertEquals(2, list.size());
        assertEquals(second, list.after(first));
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        Position<String> second = list.addAfter(first, "two");
        assertEquals(2, list.size());
        assertEquals(second, list.after(first));
        Position<String> third = list.addBefore(first, "three");
        assertEquals(3, list.size());
        assertEquals(third, list.before(first));
        
        assertEquals("one", list.set(first, "four"));
        assertEquals("four", list.after(third).getElement());
        assertEquals(3, list.size());
        assertEquals(third, list.first());
        assertEquals("four", list.after(third).getElement());
        assertEquals(second, list.last());
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        Position<String> second = list.addAfter(first, "two");
        assertEquals(2, list.size());
        assertEquals(second, list.after(first));
        Position<String> third = list.addBefore(first, "three");
        assertEquals(3, list.size());
        assertEquals(third, list.before(first));
        
        assertEquals("one", list.remove(first));
        assertEquals(2, list.size());
        assertEquals(third, list.first());
        assertEquals(second, list.after(third));
        
        assertEquals("two", list.remove(second));
        assertEquals(1, list.size());
        assertEquals(third, list.first());
        assertEquals(null, list.after(third));
        
        assertEquals("three", list.remove(third));
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
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
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());
        
        // Now add an element
        Position<String> first = list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(first, list.first());
        
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
        
        it.remove();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
    }
}
