package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for MinimumSpanningTreeUtil
 * Checks the expected outputs of Prim-Jarnik's algorithm
 * and Kruskal's algorithm
 *
 * @author Dr. King
 * @author Charlie Austin (cjausti2) 
 *
 */
public class MinimumSpanningTreeUtilTest {
	
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

	/** Undirected graph used for testing. */
	private Graph<String, Highway> undirectedGraph;
	/** Positional-List used for testing kruskal alg */
	private PositionalList<Edge<Highway>> list;
	
	/**
	 * Create a new instance of an adjacent list graph before each test case executes.
	 */
	@Before
	public void setUP() {
		undirectedGraph = new AdjacencyListGraph<String, Highway>();
	}
	
    /**
     * Test the output of Prim-Jarnik's algorithm
     */ 
    @Test
    public void testPrimJarnik() {
    	Highway highway1 = new Highway("Highway 1", 5);
    	Highway highway2 = new Highway("Highway 2", 2);
    	Highway highway3 = new Highway("Highway 3", 10);
    	Highway highway4 = new Highway("Highway 4", 7);
    	Highway highway5 = new Highway("Highway 5", 1);
    	
    	Vertex<String> vertexA = undirectedGraph.insertVertex("A");
    	Vertex<String> vertexB = undirectedGraph.insertVertex("B");
    	Vertex<String> vertexC = undirectedGraph.insertVertex("C");
    	Vertex<String> vertexD = undirectedGraph.insertVertex("D");
    	Vertex<String> vertexE = undirectedGraph.insertVertex("E");
    	
    	Edge<Highway> e1 = undirectedGraph.insertEdge(vertexA, vertexB, highway1);
    	Edge<Highway> e2 = undirectedGraph.insertEdge(vertexA, vertexC, highway5);
    	undirectedGraph.insertEdge(vertexB, vertexC, highway3);
    	Edge<Highway> e4 = undirectedGraph.insertEdge(vertexB, vertexD, highway4);
    	Edge<Highway> e5 = undirectedGraph.insertEdge(vertexD, vertexE, highway2);   
    	
    	list = MinimumSpanningTreeUtil.primJarnik(undirectedGraph);
    	//  e2  e1  e4  e5
       	// C - A - B - D - E 
       	// \______/ <- e3	    
    	
    	assertEquals(4, list.size());
    	assertEquals(e2, list.first().getElement());
    	assertEquals(highway5, list.first().getElement().getElement());
    	
    	assertEquals(e1, list.after(list.first()).getElement());
    	assertEquals(highway1, list.after(list.first()).getElement().getElement());
    	
    	assertEquals(e4, list.after(list.after(list.first())).getElement());
    	assertEquals(highway4, list.after(list.after(list.first())).getElement().getElement());
    	
    	assertEquals(e5, list.after(list.after(list.after(list.first()))).getElement());
    	assertEquals(highway2, list.after(list.after(list.after(list.first()))).getElement().getElement());
    	
    	assertNull(list.after(list.after(list.after(list.after(list.first())))));
    }
    
    /**
     * Test the output of Kruskal's algorithm
     */ 
    @Test
    public void testKruskal() {
    	Highway highway1 = new Highway("Highway 1", 5);
    	Highway highway2 = new Highway("Highway 2", 2);
    	Highway highway3 = new Highway("Highway 3", 10);
    	Highway highway4 = new Highway("Highway 4", 7);
    	Highway highway5 = new Highway("Highway 5", 1);
    	
    	Vertex<String> vertexA = undirectedGraph.insertVertex("A");
    	Vertex<String> vertexB = undirectedGraph.insertVertex("B");
    	Vertex<String> vertexC = undirectedGraph.insertVertex("C");
    	Vertex<String> vertexD = undirectedGraph.insertVertex("D");
    	Vertex<String> vertexE = undirectedGraph.insertVertex("E");
    	
    	Edge<Highway> e1 = undirectedGraph.insertEdge(vertexA, vertexB, highway1);
    	Edge<Highway> e2 = undirectedGraph.insertEdge(vertexA, vertexC, highway5);
    	undirectedGraph.insertEdge(vertexB, vertexC, highway3);
    	Edge<Highway> e4 = undirectedGraph.insertEdge(vertexB, vertexD, highway4);
    	Edge<Highway> e5 = undirectedGraph.insertEdge(vertexD, vertexE, highway2);   	
    	
    	list = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
    	//  e2  e1  e4  e5
       	// C - A - B - D - E 
       	// \______/ <- e3	    
    	
    	assertEquals(4, list.size());
    	assertEquals(e2, list.first().getElement());
    	assertEquals(highway5, list.first().getElement().getElement());
    	
    	assertEquals(e5, list.after(list.first()).getElement());
    	assertEquals(highway2, list.after(list.first()).getElement().getElement());
    	
    	assertEquals(e1, list.after(list.after(list.first())).getElement());
    	assertEquals(highway1, list.after(list.after(list.first())).getElement().getElement());
    	
    	assertEquals(e4, list.after(list.after(list.after(list.first()))).getElement());
    	assertEquals(highway4, list.after(list.after(list.after(list.first()))).getElement().getElement());
    	
    	assertNull(list.after(list.after(list.after(list.after(list.first())))));
    }
    
}
