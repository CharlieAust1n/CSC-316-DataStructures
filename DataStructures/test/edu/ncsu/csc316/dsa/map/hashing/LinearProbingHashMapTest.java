package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 */
public class LinearProbingHashMapTest {

    /** 'Testing' Map used (no randomization) to check placement of entries in the hash table */
    private Map<Integer, String> testMap;
    
    /** 'Production' Map (with randomization) to check correctness of ADT behaviors */
    private Map<Integer, String> prodMap;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        testMap = new LinearProbingHashMap<Integer, String>(7, true);
        prodMap = new LinearProbingHashMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, testMap.size());
        assertTrue(testMap.isEmpty());
     
        // Inserting entries into the testing map.
        assertNull(testMap.put(3, "string3"));
        assertNull(testMap.put(4, "string4"));
        assertNull(testMap.put(17, "string17")); // Collision at index 4.
        assertNull(testMap.put(11, "string11")); // Collision at index 5.
        
        
        // Verifying the positions after collisions
        Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(17, (int)it.next().getKey());
        
        
        // Checking to make sure that entries are correctly inserted into map.
        assertEquals("string3", testMap.get(3));
        assertEquals("string17", testMap.get(17));
        assertEquals("string4", testMap.get(4));
        assertEquals("string11", testMap.get(11));
        
        // Testing prodMap.
        assertNull(prodMap.put(3, "prodString3"));
        assertEquals("prodString3", prodMap.put(3, "string3"));
        assertEquals(1, prodMap.size());
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(testMap.isEmpty());
        
        // Inserting entries into the testing map
        assertNull(testMap.put(3, "string3"));
        assertNull(testMap.put(10, "string10"));
        
        // Verifying that entries have correct values.
        assertEquals("string3", testMap.get(3));
        assertEquals("string10", testMap.get(10));

        // Attempting to get values for keys that do not exist within the map.
        assertNull(testMap.get(5)); 
        assertNull(testMap.get(7)); 

        // Inserting entries into the randomized map.
        assertNull(prodMap.put(5, "prodString5"));
        assertNull(prodMap.put(7, "prodString7"));

        // Verifying that the keys align to the values in the randomized map.
        assertEquals("prodString5", prodMap.get(5));
        assertEquals("prodString7", prodMap.get(7));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(testMap.isEmpty());

        // Inserting entries into the testing map, collisions included.
        assertNull(testMap.put(3, "string3"));
        assertNull(testMap.put(17, "string17")); // Collision at index 4.
        assertNull(testMap.put(4, "string4"));
        assertNull(testMap.put(11, "string11")); // Collision at index 5.
        assertEquals(4, testMap.size());
        
        // Trying to remove entries from map that do not exist.
        assertNull(testMap.remove(15));
        assertNull(testMap.remove(1));
        assertEquals(4, testMap.size());
        
        // Removing entries with collisions.
        assertEquals("string3", testMap.remove(3));
        assertNull(testMap.get(3));
        assertEquals("string11", testMap.remove(11));
        assertNull(testMap.get(11));
        assertEquals(2, testMap.size());
        
        // Removing final entries.
        assertEquals("string4", testMap.remove(4));
        assertNull(testMap.get(4));
        assertEquals("string17", testMap.remove(17));
        assertNull(testMap.get(17));
        assertEquals(0, testMap.size());
        assertTrue(testMap.isEmpty());
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	// Inserting entries into the testing map, collisions included.
        assertNull(testMap.put(3, "string3"));
        assertNull(testMap.put(17, "string17")); // Collision at index 4.
        assertNull(testMap.put(4, "string4"));
        assertNull(testMap.put(11, "string11")); // Collision at index 5.
        assertEquals(4, testMap.size());
        
        Iterator<Integer> it = testMap.iterator();
        assertTrue(it.hasNext());
        int key = it.next();
        assertEquals(3, key);
        assertTrue(it.hasNext());
        key = it.next();
        assertEquals(11, key);
        assertTrue(it.hasNext());
        key = it.next();
        assertEquals(17, key);
        assertTrue(it.hasNext());
        key = it.next();
        assertEquals(4, key);
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	// Inserting entries into the testing map, collisions included.
        assertNull(testMap.put(3, "string3"));
        assertNull(testMap.put(17, "string17")); // Collision at index 4.
        assertNull(testMap.put(4, "string4"));
        assertNull(testMap.put(11, "string11")); // Collision at index 5.
        assertEquals(4, testMap.size());
        
        Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();        
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals("string3", (String)entry.getValue());
        assertEquals(3, (int)entry.getKey());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("string11", (String)entry.getValue());
        assertEquals(11, (int)entry.getKey());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("string17", (String)entry.getValue());
        assertEquals(17, (int)entry.getKey());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("string4", (String)entry.getValue());
        assertEquals(4, (int)entry.getKey());
        assertFalse(it.hasNext());

    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	// Inserting entries into the testing map, collisions included.
        assertNull(testMap.put(3, "string3"));
        assertNull(testMap.put(17, "string17")); // Collision at index 4.
        assertNull(testMap.put(4, "string4"));
        assertNull(testMap.put(11, "string11")); // Collision at index 5.
        assertEquals(4, testMap.size());
        
        Iterator<String> it = testMap.values().iterator();
        assertTrue(it.hasNext());
        String value = it.next();
        assertEquals("string3", value);
        assertTrue(it.hasNext());
        value = it.next();
        assertEquals("string11", value);
        assertTrue(it.hasNext());
        value = it.next();
        assertEquals("string17", value);
        assertTrue(it.hasNext());
        value = it.next();
        assertEquals("string4", value);
        assertFalse(it.hasNext());
    }
}