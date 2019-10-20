import java.io.Serializable;

public class Node implements Serializable {
    private String data;

    public Node(){
        data = "";
    }

    public Node(String s){
        data = s;
    }

    @Override
    public String toString(){
        return data;
    }
}
