package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 */
public class LinkedBinaryTreeTest {

    private LinkedBinaryTree<String> tree;
    private Position<String> one;
    private Position<String> two;
    private Position<String> three;
    private Position<String> four;
    private Position<String> five;
    private Position<String> six;
    private Position<String> seven;
    private Position<String> eight;
    private Position<String> nine;
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        assertEquals("eight", tree.set(eight, null));
        assertNull(tree.left(four).getElement());
        assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   five\n three\n  four\n   null\n   nine\n]", tree.toString());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(0, tree.numChildren(six));
        assertEquals(2, tree.numChildren(ten));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(five));
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        
        assertEquals(null, tree.parent(one));
        
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        
        assertEquals(two, tree.parent(six));
        assertEquals(two, tree.parent(ten));
        
        assertEquals(ten, tree.parent(seven));
        assertEquals(ten, tree.parent(five));
        
        assertEquals(three, tree.parent(four));
        
        assertEquals(four, tree.parent(eight));
        assertEquals(four, tree.parent(nine));
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        
        assertNull(tree.sibling(one));
        
        assertEquals(three, tree.sibling(two));
        assertEquals(two, tree.sibling(three));
        
        assertEquals(ten, tree.sibling(six));
        assertEquals(six, tree.sibling(ten));
        
        assertNull(tree.sibling(four));
        
        assertEquals(five, tree.sibling(seven));
        assertEquals(seven, tree.sibling(five));
        
        assertEquals(nine, tree.sibling(eight));
        assertEquals(eight, tree.sibling(nine));
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(four));
        assertTrue(tree.isInternal(ten));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(four));
        assertFalse(tree.isLeaf(ten));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(ten));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();
        assertTrue(it.hasNext());
        Position<String> entry = it.next();
        assertEquals("one", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("two", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("six", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("ten", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("seven", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("five", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("three", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("four", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("eight", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("nine", entry.getElement());
        assertFalse(it.hasNext());
        
        Exception exception = assertThrows(UnsupportedOperationException.class,
        		() -> it.remove());
        assertEquals("The remove operation is not supported yet.", exception.getMessage());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        assertTrue(it.hasNext());
        Position<String> entry = it.next();
        assertEquals("six", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("seven", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("five", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("ten", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("two", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("eight", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("nine", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("four", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("three", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("one", entry.getElement());
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        assertTrue(it.hasNext());
        Position<String> entry = it.next();
        assertEquals("six", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("two", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("seven", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("ten", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("five", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("one", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("eight", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("four", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("nine", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("three", entry.getElement());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertNull(tree.root());
    }
    
    /**
     * Test the output of the levelOrder traversal behavior
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertTrue(it.hasNext());
        Position<String> entry = it.next();
        assertEquals("one", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("two", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("three", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("six", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("ten", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("four", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("seven", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("five", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("eight", entry.getElement());
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals("nine", entry.getElement());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	one = tree.addRoot("one");
        assertEquals("two", tree.addLeft(one, "two").getElement());
        assertEquals(2, tree.size());
        Exception exception = assertThrows(IllegalArgumentException.class,
        		() -> tree.addLeft(one, "DNE"));
        assertEquals("Node already has a left child.", exception.getMessage());
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	one = tree.addRoot("one");
        assertEquals("three", tree.addRight(one, "three").getElement());
        Exception exception = assertThrows(IllegalArgumentException.class,
        		() -> tree.addRight(one, "DNE"));
        assertEquals("Node already has a right child.", exception.getMessage());
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        assertEquals("six", tree.remove(six));
        assertEquals(9, tree.size());
        
        assertEquals("two", tree.remove(two));
        assertEquals(8, tree.size());
        assertEquals(one, tree.parent(ten));
        
        assertEquals("nine", tree.remove(nine));
        assertEquals(7, tree.size());
        
        Exception tooManyChildren = assertThrows(IllegalArgumentException.class,
        		() -> tree.remove(one));
        assertEquals("The node has two children", tooManyChildren.getMessage());
    }
}
