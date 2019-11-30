package graph.adapters;

import graph.Node;

public class NodeAdapter {
    private Node node;
    private int x;
    private int y;

    public NodeAdapter(Node node, int x, int y) {
        this.node = node;
        this.x = x;
        this.y = y;
    }

    public NodeAdapter(NodeAdapter node) {
        this.node = node.node;
        this.x = node.x;
        this.y = node.y;
    }

    public Node getNode() {
        return node;
    }

    public String getKey() {
        return node.getKey();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
