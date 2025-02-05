package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 */
public class BinarySearchTreeMapTest {
	/** Empty binary search tree used for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        assertNull(tree.put(1, "one"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(3, "three"));
        assertEquals(2, tree.size());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(2, "two"));
        assertEquals(3, tree.size());
        assertEquals(2, (int)tree.left(tree.right(tree.root())).getElement().getKey());

        assertNull(tree.put(4, "four"));
        assertEquals(4, tree.size());
        assertEquals(4, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        assertNull(tree.put(-1, "negOne"));
        assertEquals(5, tree.size());
        assertEquals(-1, (int)tree.left(tree.root()).getElement().getKey());
        
        assertNull(tree.put(0, "zero"));
        assertEquals(6, tree.size());
        assertEquals(0, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        
        assertNull(tree.put(-2, "negTwo"));
        assertEquals(7, tree.size());
        assertEquals(-2, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        
        // What tree looks like after inserting all keys & values (this only shows the keys)
        //         1
        //       /   \
        //     -1     3
        //     / \   / \
        //   -2   0 2   4
        
        
        //Over-writing value
        assertEquals("two", tree.put(2, "newValue"));
        assertEquals(7, tree.size());
        assertEquals("newValue", (String)tree.left(tree.right(tree.root())).getElement().getValue());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertEquals("BalanceableBinaryTree[\nnull\n]", tree.toString());
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.get(1));
        
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(2, "two"));	
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(-1, "negOne"));
        assertNull(tree.put(0, "zero"));
        assertNull(tree.put(-2, "negTwo"));
        assertEquals(7, tree.size());
        
        // Searching for nodes that exist & returning values
        assertEquals("negOne", tree.get(-1));
        assertEquals("three", tree.get(3));
        assertEquals("one", tree.get(1));
        assertEquals("negTwo", tree.get(-2));
        assertEquals("negOne", tree.get(-1));
        assertEquals("two", tree.get(2));
        assertEquals("zero", tree.get(0));
        // Searching for node that does not exist
        assertNull(tree.get(5));
        
        Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        // Inserting keys and values
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(2, "two"));	
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(-1, "negOne"));
        assertNull(tree.put(0, "zero"));
        assertNull(tree.put(-2, "negTwo"));
        assertNull(tree.put(6, "six"));
        assertNull(tree.put(4, "four"));
        assertEquals(8, tree.size());
        
        // Current tree
        //         3
        //       /   \
        //      2     5
        //     / \   / \
        //   -1     4   6
        //   / \
        //  -2  0
        
        // Removing node with two non-sentinel node children
        assertEquals("negOne", tree.remove(-1));
        assertEquals(7, tree.size());
        assertEquals(0, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertNull(tree.right(tree.left(tree.left(tree.root()))).getElement());
        assertEquals(-2, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        // Current tree
        //         3
        //       /   \
        //      2     5
        //     / \   / \
        //    0     4   6
        //   / \
        //  -2  
        
        // Removing node with only a left child.
        assertEquals("zero", tree.remove(0));
        assertEquals(6, tree.size());
        assertEquals(-2, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertNull(tree.right(tree.left(tree.left(tree.root()))).getElement());
        assertNull(tree.left(tree.left(tree.left(tree.root()))).getElement());
        // Current tree
        //         3
        //       /   \
        //      2     5
        //     / \   / \
        //   -2     4   6
        
        // Removing root node.
        assertEquals("three", tree.remove(3));
        assertEquals(5, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.right(tree.root())).getElement());
        // Current tree
        //         4
        //       /   \
        //      2     5
        //     / \   / \
        //   -2         6
        
        // Removing node with only a right child.
        assertEquals("five", tree.remove(5));
        assertEquals(4, tree.size());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertNull(tree.right(tree.right(tree.root())).getElement());
        // Current tree
        //         4
        //       /   \
        //      2     6
        //     / \   / \
        //   -2    
        
        // Removing node with no children (children are sentinel nodes)
        assertEquals("negTwo", tree.remove(-2));
        assertEquals(3, tree.size());
        assertNull(tree.left(tree.left(tree.root())).getElement());
        // Current tree
        //         4
        //       /   \
        //      2     6
        //     / \   / \
    }
}
