import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph implements Serializable {
    private HashMap<Node, HashMap<Node, Label>> adjacencyList;
    private Boolean isOriented;
    private Boolean isWeighted;

    public Graph() {
        adjacencyList = new HashMap<Node, HashMap<Node, Label>>();
        isOriented = false;
        isWeighted = false;
    }

    public Graph(Boolean Oriented, Boolean Weighted) {
        adjacencyList = new HashMap<Node, HashMap<Node, Label>>();
        this.isOriented = Oriented;
        this.isWeighted = Weighted;
    }

    public Graph(Graph g) {
        adjacencyList = new HashMap<>(g.adjacencyList);
        isOriented = g.isOriented;
        isWeighted = g.isWeighted;
    }

    //TODO Constructor with file
//    public Graph() {
//
//    }

    public void addNode(Node a) {
        adjacencyList.put(a, new HashMap<Node, Label>());
    }

    public void deleteNode(Node a) {
        adjacencyList.remove(a);
    }

    public void addEdge(Node a, Node b) {
        if(isOriented){
            adjacencyList.get(a).put(b, new Label());
            adjacencyList.get(b).put(a, new Label());
        }
        else {
            adjacencyList.get(a).put(b, new Label());
        }
    }

    public void removeEdge(Node a, Node b){
        if(isOriented) {
            adjacencyList.get(a).remove(b);
            adjacencyList.get(b).remove(a);
        }
        else {
            adjacencyList.get(a).remove(b);
        }
    }

    public void save(Graph g) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("\\save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // сохраняем игру в файл
        objectOutputStream.writeObject(g);

        //закрываем поток и освобождаем ресурсы
        objectOutputStream.close();
    }

    public Set getNodes() {
        return adjacencyList.keySet();
    }

    @Override
    public String toString() {
        String s = "";

        for(Map.Entry<Node, HashMap<Node, Label>> entry : adjacencyList.entrySet()) {
            String nodeStr = entry.getKey().toString();
            s += nodeStr + ";";
            HashMap<Node, Label> value = entry.getValue();
            for(Map.Entry<Node, Label> edge : value.entrySet()) {
                Node adjacency = edge.getKey();
                String labelStr = edge.getValue().toString();
                s += adjacency + ";" + labelStr;
            }
            s += "\n";
        }
        return s;
    }
}
