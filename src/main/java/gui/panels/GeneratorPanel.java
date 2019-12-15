package gui.panels;


import graph.Graph;

import javax.swing.*;
import java.awt.*;

public class GeneratorPanel extends JPanel{
    JRadioButton buttonTree;
    JRadioButton buttonBigraph;
    JRadioButton buttonFullGraph;


    public GeneratorPanel() {
        this.setLayout(new GridLayout(0, 1));

        buttonTree = new JRadioButton("Tree");
        buttonBigraph = new JRadioButton("Bigraph");
        buttonFullGraph = new JRadioButton("Full graph");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(buttonTree);
        buttonGroup.add(buttonBigraph);
        buttonGroup.add(buttonFullGraph);
        this.add(buttonTree);
        this.add(buttonBigraph);
        this.add(buttonFullGraph);

        JTextField nodeCount = new JTextField("Nodes",2);

        this.add(nodeCount);

        JTextField edgeCount = new JTextField("Edges", 3);
        this.add(edgeCount);
    }

    public Graph createGraph() {
        Graph graph = new Graph();
        return graph;
    }
}
