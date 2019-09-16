import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ide.util.Graph;

public class Graph {
    private Map<String, String> neighbours = new HashMap<String, String>();

    public void addNeighbour(String a, String b){
        neighbours.put(a, b);
    }
    
    public void show(){
        for (Map.Entry<String, String> entry : neighbours.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
    }


}
