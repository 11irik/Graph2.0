package graph;

import java.util.Random;

public class Node {

    private String key;
    private boolean used;
    private int x;
    private int y;


    public Node(String key) {
        this.key = key;
        used = false;
        Random random = new Random();
        x = random.nextInt(100000);
        y = random.nextInt(100000);
    }

    public Node(Node node) {
        this.key = node.key;
        this.used = node.used;
    }

    public String getKey() {
        return key;
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

    @Override
    public String toString() {
        return "" + key;
    }
}
