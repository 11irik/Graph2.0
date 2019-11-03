package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {
    private static int count = 0;
    private Boolean isOriented;
    private Boolean isWeighted;
    private HashMap<String, HashMap<String, Label>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<String, HashMap<String, Label>>();
        isOriented = false;
        isWeighted = false;
    }

    public Graph(Boolean Oriented, Boolean Weighted) {

        adjacencyList = new HashMap<String, HashMap<String, Label>>();
        this.isOriented = Oriented;
        this.isWeighted = Weighted;
    }

    public Graph(Graph g) {
        adjacencyList = new HashMap<>();

        for (String key : g.adjacencyList.keySet()) {
            HashMap<String, Label> temp = new HashMap<>();
            temp.putAll(g.adjacencyList.get(key));
            this.adjacencyList.put((String) key, temp);
        }

        isOriented = g.isOriented;
        isWeighted = g.isWeighted;
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public boolean addNode(String a) {
        if (adjacencyList.containsKey(a)) {
            return false;
        } else {
            adjacencyList.put(a, new HashMap<String, Label>());
            return true;
        }
    }

    public boolean hasNode(String a) {
        return adjacencyList.containsKey(a);
    }

    public boolean deleteNode(String a) {
        if (!adjacencyList.containsKey(a)) {
            return false;
        } else {
            adjacencyList.remove(a);
            for (Map.Entry<String, HashMap<String, Label>> entry : adjacencyList.entrySet()) {
                entry.getValue().remove(a);
            }
            return true;
        }
    }

    public boolean addEdge(String a, String b) {
        if (!(hasNode(a) && hasNode(b))) {
            throw new NullPointerException("There is no such node");
        } else {
            if (adjacencyList.get(a).containsKey(b)) {
                return false;
            } else {
                if (!isOriented) {
                    adjacencyList.get(a).put(b, new Label());
                    adjacencyList.get(b).put(a, new Label());
                    return true;
                } else {
                    adjacencyList.get(a).put(b, new Label());
                    return true;
                }
            }
        }
    }

    public boolean addEdge(String a, String b, double weight) {
        if (!isWeighted) {
            //todo exception
            throw new NullPointerException("Graph is not weighted");
        } else {
            if (!(hasNode(a) && hasNode(b))) {
                throw new NullPointerException("There is no such node");
            } else {
                if (adjacencyList.get(a).containsKey(b)) {
                    return false;
                } else {
                    if (!isOriented) {
                        adjacencyList.get(a).put(b, new Label(weight));
                        adjacencyList.get(b).put(a, new Label(weight));
                        return true;
                    } else {
                        adjacencyList.get(a).put(b, new Label(weight));
                        return true;
                    }
                }
            }
        }
    }

    public boolean deleteEdge(String a, String b) {
        if (!(hasNode(a) && hasNode(b))) {
            throw new NullPointerException("There is no such node");
        } else {
            if (!isOriented) {
                if (adjacencyList.get(a).containsKey(b)) {
                    adjacencyList.get(a).remove(b);
                    adjacencyList.get(b).remove(a);
                    return true;
                }
                return false;
            } else {
                if (adjacencyList.get(a).containsKey(b)) {
                    adjacencyList.get(a).remove(b);
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    @Override
    public java.lang.String toString() {
        java.lang.String s = "";

        for (Map.Entry<String, HashMap<String, Label>> entry : adjacencyList.entrySet()) {
            java.lang.String nodeStr = entry.getKey();
            s += nodeStr + ";";
            HashMap<String, Label> value = entry.getValue();
            for (Map.Entry<String, Label> edge : value.entrySet()) {
                String adjacency = edge.getKey();
                java.lang.String labelStr = edge.getValue().toString();
                s += adjacency + ";" + labelStr;
            }
            s += "\n";
        }
        return s;
    }
    //----------------------------TASKS--------------------------------

    //task Ia(6)
    public ArrayList<String> getZeros() {
        ArrayList<String> nodes = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, Label>> entry : adjacencyList.entrySet()) {
            if (entry.getValue().size() == 0) {
                nodes.add(entry.getKey());
            }
        }
        return nodes;
    }

    //task Ia(17) Wrong
    public boolean doesCallingExist(String a, String b) {
        if (!(hasNode(a) && hasNode(b))) {
            throw new NullPointerException("There is no such a node");
        } else {
            Set<String> aSet = adjacencyList.get(a).keySet();
            Set<String> bSet = adjacencyList.get(b).keySet();
            aSet.retainAll(bSet);
            System.out.println(aSet);
            if (aSet.size() == 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    //task Ia(17)
    public boolean doesIssueExist(String a, String b) {
        if (!(hasNode(a) && hasNode(b))) {
            throw new NullPointerException("There is no such a node");
        } else {

            for (Map.Entry<String, HashMap<String, Label>> entry : adjacencyList.entrySet()) {
                if (entry.getValue().containsKey(a) && entry.getValue().containsKey(b)) {
                    return true;
                }
            }
            return false;

        }
    }

    //task Ib(8)
    public Graph deleteOddEdges() {
        Graph temp = new Graph(this);
        for (Map.Entry<String, HashMap<String, Label>> entry : temp.adjacencyList.entrySet()) {
            if (entry.getKey().toString().length() % 2 == 0) {
                for (String node : entry.getValue().keySet()) {
                    if (node.toString().length() % 2 == 0) {
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
            for (Map.Entry<String, HashMap<String, Label>> from : temp.adjacencyList.entrySet()) {
                for (Map.Entry<String, Label> to : from.getValue().entrySet()) {
                    edges.add(new Edge(from.getKey(), to.getKey(), to.getValue().getWeight()));
                }
            }
        }
        else {
            ArrayList<String> usedNodes = new ArrayList<>();
            for (Map.Entry<String, HashMap<String, Label>> from : temp.adjacencyList.entrySet()) {
                for (Map.Entry<String, Label> to : from.getValue().entrySet()) {
                    if (!usedNodes.contains(to.getKey()))
                        edges.add(new Edge(from.getKey(), to.getKey(), to.getValue().getWeight()));
                }
                usedNodes.add(from.getKey().toString());
            }
        }
        return edges;
    }

    //task II(B)
    public void boruvka() {

    }

}
