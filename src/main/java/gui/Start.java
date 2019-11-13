package gui;

import graph.Graph;
import graph.wrappers.GraphWrapper;
import gui.panels.GraphImagePanel;
import gui.panels.GraphSettingsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The main window of the application
 */
public class Start extends JFrame {

    public Start() {
        super("Graph");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width/8, dimension.height/8, dimension.width/4, dimension.height/2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Graph graph = new Graph();
        graph.addNode("5");
        graph.addNode("6");
        graph.addNode("2");
        graph.addNode("1");
        graph.addNode("10");
        graph.addNode("11");
        graph.addEdge(graph.getNode("5"), graph.getNode("6"));
        graph.addEdge(graph.getNode("5"), graph.getNode("2"));
        graph.addEdge(graph.getNode("6"), graph.getNode("2"));
        graph.addEdge(graph.getNode("5"), graph.getNode("11"));


        GridLayout gridLayout = new GridLayout(0, 2);
        JPanel workspace = new JPanel();
        workspace.setLayout(gridLayout);
        add(workspace);

        GraphWrapper graphWrapper = new GraphWrapper(graph);
        GraphImagePanel graphImagePanel = new GraphImagePanel(graphWrapper);
        workspace.add(graphImagePanel);


        GraphSettingsPanel settingsPanel = new GraphSettingsPanel(graphWrapper);

        workspace.add(settingsPanel);

    }

    public static void main(String[] args) {
        new Start();
    }

}
