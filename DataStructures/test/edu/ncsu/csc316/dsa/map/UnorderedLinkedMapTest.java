package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 */
public class UnorderedLinkedMapTest {
	/** Map of string values and integer keys */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        assertEquals("string3", map.put(3, "newString"));
        assertEquals(1, map.size());
        assertEquals("newString", map.get(3));
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
       
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
        
        assertNull(map.get(null));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string2", map.remove(2));
        assertNull(map.get(2));
        assertNull(map.remove(2));
        assertEquals("UnorderedLinkedMap[1, 4, 5, 3]", map.toString());
        assertEquals(4, map.size());
        
        assertEquals("string3", map.remove(3));
        assertNull(map.get(3));
        assertNull(map.remove(3));
        assertEquals("UnorderedLinkedMap[1, 4, 5]", map.toString());
        assertEquals(3, map.size());
        
        assertEquals("string1", map.remove(1));
        assertNull(map.get(1));
        assertNull(map.remove(1));
        assertEquals("UnorderedLinkedMap[4, 5]", map.toString());
        assertEquals(2, map.size());
        
        assertEquals("string4", map.remove(4));
        assertNull(map.get(4));
        assertNull(map.remove(4));
        assertEquals("UnorderedLinkedMap[5]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals("string5", map.remove(5));
        assertNull(map.get(5));
        assertNull(map.remove(5));
        assertEquals("UnorderedLinkedMap[]", map.toString());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        int entryKey = it.next();
        assertEquals(1, entryKey);
        assertTrue(it.hasNext());
        entryKey = it.next();
        assertEquals(4, entryKey);
        assertTrue(it.hasNext());
        entryKey = it.next();
        assertEquals(2, entryKey);
        assertTrue(it.hasNext());
        entryKey = it.next();
        assertEquals(5, entryKey);
        assertTrue(it.hasNext());
        entryKey = it.next();
        assertEquals(3, entryKey);
        assertFalse(it.hasNext());
        
        Exception removeException = assertThrows(UnsupportedOperationException.class,
        		() -> it.remove());
        assertEquals("The remove operation is not supported yet.", removeException.getMessage());
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(4, (int)(entry.getKey()));
        assertEquals("string4", (String)(entry.getValue()));
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(2, (int)(entry.getKey()));
        assertEquals("string2", (String)(entry.getValue()));
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(5, (int)(entry.getKey()));
        assertEquals("string5", (String)(entry.getValue()));
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(3, (int)(entry.getKey()));
        assertEquals("string3", (String)(entry.getValue()));
        assertFalse(it.hasNext());
        
        Exception removeException = assertThrows(UnsupportedOperationException.class,
        		() -> it.remove());
        assertEquals("The remove operation is not supported yet.", removeException.getMessage());
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        String entryValue = it.next();
        assertEquals("string1", entryValue);
        assertTrue(it.hasNext());
        entryValue = it.next();
        assertEquals("string4", entryValue);
        assertTrue(it.hasNext());
        entryValue = it.next();
        assertEquals("string2", entryValue);
        assertTrue(it.hasNext());
        entryValue = it.next();
        assertEquals("string5", entryValue);
        assertTrue(it.hasNext());
        entryValue = it.next();
        assertEquals("string3", entryValue);
        assertFalse(it.hasNext());
        
        Exception removeException = assertThrows(UnsupportedOperationException.class,
        		() -> it.remove());
        assertEquals("The remove operation is not supported yet.", removeException.getMessage());
    }
}
