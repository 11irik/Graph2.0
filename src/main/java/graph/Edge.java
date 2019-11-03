package graph;

public class Edge {
    private String from;
    private String to;
    private double weight;

    public Edge(){
        weight = 0;;
    }

    public Edge(String a, String b, double w){
        from = a;
        to = b;
        weight = w;
    }

    public Edge(String a, String b){
        from = a;
        to = b;
        weight = 0;
    }

    @Override
    public java.lang.String toString() {
        return from + ";" + to + ";" + weight + ";";
    }
}
