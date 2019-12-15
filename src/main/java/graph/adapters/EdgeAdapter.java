package graph.adapters;

import graph.Edge;

import java.util.ArrayList;

public class EdgeAdapter {
    private NodeAdapter start = null;
    private NodeAdapter end = null;
    private double weight;
    private boolean weighted;
    private boolean oriented;

    //todo check this kostil
    public EdgeAdapter(Edge edge, ArrayList<NodeAdapter> nodes) {

        if (edge.isWeighted()) {
            try {
                weighted = true;
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
        oriented = edge.isOriented();
        if (start == null || end == null) {
            throw new NullPointerException();
        }
    }

    public EdgeAdapter(NodeAdapter start, NodeAdapter end, boolean weighted, boolean oriented) {
        this.start = start;
        this.end = end;
        this.weighted = weighted;
        this.oriented = oriented;
    }

    public EdgeAdapter(NodeAdapter start, NodeAdapter end, double weight, boolean oriented) {
        this.start = start;
        this.end = end;
        this.weighted = true;
        this.weight = weight;
        this.oriented = oriented;
    }

    public boolean isOriented() {
        return oriented;
    }

    public boolean isWeighted() {
        return weighted;
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
