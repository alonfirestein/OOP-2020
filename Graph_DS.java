package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Graph_DS implements graph {
    private int SumOfEdges, ModeCounter = 0;
    private HashMap<Integer, node_data> NodesInGraph = new HashMap<>();

    /**
     * Basic constructor, setting the class instance variables to 0 and initializing the hashmap.
     */
    public Graph_DS() {
        SumOfEdges = 0;
        ModeCounter = 0;
        NodesInGraph = new HashMap<>();
    }

    /**
     * Basic graph copy constructor.
     * @param g - the graph of which we are copying.
     */
    public Graph_DS(graph g) {
        SumOfEdges = g.edgeSize();
        ModeCounter = g.getMC()/2;
        NodesInGraph = new HashMap<>();
    }
    
    @Override
    /**
     * If NodesInGraph Hashmap is empty, then the node does not exist, therefore we return null.
     * else - it does exist, therefore we return the node using its key.
     * @param key - the key of the node that we want to return its data
     */
    public node_data getNode(int key) {

        if (NodesInGraph.isEmpty()) { return null; }
        if (NodesInGraph.get(key) == null) { return null; }
        return NodesInGraph.get(key);
    }

    @Override
    /**
     * If node 1 and node 2 are neighbours, then they have an edge between them, therefore returning true.
     * @param node1 - key of first node to check if it has an edge with node2
     * @param node2 - key of second node to check if it has an edge with node1
     */
    public boolean hasEdge(int node1, int node2) {

        return getNode(node1).getNi().contains(getNode(node2));
    }

    @Override
    /**
     * Adding the node in the parameter to our Hashmap containing all the nodes in the graph.
     * If the node is already in the graph, then we do nothing.
     * @param n - the node_data in which we want to add to the graph
     */
    public void addNode(node_data n) {

        if (!NodesInGraph.containsKey(n.getKey())) {
            NodesInGraph.put(n.getKey(), n);
            ModeCounter++;
        }
    }

    @Override
    /**
     * Connecting two nodes by adding each other to their neighbours list.
     * Here we only add node 2 to node 1's neighbours list because our addNi function in the NodeData class
     * automatically works both ways.
     * If they are already neighbours, therefore they already have an edge between them, we do nothing.
     * @param node1 - key of first node to connect an edge
     * @param node2 - key of second node to connect an edge
     */
    public void connect(int node1, int node2) {
        if (node1 == node2) { return;}
        if (getNode(node1)==null || getNode(node2) == null) { return; }
        if (getV(node1).contains(getNode(node2))) {
            return;
        }
        // addNi automatically adds each other to both neighbours lists
        getNode(node1).addNi(getNode(node2));
        SumOfEdges++;
        ModeCounter++;
    }

    @Override
    /**
     * Returning a collection of all the nodes in the graph using the Hashmap.values function.
     */
    public Collection<node_data> getV() {
        return NodesInGraph.values();
    }

    @Override
    /**
     * Returning a collection of all the nodes connected by an edge to the node we receive in the parameter.
     * Therefore we return its neighbours list.
     * @param node_id - the ID of the node that we return his neighbours
     */
    public Collection<node_data> getV(int node_id) {
        return getNode(node_id).getNi();
    }

    @Override
    /**
     * If the node we want to remove does not have any neighbours, then we remove him from the graph/hashmap.
     * If he does have neighbours, then we need to delete all the edges connected to him, meaning to
     * remove the node from his adjacent nodes neighbours lists.
     * @param key - the key of the node to remove
     */
    public node_data removeNode(int key)  {

        if (NodesInGraph.get(key) == null || !NodesInGraph.containsKey(key)) {
            return null; }
        node_data result = getNode(key);
        ArrayList<Integer> NeighboursToRemove = new ArrayList<>();

        //If our node doesn't have neighbours
        if (getNode(key).getNi().isEmpty()) {
            NodesInGraph.remove(key);
            ModeCounter++;
            return result;
        }
        for (node_data neighbour : getNode(key).getNi()) {
            NeighboursToRemove.add(neighbour.getKey());
        }
        for (int neighbour : NeighboursToRemove) {
            removeEdge(neighbour, key);

        }
        NodesInGraph.remove(key);
        ModeCounter++;
        return result;

    }

    @Override
    /**
     * To remove an edge, we remove each node from the second nodes neighbours list.
     * If no such edge exists between them, then we do nothing.
     * @param node1 - (first node of edge to remove)
     * @param node2 - (second node of edge to remove)
     */
    public void removeEdge(int node1, int node2) {

        if (getNode(node1) == null || getNode(node2) == null) { return; }
        if (!getNode(node1).getNi().contains(getNode(node2))) { return; }

        getNode(node1).getNi().remove(getNode(node2));
        getNode(node2).getNi().remove(getNode(node1));
        SumOfEdges--;
        ModeCounter++;
    }

    @Override
    /**
     * Returning the total number of nodes in the graph.
     */
    public int nodeSize() {
        return NodesInGraph.size();
    }

    @Override
    /**
     * Returning the total number of edges in the graph.
     */
    public int edgeSize() {
        if (NodesInGraph.isEmpty()) {
            return 0;
        }
        return SumOfEdges;
    }

    @Override
    /**
     * Using a class instance variable and increasing it in particular functions, we return the number of changes
     * made in the graph, including adding nodes/edges, and removing nodes/edges from the graph.
     */
    public int getMC() {
        return ModeCounter;
    }
}

