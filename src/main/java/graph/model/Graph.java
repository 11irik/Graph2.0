package graph.model;

import java.io.Serializable;
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

        for (String str : g.adjacencyList.keySet()) {
            HashMap<String, Label> temp = new HashMap<>();
            temp.putAll(g.adjacencyList.get(str));
            this.adjacencyList.put(str, temp);
        }

        isOriented = g.isOriented;
        isWeighted = g.isWeighted;
    }

    //TODO Constructor with file
    //TODO test commit
//    public Graph() {
//
//    }

    public void addNode(String a) {

        adjacencyList.put(a, new HashMap<String, Label>());
    }

    public boolean hasNode(String a) {
        return adjacencyList.containsKey(a);
    }

    public void deleteNode(String a) {
        adjacencyList.remove(a);

        for (Map.Entry<String, HashMap<String, Label>> entry : adjacencyList.entrySet()) {
            entry.getValue().remove(a);
        }
    }


    //TODO проверка на дублирование
    public boolean addEdge(String a, String b) {
        if (!(hasNode(a) && hasNode(b))) {
            throw new NullPointerException("There is no such a node");
        } else {
            if (adjacencyList.get(a).containsKey(b)) {
                return false;
            }
            else {
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

    //TODO проверка на существование
    public boolean deleteEdge(String a, String b) {
        if (!(hasNode(a) && hasNode(b))) {
            throw new NullPointerException("There is no such a node");
        } else {
            if (!isOriented) {
                if (adjacencyList.get(a). containsKey(b)) {
                    adjacencyList.get(a).remove(b);
                    adjacencyList.get(b).remove(a);
                    return true;
                }
                return false;
            }
            else {
                if (adjacencyList.get(a). containsKey(b)) {
                    adjacencyList.get(a).remove(b);
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

    //TODO task Ia(6)
    public ArrayList getZeros() {
        ArrayList <String> nodes = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, Label>> entry : adjacencyList.entrySet()) {
            if (entry.getValue().size() == 0) {
                nodes.add(entry.getKey());
            }
        }
        return nodes;
    }

    //TODO task Ia(17) Wrong
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
            }
            else{
                return true;
            }
        }
    }

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

    public Graph deleteOddEdges() {
        Graph temp = new Graph(this);
        for (Map.Entry<String, HashMap<String, Label>> entry : temp.adjacencyList.entrySet()) {
            if (entry.getKey().length() % 2 == 0) {
                for (String node: entry.getValue().keySet()) {
                    if (node.length() % 2 == 0) {
                        temp.deleteEdge(entry.getKey(), node);
                    }
                }
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        String s = "";

        for (Map.Entry<String, HashMap<String, Label>> entry : adjacencyList.entrySet()) {
            String nodeStr = entry.getKey().toString();
            s += nodeStr + ";";
            HashMap<String, Label> value = entry.getValue();
            for (Map.Entry<String, Label> edge : value.entrySet()) {
                String adjacency = edge.getKey();
                String labelStr = edge.getValue().toString();
                s += adjacency + ";" + labelStr;
            }
            s += "\n";
        }
        return s;
    }
}
