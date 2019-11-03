package graph;

public class Edge<T> {
    private T from;
    private T to;
    private int weight;

    public Edge(){
        weight = 0;
    }

    public Edge(T a, T b, int w){
        from = a;
        to = b;
        weight = w;
    }

    public Edge(T a, T b){
        from = a;
        to = b;
        weight = 0;
    }
}
