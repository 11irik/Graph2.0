package graph;

import java.util.*;

public class Graph {
    private static int count = 0;
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
            this.adjacencyList.put(new Node(key), new HashMap<>());
        }

        //todo Exceptions
        if (copyEdges) {
            for (Node key : this.adjacencyList.keySet()) {
                Node graphKey = graph.getThisNode(key.getKey());
                for (Node value : this.adjacencyList.keySet()) {
                    Node graphValue = graph.getThisNode(value.getKey());
                    if (graph.adjacencyList.get(graphKey).containsKey(graphValue)) {
                        if (weighted) {
                            addEdge(key, value, graph.adjacencyList.get(graphKey).get(graphValue));
                        } else {
                            addEdge(key, value);
                        }
                    }

                }
            }
        }
    }

    public boolean getWeighted() {
        return weighted;
    }

    public boolean addNode(String key) {
        if (getNode(key) == null) {
            adjacencyList.put(new Node(key), new HashMap<Node, Double>());
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

    public Node getNode(String key) {
        for (Node node : adjacencyList.keySet()) {
            if (node.getKey().equals(key)) {
                return new Node(node);
            }
        }
        return null;
    }

    private boolean hasNode(Node node) {
        if (adjacencyList.keySet().contains(node)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasNode(String key) {
        for (Node node : adjacencyList.keySet()) {
            if (node.getKey().equals(key)) {
                return true;
            }
        }
        return false;
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

    public boolean deleteNode(String key) {
        return deleteNode(getThisNode(key));
    }

    private boolean addEdge(Node startNode, Node endNode) {
        if (!(hasNode(startNode) && hasNode(endNode))) {
            throw new NullPointerException("There is no such node");
        } else {
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
    }

    public boolean addEdge(String startKey, String endKey) {
        return addEdge(getThisNode(startKey), getThisNode(endKey));
    }

    private boolean addEdge(Node startNode, Node endNode, double weight) {
        if (!weighted) {
            throw new NullPointerException("Graph is not weighted");
        } else {
            if (!(hasNode(startNode) && hasNode(endNode))) {
                throw new NullPointerException("There is no such node");
            } else {
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
        }
    }

    public boolean addEdge(String startKey, String endKey, double weight) {
        return addEdge(getThisNode(startKey), getThisNode(endKey), weight);
    }

    private boolean deleteEdge(Node startNode, Node endNode) {
        if (!(hasNode(startNode) && hasNode(endNode))) {
            throw new NullPointerException("There is no such node");
        } else {
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
    }

    public boolean deleteEdge(String startKey, String endKey) {
        return deleteEdge(getThisNode(startKey), getThisNode(endKey));
    }

    public Set<Node> getNodes() {
        return adjacencyList.keySet();
    }

    private void setNodesUsedFalse() {
        for (Node n : adjacencyList.keySet()) {
            n.setUsed(false);
        }
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
            throw new NullPointerException("There is no such a node");
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
            throw new NullPointerException("There is no such a node");
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
    //Todo remove exception
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
            for (Map.Entry<Node, HashMap<Node, Double>> from : adjacencyList.entrySet()) {
                for (Map.Entry<Node, Double> to : from.getValue().entrySet()) {
                    if (weighted) {
                        edges.add(new Edge(from.getKey(), to.getKey(), to.getValue(), oriented));
                    } else {
                        edges.add(new Edge(from.getKey(), to.getKey(), oriented));
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
        ArrayList<Edge> edges = new ArrayList<>();
        if (weighted) {
            for (Edge edge : this.edges) {
                edges.add(new Edge(new Node(edge.getStart()), new Node(edge.getEnd()), edge.getWeight(), oriented));
            }
        } else {
            for (Edge edge : this.edges) {
                edges.add(new Edge(new Node(edge.getStart()), new Node(edge.getEnd()), oriented));
            }
        }
        return edges;
    }

    private void dfs(Node node, Graph graph) {
        node.setUsed(true);
        for (Node n : adjacencyList.get(node).keySet()) {
            if (!n.getUsed()) {
                graph.addEdge(graph.getThisNode(node.getKey()), graph.getThisNode(n.getKey()));
                dfs(n, graph);
            }
        }
    }

    private void dfsCycle(Node node, ArrayList<Node> nodes, int timer) {
        nodes.add(node);
        node.setUsed(true);
        node.setTimeIn(timer++);
        for (Node n : adjacencyList.get(node).keySet()) {
            if (!n.getUsed()) {
                dfsCycle(n, nodes, timer);
            } else if (node.getTimeIn() - n.getTimeIn() > 1) {
                nodes.add(n);
                break;
            }
        }
    }

    private Graph getSpanningComponent(Node node) {
        Graph spanningTree = new Graph(this, false);
        if (!oriented) {
            dfs(node, spanningTree);
        } else {
            for (Node n : adjacencyList.keySet()) {
                dfs(n, spanningTree);
            }
        }
        if (spanningTree.adjacencyList.get(spanningTree.getThisNode(node.getKey())).keySet().size() == 0) {
            spanningTree = spanningTree.removeIsolated();
            spanningTree.addNode(node.getKey());
        } else {
            spanningTree = spanningTree.removeIsolated();
        }
        return spanningTree;
    }

    private ArrayList<Node> getCycle(Node node) {
        ArrayList<Node> cycle = new ArrayList<>();
        int timer = 0;
        dfsCycle(node, cycle, timer);

        if (new HashSet(cycle).size() < cycle.size()) {
            return cycle;
        } else {
            return null;
        }
    }

    //task II(23)
    //todo bug
    public ArrayList<ArrayList<Node>> getFundamentalSetOfCycles() {
        Graph spanningTree = this.getSpanningComponent((Node) adjacencyList.keySet().toArray()[0]);

        spanningTree.convertIntoEdges();
        HashSet<Edge> spanningEdgesSet = new HashSet<>(spanningTree.edges);

        this.convertIntoEdges();
        HashSet<Edge> edgesSet = new HashSet<>(this.edges);

        HashSet<Edge> cycleEdges = new HashSet<>();
        for (Edge edge : edgesSet) {
            boolean equal = false;
            for (Edge spanningEdge : spanningEdgesSet) {
                if (edge.equals(spanningEdge)) {
                    equal = true;
                    break;
                }
            }
            if (!equal) {
                cycleEdges.add(edge);
            }
        }


        ArrayList<ArrayList<Node>> cycles = new ArrayList<>();
        for (Edge edge : cycleEdges) {
            Graph temp = new Graph(spanningTree, true);
            temp.addEdge(edge.getStart().getKey(), edge.getEnd().getKey());
            temp.setNodesUsedFalse();
            if (!oriented) {
                setNodesUsedFalse();
                cycles.add(temp.getCycle((Node) temp.adjacencyList.keySet().toArray()[0]));
            } else {
                setNodesUsedFalse();
                for (Node node : temp.adjacencyList.keySet()) {
                    ArrayList<Node> cycle = temp.getCycle(node);
                    if (cycle != null) {
                        cycles.add(cycle);
                    }
                }
            }
        }
        return cycles;
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
        //ArrayList<ArrayList<Node>> route = new ArrayList<>();

        HashSet<Node> unusedNodes = new HashSet<>(adjacencyList.keySet());
        HashMap<Node, Double> lengths = new HashMap<>();
        double inf = Double.POSITIVE_INFINITY;
        for (Node adj : adjacencyList.keySet()) {
            lengths.put(adj, inf);
        }
        lengths.put(node, 0.0);

        while (unusedNodes.size() != 0) {
            Node minUnusedNode = null;
            Double minMark = inf;

            for (Node unusedNode : unusedNodes) {
                double unusedMinMark = lengths.get(unusedNode);
                if (unusedMinMark <= minMark) {
                    minMark = unusedMinMark;
                    minUnusedNode = unusedNode;
                }
            }

            if (minUnusedNode != null) {
                setNodesUsedFalse();
                for (int i = 0; i < adjacencyList.get(minUnusedNode).keySet().size(); ++i) {
                    Node nearestNeighbor = getNearestNeighborFromUnused(minUnusedNode);
                    double mark = lengths.get(minUnusedNode) + adjacencyList.get(minUnusedNode).get(nearestNeighbor);
                    if (lengths.get(nearestNeighbor) > mark) {
                        lengths.put(nearestNeighbor, mark);
                    }
                    nearestNeighbor.setUsed(true);
                }
                unusedNodes.remove(minUnusedNode);
            }
        }

        return lengths;
    }

    public HashMap<Node, HashMap<Node, Double>> minimalRouteLengths() {
        Graph temp = new Graph(this, true);
        HashMap<Node, HashMap<Node, Double>> lengths = new HashMap<>();

        for (Node node : temp.adjacencyList.keySet()) {
            lengths.put(node, temp.dijkstra(node));
        }

        return lengths;
    }


    private void bfs(Node node, HashMap<Node, HashMap<Node, Double>> lengths) {
        if (!node.getUsed()) {
            node.setUsed(true);
            for (Node adj : adjacencyList.get(node).keySet()) {
                if (!adj.getUsed()) {
                    lengths.get(node).put(adj, adjacencyList.get(node).get(adj));
                    bfs(adj, lengths);
                }

            }
        }
        else {

        }
    }
    public HashMap<Node, HashMap<Node, Double>> minRouteLength() {
        Graph temp = new Graph(this, true);
        HashMap<Node, HashMap<Node, Double>> lengths = new HashMap<>();
        for (Node node : temp.adjacencyList.keySet()) {
            lengths.put(node, new HashMap<>());
        }

        temp.bfs((Node) temp.adjacencyList.keySet().toArray()[0], lengths);
        System.out.println(lengths);

        return null;
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

    public Graph boruvka() {
        Graph tree = new Graph(this);
        ArrayList<Graph> components;
        do {
            components = new ArrayList<>();
            tree.setNodesUsedFalse();
            for (Node node : tree.adjacencyList.keySet()) {
                if (!node.getUsed()) {
                    components.add(tree.getSpanningComponent(node));
                    node.setUsed(true);
                }
            }

            for (Graph component : components) {
                setNodesUsedFalse();
                for (Node node : component.adjacencyList.keySet()) {
                    this.getThisNode(node.getKey()).setUsed(true);
                }

                Node start = null;
                Node end = null;
                double minimalLength = Double.POSITIVE_INFINITY;

                for (Node node : component.adjacencyList.keySet()) {
                    Node tempStart = getThisNode(node.getKey());
                    Node tempEnd = getNearestNeighborFromUnused(getThisNode(node.getKey()));
                    double tempLength = adjacencyList.get(tempStart).get(tempEnd);
                    if (minimalLength > tempLength) {
                        minimalLength = tempLength;
                        start = tempStart;
                        end = tempEnd;
                    }
                }

                tree.addEdge(tree.getThisNode(start.getKey()), tree.getThisNode(end.getKey()),
                        minimalLength);
            }

        } while (tree.getEdges().size() != tree.adjacencyList.keySet().size()-1);
        return tree;
    }
}

