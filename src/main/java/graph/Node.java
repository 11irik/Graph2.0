package graph;

import java.util.Objects;
import java.util.Random;

public class Node {

    private String key;
    private boolean used;
    private int timeIn;

    public Node() {

    }

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

    public void setKey(String key) {
        this.key = key;
    }

    public void setTimeIn(int timeIn) {
        this.timeIn = timeIn;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean getUsed() {
        return used;
    }

    @Override
    public String toString() {
        return "" + key;
    }
}
