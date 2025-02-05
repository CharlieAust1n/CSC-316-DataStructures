package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Test class for ShortestPathUtil
 * Checks the expected outputs of Dijksra's algorithm
 * and the shortest path tree construction method
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 */
public class ShortestPathUtilTest {
	
	/**
	 * Class that represents edges for graph. Pulled from workshop 12.
	 * @author Dr. King 
	 */
	public class Highway implements Weighted {
		/** Name of highway */
        private String name;
        /** Length of highway */
        private int length;
        
        /**
         * Constructs an instance of a highway
         * @param n Name of the highway
         * @param l The weight/length of the highway
         */
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }

        /**
         * Sets the name of the highway
         * @param name Name to set the highway to.
         */
        public void setName(String name) {
            this.name = name;
        }
        
        /**
         * Retrieves the name of the highway.
         * @return name of highway.
         */
        public String getName() {
        	return name;
        }

        /**
         * Retrieves the length/weight of the highway.
         * @return the length/weight of the highway.
         */
        public int getLength() {
            return length;
        }

        /**
         * Sets the length/weight of the highway.
         * @param length Length/weight of the highway.
         */
        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }

	/** Directed graph used for testing. */
	private Graph<String, Highway> directedGraph;
	/** Map used for testing dijkstra algorithm. */
	private Map<Vertex<String>, Integer> dijkstraMap;
	/** Map used for testing shortestPathTree */
	private Map<Vertex<String>, Edge<Highway>> shortMap;
	
	/**
	 * Create a new instance of an adjacent list graph before each test case executes.
	 */
	@Before
	public void setUP() {
		directedGraph = new AdjacencyListGraph<String, Highway>(true);
	}

    /**
     * Test the output of Dijkstra's algorithm
     */ 
    @Test
    public void testDijkstra() {
    	Highway highway1 = new Highway("Highway 1", 3);
    	Highway highway2 = new Highway("Highway 2", 6);
    	Highway highway3 = new Highway("Highway 3", 1);
    	Highway highway4 = new Highway("Highway 4", 3);
    	Highway highway5 = new Highway("Highway 5", 3);
    	Highway highway6 = new Highway("Highway 6", 1);
    	Highway highway7 = new Highway("Highway 7", 1);
    	Highway highway8 = new Highway("Highway 8", 1);
    	Highway highway9 = new Highway("Highway 9", 4);
    	Highway highway10 = new Highway("Highway 10", 6);
    	
    	//	Graph
    	//
    	//  A -> B   E
    	//  |  / |  > ^ 
    	//  V <  V /  |
    	//  C -> D -> F
    	//  ^   /     ^
    	//  \__/______|
    	
    	Vertex<String> vertexA = directedGraph.insertVertex("A");
    	Vertex<String> vertexB = directedGraph.insertVertex("B");
    	Vertex<String> vertexC = directedGraph.insertVertex("C");
    	Vertex<String> vertexD = directedGraph.insertVertex("D");
    	Vertex<String> vertexE = directedGraph.insertVertex("E");
    	Vertex<String> vertexF = directedGraph.insertVertex("F");
    	
    	directedGraph.insertEdge(vertexA, vertexB, highway1);
    	directedGraph.insertEdge(vertexA, vertexC, highway2);
    	directedGraph.insertEdge(vertexB, vertexC, highway3);
    	directedGraph.insertEdge(vertexC, vertexD, highway4);
    	directedGraph.insertEdge(vertexB, vertexD, highway5);
    	directedGraph.insertEdge(vertexD, vertexC, highway6);
    	directedGraph.insertEdge(vertexC, vertexF, highway7);
    	directedGraph.insertEdge(vertexD, vertexF, highway8);
    	directedGraph.insertEdge(vertexD, vertexE, highway9);
    	directedGraph.insertEdge(vertexF, vertexE, highway10);
    	
    	dijkstraMap = ShortestPathUtil.dijkstra(directedGraph, vertexA);
    	
    	assertEquals(6, dijkstraMap.size());
    	assertEquals(3, (int)dijkstraMap.get(vertexB));
    	assertEquals(4, (int)dijkstraMap.get(vertexC));
    	assertEquals(6, (int)dijkstraMap.get(vertexD));
    	assertEquals(5, (int)dijkstraMap.get(vertexF));
    	assertEquals(10, (int)dijkstraMap.get(vertexE));
    }
    
    /**
     * Test the output of the shortest path tree construction method
     */ 
    @Test
    public void testShortestPathTree() {
    	Highway highway1 = new Highway("Highway 1", 3);
    	Highway highway2 = new Highway("Highway 2", 6);
    	Highway highway3 = new Highway("Highway 3", 1);
    	Highway highway4 = new Highway("Highway 4", 3);
    	Highway highway5 = new Highway("Highway 5", 3);
    	Highway highway6 = new Highway("Highway 6", 1);
    	Highway highway7 = new Highway("Highway 7", 1);
    	Highway highway8 = new Highway("Highway 8", 1);
    	Highway highway9 = new Highway("Highway 9", 4);
    	Highway highway10 = new Highway("Highway 10", 6);
    	
    	//	Graph
    	//
    	//  A -> B   E
    	//  |  / |  > ^ 
    	//  V <  V /  |
    	//  C -> D -> F
    	//  ^   /     ^
    	//  \__/______|
    	
    	Vertex<String> vertexA = directedGraph.insertVertex("A");
    	Vertex<String> vertexB = directedGraph.insertVertex("B");
    	Vertex<String> vertexC = directedGraph.insertVertex("C");
    	Vertex<String> vertexD = directedGraph.insertVertex("D");
    	Vertex<String> vertexE = directedGraph.insertVertex("E");
    	Vertex<String> vertexF = directedGraph.insertVertex("F");
    	
    	directedGraph.insertEdge(vertexA, vertexB, highway1);
    	directedGraph.insertEdge(vertexA, vertexC, highway2);
    	directedGraph.insertEdge(vertexB, vertexC, highway3);
    	directedGraph.insertEdge(vertexC, vertexD, highway4);
    	directedGraph.insertEdge(vertexB, vertexD, highway5);
    	directedGraph.insertEdge(vertexD, vertexC, highway6);
    	directedGraph.insertEdge(vertexC, vertexF, highway7);
    	directedGraph.insertEdge(vertexD, vertexF, highway8);
    	directedGraph.insertEdge(vertexD, vertexE, highway9);
    	directedGraph.insertEdge(vertexF, vertexE, highway10);
    	
    	Map<Vertex<String>, Integer> costs = new LinearProbingHashMap<Vertex<String>, Integer>();
    	costs.put(vertexA, 0);
    	costs.put(vertexB, 3);
    	costs.put(vertexC, 1);
    	costs.put(vertexD, 3);
    	costs.put(vertexE, 4);
    	costs.put(vertexF, 1);
    	
    	shortMap = ShortestPathUtil.shortestPathTree(directedGraph, vertexA, costs);
    	
    	assertEquals(1, shortMap.size());
    	assertEquals("B", shortMap.iterator().next().getElement());
    }
    
}
