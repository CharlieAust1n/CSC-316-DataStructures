package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Class that tests the methods of the Java Class SkipListMap.
 * 
 * @author Charlie Austin (cjausti2)
 */
public class SkipListMapTest {
	/** Map of string values and integer keys */
	private Map<Integer, String> map;
	/** Map of integer values and student keys */
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SkipListMap<Integer, String>();
        studentMap = new SkipListMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SkipListMap[3]", map.toString());
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string2", map.remove(2));
        assertEquals(4, map.size());
        assertNull(map.get(2));
        assertNull(map.remove(2));
        assertEquals("SkipListMap[1, 3, 4, 5]", map.toString());
        
        assertEquals("string5", map.remove(5));
        assertNull(map.get(5));
        assertNull(map.remove(5));
        assertEquals("SkipListMap[1, 3, 4]", map.toString());
        assertEquals(3, map.size());
        
        assertEquals("string1", map.remove(1));
        assertNull(map.get(1));
        assertNull(map.remove(1));
        assertEquals("SkipListMap[3, 4]", map.toString());
        assertEquals(2, map.size());
        
        assertEquals("string3", map.remove(3));
        assertNull(map.get(3));
        assertNull(map.remove(3));
        assertEquals("SkipListMap[4]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals("string4", map.remove(4));
        assertNull(map.get(4));
        assertNull(map.remove(4));
        assertEquals("SkipListMap[]", map.toString());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        assertNull(studentMap.put(s1, 1));
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s4, 4));
        assertNull(studentMap.put(s2, 2));
        assertNull(studentMap.put(s5, 5));
        
        assertEquals(1, (int)(studentMap.get(s1)));
        assertEquals(2, (int)(studentMap.get(s2)));
        assertEquals(3, (int)(studentMap.get(s3)));
        assertEquals(4, (int)(studentMap.get(s4)));
        assertEquals(5, (int)(studentMap.get(s5)));
        // Changing value of key S1
        assertEquals(1, (int)(studentMap.put(s1, 5)));
        
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
        assertEquals(2, entryKey);
        assertTrue(it.hasNext());
        entryKey = it.next();
        assertEquals(3, entryKey);
        assertTrue(it.hasNext());
        entryKey = it.next();
        assertEquals(4, entryKey);
        assertTrue(it.hasNext());
        entryKey = it.next();
        assertEquals(5, entryKey);
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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(2, (int)(entry.getKey()));
        assertEquals("string2", (String)(entry.getValue()));
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(3, (int)(entry.getKey()));
        assertEquals("string3", (String)(entry.getValue()));
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(4, (int)(entry.getKey()));
        assertEquals("string4", (String)(entry.getValue()));
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(5, (int)(entry.getKey()));
        assertEquals("string5", (String)(entry.getValue()));
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
        assertEquals("string2", entryValue);
        assertTrue(it.hasNext());
        entryValue = it.next();
        assertEquals("string3", entryValue);
        assertTrue(it.hasNext());
        entryValue = it.next();
        assertEquals("string4", entryValue);
        assertTrue(it.hasNext());
        entryValue = it.next();
        assertEquals("string5", entryValue);
        assertFalse(it.hasNext());
        
        Exception removeException = assertThrows(UnsupportedOperationException.class,
        		() -> it.remove());
        assertEquals("The remove operation is not supported yet.", removeException.getMessage());
    }
}
