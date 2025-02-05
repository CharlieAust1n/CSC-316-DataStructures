package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 */
public class GraphTraversalUtilTest {
	
	/** Undirected graph used for testing. */
	private Graph<String, Integer> undirectedGraph;
	/** Map used for testing. */
	private Map<Vertex<String>, Edge<Integer>> map;
	
	/**
	 * Create a new instance of an adjacent list graph before each test case executes.
	 */
	@Before
	public void setUP() {
		undirectedGraph = new AdjacencyListGraph<String, Integer>();
	}
    
    /**
     * Tests the depth-first-search searching algorithm.
     */
    @Test
    public void testDepthFirstSearch() {
    	Vertex<String> vertexA = undirectedGraph.insertVertex("A");
    	Vertex<String> vertexB = undirectedGraph.insertVertex("B");
    	Vertex<String> vertexC = undirectedGraph.insertVertex("C");
    	Vertex<String> vertexD = undirectedGraph.insertVertex("D");
    	Vertex<String> vertexE = undirectedGraph.insertVertex("E");
    	
    	Edge<Integer> e1 = undirectedGraph.insertEdge(vertexA, vertexB, 1);
		undirectedGraph.insertEdge(vertexA, vertexC, 2);
    	Edge<Integer> e3 = undirectedGraph.insertEdge(vertexB, vertexC, 3);
    	Edge<Integer> e4 = undirectedGraph.insertEdge(vertexB, vertexD, 4);
    	Edge<Integer> e5 = undirectedGraph.insertEdge(vertexD, vertexE, 5);
    	
    	map = GraphTraversalUtil.depthFirstSearch(undirectedGraph, vertexA);
    	
    	assertEquals(4, map.size());
    	
    	//  e2  e1  e4  e5
    	// C - A - B - D - E 
    	// \______/ <- e3
    	
    	assertNull(map.get(vertexA));
    	assertEquals(e1, map.get(vertexB));
    	assertEquals(e3, map.get(vertexC));
    	assertEquals(e4, map.get(vertexD));
    	assertEquals(e5, map.get(vertexE));
    }
    
    /**
     * Tests the breadth-first-search searching algorithm.
     */
    @Test
    public void testBreadthFirstSearch() {
    	Vertex<String> vertexA = undirectedGraph.insertVertex("A");
    	Vertex<String> vertexB = undirectedGraph.insertVertex("B");
    	Vertex<String> vertexC = undirectedGraph.insertVertex("C");
    	Vertex<String> vertexD = undirectedGraph.insertVertex("D");
    	Vertex<String> vertexE = undirectedGraph.insertVertex("E");
    	
    	Edge<Integer> e1 = undirectedGraph.insertEdge(vertexA, vertexB, 1);
    	Edge<Integer> e2 = undirectedGraph.insertEdge(vertexA, vertexC, 2);
		undirectedGraph.insertEdge(vertexB, vertexC, 3);
    	Edge<Integer> e4 = undirectedGraph.insertEdge(vertexB, vertexD, 4);
    	Edge<Integer> e5 = undirectedGraph.insertEdge(vertexD, vertexE, 5);
    	
    	map = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, vertexA);
    	
    	assertEquals(4, map.size());
    	
    	//  e2  e1  e4  e5
    	// C - A - B - D - E 
    	// \______/ <- e3
    	
    	assertNull(map.get(vertexA));
    	assertEquals(e1, map.get(vertexB));
    	assertEquals(e2, map.get(vertexC));
    	assertEquals(e4, map.get(vertexD));
    	assertEquals(e5, map.get(vertexE));
    }
}
