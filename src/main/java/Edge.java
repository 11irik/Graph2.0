import java.io.Serializable;

public class Edge implements Serializable {
    private Node from;
    private Node to;
    private int weight;

    public Edge(){
        from = new Node();
        to = new Node();
        weight = 0;
    }

    public Edge(Node a, Node b, int w){
        from = a;
        to = b;
        weight = w;
    }

    public Edge(Node a, Node b){
        from = a;
        to = b;
        weight = 0;
    }
}
