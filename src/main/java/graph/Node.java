package graph;

public class Node {

    private String key;
    private boolean used;

    public Node(String key) {
        this.key = key;
        used = false;
    }

    public Node(Node node) {
        this.key = node.key;
        this.used = node.used;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "" + key;
    }
}
