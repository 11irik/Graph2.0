package graph.adapters;

import graph.Edge;

import java.util.ArrayList;

public class EdgeAdapter {
    NodeAdapter start = null;
    NodeAdapter end = null;
    double weight;

    //todo check this kostil
    public EdgeAdapter(Edge edge, ArrayList<NodeAdapter> nodes) {

        if (edge.isWeighted()) {
            try {
                weight = edge.getWeight();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (NodeAdapter node : nodes) {
            if (node.getNode().getKey() == edge.getStart().getKey()) {
                start = node;
            }
            if (node.getNode().getKey() == edge.getEnd().getKey()) {
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

    public NodeAdapter getStart() {
        return start;
    }

    public NodeAdapter getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }
}
