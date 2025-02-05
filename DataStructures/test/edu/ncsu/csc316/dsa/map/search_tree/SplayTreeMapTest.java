package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.search_tree.*;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 */
public class SplayTreeMapTest {
	/** Empty binary search tree used for testing. */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
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
        // Current Tree
        //    -2
        //    / \
        
        // Left Zig insert
        assertNull(tree.put(3, "three"));
        assertEquals(2, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.root()).getElement().getKey());
        // Current Tree
        //    3
        //   / \
        //  -2
        //  / \
        
        // Right Zig insert
        assertEquals("negTwo", tree.put(-2, "negativeTwo"));
        assertEquals(2, tree.size());
        assertEquals(-2, (int)tree.root().getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        // Current Tree
        //   -2
        //   / \ 
        //      3
        //     / \
        
        // Zig-Zag (on right side) insert
        assertNull(tree.put(1, "one"));
        assertEquals(3, tree.size());
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        // Current Tree
        //      1
        //     / \
        //   -2   3
        //   / \ / \
        
        // Zig-Zag (on left side) insert
        assertNull(tree.put(0, "zero"));
        assertEquals(4, tree.size());
        assertEquals(0, (int)tree.root().getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        // Current Tree
        //          0
        //        /   \
        //      -2     1
        //      / \   / \
        //               3
        
        // Zig-Zig insert (on right side)
        assertNull(tree.put(4, "four"));
        assertEquals(5, tree.size());
        // Tree before splay
        //            0
        //         /     \
        //       -2       1
        //       / \     / \
        //                  3
        //                 / \
        //                    4
        //                   / \
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.right(tree.left(tree.root()))).getElement().getKey());
        // Current Tree after splay
        //            4
        //          /   \
        //         0  
        //       /   \
        //     -2     3
        //     / \   / \
        //          1
        //         / \
        
        // Zig-Zig insert (on left side)
        assertNull(tree.put(-3, "negThree"));
        assertEquals(6, tree.size());
        assertEquals(-3, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(0, (int)tree.right(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.right(tree.left(tree.right(tree.root())))).getElement().getKey());
        assertEquals(1, 
        		(int)tree.left(tree.right(tree.right(tree.left(tree.right(tree.root()))))).getElement().getKey());
        // Current Tree after splay
        //             -3
        //          /       \
        //                   4
        //                /     \
        //              -2
        //           /      \
        //                   0
        //                /     \
        //                       3
        //                    /     \
        //                   1
        //                /     \
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
        assertEquals("negTwo", tree.put(-2, "negativeTwo"));
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(0, "zero"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(-3, "negThree"));
        assertEquals(6, tree.size());
        assertEquals(-3, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(0, (int)tree.right(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.right(tree.left(tree.right(tree.root())))).getElement().getKey());
        assertEquals(1, 
        		(int)tree.left(tree.right(tree.right(tree.left(tree.right(tree.root()))))).getElement().getKey());
        // Current Tree after splay
        //             -3
        //          /       \
        //                   4
        //                /     \
        //              -2
        //           /      \
        //                   0
        //                /     \
        //                       3
        //                    /     \
        //                   1
        //                /     \
        
        
        // Accessing information of leaf node
        assertNull(tree.get(-1));
        assertEquals(0, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals(-3, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(-2, (int)tree.right(tree.left(tree.root())).getElement().getKey()); 
        // Current Tree after splay
        //              0
        //          /       \
        //        -3         4
        //        / \     /     \
        //          -2   3
        //              / \
        //             1
        //            / \
        
        // Accessing information of internal node
        assertEquals("one", tree.get(1));
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(-3, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(-2, (int)tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
        // Current Tree after splay
        //              1
        //          /       \
        //         0         3
        //        / \     /     \
        //       -3              4
        //       / \           /   \
        //         -2
        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	
    	 assertNull(tree.put(-2, "negTwo"));
         assertNull(tree.put(3, "three"));
         assertEquals("negTwo", tree.put(-2, "negativeTwo"));
         assertNull(tree.put(1, "one"));
         assertNull(tree.put(0, "zero"));
         assertNull(tree.put(4, "four"));
         assertNull(tree.put(-3, "negThree"));
         assertEquals(6, tree.size());
         assertEquals(-3, (int)tree.root().getElement().getKey());
         assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
         assertEquals(-2, (int)tree.left(tree.right(tree.root())).getElement().getKey());
         assertEquals(0, (int)tree.right(tree.left(tree.right(tree.root()))).getElement().getKey());
         assertEquals(3, (int)tree.right(tree.right(tree.left(tree.right(tree.root())))).getElement().getKey());
         assertEquals(1, 
         		(int)tree.left(tree.right(tree.right(tree.left(tree.right(tree.root()))))).getElement().getKey());
         // Current Tree after splay
         //             -3
         //          /       \
         //                   4
         //                /     \
         //              -2
         //           /      \
         //                   0
         //                /     \
         //                       3
         //                    /     \
         //                   1
         //                /     \
         
         
         // Removing leaf node (key does not exist within tree)
         assertNull(tree.remove(-1));
         assertEquals(6, tree.size());
         assertEquals(0, (int)tree.root().getElement().getKey());
         assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
         assertEquals(3, (int)tree.left(tree.right(tree.root())).getElement().getKey());
         assertEquals(1, (int)tree.left(tree.left(tree.right(tree.root()))).getElement().getKey());
         assertEquals(-3, (int)tree.left(tree.root()).getElement().getKey());
         assertEquals(-2, (int)tree.right(tree.left(tree.root())).getElement().getKey()); 
         // Current Tree after splay
         //              0
         //          /       \
         //        -3         4
         //        / \     /     \
         //          -2   3
         //              / \
         //             1
         //            / \
         
         // Removing the root node
         assertEquals("zero", tree.remove(0));
         assertEquals(5, tree.size());
         // Tree after removing root node but before the splay
         //              1
         //          /       \
         //        -3         4
         //        / \     /     \
         //          -2   3
         //              / \         
         assertEquals(3, (int)tree.root().getElement().getKey());
         assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
         assertEquals(-3, (int)tree.left(tree.left(tree.root())).getElement().getKey());
         assertEquals(-2, (int)tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
         assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
         // Current tree after splay
         //              3
         //          /       \
         //         1         4
         //       /   \     /     \
         //     -3       
         //   /    \   
         //        -2
         //       /   \
         
         // Removing internal node
         assertEquals("one", tree.remove(1));
         assertEquals(4, tree.size());
         assertEquals(3, (int)tree.root().getElement().getKey());
         assertEquals(-3, (int)tree.left(tree.root()).getElement().getKey());
         assertEquals(-2, (int)tree.right(tree.left(tree.root())).getElement().getKey());
         assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
         // Current tree after splay
         //              3
         //          /       \
         //        -3         4
         //       /   \     /   \  
         //           -2
         //          /   \
    }
}
