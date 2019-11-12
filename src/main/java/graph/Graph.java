package graph;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {
    private static int count = 0;
    private Boolean isOriented;
    private Boolean isWeighted;
    private HashMap<Node, HashMap<Node, Double>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
        isOriented = false;
        isWeighted = false;
    }

    public Graph(Boolean Oriented, Boolean Weighted) {

        adjacencyList = new HashMap<>();
        this.isOriented = Oriented;
        this.isWeighted = Weighted;
    }

    public Graph(Graph graph) {
        adjacencyList = new HashMap<>();
        isOriented = graph.isOriented;
        isWeighted = graph.isWeighted;

        for (Node key : graph.adjacencyList.keySet()) {
            this.adjacencyList.put(new Node(key), new HashMap<>());
        }

        for (Node key : this.adjacencyList.keySet()) {
            for (Node value : this.adjacencyList.keySet()) {
                if (graph.adjacencyList.get(graph.getNode(key.getKey())).containsKey(graph.getNode(value.getKey())))
                    adjacencyList.get(key).put(value, graph.adjacencyList.get(graph.getNode(key.getKey())).get(graph.getNode(value.getKey())));
            }
        }
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public Node getNode(String key) {
        for (Node node : adjacencyList.keySet()) {
            if (node.getKey() == key) {
                return node;
            }
        }
        return null;
    }

    public boolean addNode(String key) {
        if (getNode(key) == null) {
            adjacencyList.put(new Node(key), new HashMap<Node, Double>());
            return true;
        } else {
            return false;
        }
    }

    public boolean hasNode(Node node) {
        return adjacencyList.containsKey(node);
    }

    public boolean deleteNode(Node node) {
        if (!adjacencyList.containsKey(node)) {
            return false;
        } else {
            adjacencyList.remove(node);
            for (Map.Entry<Node, HashMap<Node, Double>> entry : adjacencyList.entrySet()) {
                entry.getValue().remove(node);
            }
            return true;
        }
    }

    public boolean addEdge(Node startNode, Node endNode) {
        if (!(hasNode(startNode) && hasNode(endNode))) {
            throw new NullPointerException("There is no such node");
        } else {
            if (adjacencyList.get(startNode).containsKey(endNode)) {
                return false;
            } else {
                if (!isOriented) {
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

    public boolean addEdge(Node startNode, Node endNode, double weight) {
        if (!isWeighted) {
            throw new NullPointerException("Graph is not weighted");
        } else {
            if (!(hasNode(startNode) && hasNode(endNode))) {
                throw new NullPointerException("There is no such node");
            } else {
                if (adjacencyList.get(startNode).containsKey(endNode)) {
                    return false;
                } else {
                    if (!isOriented) {
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

    public boolean deleteEdge(Node startNode, Node endNode) {
        if (!(hasNode(startNode) && hasNode(endNode))) {
            throw new NullPointerException("There is no such node");
        } else {
            if (!isOriented) {
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

    public Set<Node> getNodes() {
        return adjacencyList.keySet();
    }

    @Override
    public java.lang.String toString() {
        StringBuilder s = new StringBuilder();

        for (Map.Entry<Node, HashMap<Node, Double>> entry : adjacencyList.entrySet()) {
            s.append(entry.getKey().toString()).append(";");
            HashMap<Node, Double> value = entry.getValue();
            for (Map.Entry<Node, Double> edge : value.entrySet()) {
                Node adjacency = edge.getKey();
                s.append(adjacency.toString()).append(";");
                if (isWeighted) {
                    s.append(edge.getValue().toString()).append(";");
                }
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
                nodes.add(entry.getKey());
            }
        }
        return nodes;
    }

    //task Ia(17) Wrong
    public boolean doesCallingExist(Node startNode, Node endNode) {
        if (!(hasNode(startNode) && hasNode(endNode))) {
            throw new NullPointerException("There is no such a node");
        } else {
            Set<Node> aSet = adjacencyList.get(startNode).keySet();
            Set<Node> bSet = adjacencyList.get(endNode).keySet();
            aSet.retainAll(bSet);
            return aSet.size() != 0;
        }
    }

    //task Ia(17)
    public boolean doesIssueExist(Node startNode, Node endNode) {
        if (!(hasNode(startNode) && hasNode(endNode))) {
            throw new NullPointerException("There is no such a node");
        } else {
            for (Map.Entry<Node, HashMap<Node, Double>> entry : adjacencyList.entrySet()) {
                if (entry.getValue().containsKey(startNode) && entry.getValue().containsKey(endNode)) {
                    return true;
                }
            }
            return false;
        }
    }

    //task Ib(8)
    //Todo remove exception
    public Graph deleteOddEdges() {
        Graph temp = new Graph(this);
        for (Map.Entry<Node, HashMap<Node, Double>> entry : temp.adjacencyList.entrySet()) {
            if (Integer.parseInt(entry.getKey().toString()) % 2 == 0) {
                for (Node node : entry.getValue().keySet()) {
                    if (Integer.parseInt(node.toString()) % 2 == 0) {
                        temp.deleteEdge(entry.getKey(), node);

                    }
                }
            }
        }
        return temp;
    }

    //task II(23)

    public ArrayList<Edge> convertIntoEdges() {
        ArrayList<Edge> edges = new ArrayList<Edge>();
        Graph temp = new Graph(this);

        if (isOriented) {
            for (Map.Entry<Node, HashMap<Node, Double>> from : temp.adjacencyList.entrySet()) {
                for (Map.Entry<Node, Double> to : from.getValue().entrySet()) {
                    edges.add(new Edge(from.getKey(), to.getKey(), to.getValue()));
                }
            }
        }
        else {
            ArrayList<Node> usedNodes = new ArrayList<>();
            for (Map.Entry<Node, HashMap<Node, Double>> from : temp.adjacencyList.entrySet()) {
                for (Map.Entry<Node, Double> to : from.getValue().entrySet()) {
                    if (!usedNodes.contains(to.getKey()))
                        edges.add(new Edge(from.getKey(), to.getKey(), to.getValue()));
                }
                usedNodes.add(from.getKey());
            }
        }
        return edges;
    }

    //task II(B)
    public void boruvka() {

    }

}
