package graph;

import java.io.Serializable;

public class Label {
    private double weight;
    private Boolean used;

    Label() {
        weight = 0;
        used = false;
    }

    Label(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
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
