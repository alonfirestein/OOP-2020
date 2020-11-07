package ex0;


import java.util.*;

public class Graph_Algo implements graph_algorithms {
    private graph graph_algo;

    /**
     * Basic constructor with no parameter.
     */
    public Graph_Algo() {
        graph_algo = new Graph_DS();
    }

    /**
     * Basic constructor with one parameter.
     * @param g - received graph
     */
    public Graph_Algo(graph g) {
        graph_algo = g;
    }

    @Override
    /**
     * Initializing the graph to use the graph_algo class
     * @param g - graph received in order to initialize the graph algrothims
     */
    public void init(graph g) {
        graph_algo = g;
    }

    @Override
    /**
     * Returning a deep copy of the original graph and its information. Including adding each node to the new
     * graph by using the node copy constructor in the NodeData class. Then, for every one of his neighbours,
     * we do the same process, using the copy constructor, and then copying the rest of the data, info, tag...
     * At the end, we connect the new copied node to his new copied neighbours.
     */
    public graph copy() {

        graph NewGraphCopy = new Graph_DS();
        HashMap<Integer, node_data> NewNodeMap = new HashMap<>();

        for (node_data OriginalNode : graph_algo.getV()) {
            if (!NewGraphCopy.getV().contains(OriginalNode)) {
                node_data CopiedNode = new NodeData(OriginalNode.getKey());
                CopiedNode.setTag(OriginalNode.getTag());
                CopiedNode.setInfo(OriginalNode.getInfo());
                NewGraphCopy.addNode(CopiedNode);
                NewNodeMap.put(OriginalNode.getKey(), CopiedNode);

                if (!OriginalNode.getNi().isEmpty()) {
                    for (node_data neighbour : OriginalNode.getNi()) {
                        if (NewNodeMap.get(neighbour.getKey()) == null) {
                            node_data CopiedNeighbour = new NodeData(neighbour.getKey());
                            CopiedNeighbour.setTag(neighbour.getTag());
                            CopiedNeighbour.setInfo(neighbour.getInfo());
                            NewGraphCopy.addNode(CopiedNeighbour);
                            NewNodeMap.put(OriginalNode.getKey(), CopiedNeighbour);
                            NewGraphCopy.connect(CopiedNode.getKey(), CopiedNeighbour.getKey());
                        }
                    }
                }
            }
        }
        //In order to complete the difference of the MC for the copied graph:
        int MCdifference = (graph_algo.getMC() - NewGraphCopy.getMC());
        for (int i = 0; i<MCdifference/2; i++) {
            node_data tempNode = new NodeData();
            NewGraphCopy.addNode(tempNode);
            NewGraphCopy.removeNode(tempNode.getKey());
        }
        return NewGraphCopy;
    }

    @Override
    /**
     * Using the BFS algorithm method, we check if the all the nodes in the graph are connected by using the tag
     * feature that every time a node is visited in the path, the node's tag changes to 1. (false = -1)
     * We do this using the Queue data structure, everytime a node is visited, it's neighbours enter the queue.
     * After we move on to a certain neighbour, the prior node exits the queue. We stop the path once the queue
     * is empty. At the end, if a nodes tag is still valued at -1, therefore the graph is not connected.
     */
    public boolean isConnected() {
        if (graph_algo.getV().isEmpty() || (graph_algo.nodeSize() == 1)) { return true; }
        if (graph_algo.edgeSize() < graph_algo.nodeSize()-1) { return false; }
        for (node_data node : graph_algo.getV())
            node.setTag(-1);
        node_data CurrentNode = null;
        Queue<node_data> NodeQueue = new LinkedList<>();
        for (node_data node : graph_algo.getV()) {
            CurrentNode = node;
            break;
        }
        NodeQueue.add(CurrentNode);
        CurrentNode.setTag(1);
        while (!NodeQueue.isEmpty()) {
            CurrentNode = NodeQueue.poll();

            for (node_data neighbour : CurrentNode.getNi()) {
                if (neighbour.getTag()==-1) {
                    neighbour.setTag(1);
                    NodeQueue.add(neighbour);
                }
            }
        }
        for (node_data check : graph_algo.getV()) {
            if (check.getTag() == -1) {
                return false;
            }
        }
        return true;
    }

    @Override
    /**
//     * Using the shortestPath function, we return the length of steps/edges the path goes through.
     * its length.
     * @param src - start node
     * @param dest - end (destination) node
     */
    public int shortestPathDist(int src, int dest) {

        if (src == dest) { return 0; }
        if (shortestPath(src, dest) == null) { return -1; }
        shortestPath(dest, src);
        return shortestPath(src, dest).size() - 1;

    }

    @Override
    /**
     * Using the BFS algorithm method, this function visits every node in the graph by layers starting at the src
     * and until it meets the dest. This function uses different types of data structures to complete the task, such as:
     * - A Boolean array to mark which node has been visited.
     * - An Integer array to mark each nodes parent in order to backtrack the shortest path from src to dest.
     * Once the Queues first in line is the dest, then a LinkedList creates the shortest path using help from
     * the ParentsList array.
     *
     * If there are no nodes in the graph, src or dest are without neighbours, or the path is disconnected between
     * them, the function returns null.
     * @param src - start node
     * @param dest - end (destination) node
     */
    public List<node_data> shortestPath(int src, int dest) {
        if (graph_algo.getV().isEmpty()) { return null; }
        if (graph_algo.getV(src).isEmpty() || graph_algo.getV(dest).isEmpty()) { return null; }
        for (node_data node : graph_algo.getV())
            node.setTag(-1);
        node_data CurrentNode;
        Queue<node_data> NodeQueue = new LinkedList<>();
        LinkedList<node_data> path = new LinkedList<>();
        HashMap<Integer, Integer> ParentsList = new HashMap<>();
        ParentsList.put(src,src);
        NodeQueue.add(graph_algo.getNode(src));

        while (!NodeQueue.isEmpty()) {
            CurrentNode = NodeQueue.poll();
            CurrentNode.setTag(1);

            if (CurrentNode.getKey() == dest) {
                int BacktrackPath = dest;

                while (ParentsList.get(BacktrackPath) != graph_algo.getNode(BacktrackPath).getKey()) {

                    path.add(graph_algo.getNode(BacktrackPath));
                    BacktrackPath = ParentsList.get(BacktrackPath);
                }
                path.add(graph_algo.getNode(BacktrackPath));
                Collections.reverse(path);
                return path;
            }
            for (node_data neighbour : CurrentNode.getNi()) {
                if (neighbour.getTag() == -1 && !(NodeQueue.contains(neighbour))) {
                    NodeQueue.add(neighbour);
                    ParentsList.put(neighbour.getKey(), CurrentNode.getKey());
                }
            }
        }
        return null;
    }


}

