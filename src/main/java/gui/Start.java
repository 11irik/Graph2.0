package gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graph.Graph;
import graph.adapters.GraphAdapter;
import gui.panels.GraphImagePanel;
import gui.panels.GraphSettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * The main window of the application
 */
public class Start extends JFrame {
    Graph graph;
    public void createGraph() throws Exception {
        graph = new Graph(true, true);
        graph.addNode("5");
        graph.addNode("6");
        graph.addNode("2");
        graph.addNode("1");
        graph.addNode("10");
        graph.addNode("11");
        graph.addNode("9");
        graph.addNode("12");


        graph.addEdge("5", "6", 6);
        graph.addEdge("5", "2", 9);
        graph.addEdge("6", "2", 12);
        graph.addEdge("5", "11", 3);
        graph.addEdge("11", "1", 10);
        graph.addEdge("1", "10", 20);
        graph.addEdge("2", "9", 7);
        graph.addEdge("10", "9", 13);
        graph.addEdge("10", "12", 8);
        graph.addEdge("11", "12", 14);

        System.out.println(graph + "---Before---\n");
    }

    public void createOrGraph() throws Exception {
        graph = new Graph(true, true);
        graph.addNode("5");
        graph.addNode("6");
        graph.addNode("2");
        graph.addNode("1");
        graph.addNode("10");
        graph.addNode("11");
        graph.addNode("7");
        graph.addEdge("6", "7", 1);
        graph.addEdge("6", "2", 1);
        graph.addEdge("5", "6", 1);
        graph.addEdge("2", "5", 0);
        graph.addEdge("11", "10", 0);
        graph.addEdge("5", "11", 1);
        graph.addEdge("1", "10", 1);
        graph.addEdge("5", "1", 1);
        graph.addEdge("2", "1", 1);


        System.out.println(graph + "---Before---\n");
    }

    public Start() throws Exception {
        super("Graph");
        setSize(1920, 1080);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        GridLayout gridLayout = new GridLayout(0, 2);
        JPanel workspace = new JPanel();
        workspace.setLayout(gridLayout);
        add(workspace);

        createGraph();
        GraphAdapter graphAdapter = new GraphAdapter(graph);

        GraphImagePanel graphImagePanel = new GraphImagePanel(graphAdapter);
        workspace.add(graphImagePanel);

        GraphSettingsPanel settingsPanel = new GraphSettingsPanel(graphAdapter, graphImagePanel);
        workspace.add(settingsPanel);
    }

    public static void main(String[] args) throws Exception {
        new Start();
    }

}
