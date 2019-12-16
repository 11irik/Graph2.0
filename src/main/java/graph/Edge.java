package graph;

import java.util.Objects;

public class Edge {
    private Node start;
    private Node end;
    private boolean oriented;
    private boolean weighted;
    private double weight;
    private double flow;

    public Edge(Node startNode, Node endNode, double weight, boolean oriented){
        start = startNode;
        end = endNode;
        this.oriented = oriented;
        weighted = true;
        this.weight = weight;
        flow = 0;
    }

    public Edge(Node startNode, Node endNode, boolean oriented){
        start = startNode;
        end = endNode;
        this.oriented = oriented;
        weighted = false;
        weight = 0;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public double getWeight() throws Exception {
        if (weighted) {
            return weight;
        }
        else {
            throw new Exception("This edge is not weighted");
        }
    }

    public boolean isWeighted() {
        return weighted;
    }

    public boolean isOriented() {
        return oriented;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public double getCurrentFlow() {
        return weight - flow;
    }

    public void setStart(Node node) {
        this.start = node;
    }

    public void setEnd(Node node) {
        this.end = node;
    }

    public void setWeight(double weight) throws Exception {
        if (weighted) {
            this.weight = weight;
        }
        else {
            throw new Exception("This edge is not weighted");
        }
    }

    //todo check this moment
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        if (oriented) {
            return weighted == edge.weighted &&
                    Double.compare(edge.weight, weight) == 0 &&
                    start.getKey().equals(edge.start.getKey()) &&
                    end.getKey().equals(edge.end.getKey());
        }
        else {
            return weighted == edge.weighted &&
                    Double.compare(edge.weight, weight) == 0 &&
                    (start.getKey().equals(edge.start.getKey()) && end.getKey().equals(edge.end.getKey()) ||
                            end.getKey().equals(edge.start.getKey()) && start.getKey().equals(edge.end.getKey()));        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, weighted, weight);
    }

    @Override
    public java.lang.String toString() {
        String s = "";
        s += start + ";" + end + ";";
        if (weighted) {
            s += weight + ";";
        }
        return s;
    }
}
