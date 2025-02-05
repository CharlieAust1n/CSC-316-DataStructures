package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 */
public class HeapAdaptablePriorityQueueTest {
	/** HeapAdaptablePriorityQueue used for testing. */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(-5, (int)e8.getKey());

        // Replace key of e0 with a larger key
        heap.replaceKey(e0, 100);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(100, (int)e0.getKey());
        
        // Replace key of e1 with a smaller key
        heap.replaceKey(e1, -100);
        assertEquals(-100, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(-100, (int)e1.getKey());
        
        // Replace key of e2 with a larger key
        heap.replaceKey(e2, 50);
        assertEquals(-100, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(50, (int)e2.getKey());
        
        // Replace key of e3 with a smaller key
        heap.replaceKey(e3, -10);
        assertEquals(-100, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(-10, (int)e3.getKey());
        
        // Replace key of e4 with a larger key
        heap.replaceKey(e4, 20);
        assertEquals(-100, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(20, (int)e4.getKey());
        
        // Replace key of e5 with a smaller key
        heap.replaceKey(e5, -50);
        assertEquals(-100, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(-50, (int)e5.getKey());
        
        // Replace key of e6 with a larger key
        heap.replaceKey(e6, 200);
        assertEquals(-100, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(200, (int)e6.getKey());
        
        // Replace key of e7 with a smaller key
        heap.replaceKey(e7, -200);
        assertEquals(-200, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(-200, (int)e7.getKey());
        
        // Replace key of e8 with a larger key
        heap.replaceKey(e8, 500);
        assertEquals(-200, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(500, (int)e8.getKey());
        
        // Replace key of e0 with a smaller key
        heap.replaceKey(e0, -500);
        assertEquals(-500, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals(-500, (int)e0.getKey());
        
        Exception exception1 = assertThrows(IllegalArgumentException.class, 
        		() -> heap.replaceKey(null, 50));
        assertEquals("Entry is not a valid adaptable priority queue entry.", exception1.getMessage());
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceValue(e8, "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        
        heap.replaceValue(e7, "SEVEN");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("SEVEN", e7.getValue());
        
        heap.replaceValue(e6, "SIX");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("SIX", e6.getValue());
        
        heap.replaceValue(e5, "FIVE");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("FIVE", e5.getValue());
        
        heap.replaceValue(e4, "FOUR");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("FOUR", e4.getValue());
        
        heap.replaceValue(e3, "THREE");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("THREE", e3.getValue());
        
        heap.replaceValue(e2, "TWO");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("TWO", e2.getValue());
        
        heap.replaceValue(e1, "ONE");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("ONE", e1.getValue());
        
        heap.replaceValue(e0, "ZERO");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("ZERO", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("ZERO", e0.getValue());
        
        Exception exception1 = assertThrows(IllegalArgumentException.class,
        		() -> heap.replaceValue(null, "DNE"));
        assertEquals("Entry is not a valid adaptable priority queue entry.", exception1.getMessage());
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());
        
        heap.remove(e8);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(7, heap.size());
        
        heap.remove(e7);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(6, heap.size());
        
        heap.remove(e1);
        assertEquals(2, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(5, heap.size());
        
        heap.remove(e2);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(4, heap.size());
        
        heap.remove(e4);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.remove(e5);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(2, heap.size());
        
        heap.remove(e6);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(1, heap.size());
        
        // Forcibly modify index of e3 to make it invalid.
        ((HeapAdaptablePriorityQueue.AdaptablePQEntry<Integer, String>) e3).setIndex(heap.size());
        
        // Attempt to remove e3 from heap. 
        Exception exception1 = assertThrows(IllegalArgumentException.class,
        		() -> heap.remove(e3));
        assertEquals("Invalid Adaptable PQ Entry.", exception1.getMessage());
        assertEquals(1, heap.size());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        Entry<Student, String> e1 = sHeap.insert(s3, "student3");
        assertFalse(sHeap.isEmpty());
        assertEquals(1, sHeap.size());
        assertEquals(s3, sHeap.min().getKey());
        assertEquals("student3", sHeap.min().getValue());
        
        Entry<Student, String> e2 = sHeap.insert(s1, "student1");
        assertFalse(sHeap.isEmpty());
        assertEquals(2, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        assertEquals("student1", sHeap.min().getValue());
        
        sHeap.remove(e2);
        assertEquals(s3, sHeap.min().getKey());
        assertEquals("student3", sHeap.min().getValue());
        assertEquals(1, sHeap.size());
        assertFalse(sHeap.isEmpty());
        
        sHeap.remove(e1);
        assertEquals(0, sHeap.size());
        assertTrue(sHeap.isEmpty());
        
        Entry<Student, String> e3 = sHeap.insert(s4, "student4");
        assertFalse(sHeap.isEmpty());
        assertEquals(1, sHeap.size());
        assertEquals(s4, sHeap.min().getKey());
        assertEquals("student4", sHeap.min().getValue()); 
        
        // Forcibly modify index of e3 to make it invalid.
        ((HeapAdaptablePriorityQueue.AdaptablePQEntry<Student, String>) e3).setIndex(100);
        
        // Attempt to remove e3 from heap. 
        Exception exception1 = assertThrows(IllegalArgumentException.class,
        		() -> sHeap.remove(e3));
        assertEquals("Invalid Adaptable PQ Entry.", exception1.getMessage());
        assertEquals(1, sHeap.size());
        
        Entry<Student, String> e4 = sHeap.insert(s5, "student5");
        Entry<Student, String> e5 = sHeap.insert(s2, "student2");
        assertEquals(3, sHeap.size());
        assertEquals(s2, sHeap.min().getKey());
        assertEquals("student2", sHeap.min().getValue());
        
        sHeap.replaceValue(e4, "STUDENT5");
        sHeap.replaceKey(e5, s1);
        assertEquals(3, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        assertEquals("student2", sHeap.min().getValue());
        assertEquals("STUDENT5", e4.getValue());
        assertEquals(s1, e5.getKey());
    }
}
