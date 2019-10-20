package graph.model;

import java.io.Serializable;

public class Node {
    private String data;

    Node(){
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
