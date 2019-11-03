package graph;

import java.io.Serializable;

public class Label {
    private int weight;
    private Boolean used;

    Label() {
        weight = 0;
        used = false;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeitgh(int weight) {
        this.weight = weight;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return weight + ";" + used + ";";
    }
}
