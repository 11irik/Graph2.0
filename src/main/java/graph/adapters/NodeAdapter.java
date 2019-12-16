package graph.adapters;

import graph.Node;

import java.awt.*;

public class NodeAdapter {
    private static Color defaultColor = Color.PINK;
    private Node node;
    private Color color;
    private int x;
    private int y;

    public NodeAdapter(Node node, int x, int y) {
        this.node = node;
        this.color = defaultColor;
        this.x = x;
        this.y = y;
    }

    public NodeAdapter(NodeAdapter node) {
        this.node = node.node;
        this.color = node.color;
        this.x = node.x;
        this.y = node.y;
    }

    public void setDefaultColor() {
        color = defaultColor;
    }

    public Node getNode() {
        return node;
    }

    public String getKey() {
        return node.getKey();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
