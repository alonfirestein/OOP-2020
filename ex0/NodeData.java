package ex0;

import java.util.*;

public class NodeData implements node_data {
    private int key , Tag;
    private static int IDcreator = 0;
    private String NodeInfo;
    private ArrayList<node_data> neighbours;

    /**
     * Basic Constructor with no parameter.
     */
    public NodeData() {
        key = IDcreator++;
        Tag = -1;
        NodeInfo = null;
        neighbours = new ArrayList<>();
    }

    /**
     * Basic constructor with one parameter.
     * @param nodeInfo
     */
    public NodeData(String nodeInfo) {
        this.NodeInfo = nodeInfo;
        key = IDcreator++;
        Tag = -1;
        neighbours = new ArrayList<>();
    }

    /**
     * Basic node copy constructor
     * @param node
     */
    public NodeData(node_data node) {
        key = node.getKey();
        Tag = node.getTag();
        NodeInfo = node.getInfo();
        neighbours.addAll(node.getNi());
    }
    /**
     * Basic constructor with one parameter.
     * @param newKey
     */
    public NodeData(int newKey) {
        key = newKey;
        Tag = -1;
        NodeInfo = null;
        neighbours = new ArrayList<>();
    }

    public String toString() {
        return "" + key;
    }

    @Override
    public int getKey() { return key;
    }

    @Override
    /**
     * Returning the ArrayList containing the neighbours of current node.
     */
    public Collection<node_data> getNi() {
        return neighbours;
    }

    @Override
    /**
     * Using a basic for-each loop to check if current node and key-node are neighbours.
     * @param key - the key of the second node to check if it's neighbours with our current node
     */
    public boolean hasNi(int key) {

        if (neighbours.isEmpty()) { return false; }

        for (node_data node : neighbours)
            if (node.getKey() == key) { return true; }

        return false;
    }

    @Override
    /**
     * If node in parameter is not in our current nodes neighbours list, therefore they are not neighbours.
     * Then add them as neighbours to both of their neighbours lists.
     * @param t - the node_data of a node to add as a neighbour to our current node
     */
    public void addNi(node_data t) {

        if (neighbours.contains(t) && t.getNi().contains(this)) { return; }
        if (neighbours.contains(t) && !t.getNi().contains(this)) { t.addNi(this); }

        neighbours.add(t);

        if (!t.getNi().contains(this))
            t.addNi(this);
    }

    @Override
    /**
     * When removing a node, we will remove all of the nodes appearances as a neighbour in all its adjacent nodes.
     * Using a simple for-each Loop.
     * @param node - the node_data of the node to remove
     */
    public void removeNode(node_data node) {
        for (node_data neighbour : node.getNi())
            neighbour.getNi().remove(node);
    }

    @Override
    public String getInfo() {
        return NodeInfo;
    }

    @Override
    public void setInfo(String s) {
        NodeInfo = s;

    }

    @Override
    public int getTag() {
        return Tag;
    }

    @Override
    public void setTag(int t) {
        Tag = t;
    }
}
