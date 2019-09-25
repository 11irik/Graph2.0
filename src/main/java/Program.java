public class Program {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode("Hel");
        g.addNode("Llo");
        g.addEdge("Hel", "Llo");
        System.out.println(g.toString());
    }
}
