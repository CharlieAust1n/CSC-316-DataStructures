package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2) 
 *
 */
public class RedBlackTreeMapTest {
	/** Empty binary search tree used for testing. */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty()); 
        
        assertNull(tree.put(-2, "negTwo"));
        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
        assertEquals(-2, (int)tree.root().getElement().getKey());
        assertEquals(0, tree.getProperty(tree.root()));
        // Current Tree
        //       -2 (Black)
        //       / \
        
        assertNull(tree.put(3, "three"));
        assertEquals(2, tree.size());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(0, tree.getProperty(tree.root()));
        assertEquals(1, tree.getProperty(tree.right(tree.root())));
        assertEquals(0, tree.getProperty(tree.right(tree.right(tree.root())))); // Leaf Node is black (sentinel node)
        assertEquals(0, tree.getProperty(tree.left(tree.right(tree.root())))); // Leaf Node is black (sentinel node)
        assertEquals(0, tree.getProperty(tree.left(tree.root()))); // Leaf Node is black (sentinel node)
        // Current Tree
        //           -2 (Black)
        // (Black)  / \     
        //             3 (Red)
        //            / \ (Black)
        
        // Re-coloring Insert (Sibling of parent is black)
        assertNull(tree.put(4, "four"));
        assertEquals(3, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(0, tree.getProperty(tree.root()));
        assertEquals(-2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, tree.getProperty(tree.left(tree.root())));
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(1, tree.getProperty(tree.right(tree.root())));
        assertEquals(0, tree.getProperty(tree.left(tree.left(tree.root())))); // Checking color of leaf node.
        assertEquals(0, tree.getProperty(tree.right(tree.left(tree.root())))); // Checking color of leaf node.
        assertEquals(0, tree.getProperty(tree.right(tree.right(tree.root())))); // Checking color of leaf node.
        assertEquals(0, tree.getProperty(tree.left(tree.right(tree.root())))); // Checking color of leaf node.
        // Current Tree
        //           3 (Black)
        //          / \     
        // (Red)  -2   4 (Red)
        //(Black) / \ / \ (Black)
        
        // Re-coloring Insert (Sibling of parent is red)
        assertNull(tree.put(5, "five"));
        assertEquals(4, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(0, tree.getProperty(tree.root()));
        assertEquals(-2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(0, tree.getProperty(tree.left(tree.root())));
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(0, tree.getProperty(tree.right(tree.root())));
        assertEquals(5, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(1, tree.getProperty(tree.right(tree.right(tree.root()))));
        assertEquals(0, tree.getProperty(tree.right(tree.left(tree.root())))); // Checking color of leaf node.
        assertEquals(0, tree.getProperty(tree.left(tree.left(tree.root())))); // Checking color of leaf node.
        assertEquals(0, tree.getProperty(tree.left(tree.right(tree.root())))); // Checking color of leaf node.
        assertEquals(0, tree.getProperty(tree.left(tree.right(tree.right(tree.root()))))); // Checking color of leaf node.
        assertEquals(0, tree.getProperty(tree.right(tree.right(tree.right(tree.root()))))); // Checking color of leaf node.
        // Current Tree
        //           3 (Black)
        //          / \     
        //(Black) -2   4 (Black)
        //(Black) / \ / \ 
        //               5 (Red)
        //              / \ (Black)
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertTrue(tree.isEmpty());
    	assertEquals(0, tree.size());
    	
    	assertNull(tree.put(-2, "negTwo"));
    	assertNull(tree.put(3, "three"));
    	assertNull(tree.put(4, "four"));
    	assertNull(tree.put(5, "five"));
    	assertEquals(4, tree.size());
    	
    	assertEquals("negTwo", tree.get(-2));
    	assertEquals("three", tree.get(3));
    	assertEquals("four", tree.get(4));
    	assertEquals("five", tree.get(5));
    	assertNull(tree.get(100));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
            
        assertNull(tree.put(-2, "negTwo"));
    	assertNull(tree.put(3, "three"));
    	assertNull(tree.put(4, "four"));
    	assertNull(tree.put(5, "five"));
    	assertEquals(4, tree.size());
    	// Current Tree
        //           3 (Black)
        //          / \     
        //(Black) -2   4 (Black)
        //(Black) / \ / \ 
        //               5 (Red)
        //              / \ (Black)
    	
    	// Removing black node with two sentinel node children.
    	assertEquals("negTwo", tree.remove(-2));
    	assertEquals(3, tree.size());
    	assertEquals(4, (int)tree.root().getElement().getKey());
    	assertEquals(0, tree.getProperty(tree.root()));
    	assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(0, tree.getProperty(tree.left(tree.root())));
    	assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
    	assertEquals(0, tree.getProperty(tree.right(tree.root())));
    	assertEquals(0, tree.getProperty(tree.left(tree.left(tree.root())))); // Checking color of lead node.
    	assertEquals(0, tree.getProperty(tree.right(tree.left(tree.root())))); // Checking color of leaf node.
    	assertEquals(0, tree.getProperty(tree.left(tree.right(tree.root())))); // Checking color of leaf node.
    	assertEquals(0, tree.getProperty(tree.right(tree.right(tree.root())))); // Checking color of leaf node.
    	// Current Tree
        //           4 (Black)
        //          / \     
        //(Black)  3   5 (Black)
        //(Black) / \ / \ (Black)
    	
    	// Adding red node to right side of tree
    	assertNull(tree.put(6, "six"));
    	// Adding red node to left side of tree
    	assertNull(tree.put(2, "two"));
    	assertEquals(2, (int)tree.left(tree.left(tree.root())).getElement().getKey());
    	assertEquals(1, tree.getProperty(tree.left(tree.left(tree.root()))));
    	assertEquals(6, (int)tree.right(tree.right(tree.root())).getElement().getKey());
    	assertEquals(1, tree.getProperty(tree.right(tree.right(tree.root()))));
    	assertEquals(0, tree.getProperty(tree.left(tree.left(tree.left(tree.root()))))); // Checking color of leaf node
    	assertEquals(0, tree.getProperty(tree.right(tree.left(tree.left(tree.root()))))); // Checking color of leaf node.
    	assertEquals(0, tree.getProperty(tree.left(tree.right(tree.right(tree.root()))))); // Checking color of leaf node.
    	assertEquals(0, tree.getProperty(tree.right(tree.right(tree.right(tree.root()))))); // Checking color of leaf node.
    	// Current Tree
        //           4 (Black)
        //          / \     
        //(Black)  3   5 (Black)
        //(Black) / \ / \ (Black)
    	// (Red) 2       6 (Red)
    	//      / \     / \ (Black)
    	
    	// Removing red node with key 2.
    	assertEquals("two", tree.remove(2));
    	assertEquals(4, (int)tree.root().getElement().getKey());
    	assertEquals(0, tree.getProperty(tree.root()));
    	assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(0, tree.getProperty(tree.left(tree.root())));
    	assertNull(tree.left(tree.left(tree.root())).getElement());
    	assertEquals(0, tree.getProperty(tree.left(tree.left(tree.root())))); // Checking color of leaf node.
    	assertEquals(0, tree.getProperty(tree.right(tree.left(tree.root())))); // Checking color of leaf node.
    	assertEquals(5, (int)tree.right(tree.root()).getElement().getKey()); 
    	assertEquals(0, tree.getProperty(tree.right(tree.root())));
    	assertEquals(0, tree.getProperty(tree.left(tree.right(tree.root())))); // Checking color of leaf node.
    	assertEquals(6, (int)tree.right(tree.right(tree.root())).getElement().getKey());
    	assertEquals(1, tree.getProperty(tree.right(tree.right(tree.root()))));
    	assertEquals(0, tree.getProperty(tree.left(tree.right(tree.right(tree.root()))))); // Checking color of leaf node.
    	assertEquals(0, tree.getProperty(tree.right(tree.right(tree.right(tree.root()))))); // Checking color of leaf node.
    	// Current Tree
        //           4 (Black)
        //          / \     
        //(Black)  3   5 (Black)
        //(Black) / \ / \ (Black)
    	//               6 (Red)
    	//              / \ (Black)
    	
    	// Removing black node with key 5 that has a red child.
    	assertEquals("five", tree.remove(5));
    	assertEquals(4, (int)tree.root().getElement().getKey());
    	assertEquals(0, tree.getProperty(tree.root()));
    	assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(0, tree.getProperty(tree.left(tree.root())));
    	assertEquals(0, tree.getProperty(tree.left(tree.left(tree.root())))); // Checking color of leaf node.
    	assertEquals(0, tree.getProperty(tree.right(tree.left(tree.root())))); // Checking color of leaf node.
    	assertEquals(6, (int)tree.right(tree.root()).getElement().getKey()); 
    	assertEquals(0, tree.getProperty(tree.right(tree.root())));
    	assertEquals(0, tree.getProperty(tree.left(tree.right(tree.root())))); // Checking color of leaf node.
    	assertNull(tree.right(tree.right(tree.root())).getElement());
    	assertEquals(0, tree.getProperty(tree.left(tree.right(tree.root())))); // Checking color of leaf node.
    	assertEquals(0, tree.getProperty(tree.right(tree.right(tree.root())))); // Checking color of leaf node.
    	// Current Tree
        //           4 (Black)
        //          / \     
        //(Black)  3   6 (Black)
        //(Black) / \ / \ (Black)   
    	
    	assertEquals("four", tree.remove(4));
    	assertEquals("six", tree.remove(6));
    	assertEquals("three", tree.remove(3));
    	assertNull(tree.remove(10));
    }
}
