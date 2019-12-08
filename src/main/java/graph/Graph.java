package graph;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.*;
import java.util.*;


public class Graph {

    private Boolean oriented;
    private Boolean weighted;
    private HashMap<Node, HashMap<Node, Double>> adjacencyList;
    private ArrayList<Edge> edges;

    public Graph() {
        adjacencyList = new HashMap<>();
        oriented = false;
        weighted = false;
    }

    public Graph(Boolean Oriented, Boolean Weighted) {
        adjacencyList = new HashMap<>();
        this.oriented = Oriented;
        this.weighted = Weighted;
    }

    public Graph(Graph graph) {
        adjacencyList = new HashMap<>();
        oriented = graph.oriented;
        weighted = graph.weighted;

        for (Node key : graph.adjacencyList.keySet()) {
            this.adjacencyList.put(new Node(key), new HashMap<>());
        }
    }

    public Graph(Graph graph, boolean copyEdges) {
        adjacencyList = new HashMap<>();
        oriented = graph.oriented;
        weighted = graph.weighted;

        for (Node key : graph.adjacencyList.keySet()) {
            this.adjacencyList.put(key, new HashMap<>());
        }

        if (copyEdges) {
            for (Node node : this.adjacencyList.keySet()) {
                for (Node adjacencyNode : this.adjacencyList.keySet()) {
                    if (graph.hasEdge(node.getKey(), adjacencyNode.getKey())) {
                        if (weighted) {
                            addEdge(node, adjacencyNode, graph.adjacencyList.get(graph.getThisNode(node.getKey())).get(graph.getThisNode(adjacencyNode.getKey())));
                        } else {
                            addEdge(node, adjacencyNode);
                        }
                    }

                }
            }
        }
    }

    public HashMap<Node, HashMap<Node, Double>> getAdjacencies() {
        adjacencyList = new HashMap<>();
        for (Node key : adjacencyList.keySet()) {
            this.adjacencyList.put(new Node(key), new HashMap<>());
        }
        for (Node node : this.adjacencyList.keySet()) {
            for (Node adjacencyNode : this.adjacencyList.keySet()) {
                if (hasEdge(node.getKey(), adjacencyNode.getKey())) {
                    if (weighted) {
                        addEdge(node, adjacencyNode, adjacencyList.get(getThisNode(node.getKey())).get(getThisNode(adjacencyNode.getKey())));
                    } else {
                        addEdge(node, adjacencyNode);
                    }
                }

            }
        }
        return adjacencyList;
    }

    private boolean hasNode(Node node) {
        if (adjacencyList.keySet().contains(node)) {
            return true;
        } else {
            return false;
        }
    }

    private Node getThisNode(String key) {
        for (Node node : adjacencyList.keySet()) {
            if (node.getKey().equals(key)) {
                return node;
            }
        }
        return null;
    }

    private boolean deleteNode(Node node) {
        if (!adjacencyList.keySet().contains(node)) {
            return false;
        } else {
            adjacencyList.remove(node);
            for (Map.Entry<Node, HashMap<Node, Double>> entry : adjacencyList.entrySet()) {
                entry.getValue().remove(node);
            }
            return true;
        }
    }

    private boolean addEdge(Node startNode, Node endNode) {
        if (adjacencyList.get(startNode).containsKey(endNode)) {
            return false;
        } else {
            if (!oriented) {
                adjacencyList.get(startNode).put(endNode, (double) 0);
                adjacencyList.get(endNode).put(startNode, (double) 0);
                return true;
            } else {
                adjacencyList.get(startNode).put(endNode, (double) 0);
                return true;
            }
        }
    }

    private boolean addEdge(Node startNode, Node endNode, double weight) {
        if (adjacencyList.get(startNode).containsKey(endNode)) {
            return false;
        } else {
            if (!oriented) {
                adjacencyList.get(startNode).put(endNode, weight);
                adjacencyList.get(endNode).put(startNode, weight);
                return true;
            } else {
                adjacencyList.get(startNode).put(endNode, weight);
                return true;
            }
        }
    }

    private boolean deleteEdge(Node startNode, Node endNode) {
        if (!oriented) {
            if (adjacencyList.get(startNode).containsKey(endNode)) {
                adjacencyList.get(startNode).remove(endNode);
                adjacencyList.get(endNode).remove(startNode);
                return true;
            }
            return false;
        } else {
            if (adjacencyList.get(startNode).containsKey(endNode)) {
                adjacencyList.get(startNode).remove(endNode);
                return true;
            } else {
                return false;
            }
        }
    }

    private void setNodesUsedFalse() {
        for (Node n : adjacencyList.keySet()) {
            n.setUsed(false);
        }
    }

    //public

    public boolean hasEdge(String key1, String key2) {
        Node node1 = getThisNode(key1);
        Node node2 = getThisNode(key2);
        if (node1 == null || node2 == null) {
            throw new NullPointerException("There is no such node");
        }
        if (adjacencyList.get(node1).containsKey(node2)) {
            return true;
        } else {
            return false;
        }
    }

    public double getEdgeWeight(String key1, String key2) throws Exception {
        Node node1 = getThisNode(key1);
        Node node2 = getThisNode(key2);
        if (node1 == null || node2 == null) {
            throw new NullPointerException("There is no such node");
        }
        if (weighted) {
            if (adjacencyList.get(node1).containsKey(node2)) {
                return adjacencyList.get(node1).get(node2);
            } else {
                throw new NullPointerException("There is no such edge");
            }
        } else {
            throw new Exception("Graph is not weighted");
        }
    }

    public boolean getWeighted() {
        return weighted;
    }

    public Boolean getOriented() {
        return oriented;
    }

    public boolean addNode(String key) {
        if (getThisNode(key) == null) {
            adjacencyList.put(new Node(key), new HashMap<Node, Double>());
            return true;
        } else {
            return false;
        }
    }

    public Node getNode(String key) {
        Node node = getThisNode(key);
        if (node == null) {
            throw new NullPointerException("There is no such node");
        } else {
            return node;
        }
    }

    public boolean hasNode(String key) {
        if (getThisNode(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteNode(String key) {
        return deleteNode(getThisNode(key));
    }

    public boolean addEdge(String startKey, String endKey) {
        Node startNode = getThisNode(startKey);
        Node endNode = getThisNode(endKey);
        if (startNode == null || endNode == null) {
            throw new NullPointerException("There is no such node");
        } else {
            return addEdge(startNode, endNode);
        }
    }

    public boolean addEdge(String startKey, String endKey, double weight) throws Exception {
        Node startNode = getThisNode(startKey);
        Node endNode = getThisNode(endKey);
        if (!weighted) {
            throw new Exception("Graph is not weighted");
        }
        if (startNode == null || endNode == null) {
            throw new NullPointerException("There is no such node");
        } else {
            return addEdge(startNode, endNode, weight);
        }
    }

    public boolean deleteEdge(String startKey, String endKey) {
        Node startNode = getThisNode(startKey);
        Node endNode = getThisNode(endKey);
        if (startNode == null || endNode == null) {
            throw new NullPointerException("There is no such node");
        }
        return deleteEdge(startNode, endNode);
    }

    public Set<Node> getNodes() {
        return adjacencyList.keySet();
    }

//    public static void serialize(Graph graph, String filePath) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        try (Writer writer = new FileWriter(filePath)) {
//            gson.toJson(graph, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Graph deserialize(String filePath) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        Graph graph = new Graph();
//        try (Reader reader = new FileReader(filePath)) {
//            graph = gson.fromJson(reader, Graph.class);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            System.out.println("Exception was processed. Program continues");
//        }
//        return graph;
//    }

    public static void serialize(Graph graph, File file) {
        Kryo kryo = new Kryo();
        try (Output output = new Output(new FileOutputStream(file))) {
            kryo.writeClassAndObject(output, graph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Graph deserialize(File file) {
        Graph graph = new Graph();
        Kryo kryo = new Kryo();

        try (Input input = new Input(new FileInputStream(file))) {
            graph = (Graph) kryo.readClassAndObject(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }

    @Override
    public java.lang.String toString() {
        StringBuilder s = new StringBuilder();

        for (Map.Entry<Node, HashMap<Node, Double>> entry : adjacencyList.entrySet()) {
            s.append(entry.getKey().toString()).append(";");
            HashMap<Node, Double> value = entry.getValue();
            for (Map.Entry<Node, Double> edge : value.entrySet()) {
                Node adjacency = edge.getKey();
                s.append(adjacency.toString());
                if (weighted) {
                    s.append("(").append(edge.getValue().toString()).append(")");
                }
                s.append(";");
            }
            s.append("\n");
        }
        return s.toString();
    }
    //----------------------------TASKS--------------------------------

    //task Ia(6)
    public ArrayList<Node> getZeros() {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Map.Entry<Node, HashMap<Node, Double>> entry : adjacencyList.entrySet()) {
            if (entry.getValue().size() == 0) {
                nodes.add(new Node(entry.getKey()));
            }
        }
        return nodes;
    }

    public Graph removeIsolated() {
        Graph temp = new Graph(this, true);
        for (Node node : adjacencyList.keySet()) {
            if (adjacencyList.get(node).keySet().size() == 0) {
                temp.deleteNode(temp.getThisNode(node.getKey()));
            }
        }
        return temp;
    }

    //task Ia(17) Wrong
    public boolean doesCallingExist(String startKey, String endKey) {
        if (!(hasNode(startKey) && hasNode(endKey))) {
            throw new NullPointerException("There is no such node");
        } else {
            Set<Node> aSet = adjacencyList.get(getThisNode(startKey)).keySet();
            Set<Node> bSet = adjacencyList.get(getThisNode(endKey)).keySet();
            aSet.retainAll(bSet);
            return aSet.size() != 0;
        }
    }

    //task Ia(17)
    public boolean doesIssueExist(String startKey, String endKey) {
        if (!(hasNode(startKey) && hasNode(endKey))) {
            throw new NullPointerException("There is no such node");
        } else {
            for (Map.Entry<Node, HashMap<Node, Double>> entry : adjacencyList.entrySet()) {
                if (entry.getValue().containsKey(getThisNode(startKey)) && entry.getValue().containsKey(getThisNode(endKey))) {
                    return true;
                }
            }
            return false;
        }
    }

    //task Ib(8)
    public Graph deleteOddEdges() {
        Graph temp = new Graph(this, true);
        for (Map.Entry<Node, HashMap<Node, Double>> entry : temp.adjacencyList.entrySet()) {
            if (Integer.parseInt(entry.getKey().getKey()) % 2 == 0) {
                for (Node node : entry.getValue().keySet()) {
                    if (Integer.parseInt(node.getKey()) % 2 == 0) {
                        temp.deleteEdge(entry.getKey(), node);
                    }
                }
            }
        }
        return temp;
    }

    private void convertIntoEdges() {
        edges = new ArrayList<>();

        if (oriented) {
            for (Map.Entry<Node, HashMap<Node, Double>> nodeStart : adjacencyList.entrySet()) {
                for (Map.Entry<Node, Double> nodeEnd : nodeStart.getValue().entrySet()) {
                    if (weighted) {
                        edges.add(new Edge(nodeStart.getKey(), nodeEnd.getKey(), nodeEnd.getValue(), oriented));
                    } else {
                        edges.add(new Edge(nodeStart.getKey(), nodeEnd.getKey(), oriented));
                    }
                }
            }
        } else {
            ArrayList<Node> usedNodes = new ArrayList<>();
            for (Map.Entry<Node, HashMap<Node, Double>> from : adjacencyList.entrySet()) {
                for (Map.Entry<Node, Double> to : from.getValue().entrySet()) {
                    if (!usedNodes.contains(to.getKey())) {
                        if (weighted) {
                            edges.add(new Edge(from.getKey(), to.getKey(), to.getValue(), oriented));
                        } else {
                            edges.add(new Edge(from.getKey(), to.getKey(), oriented));
                        }
                    }
                }
                usedNodes.add(from.getKey());
            }
        }
    }

    public ArrayList<Edge> getEdges() {
        convertIntoEdges();
        return edges;
    }

    private void dfs(Node node, Graph graph, Queue<Node> nodes) {
        node.setUsed(true);
        for (Node n : adjacencyList.get(node).keySet()) {
            if (!n.getUsed()) {
                graph.addEdge(graph.getThisNode(node.getKey()), graph.getThisNode(n.getKey()));
                nodes.add(n);
                dfs(n, graph,  nodes);
            }
        }
    }

    public Queue<Node> getSpanningComponent(Node node) {
        Graph spanningTree = new Graph(this, false);
        Queue<Node> nodes = new LinkedList<>();
        if (!oriented) {
            dfs(node, spanningTree, nodes);
        } else {
            for (Node n : adjacencyList.keySet()) {
                dfs(n, spanningTree, nodes);
            }
        }
        if (spanningTree.adjacencyList.get(spanningTree.getThisNode(node.getKey())).keySet().size() == 0) {
            spanningTree = spanningTree.removeIsolated();
            spanningTree.addNode(node.getKey());
        } else {
            spanningTree = spanningTree.removeIsolated();
        }
        //return spanningTree;
        return nodes;
    }

    //task II(23)
    private void dfsCycle(Node node, Stack<Node> nodes, int timer) {
        node.setTimeIn(++timer);
        node.setUsed(true);
        for (Node n : adjacencyList.get(node).keySet()) {
            if (!n.getUsed()) {
                nodes.add(n);
                dfsCycle(n, nodes, timer);
            } else {
                if (n.getTimeIn() - node.getTimeIn() < -1) {
                    nodes.add(n);
                    System.out.println(nodes);
                    nodes.pop();
                }
            }
        }
        nodes.pop();
    }

    public void getFundamentalSetOfCycles(String key) {
        Graph temp = new Graph(this, false);
        Node node = getThisNode(key);
        Stack<Node> nodes = new Stack<>();
        setNodesUsedFalse();
        nodes.add(node);
        int timer = 0;
        dfsCycle(node, nodes, timer);
    }

    //Task II(35)
    private Node getNearestNeighborFromUnused(Node a) {
        Node temp = null;
        double minimalLength = Double.POSITIVE_INFINITY;
        for (Node node : adjacencyList.get(a).keySet()) {
            if (!node.getUsed()) {
                double length = adjacencyList.get(a).get(node);
                if (length < minimalLength) {
                    minimalLength = length;
                    temp = node;
                }
            }
        }
        return temp;
    }

    private HashMap<Node, Double> dijkstra(Node node) {
        HashSet<Node> unusedNodes = new HashSet<>(adjacencyList.keySet());
        HashMap<Node, Double> distances = new HashMap<>();
        double inf = Double.POSITIVE_INFINITY;
        for (Node adj : adjacencyList.keySet()) {
            distances.put(adj, inf);
        }
        distances.put(node, 0.0);

        while (unusedNodes.size() != 0) {
            Node minUnusedNode = null;
            Double minMark = inf;

            for (Node unusedNode : unusedNodes) {
                double unusedMinMark = distances.get(unusedNode);
                if (unusedMinMark <= minMark) {
                    minMark = unusedMinMark;
                    minUnusedNode = unusedNode;
                }
            }

            if (minUnusedNode != null) {
                setNodesUsedFalse();
                for (int i = 0; i < adjacencyList.get(minUnusedNode).keySet().size(); ++i) {
                    Node nearestNeighbor = getNearestNeighborFromUnused(minUnusedNode);
                    double mark = distances.get(minUnusedNode) + adjacencyList.get(minUnusedNode).get(nearestNeighbor);
                    if (distances.get(nearestNeighbor) > mark) {
                        distances.put(nearestNeighbor, mark);
                    }
                    nearestNeighbor.setUsed(true);
                }
                unusedNodes.remove(minUnusedNode);
            }
        }

        return distances;
    }

    public HashMap<Node, HashMap<Node, Double>> distancesDijkstra() {
        Graph temp = new Graph(this, true);
        HashMap<Node, HashMap<Node, Double>> lengths = new HashMap<>();

        for (Node node : temp.adjacencyList.keySet()) {
            lengths.put(node, temp.dijkstra(node));
        }

        return lengths;
    }

    private HashMap<Node, Double> bfs(Node start) {
        HashMap<Node, Double> distances = new HashMap<>();
        for (Node node : adjacencyList.keySet()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);

        HashMap<Node, Node> parents = new HashMap<>();
        for (Node node : adjacencyList.keySet()) {
            parents.put(node, null);
        }

        Deque<Node> deque = new LinkedList<>();
        deque.addFirst(start);

        while (!deque.isEmpty()) {
            Node begin;
            double length;
            begin = deque.getFirst();
            deque.removeFirst();
            for (Node end : adjacencyList.get(begin).keySet()) {
                length = adjacencyList.get(begin).get(end);
                if (distances.get(end) == Double.POSITIVE_INFINITY) {
                    distances.put(end, distances.get(begin) + length);
                    parents.put(end, begin);

                    if (length == 0.0) {
                        deque.addFirst(end);
                    } else {
                        deque.addLast(end);
                    }
                }
            }
        }

        return distances;
    }

    public HashMap<Node, HashMap<Node, Double>> minimalDistances() {
        HashMap<Node, HashMap<Node, Double>> distances = new HashMap<>();
        for (Node node : adjacencyList.keySet()) {
            distances.put(node, bfs(node));
        }
        return distances;
    }

    //Task III(B)
    private Node getNearestNeighbor(Node a) {
        Node temp = null;
        double minimalLength = Double.POSITIVE_INFINITY;
        for (Node node : adjacencyList.get(a).keySet()) {
            double length = adjacencyList.get(a).get(node);
            if (length < minimalLength) {
                minimalLength = length;
                temp = node;
            }
        }
        return temp;
    }

//    public Graph boruvka() {
//        Graph tree = new Graph(this);
//        ArrayList<Graph> components;
//        do {
//            components = new ArrayList<>();
//            tree.setNodesUsedFalse();
//            for (Node node : tree.adjacencyList.keySet()) {
//                if (!node.getUsed()) {
//                    components.add(tree.getSpanningComponent(node));
//                    node.setUsed(true);
//                }
//            }
//
//            for (Graph component : components) {
//                setNodesUsedFalse();
//                for (Node node : component.adjacencyList.keySet()) {
//                    this.getThisNode(node.getKey()).setUsed(true);
//                }
//
//                Node start = null;
//                Node end = null;
//                double minimalLength = Double.POSITIVE_INFINITY;
//
//                for (Node node : component.adjacencyList.keySet()) {
//                    Node tempStart = getThisNode(node.getKey());
//                    Node tempEnd = getNearestNeighborFromUnused(getThisNode(node.getKey()));
//                    double tempLength = adjacencyList.get(tempStart).get(tempEnd);
//                    if (minimalLength > tempLength) {
//                        minimalLength = tempLength;
//                        start = tempStart;
//                        end = tempEnd;
//                    }
//                }
//
//                tree.addEdge(tree.getThisNode(start.getKey()), tree.getThisNode(end.getKey()),
//                        minimalLength);
//            }
//
//        } while (tree.getEdges().size() != tree.adjacencyList.keySet().size() - 1);
//        return tree;
//    }


    private Graph revertEdges() {
        Graph graph = new Graph(this, false);
        for (Node node : adjacencyList.keySet()) {
            for (Node end : adjacencyList.get(node).keySet()) {
                graph.addEdge(end, node, adjacencyList.get(node).get(end));
            }
        }
        return graph;
    }

    public double getEccentricity() {
        Graph temp;
        if (oriented) {
            temp = revertEdges();
        } else {
            temp = this;
        }
        double radius = Double.POSITIVE_INFINITY;
        for (Node node : temp.adjacencyList.keySet()) {
            double eccentricity = Double.NEGATIVE_INFINITY;
            for (Double distance : temp.dijkstra(node).values()) {
                if (eccentricity < distance) {
                    eccentricity = distance;
                }
            }
            if (eccentricity < radius && eccentricity != Double.POSITIVE_INFINITY) {
                radius = eccentricity;
            }
        }
        return radius;
    }

    public HashMap<Node, Double> ford(String nodeStart, String nodeEnd) {
        Node begin = this.getNode(nodeStart);
        Node end = this.getNode(nodeEnd);
        HashMap<Node, Double> distances = new HashMap<>();
        double inf = Double.POSITIVE_INFINITY;
        for (Node adj : adjacencyList.keySet()) {
            distances.put(adj, inf);
        }
        distances.put(begin, 0.0);


        for (int i = 1; i < adjacencyList.keySet().size() - 1; ++i) {
            for (Node u : adjacencyList.keySet()) {
                for (Node v : adjacencyList.get(u).keySet()) {
                    double distance = distances.get(u) + adjacencyList.get(u).get(v);
                    if (v == end) {
                        System.out.println(distance);
                    }
                    if (distances.get(v) > distance) {
                        distances.put(v, distance);
                    }
                }
            }
        }

        return distances;
    }


    public void floyd() {
        HashMap<Node, HashMap<Node, Double>> distances = new HashMap<>();
        double inf = Double.POSITIVE_INFINITY;

        for (Node node1 : adjacencyList.keySet()) {
            HashMap<Node, Double> map = new HashMap<>();
            for (Node node2 : adjacencyList.keySet()) {
                map.put(node2, Double.POSITIVE_INFINITY);
            }
            distances.put(node1, map);
            distances.get(node1).put(node1, 0.0);
        }

        for (Node st : adjacencyList.keySet()) {
            for (Node nd : adjacencyList.get(st).keySet()) {
                distances.get(st).put(nd, adjacencyList.get(st).get(nd));
            }
        }

        for (Node k : adjacencyList.keySet()) {
            for (Node i : adjacencyList.keySet()) {
                for (Node j : adjacencyList.keySet()) {
                        double min = Math.min(distances.get(i).get(j), distances.get(i).get(k) + distances.get(k).get(j));
                        distances.get(i).put(j, min);
                }
            }
        }

        for (Node k : adjacencyList.keySet()) {
            for (Node i : adjacencyList.keySet()) {
                for (Node j : adjacencyList.keySet()) {
                    if (distances.get(i).get(k) < inf && distances.get(k).get(j) < inf && distances.get(j).get(j) < 0) {
                        //double min = Math.min(distances.get(i).get(j), distances.get(i).get(k) + distances.get(k).get(j));
                        distances.get(i).put(j, Double.NEGATIVE_INFINITY);
                    }
                }
            }
        }

        for (Node k : adjacencyList.keySet()) {
            System.out.println(k + ": " + distances.get(k));
        }
    }


//    Node t;
//    double maxFlow;
//
//    void dfs(Node v, int curflow) {
//        if (t == v) {
//
//        }
//        v.setUsed(true);
//
//        for
//
//    }
//
//    public void findFlow() {
//        convertIntoEdges();
//
//        int maxflow = 0;
//    }

}

