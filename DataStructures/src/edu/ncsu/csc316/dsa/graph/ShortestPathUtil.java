package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 */
public class ShortestPathUtil {
	
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
    	HeapAdaptablePriorityQueue<Integer, Vertex<V>> queue = new HeapAdaptablePriorityQueue<Integer, Vertex<V>>();
    	Map<Vertex<V>, Integer> c = new LinearProbingHashMap<Vertex<V>, Integer>();
    	Set<Vertex<V>> s = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, Entry<Integer, Vertex<V>>> e = new LinearProbingHashMap<Vertex<V>, Entry<Integer, Vertex<V>>>();
    	
    	for(Vertex<V> vertex : graph.vertices()) {
    		if(vertex == start ) {
    			c.put(vertex, 0);
    		} else {
    			c.put(vertex, Integer.MAX_VALUE);
    		}
    		int currentCost = c.get(vertex);
    		Entry<Integer, Vertex<V>> apqEntry = queue.insert(currentCost, vertex);
    		e.put(vertex, apqEntry);
    	}
    	while(!queue.isEmpty()) {
    		Entry<Integer, Vertex<V>> entry = queue.deleteMin();
    		Vertex<V> u = entry.getValue();
    		s.add(u);
    		for(Edge<E> edge : graph.outgoingEdges(u)) {
    			Vertex<V> z = graph.opposite(u, edge);
    			if(!s.contains(z)) {
    				int r = edge.getElement().getWeight() + c.get(u);
    				if(r < c.get(z)) {
    					c.put(z, r);
    					queue.replaceKey(e.get(z), r);
    				}
    			}
    		}
    	}
    	return c;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
        Map<Vertex<V>, Edge<E>> m = new LinearProbingHashMap<>();
        for(Vertex<V> v : costs) {
        	if(v != start) {
        		for(Edge<E> e : graph.incomingEdges(v)) {
        			Vertex<V> u = graph.opposite(v, e);
        			if(costs.get(v).equals(costs.get(u) + e.getElement().getWeight())) {
        				m.put(v, e);
        			}
        		}
        	}
        }
        return m;
    }
}
