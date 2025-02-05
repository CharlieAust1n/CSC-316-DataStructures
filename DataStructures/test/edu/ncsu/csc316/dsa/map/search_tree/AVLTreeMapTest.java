package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2) 
 *
 */
public class AVLTreeMapTest {
	/** Empty binary search tree used for testing. */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(-2, "negTwo"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(-2, (int)tree.root().getElement().getKey());
        // Current Tree
        //     -2
        //     / \
        assertNull(tree.put(3, "three"));
        assertEquals(2, tree.size());
        assertEquals(-2, (int)tree.root().getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        // Current Tree
        //     -2 h = 2
        //     / \
        // h=0    3 h = 1
        //       / \ h = 0
        
        // Right side double rotation
        assertNull(tree.put(2, "two"));
        assertEquals(3, tree.size());
        // Tree before trinode restructure
        //     -2 h = 2
        //     / \
        // h=0    3 h = 2
        //       / \ h = 0
        //      2  h = 1
        // h=0 / \ h =0
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        // Tree after trinode restructure
        //        2 h = 1
        //       / \
        // h=1 -2   3 h = 1
        // h=0 / \ / \ h = 0
        
        // Regular insertion
        assertNull(tree.put(4, "four"));
        assertEquals(4, tree.size());
        // Current tree
        //        2 h = 3
        //       / \
        // h=1 -2   3 h = 2
        // h=0 / \ / \
        //            4 h = 1
        //           / \ h = 0
        assertEquals(4, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        // Single rotation on right side
        assertNull(tree.put(5, "five"));
        assertEquals(5, tree.size());
        // Tree before trinode restructure
        //        2 h = 4
        //       / \
        // h=1 -2   3 h = 3
        // h=0 / \ / \
        //            4 h = 2
        //           / \ 
        //              5 h = 1
        //             / \ h = 0
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(5, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        // Tree after trinode restructure
        //         2  h = 3
        //       /   \
        // h=1 -2     4 h = 2
        // h=0 / \   / \
        //   h = 1  3    5 h = 1
        //  h = 0  / \  / \ h = 0    
        
        // Regular insertions
        assertNull(tree.put(-1, "negOne"));
        assertNull(tree.put(-4, "negFour"));
        assertNull(tree.put(-6, "negSix"));
        assertEquals(8, tree.size());
        // Current Tree
        //             2  h = 4
        //         /       \
        // h=3    -2        4 h = 2
        //       /  \      /  \
        // h=2 -4   -1    3    5 h = 1
        //     / \  / \  / \  / \ h = 0
        //    -6 h=1
        
        // Double rotation
        assertNull(tree.put(-5, "negFive"));
        assertEquals(9, tree.size());
        // Tree before trinode restructure
        //             2  h = 5
        //         /       \
        // h=4    -2        4 h = 2
        //       /  \      /  \
        // h=3 -4   -1    3    5 h = 1
        //     / \  / \  / \  / \ h = 0
        //   -6 h=2
        //   / \
        //     -5 h=1
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(-1, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(5, (int)tree.right(tree.right(tree.root())).getElement().getKey());   
        assertEquals(-5, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(-6, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(-4, (int)tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
        // Tree after trinode restructure
        //             2  h = 4
        //         /       \
        // h=3    -2        4 h = 2
        //       /  \      /  \
        // h=2 -5   -1    3    5 h = 1
        //     / \  / \  / \  / \ h = 0
        //   -6  -4 h=1
        //   / \ / \ h=0
        
        // Single rotation
        assertNull(tree.put(-7, "negSeven"));
        assertEquals(10, tree.size());
        // Tree before trinode restructure
        //             2  h = 5
        //         /       \
        // h=4    -2        4 h = 2
        //       /  \      /  \
        // h=3 -5   -1    3    5 h = 1
        //     / \  / \  / \  / \ h = 0
        //h=2 -6 -4 h=1
        //   / \ / \ h=0
        // -7 h=1
        // / \ h=0
        assertEquals(-5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(-2, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(-4, (int)tree.left(tree.right(tree.left(tree.root()))).getElement().getKey());
        assertEquals(-1, (int)tree.right(tree.right(tree.left(tree.root()))).getElement().getKey());
        assertEquals(-6, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(-7, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        // Tree after trinode restructure
        //             2  h = 4
        //         /         \
        // h=3    -5         4 h = 2
        //       /  \       /  \
        // h=2 -6   -2     3    5 h = 1
        //     / \  / \   / \  / \ h = 0
        //h=1 -7   -4  -1
        //    / \  / \ / \ h=0
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
       
        assertNull(tree.put(-2, "negTwo"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(-1, "negOne"));
        assertNull(tree.put(-4, "negFour"));
        assertNull(tree.put(-6, "negSix"));
        assertNull(tree.put(-5, "negFive"));
        assertNull(tree.put(-7, "negSeven"));
        assertEquals(10, tree.size());
        assertFalse(tree.isEmpty());
        // Current Tree
        //             2  h = 4
        //         /         \
        // h=3    -5         4 h = 2
        //       /  \       /  \
        // h=2 -6   -2     3    5 h = 1
        //     / \  / \   / \  / \ h = 0
        //h=1 -7   -4  -1
        //    / \  / \ / \ h=0
        
        assertEquals("negTwo", tree.get(-2));
        assertEquals("three", tree.get(3));
        assertEquals("two", tree.get(2));
        assertEquals("four", tree.get(4));
        assertEquals("five", tree.get(5));
        assertEquals("negOne", tree.get(-1));
        assertEquals("negFour", tree.get(-4));
        assertEquals("negSix", tree.get(-6));
        assertEquals("negFive", tree.get(-5));
        assertEquals("negSeven", tree.get(-7));
        assertNull(tree.get(100));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(-2, "negTwo"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(-1, "negOne"));
        assertNull(tree.put(-4, "negFour"));
        assertNull(tree.put(-6, "negSix"));
        assertNull(tree.put(-5, "negFive"));
        assertNull(tree.put(-7, "negSeven"));
        assertEquals(10, tree.size());
        assertFalse(tree.isEmpty());
        // Current Tree
        //             2  h = 4
        //         /         \
        // h=3    -5         4 h = 2
        //       /  \       /  \
        // h=2 -6   -2     3    5 h = 1
        //     / \  / \   / \  / \ h = 0
        //h=1 -7   -4  -1
        //    / \  / \ / \ h=0
        
        // Removing root node
        assertEquals("two", tree.remove(2));
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.right(tree.root())).getElement());
        assertEquals(9, tree.size());
        // Current Tree
        //             3  h = 4
        //         /         \
        // h=3    -5         4 h = 2
        //       /  \       /  \
        // h=2 -6   -2          5 h = 1
        //     / \  / \        / \ h = 0
        //h=1 -7   -4  -1
        //    / \  / \ / \ h=0
        
        // Trinode restructure
        assertEquals("five", tree.remove(5));
        // Tree before trinode restructure
        //             3  h = 4
        //         /         \
        // h=3    -5         4 h = 1
        //       /  \       /  \
        // h=2 -6   -2          
        //     / \  / \        
        //h=1 -7   -4  -1
        //    / \  / \ / \ h=0
        assertEquals(-5, (int)tree.root().getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(-4, (int)tree.left(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals(-1, (int)tree.right(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals(-6, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(-7, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(8, tree.size());
        // Tree after trinode restructure
        //              -5  h = 4
        //         /          \
        // h=2    -6           3 h = 3
        //       /  \        /   \
        // h=1 -7          -2 h=2 4 h = 1
        //     / \         / \   / \
        //               -4  -1  
        
        // Regular removals
        assertEquals("negTwo", tree.remove(-2));
        assertEquals(-1, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(-4, (int)tree.left(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals("negFour", tree.remove(-4));
        //assertEquals("negOne", tree.remove(-1));
        assertEquals(-5, (int)tree.root().getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(6, tree.size());
        // Current Tree
        //              -5  h = 4
        //         /          \
        // h=2    -6           3 h = 1
        //       /  \        /   \
        // h=1 -7           -1    4
        //     / \          / \  / \
        
        // Trinode restructure
        assertEquals("negSeven", tree.remove(-7));
        assertEquals("negSix", tree.remove(-6));
        assertEquals(4, tree.size());
        // Tree before trinode restructure
        //     -5 h=3
        //     / \
        //        3 h=2
        //       / \
        //      -1  4 h=1
        //      / \ / \
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(-5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(-1, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        // Current Tree
        //        3 h=3
        //      /   \
        // h=2 -5    4 h=1
        //     / \  / \
        //   h=1 -1
        //       / \
    }
}
