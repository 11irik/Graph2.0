package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph<T> {
    private static int count = 0;
    private Boolean isOriented;
    private Boolean isWeighted;
    private HashMap<T, HashMap<T, Label>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<T, HashMap<T, Label>>();
        isOriented = false;
        isWeighted = false;
    }

    public Graph(Boolean Oriented, Boolean Weighted) {
        adjacencyList = new HashMap<T, HashMap<T, Label>>();
        this.isOriented = Oriented;
        this.isWeighted = Weighted;
    }

    public Graph(Graph<? extends T> g) {

        adjacencyList = new HashMap<>();

        for (T key : g.adjacencyList.keySet()) {
            HashMap<T, Label> temp = new HashMap<>();
            temp.putAll(g.adjacencyList.get(key));
            this.adjacencyList.put((T) key, temp);
        }

        isOriented = g.isOriented;
        isWeighted = g.isWeighted;
    }

    //TODO Constructor with file
    //TODO test commit
//    public Graph() {
//
//    }

    public void addNode(T a) {

        adjacencyList.put(a, new HashMap<T, Label>());
    }

    public boolean hasNode(T a) {
        return adjacencyList.containsKey(a);
    }

    public void deleteNode(T a) {
        adjacencyList.remove(a);

        for (Map.Entry<T, HashMap<T, Label>> entry : adjacencyList.entrySet()) {
            entry.getValue().remove(a);
        }
    }


    //TODO проверка на дублирование
    public boolean addEdge(T a, T b) {
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
    public boolean deleteEdge(T a, T b) {
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
    public ArrayList<T> getZeros() {
        ArrayList <T> nodes = new ArrayList<>();
        for (Map.Entry<T, HashMap<T, Label>> entry : adjacencyList.entrySet()) {
            if (entry.getValue().size() == 0) {
                nodes.add(entry.getKey());
            }
        }
        return nodes;
    }

    //TODO task Ia(17) Wrong
    public boolean doesCallingExist(T a, T b) {
        if (!(hasNode(a) && hasNode(b))) {
            throw new NullPointerException("There is no such a node");
        } else {
            Set<T> aSet = adjacencyList.get(a).keySet();
            Set<T> bSet = adjacencyList.get(b).keySet();
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

    public boolean doesIssueExist(T a, T b) {
        if (!(hasNode(a) && hasNode(b))) {
            throw new NullPointerException("There is no such a node");
        } else {

            for (Map.Entry<T, HashMap<T, Label>> entry : adjacencyList.entrySet()) {
                if (entry.getValue().containsKey(a) && entry.getValue().containsKey(b)) {
                    return true;
                }
            }
            return false;

        }
    }

    public Graph<T> deleteOddEdges() {
        Graph<T> temp = new Graph<T>(this);
        for (Map.Entry<T, HashMap<T, Label>> entry : temp.adjacencyList.entrySet()) {
            if (entry.getKey().toString().length() % 2 == 0) {
                for (T node: entry.getValue().keySet()) {
                    if (node.toString().length() % 2 == 0) {
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

        for (Map.Entry<T, HashMap<T, Label>> entry : adjacencyList.entrySet()) {
            String nodeStr = entry.getKey().toString();
            s += nodeStr + ";";
            HashMap<T, Label> value = entry.getValue();
            for (Map.Entry<T, Label> edge : value.entrySet()) {
                T adjacency = edge.getKey();
                String labelStr = edge.getValue().toString();
                s += adjacency.toString() + ";" + labelStr;
            }
            s += "\n";
        }
        return s;
    }
}
