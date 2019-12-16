package graph.adapters;

import graph.Edge;
import graph.Graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class GraphAdapter {
    private static Color defaultColor = Color.PINK;
    private static Random random = new Random();

    private Graph graph;
    private ArrayList<NodeAdapter> nodes;
    private ArrayList<EdgeAdapter> edges;

    private int nodeCoordMax = 500;

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



    public void setNodeCoordMax(int nodeCoordMax) {
        this.nodeCoordMax = nodeCoordMax;
    }

    public GraphAdapter(GraphAdapter graph) {
        this(graph.graph);
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        graph.getGraph().getEdges().forEach(edge -> edges.add(new EdgeAdapter(edge, nodes)));
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

    public void addEdge(NodeAdapter a, NodeAdapter b, double weight) {
        try {
            graph.addEdge(a.getKey(), b.getKey(), weight);
            edges.add(new EdgeAdapter(a, b, weight, graph.getOriented()));
            a.setColor(defaultColor);
            b.setColor(defaultColor);
        } catch (Exception e) {

        }
    }

    public void addEdge(NodeAdapter a, NodeAdapter b) {
        graph.addEdge(a.getKey(), b.getKey());
        edges.add(new EdgeAdapter(a, b, graph.getWeighted(), graph.getOriented()));
        a.setColor(defaultColor);
        b.setColor(defaultColor);
    }

    public void deleteEdge(NodeAdapter a, NodeAdapter b) {
        graph.deleteEdge(a.getKey(), b.getKey());

        edges = new ArrayList<>();
        graph.getEdges().forEach(edge -> edges.add(new EdgeAdapter(edge, nodes)));
        a.setColor(defaultColor);
        b.setColor(defaultColor);
    }

    public void deleteNode(NodeAdapter a) {
        graph.deleteNode(a.getKey());
        nodes.remove(a);
        edges = new ArrayList<>();
        graph.getEdges().forEach(edge -> edges.add(new EdgeAdapter(edge, nodes)));
        a.setColor(defaultColor);
    }

    public boolean addNode(String key, int x, int y) {
        if (graph.addNode(key)) {
            nodes.add(new NodeAdapter(graph.getNode(key), x, y));
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
        return this.graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void rebuild() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        graph.getNodes().forEach(node -> nodes.add(new NodeAdapter(node, random.nextInt(nodeCoordMax), random.nextInt(nodeCoordMax))));
        graph.getEdges().forEach(edge -> edges.add(new EdgeAdapter(edge, nodes)));
    }
}
