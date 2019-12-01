package graph.adapters;

import graph.Graph;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class GraphAdapter {
    Graph graph;
    ArrayList<NodeAdapter> nodes;
    ArrayList<EdgeAdapter> edges;

    Random random = new Random();
    int nodeCoordMax = 500;

    public GraphAdapter() {
        graph = new Graph();
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public GraphAdapter(Graph graph) {
        this.graph = graph;
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        graph.getNodes().forEach(node -> nodes.add(new NodeAdapter(node, random.nextInt(nodeCoordMax), random.nextInt(nodeCoordMax))));
        graph.getEdges().forEach(edge -> edges.add(new EdgeAdapter(edge, nodes)));
    }

    public GraphAdapter(GraphAdapter graph) {
        this(graph.graph);
    }

    public ArrayList<EdgeAdapter> getEdges() {
        return edges;
    }

    public ArrayList<NodeAdapter> getNodes() {
        return nodes;
    }

    public NodeAdapter getNode(String key) {
        for (NodeAdapter node : nodes) {
            if (node.getKey() == key) {
                return node;
            }
        }
        return null;
    }

    public boolean addNode(String key) {
        if (graph.addNode(key)) {
            nodes.add(new NodeAdapter(graph.getNode(key), random.nextInt(nodeCoordMax), random.nextInt(nodeCoordMax)));
            return true;
        }
        else {
            return false;
        }
    }

    public void removeEdges() {
        edges = new ArrayList<>();
    }
    public void addEdge (EdgeAdapter edge) {
        edges.add(edge);
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void refresh() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        graph.getNodes().forEach(node -> nodes.add(new NodeAdapter(node, random.nextInt(nodeCoordMax), random.nextInt(nodeCoordMax))));
        graph.getEdges().forEach(edge -> edges.add(new EdgeAdapter(edge, nodes)));
    }
}
