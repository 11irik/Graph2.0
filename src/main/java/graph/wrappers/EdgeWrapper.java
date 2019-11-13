package graph.wrappers;

import graph.Edge;

import java.util.ArrayList;

public class EdgeWrapper {
    NodeWrapper start = null;
    NodeWrapper end = null;
    double weight;

    public EdgeWrapper(Edge edge, ArrayList<NodeWrapper> nodes) {
        weight = edge.getWeight();
        for (NodeWrapper node : nodes) {
            if (node.getNode() == edge.getStart()) {
                start = node;
            }
            if (node.getNode() == edge.getEnd()) {
                end = node;
            }
            if (start != null && end  != null) {
                break;
            }
        }
        if (start == null || end == null) {
            //todo exception
            throw new NullPointerException();
        }
    }

    public NodeWrapper getStart() {
        return start;
    }

    public NodeWrapper getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }
}
