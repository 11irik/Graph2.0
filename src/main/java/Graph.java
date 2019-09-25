import java.util.HashMap;
import java.util.Map;

public class Graph {
    private HashMap<String, HashMap<String, Integer>> adjacencyList;
    private Boolean isOriented;
    private Boolean isWeighted;

    public Graph() {
        adjacencyList = new HashMap<String, HashMap<String, Integer>>();
        isOriented = false;
        isWeighted = false;
    }

    public Graph(Boolean Oriented, Boolean Weighted) {
        adjacencyList = new HashMap<String, HashMap<String, Integer>>();
        this.isOriented = Oriented;
        this.isWeighted = Weighted;
    }

    public Graph(Graph g) {
        adjacencyList = new HashMap<>(g.adjacencyList);
        isOriented = g.isOriented;
        isWeighted = g.isWeighted;
    }

    public void addNode(String a) {
        adjacencyList.put(a, new HashMap<String, Integer>());
    }

    public void deleteNode(String a) {
        adjacencyList.remove(a);
    }

    public void addEdge(String a, String b) {
        if(isOriented){
            adjacencyList.get(a).put(b, 0);
            adjacencyList.get(b).put(a, 0);
        }
        else {
            adjacencyList.get(a).put(b, 0);
        }
    }

    public void removeEdge(String a, String b){
        if(isOriented) {
            adjacencyList.get(a).remove(b);
            adjacencyList.get(b).remove(a);
        }
        else {
            adjacencyList.get(a).remove(b);
        }
    }

    public String toString() {
        String s = "";

        for(Map.Entry <String, HashMap<String, Integer>> entry : adjacencyList.entrySet()) {
            String node = entry.getKey();
            s += node + ";";
            HashMap<String, Integer> value = entry.getValue();
            for(Map.Entry <String, Integer> edge : value.entrySet()) {
                String adjacency = edge.getKey();
                int weight = edge.getValue();
                s += adjacency + ";" + weight + ";";
            }
            s += "\n";
        }
        return s;
    }



}
