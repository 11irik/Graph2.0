package graph;

public class Edge {
    private Node start;
    private Node end;
    private double weight;

    public Edge(){
        weight = 0;
    }

    public Edge(Node startNode, Node endNode, double weight){
        start = startNode;
        end = endNode;
        this.weight = weight;
    }

    public Edge(Node startNode, Node endNode){
        start = startNode;
        end = endNode;
        weight = 0;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }

    public void setStart(Node node) {
        this.start = node;
    }

    public void setEnd(Node node) {
        this.end = node;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public java.lang.String toString() {
        return "" + start + ";" + end + ";" + weight + ";";
    }
}
