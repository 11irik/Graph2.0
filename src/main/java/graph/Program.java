package graph;

import java.io.FileNotFoundException;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
//        ConsoleGInterface gInterface = new ConsoleGInterface();
//        gInterface.help();
//        gInterface.c();
        Graph<String> g = new Graph<>();
        g.addNode("Hello");
        g.addNode("World");
        g.addEdge("Hello", "World");
        System.out.println(g.toString());
    }
}
