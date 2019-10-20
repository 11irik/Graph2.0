package graph.model;

import java.io.Serializable;

public class Label {
    private int weitgh;
    private Boolean used;

    Label() {
        weitgh = 0;
        used = false;
    }

    public int getWeitgh() {
        return this.weitgh;
    }

    public void setWeitgh(int weitgh) {
        this.weitgh = weitgh;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return weitgh + ";" + used + ";";
    }
}
