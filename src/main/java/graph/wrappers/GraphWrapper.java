package graph.wrappers;

import graph.Graph;

import java.util.ArrayList;
import java.util.Random;

public class GraphWrapper {
    Graph graph;
    ArrayList<NodeWrapper> nodes;
    ArrayList<EdgeWrapper> edges;

    Random random = new Random();
    int nodeCoordMax = 100000;

    public GraphWrapper(Graph graph) {
        this.graph = graph;
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        graph.getNodes().forEach(node -> nodes.add(new NodeWrapper(node, random.nextInt(nodeCoordMax), random.nextInt(nodeCoordMax))));
        graph.convertIntoEdges().forEach(edge -> edges.add(new EdgeWrapper(edge, nodes)));
    }

    public ArrayList<EdgeWrapper> getEdges() {
        return edges;
    }

    public ArrayList<NodeWrapper> getNodes() {
        return nodes;
    }

    public boolean addNode(String key) {
        if (graph.addNode(key)) {
            nodes.add(new NodeWrapper(graph.getNode(key), random.nextInt(nodeCoordMax), random.nextInt(nodeCoordMax)));
            return true;
        }
        else {
            return false;
        }
    }
}
